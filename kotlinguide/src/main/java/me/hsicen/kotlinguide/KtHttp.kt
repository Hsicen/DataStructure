package me.hsicen.kotlinguide

import com.google.gson.Gson
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author: hsicen
 * @date: 2022/6/28 18:01
 * @email: codinghuang@163.com
 * description: 网络请求工具类
 */

interface Callback<T : Any> {
  fun onSuccess(data: T)
  fun onFailed(throwable: Throwable)
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class GET(val url: String)

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Filed(val name: String)

data class RepoList(val name: String, val description: String)

interface ApiService {
  @GET("/repo")
  fun repos(
    @Filed("lang") lang: String,
    @Filed("since") since: String
  ): KtCall<RepoList>
}

object KtHttp {
  private val okHttpClient by lazy { OkHttpClient() }
  private val gson by lazy { Gson() }

  // 这里也同样使用了泛型边界
  private fun <T : Any> invoke(path: String, method: Method, args: Array<Any>): Any? {
    if (method.parameterAnnotations.size != args.size) return null

    var url = path
    val parameterAnnotations = method.parameterAnnotations
    for (i in parameterAnnotations.indices) {
      for (parameterAnnotation in parameterAnnotations[i]) {
        /*if (parameterAnnotation is Field) {
          val key = parameterAnnotation.value
          val value = args[i].toString()
          if (!url.contains("?")) {
            url += "?$key=$value"
          } else {
            url += "&$key=$value"
          }
        }*/
      }
    }

    val request = Request.Builder()
      .url(url)
      .build()

    val call = okHttpClient.newCall(request)
    val genericReturnType = getTypeArgument(method)

    // 变化在这里
    return KtCall<T>(call, gson, genericReturnType)
  }

  // 拿到 KtCall<RepoList> 当中的 RepoList类型
  private fun getTypeArgument(method: Method) =
    (method.genericReturnType as ParameterizedType).actualTypeArguments[0]
}


class KtCall<T : Any>(
  private val call: Call,
  private val gson: Gson,
  private val type: Type
) {
  fun call(callback: Callback<T>): Call {
    call.enqueue(object : okhttp3.Callback {
      override fun onFailure(call: Call, e: java.io.IOException) {
        callback.onFailed(e)
      }

      override fun onResponse(call: Call, response: okhttp3.Response) {
        kotlin.runCatching {
          val t = gson.fromJson<T>(response.body?.string(), type)
          callback.onSuccess(t)
        }.onFailure { e ->
          callback.onFailed(e)
        }
      }
    })

    return call
  }
}