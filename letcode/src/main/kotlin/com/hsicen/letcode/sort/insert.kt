package com.hsicen.letcode.sort

/**
 * 作者：hsicen  4/5/21 10:17 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：插入排序
 * 插入排序原理：把数组分成两个区间，已排序区间和未排序区间，每次从未排序区间取一个数据，然后遍历已排序区间找到插入位置
 * 在查找插入位置的同时，移动元素的位置，为待插入数据腾出位置，重复操作直至未排序区间数据为0
 *
 * 原地排序：是
 * 稳定排序：是
 *
 * 最好时间复杂度：O(n)
 * 最差时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 *
 * 优化：判断是否插入到已排序区间的末尾，不是末尾才进行插入操作
 */
fun insertSort(items: IntArray) {
    if (items.size <= 1) return

    println("排序前：${items.contentToString()}")
    for (i in 1 until items.size) {
        val tmp = items[i]
        println("外层循环 -> $i")

        var index = i - 1
        for (j in i - 1 downTo 0) {
            if (items[j] > tmp) {
                println("有序区间移动：${items[j]}->${items[j + 1]}")
                items[j + 1] = items[j]
            } else break
            index = j - 1
        }

        if (index + 1 == i) {
            println("不插入：${items.contentToString()}")
        } else {
            println("插入前：${items.contentToString()}")
            items[index + 1] = tmp
            println("插入后：${items.contentToString()}")
        }
        println()
    }

    println("排序后：${items.contentToString()}")
}

fun main() {
    val intArray = intArrayOf(3, 5, 7, 3, 2, 8, 9, 0, 1, 10)
    insertSort(intArray)
}