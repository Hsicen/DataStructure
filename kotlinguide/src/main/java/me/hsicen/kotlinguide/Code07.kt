package me.hsicen.kotlinguide

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author: hsicen
 * @date: 2022/6/29 15:13
 * @email: codinghuang@163.com
 * description: Channel
 */

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
  val channel = Channel<Int>()
  launch {
    for (i in 1..10) {
      println("1发送数据: $i")
      channel.send(i)
    }
    channel.close()
  }

  launch {
    for (i in channel) {
      println("1接收数据: $i")
    }
  }

  println("结束")

  val channel2 = produce {
    for (i in 1..10) {
      println("2发送数据: $i")
      send(i)
    }
  }

  channel2.consumeEach {
    println("2接收数据: $it")
  }
}