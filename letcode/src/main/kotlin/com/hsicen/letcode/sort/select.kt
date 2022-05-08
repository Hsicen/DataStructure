package com.hsicen.letcode.sort

/**
 * 作者：hsicen  4/6/21 3:28 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：选择排序
 * 将待排序数组分为已排序区间和未排序区间，每次从未排序区间找到最小的元素，放到已排序区间末尾，直至未排序区间数据为0
 *
 * 原地排序：是
 * 稳定排序：是
 *
 * 最好时间复杂度：O(n^2)
 * 最差时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 *
 * 优化：判断最小值下标是否为未排序的第一个元素，不是才进行交换操作
 */

fun selectSort(items: IntArray) {
    if (items.size <= 1) return

    var minIndex: Int
    for (i in items.indices) {
        println("第${i + 1} 次遍历")

        minIndex = i
        for (p in i until items.size - 1) {
            if (items[minIndex] > items[p]) {
                minIndex = p
                println("找到最小值位置：$minIndex")
            }
        }

        if (i != minIndex) {
            println("交换前：${items.contentToString()}")
            val tmp = items[i]
            items[i] = items[minIndex]
            items[minIndex] = tmp
            println("交换后：${items.contentToString()}")
        } else {
            println("不交换：${items.contentToString()}")
        }
    }
}

fun main() {
    val intArray = intArrayOf(3, 5, 7, 3, 2, 8, 9, 0, 1, 10)
    println("排序前：${intArray.contentToString()}")
    selectSort(intArray)
    println("排序后：${intArray.contentToString()}")
}
