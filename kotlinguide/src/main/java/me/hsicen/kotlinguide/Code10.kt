package me.hsicen.kotlinguide

import kotlinx.coroutines.*

/**
 * @author: hsicen
 * @date: 2022/6/30 18:05
 * @email: codinghuang@163.com
 * description: exception
 */

fun main() = runBlocking {
  val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("Caught $throwable")
  }

  val job = Job()
  val mainScope = CoroutineScope(job)
  mainScope.launch {
    println("main")

    launch(exceptionHandler) {
      println("launch1")
      delay(200)
      launch {
        throw NullPointerException()
      }
    }
  }

  job.join()
  println("End")
}