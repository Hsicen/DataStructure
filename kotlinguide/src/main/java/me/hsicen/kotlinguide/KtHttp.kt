package me.hsicen.kotlinguide

import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`.getRawType
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * @author: hsicen
 * @date: 2022/6/28 18:01
 * @email: codinghuang@163.com
 * description: 网络请求工具类
 */

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class GET(val value: String)

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Field(val value: String)

interface Callback<T : Any> {
  fun onSuccess(data: T)
  fun onFail(throwable: Throwable)
}

data class RepoList(val name: String, val description: String)

interface ApiServiceV3 {
  @GET("/repo")
  fun repos(
    @Field("lang") lang: String,
    @Field("since") since: String
  ): KtCall<RepoList>

  @GET("/repo")
  fun reposSync(
    @Field("lang") lang: String,
    @Field("since") since: String
  ): RepoList

  @GET("/repo")
  fun reposFlow(
    @Field("lang") lang: String,
    @Field("since") since: String
  ): Flow<RepoList>
}

object KtHttp {
  private const val baseUrl = "https://trendings.herokuapp.com"
  private val okHttpClient: OkHttpClient = OkHttpClient()
  private val gson: Gson = Gson()

  fun <T : Any> create(service: Class<T>): T {
    return Proxy.newProxyInstance(
      service.classLoader,
      arrayOf<Class<*>>(service)
    ) { proxy, method, args ->
      val annotations = method.annotations
      for (annotation in annotations) {
        if (annotation is GET) {
          val url = baseUrl + annotation.value
          return@newProxyInstance invoke<T>(url, method, args!!)
        }
      }
      return@newProxyInstance null

    } as T
  }

  private fun <T : Any> invoke(path: String, method: Method, args: Array<Any>): Any? {
    if (method.parameterAnnotations.size != args.size) return null

    var url = path
    val parameterAnnotations = method.parameterAnnotations
    for (i in parameterAnnotations.indices) {
      for (parameterAnnotation in parameterAnnotations[i]) {
        if (parameterAnnotation is Field) {
          val key = parameterAnnotation.value
          val value = args[i].toString()
          url += if (!url.contains("?")) {
            "?$key=$value"
          } else {
            "&$key=$value"
          }
        }
      }
    }

    val request = Request.Builder()
      .url(url)
      .build()

    val call = okHttpClient.newCall(request)


    return when {
      isKtCallReturn(method) -> {
        val genericReturnType = getTypeArgument(method)
        KtCall<T>(call, gson, genericReturnType)
      }

      isFlowReturn(method) -> {
        flow<T> {
          val type = getTypeArgument(method)
          val response = okHttpClient.newCall(request).execute()
          val json = response.body?.string()
          val data = gson.fromJson<T>(json, type)
          emit(data)
        }
      }

      else -> {
        val response = okHttpClient.newCall(request).execute()

        val genericReturnType = method.genericReturnType
        val json = response.body?.string()
        gson.fromJson<Any?>(json, genericReturnType)
      }
    }
  }

  private fun getTypeArgument(method: Method) =
    (method.genericReturnType as ParameterizedType).actualTypeArguments[0]

  private fun isKtCallReturn(method: Method) =
    getRawType(method.genericReturnType) == KtCall::class.java

  private fun isFlowReturn(method: Method) =
    getRawType(method.genericReturnType) == Flow::class.java
}


class KtCall<T : Any>(
  val call: Call,
  private val gson: Gson,
  private val type: Type
) {
  fun call(callback: Callback<T>): Call {
    call.enqueue(object : okhttp3.Callback {
      override fun onFailure(call: Call, e: IOException) {
        callback.onFail(e)
      }

      override fun onResponse(call: Call, response: Response) {
        try {
          val t = gson.fromJson<T>(response.body?.string(), type)
          callback.onSuccess(t)
        } catch (e: Exception) {
          callback.onFail(e)
        }
      }
    })
    return call
  }
}


suspend fun <T : Any> KtCall<T>.await(): T = suspendCancellableCoroutine { continuation ->
  call(object : Callback<T> {
    override fun onSuccess(data: T) {
      println("Request success!")
      continuation.resume(data)
    }

    override fun onFail(throwable: Throwable) {
      println("Request fail!：$throwable")
      continuation.resumeWithException(throwable)
    }
  })

  continuation.invokeOnCancellation {
    println("Call cancelled!")
    call.cancel() // cancel the request
  }
}

fun <T : Any> KtCall<T>.asFlow(): Flow<T> = callbackFlow {
  val call = call(object : Callback<T> {
    override fun onSuccess(data: T) {
      // trySend(data) 直接发送数据，如果此时 channel 已满，直接失败
      trySendBlocking(data) // 当 Channel 满的时候，会等待，直到有空间可以发送数据
        // 关闭 channel
        .onSuccess { close() }
        .onFailure { close(it) }
    }

    override fun onFail(throwable: Throwable) {
      close(throwable)
    }
  })

  awaitClose { // channel 关闭时回调
    call.cancel()
  }
}

fun main() = runBlocking {
  val start = System.currentTimeMillis()

  val deferred = async {
    KtHttp.create(ApiServiceV3::class.java)
      .repos(lang = "Kotlin", since = "weekly")
      .await()
  }

  deferred.invokeOnCompletion {
    println("invokeOnCompletion!")
  }
  delay(50L)

  deferred.cancel()
  println("Time cancel: ${System.currentTimeMillis() - start}")

  try {
    println(deferred.await())
  } catch (e: Exception) {
    println("Time exception: ${System.currentTimeMillis() - start}")
    println("Catch exception:$e")
  } finally {
    println("Time total: ${System.currentTimeMillis() - start}")
  }
}

suspend fun testFlow() {
  KtHttp.create(ApiServiceV3::class.java)
    .repos(lang = "Kotlin", since = "weekly")
    .asFlow()
    .catch { println("Catch exception:$it") }
    .collect {
      println("The result is $it")
    }
}

suspend fun testFlow2() {
  KtHttp.create(ApiServiceV3::class.java)
    .reposFlow(lang = "Kotlin", since = "weekly")
    .catch { println("Catch exception:$it") }
    .collect {
      println("The result is $it")
    }
}