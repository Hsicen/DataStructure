package com.hsicen.lib.custom

/**
 * 作者：hsicen  7/13/21 11:29
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：动态规划
 * 动态规划比较适合用来求解最优问题，比如求最大值、最小值等等；它可以非常显著地降低时间复杂度，提高代码的执行效率
 */

/**
 * 0-1 背包问题，动态规划方式解决
 * @param weight 物品重量
 * @param n 物品个数
 * @param w 背包可承载重量
 * @return 最大可装重量
 *
 * 时间复杂度：O(n*w)
 * 空间复杂度：O(n*(w+1))
 */
private fun knapsack(weight: IntArray, n: Int, w: Int): Int {
    val states = Array(n) { BooleanArray(w + 1) { false } }
    states[0][0] = true

    if (weight[0] <= w) {
        states[0][weight[0]] = true
    }

    // 动态规划，状态转移
    for (i in 1 until n) {
        // 不把第i个物品放入背包
        for (j in 0..w) {
            if (states[i - 1][j]) states[i][j] = states[i - 1][j]
        }

        // 把第i个物品放入背包
        for (j in 0..(w - weight[i])) {
            if (states[n - 1][j]) states[i][j + weight[i]] = true
        }
    }

    //输出结果
    for (i in w downTo 0) {
        if (states[n - 1][i]) return i
    }

    return 0
}

/**
 * 0-1 背包问题，动态规划方式解决
 * @param items 物品重量
 * @param n 物品个数
 * @param w 背包可承载重量
 * @return 最大可装重量
 *
 * 时间复杂度：O(n*w)
 * 空间复杂度：O(w+1)
 */
private fun knapsack2(items: IntArray, n: Int, w: Int): Int {
    val states = BooleanArray(w + 1)
    states[0] = true
    if (items[0] <= w) {
        states[items[0]] = true
    }

    for (i in 1 until n) {
        for (j in (w - items[i]) downTo 0) {
            if (states[j]) states[j + items[i]] = true
        }
    }

    for (i in w downTo 0) {
        if (states[i]) return i
    }

    return 0
}


fun main() {
    val weight = intArrayOf(2, 2, 4, 6, 3)
    println(knapsack(weight, 5, 9))
    println(knapsack2(weight, 5, 9))
}
