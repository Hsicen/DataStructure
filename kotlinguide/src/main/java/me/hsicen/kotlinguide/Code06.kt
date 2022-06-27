package me.hsicen.kotlinguide

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author: hsicen
 * @date: 2022/6/27 16:00
 * @email: codinghuang@163.com
 * description: 协程
 */

fun main(): Unit = runBlocking {
  println("A " + Thread.currentThread().name + "${System.currentTimeMillis()}")

  launch {
    println("B " + Thread.currentThread().name + "${System.currentTimeMillis()}")
    delay(100L)
  }

  launch(Dispatchers.IO) {
    println("C " + Thread.currentThread().name + "${System.currentTimeMillis()}")
    delay(100L)
  }

  Thread.sleep(1000L)
}