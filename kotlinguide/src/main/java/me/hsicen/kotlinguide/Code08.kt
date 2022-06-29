package me.hsicen.kotlinguide

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * @author: hsicen
 * @date: 2022/6/29 16:35
 * @email: codinghuang@163.com
 * description: Flow
 */

fun main() = runBlocking {
  flow {
    emit(1)
    emit(2)
    emit(3)
    emit(4)
    emit(5)
  }.filter { it > 2 }
    .map { it * 2 }
    .take(2)
    .catch { e -> println("error: $e") }
    .collect {
      println(it)
    }
}