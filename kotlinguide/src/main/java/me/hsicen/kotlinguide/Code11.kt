package me.hsicen.kotlinguide

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @author: hsicen
 * @date: 2022/7/1 15:12
 * @email: codinghuang@163.com
 * description: Continuation<T>
 */

suspend fun main() {
  val result = withContext(Dispatchers.IO) {
    delay(1000)
    "Hello"
  }

  println(result)

  val block = suspend {
    println("block")
    "Hello"
  }
}

suspend fun getUserInfo() = suspendCoroutine<String> { continuation ->
  thread {
    Thread.sleep(200)
    continuation.resume("Hello")
  }

  return@suspendCoroutine
}