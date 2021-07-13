package com.hsicen.lib.custom

/**
 * 作者：hsicen  7/13/21 10:03
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：回溯算法：在一组可能的解中，搜索满足期望的解
 *
 * 应用：
 *  1.8皇后问题
 *  2.0-1背包
 *  3.正则表达式
 */

private val result = IntArray(8) // 下标表示行，值表示queue存储在哪一列
private fun cal8queens(row: Int) {
    if (row == 8) { // 8个棋子都放好了，打印结果
        printQueens()
        return
    }

    for (col in 0 until 8) { // 每一行都有8种放法
        if (isOk(row, col)) { // 查询满足的放法
            result[row] = col // 第row行的棋子放在col行
            cal8queens(row + 1) //继续放置下一行
        }
    }
}

/*** 判断[row]行放置在[col]列是否合适*/
private fun isOk(row: Int, col: Int): Boolean {
    var leftUp = col - 1
    var rightUp = col + 1

    for (i in (row - 1) downTo 0) { // 逐行往上考察每一行
        if (result[i] == col) return false // 第i行的col列
        if (leftUp >= 0) { // 考察左上对角线
            if (result[i] == leftUp) return false
        }

        if (rightUp < 8) { // 考察右上对角线
            if (result[i] == rightUp) return false
        }

        --leftUp
        ++rightUp
    }

    return true
}

/*** 打印出一个二维矩阵*/
private fun printQueens() {
    for (row in 0 until 8) {
        for (col in 0 until 8) {
            if (result[row] == col) print("Q ")
            else print("* ")
        }
        println()
    }
    println()
}

// 存储背包中物品总重量的最大值
private var maxW = Int.MAX_VALUE

/**
 * backpack(0,0,a,10,100)
 * @param i  当前考察到哪个物品
 * @param cw 当前已经装进背包的物品总重量
 * @param items 存储的每个物品的重量
 * @param n 物品个数
 * @param w 背包可承受的重量
 */
private fun backpack(i: Int, cw: Int, items: IntArray, n: Int, w: Int) {
    if (cw == w || i == n) {
        if (cw > maxW) maxW = cw
        return
    }

    backpack(i + 1, cw, items, n, w)
    if (cw + items[i] <= w) {
        backpack(i + 1, cw + items[i], items, n, w)
    }
}

fun main() {
    cal8queens(0)
}
