package com.hsicen.lib.sort

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
 * 优化：在一次排序过程中，如果没有数据交换，则认为数据已经有序，可以退出排序
 */

fun bubbleSort(items: IntArray) {
    if (items.size <= 1) return
    var exchange: Boolean

    for (i in items.indices) {
        exchange = false
        for (j in 0 until items.size - i - 1) {
            if (items[j] > items[j + 1]) {
                val tmp = items[j]
                items[j] = items[j + 1]
                items[j + 1] = tmp
                exchange = true
            }
        }

        println("第${i + 1} 次排序：${items.contentToString()}")
        if (!exchange) return
    }
}


fun main() {
    val intArray = intArrayOf(3, 5, 7, 3, 2, 8, 9, 0, 1, 10)
    println(intArray.contentToString())
    bubbleSort(intArray)
    println(intArray.contentToString())
}
