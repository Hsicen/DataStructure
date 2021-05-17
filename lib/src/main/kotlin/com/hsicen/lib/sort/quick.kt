package com.hsicen.lib.sort

/**
 * 作者：hsicen  5/6/21 09:14
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：快速排序  分治思想
 *
 * 稳定排序：否
 * 原地排序：是
 *
 * 空间复杂度：O(1)
 * 最好时间复杂度：O(n*logn)
 * 最差时间复杂度：O(n*n)
 * 平均时间复杂度：O(n*logn)
 *
 * 递推公式:
 *  quick_sort(p…r) = quick_sort(p…q-1) + quick_sort(q+1… r)
 * 终止条件：
 *  p >= r
 */

fun quickSort(items: IntArray) {
  sortRecursion(items, 0, items.size - 1)
}

//递归分区
private fun sortRecursion(items: IntArray, start: Int, end: Int) {
  if (start >= end) return

  //拆分时排序
  val pivot = partition(items, start, end)
  sortRecursion(items, start, pivot - 1)
  sortRecursion(items, pivot + 1, end)
}

//分区  大于分区点的放在后面，小于分区点的放在前面；返回分区点所在下标
private fun partition(items: IntArray, start: Int, end: Int): Int {
  val pivot = items[end]
  var index = start

  for (pos in start until end) {
    if (items[pos] < pivot) {
      val tmp = items[index]
      items[index] = items[pos]
      items[pos] = tmp

      index++
    }
  }

  //将分区点放在分界处
  items[end] = items[index]
  items[index] = pivot
  println("分区点$pivot：${items.contentToString()}")

  return index
}


/**=========应用============**/
/**
 * 查找无序数组中第n大的元素
 * 要求：时间复杂度为O(n)
 *
 * 利用快排思想，进行分区查找
 */
fun findNthLarge(items: IntArray, n: Int): Int {

  fun partitionRecursion(items: IntArray, start: Int, end: Int): Int {
    if (start >= end) return items[start]

    val pivot = partition(items, start, end)
    return when {
      pivot + 1 == n -> items[pivot]
      pivot + 1 < n -> partitionRecursion(items, pivot + 1, end)
      else -> partitionRecursion(items, start, pivot - 1)
    }
  }

  return partitionRecursion(items, 0, items.size - 1)
}

fun main() {
  val items = intArrayOf(4, 3, 8, 9, 6, 7, 5, 2, 0, 1)
  println("原数据：${items.contentToString()}")
  //quickSort(items)

  println("第1大数据：${findNthLarge(items, items.size - (1 - 1))}")
}