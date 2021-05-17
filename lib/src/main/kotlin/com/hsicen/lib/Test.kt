package com.hsicen.lib

/**
 * 作者：hsicen  5/17/21 15:23
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：DataStructure
 */

fun videohighLight(duration: Long): Float {
  if (0L == duration) return 0f
  return (duration * 100 / 2f) / 100
}

fun main() {
  println(videohighLight(0))
  println(videohighLight(1))
  println(videohighLight(2))
  println(videohighLight(3))
  println(videohighLight(4))
  println(videohighLight(23))
  println(videohighLight(24))
  println(videohighLight(25))
  println(videohighLight(26))
}