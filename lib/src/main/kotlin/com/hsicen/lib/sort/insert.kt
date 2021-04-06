package com.hsicen.lib.sort

/**
 * 作者：hsicen  4/5/21 10:17 下午
 *
 * 邮箱：codinghuang@163.com
 *
 * 作用：
 *
 * 描述：插入排序
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
                println("内层交换：$j")
                items[j + 1] = items[j]
            } else break
            index = j - 1
        }

        println("插入前：${items.contentToString()}")
        items[index + 1] = tmp
        println("插入后：${items.contentToString()}")
    }

    println("排序后：${items.contentToString()}")
}

fun main() {
    val intArray = intArrayOf(3, 5, 7, 3, 2, 8, 9, 0, 1, 10)
    insertSort(intArray)
}