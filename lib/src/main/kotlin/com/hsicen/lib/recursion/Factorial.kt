package com.hsicen.lib.recursion

/**
 * 作者：hsicen  3/26/21 12:16 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：阶乘的实现
 */
class Factorial {

}

/**
 * 利用递归实现一个数的阶乘
 */
fun factorial(n: Int): Int {
    if (n <= 1) return 1

    return n * factorial(n - 1)
}

/**
 * 阶乘的非递归实现
 */
fun factorial2(n: Int): Int {
    if (n <= 1) return 1

    var total = 1
    for (i in 2..n) {
        total *= i
    }

    return total
}


fun main() {
    println("${factorial(1)} -> ${factorial2(1)}")
    println("${factorial(2)} -> ${factorial2(2)}")
    println("${factorial(3)} -> ${factorial2(3)}")
    println("${factorial(5)} -> ${factorial2(5)}")
    println("${factorial(6)} -> ${factorial2(6)}")
    println("${factorial(7)} -> ${factorial2(7)}")
}
