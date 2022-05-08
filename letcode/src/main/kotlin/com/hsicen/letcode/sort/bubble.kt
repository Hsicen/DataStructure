package com.hsicen.letcode.sort

/**
 * 作者：hsicen  3/28/21 10:27 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：冒泡排序
 * 冒泡排序只会操作相邻的两个数据。
 * 每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。如果不满足就让它俩互换。
 * 一次冒泡会让至少一个元素移动到它应该在的位置，重复 n 次，就完成了 n 个数据的排序工作。
 *
 * 原地排序：是
 * 稳定排序：是
 *
 * 最好时间复杂度：O(n)
 * 最差时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 *
 * 优化：在一次排序过程中，如果没有数据交换，则认为数据已经有序，可以退出排序
 */

fun bubbleSort(items: IntArray) {
    if (items.size <= 1) return
    var exchange: Boolean

    for (i in items.indices) {
        println("外层循环：${i + 1}")

        exchange = false
        for (j in 0 until items.size - i - 1) {
            if (items[j] > items[j + 1]) {
                val tmp = items[j]
                items[j] = items[j + 1]
                items[j + 1] = tmp
                exchange = true
                println("内层交换：${items[j]}->${items[j + 1]} 结果：${items.contentToString()}")
            }
        }

        if (!exchange) return
    }
}


fun main() {
    val intArray = intArrayOf(3, 5, 7, 3, 2, 8, 9, 0, 1, 10)
    println("原始数组：${intArray.contentToString()}")
    bubbleSort(intArray)
    println("排序数组：${intArray.contentToString()}")
}
