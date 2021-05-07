package com.hsicen.lib.sort

/**
 * 作者：hsicen  4/26/21 9:19 AM
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：并归排序 分治思想
 *
 * 稳定排序：是
 * 原地排序：否
 *
 * 时间复杂度：O(n*logn)
 * 空间复杂度：O(n)
 *
 * 递推公式：
 *  mergeSort(p..r) = merge(mergeSort(p...q),mergeSort(q+1...r))
 * 终止条件：
 *  p>=r
 */

fun mergeSort(items: IntArray) {
    sortRecursion(items, 0, items.size - 1)
}

//递归分区
private fun sortRecursion(items: IntArray, start: Int, end: Int) {
    //递归终止条件
    if (start >= end) return

    //递归分区
    val mid = (start + end) / 2
    sortRecursion(items, start, mid)
    sortRecursion(items, mid + 1, end)

    //递归合并  合并时排序
    sortMerge(items, start, mid, end)
}

/**
 * 两个有序数组的合并(需要额外空间)
 * 利用哨兵放在数组末尾简化合并操作
 */
private fun sortMerge(items: IntArray, start: Int, mid: Int, end: Int) {
    println("排序区间：$start $mid $end")

    val leftArray = IntArray(mid - start + 2) { Int.MAX_VALUE }
    val rightArray = IntArray(end - mid + 1) { Int.MAX_VALUE }

    items.forEachIndexed { index, item ->
        if (index < start || index > end) return@forEachIndexed

        if (index <= mid) {
            leftArray[index - start] = item
        } else {
            rightArray[index - mid - 1] = item
        }
    }

    //合并有序集合 left right
    var i = 0
    var j = 0
    var k = 0
    while (leftArray[i] != Int.MAX_VALUE || rightArray[j] != Int.MAX_VALUE) {
        if (leftArray[i] <= rightArray[j]) {
            items[k + start] = leftArray[i]
            i++
        } else {
            items[k + start] = rightArray[j]
            j++
        }
        k++
    }

    println("排序结果：${items.contentToString()}")
}

fun main() {
    val items = intArrayOf(4, 3, 8, 9, 6, 7, 5, 2, 0, 1)
    println(items.contentToString())
    mergeSort(items)
}