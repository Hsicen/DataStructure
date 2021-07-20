package com.hsicen.lib.custom

import kotlin.math.min

/**
 * 作者：hsicen  7/13/21 11:29
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：动态规划
 * 动态规划比较适合用来求解最优问题，比如求最大值、最小值等等；它可以非常显著地降低时间复杂度，提高代码的执行效率
 * 多阶段决策最优解模型，重复子问题，
 *
 * 字符串相似程度：编辑距离
 *  莱文斯坦距离 ->
 *  最长公共子串 ->
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

//矩阵最短路径问题 回溯算法
private var minDist = Int.MAX_VALUE
private fun minDistBT(i: Int, j: Int, dist: Int, w: Array<IntArray>, n: Int) {
    if (i == n && j == n) {
        if (dist < minDist) minDist = dist
        return
    }

    //往下走 更新i
    if (i < n) minDistBT(i + 1, j, dist + w[i][j], w, n)
    //往右走 更新j
    if (j < n) minDistBT(i, j + 1, dist + w[i][j], w, n)
}

//矩阵最短路径问题 动态规划 状态转移表
private fun minDistDP(matrix: Array<IntArray>, n: Int): Int {
    val states = Array(n) { IntArray(n) }
    var sum = 0

    //初始化第一列
    repeat(n - 1) { j ->
        sum += matrix[0][j]
        states[0][j] = sum
    }

    //初始化第一行
    sum = 0
    repeat(n - 1) { i ->
        sum += matrix[i][0]
        states[i][0]
    }

    //构建状态转移表
    for (i in 1 until n) {
        for (j in 1 until n) {
            states[i][j] = matrix[i][j] + min(states[i][j - 1], states[i - 1][j])
        }
    }

    return states[n - 1][n - 1]
}

//矩阵最短路径问题 动态规划 状态转移方程
private val matrix = Array(4) { IntArray(4) }
private val mem = Array(4) { IntArray(4) }
private fun minDist(i: Int, j: Int): Int {
    if (0 == i && 0 == j) return matrix[0][0]
    if (mem[i][j] > 0) return mem[i][j]

    var minLeft = Int.MAX_VALUE
    if (j - 1 >= 0) minLeft = minDist(i, j - 1)

    var minUp = Int.MAX_VALUE
    if (i - 1 >= 0) minUp = minDist(i - 1, j)

    val currMinDist = matrix[i][j] + min(minLeft, minUp)
    mem[i][j] = currMinDist

    return currMinDist
}

fun main() {
    val weight = intArrayOf(2, 2, 4, 6, 3)
    println(knapsack(weight, 5, 9))
    println(knapsack2(weight, 5, 9))
}
