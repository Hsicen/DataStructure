package com.hsicen.lib.recursion

/**
 * 作者：hsicen  3/25/21 10:07 上午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：爬楼梯
 * 假如这里有 n 个台阶，每次你可以跨 1 个台阶或者 2 个台阶，请问走这 n 个台阶有多少种走法？
 */

/**
 * 爬楼梯递归求解
 */
fun climbStairs(stair: Int): Int {
    if (1 == stair) return 1
    if (2 == stair) return 2

    return climbStairs(stair - 1) + climbStairs(stair - 2)
}


/**
 * 爬楼梯非递归求解
 */
fun climbStairs2(stair: Int): Int {
    if (1 == stair) return 1
    if (2 == stair) return 2

    var total = 0
    var pre = 2
    var preOfPre = 1
    for (step in 3..stair) {
        total = pre + preOfPre
        preOfPre = pre
        pre = total
    }

    return total
}

fun main() {
    println("${climbStairs(1)} -> ${climbStairs2(1)}")
    println("${climbStairs(2)} -> ${climbStairs2(2)}")
    println("${climbStairs(3)} -> ${climbStairs2(3)}")
    println("${climbStairs(4)} -> ${climbStairs2(4)}")
    println("${climbStairs(5)} -> ${climbStairs2(5)}")
    println("${climbStairs(6)} -> ${climbStairs2(6)}")
    println("${climbStairs(7)} -> ${climbStairs2(7)}")
}