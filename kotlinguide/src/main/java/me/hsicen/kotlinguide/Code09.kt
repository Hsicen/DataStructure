package me.hsicen.kotlinguide

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import kotlin.system.measureTimeMillis

/**
 * @author: hsicen
 * @date: 2022/6/30 16:17
 * @email: codinghuang@163.com
 * description: Select
 */

fun main() = runBlocking {
  val time = measureTimeMillis {
    val user = select<String> {
      async {
        getUserInfoFromNet(2)
      }.onAwait { it }

      async {
        getUserInfoFromCache(3)
      }.onAwait { it }
    }

    println("The user is $user")
  }

  println("Completed in $time ms")
}

suspend fun getUserInfoFromNet(id: Int): String {
  delay(200)
  return "Net user $id"
}

suspend fun getUserInfoFromCache(id: Int): String {
  delay(100)
  return "Cache user $id"
}