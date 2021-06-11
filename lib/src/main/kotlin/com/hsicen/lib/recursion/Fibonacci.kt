package com.hsicen.lib.recursion

/**
 * 作者：hsicen  3/26/21 12:24 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：斐波那契额数列算法实现
 * 每个数列的值等于前两项的和
 */


/**
 * 斐波那契额数列的递归实现
 */
fun fibonacci(n: Int): Int {
  if (n <= 1) return 1
  if (n == 2) return 2

  return fibonacci(n - 1) + fibonacci(n - 2)
}


/**
 * 斐波那契额数列的非递归实现
 */
fun fibonacci2(n: Int): Int {
  if (n <= 1) return 1
  if (n == 2) return 2

  var pre = 2
  var preOfPre = 1
  var total = 0
  for (i in 3..n) {
    total = pre + preOfPre
    preOfPre = pre
    pre = total
  }

  return total
}

fun main() {
  println("${fibonacci(1)} -> ${fibonacci2(1)}")
  println("${fibonacci(2)} -> ${fibonacci2(2)}")
  println("${fibonacci(3)} -> ${fibonacci2(3)}")
  println("${fibonacci(4)} -> ${fibonacci2(4)}")
  println("${fibonacci(5)} -> ${fibonacci2(5)}")
  println("${fibonacci(6)} -> ${fibonacci2(6)}")
  println("${fibonacci(7)} -> ${fibonacci2(7)}")
}