package com.hsicen.letcode.custom

/**
 * 作者：hsicen  7/12/21 16:18
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：分治思想
 * 思路：
 *  分解：将原问题分解成一系列子问题
 *  解决：递归的求解各个子问题，若子问题足够小，则直接求解
 *  合并：将子问题的结果合并成原问题
 * 场景：
 *  1.原问题与分解成的小问题具有相同的模式
 *  2.原问题分解成的子问题可以独立求解，子问题之间没有相关性
 *  3.具有分解终止条件，也就是说，当问题足够小时，可以直接求解
 *  4.可以将子问题合并成原问题，而这个合并操作的复杂度不能太高，否则就起不到减小算法总体复杂度的效果了
 * 应用：
 *  1.求数组的有序度
 *  2.二维平面上有 n 个点，如何快速计算出两个距离最近的点对
 *  3.有两个 n*n 的矩阵 A，B，如何快速求解两个矩阵的乘积 C=A*B
 */

private var num = 0

private fun count(a: IntArray, n: Int): Int {
    num = 0
    mergeSortCounting(a, 0, n - 1)
    return num
}

fun mergeSortCounting(a: IntArray, p: Int, r: Int) {
    if (p >= r) return
    val q = p + ((r - p) shl 1)
    mergeSortCounting(a, p, q)
    mergeSortCounting(a, q + 1, r)
    merge(a, p, q, r)
}

fun merge(a: IntArray, p: Int, q: Int, r: Int) {
    var i = p
    var j = q + 1
    var k = 0

    val tmp = IntArray(r - p + 1)
    while (i <= q && j <= r) {
        if (a[i] <= a[j]) {
            tmp[k++] = a[i++]
        } else {
            num += (q - i + 1) // 统计p~q之间，比a[j]大的元素个数
            tmp[k++] = a[j++]
        }
    }

    while (i <= q) { // 处理剩下的
        tmp[k++] = a[j++]
    }

    while (j <= r) { // 处理剩下的
        tmp[k++] = a[j++]
    }

    for (i in 0..(r - p)) { //从tmp拷贝会a
        a[p + i] = tmp[i]
    }
}
