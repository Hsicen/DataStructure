package com.hsicen.lib.search

/**
 * 作者：hsicen  6/16/21 09:26
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：二分查找(针对有序数组)
 * 二分查找针对的是一个有序的数据集合，查找思想有点类似分治思想。
 * 每次都通过跟区间的中间元素对比，将待查找的区间缩小为之前的一半，直到找到要查找的元素，或者区间被缩小为 0。
 *
 * 局限性：
 *  1.二分查找依赖的是顺序表结构，简单点说就是数组(需要根据下标随机访问)
 *  2.二分查找针对的是有序数据
 *  3.数据量太小不适合二分查找
 *  4.数据量太大也不适合二分查找(数组需要连续的存储空间)
 *
 * 时间复杂度：O(logn)
 * 空间复杂度：O(1)
 */

fun main() {
  val items = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
  println("10的下标：${bSearch1(items, 10)}")
  println("10的下标：${bSearch2(items, 10)}")
  println("15的下标：${bSearch1(items, 15)}")
  println("15的下标：${bSearch2(items, 15)}")
}

/**
 * 二分查找非递归实现
 * @param items 数据
 * @param item 被查找的元素
 * @return 查找到的位置下标
 */
private fun bSearch1(items: IntArray, item: Int): Int {
  if (items.isEmpty()) return -1

  var low = 0
  var high = items.size - 1

  while (low <= high) {
    //val mid = (low + high) / 2 容易数据溢出
    //val mid = low + (high - low) / 2 优化
    //val mid = low + (high - low) shr 1 位运算右移
    val mid = low + ((high - low) shr 1)
    when {
      //使用high/low=mid有可能发生死循环
      items[mid] == item -> return mid
      items[mid] > item -> high = mid - 1
      items[mid] < item -> low = mid + 1
    }
  }

  return -1
}

/**
 * 二分查找递归实现
 * @param items 数据
 * @param item 被查找的元素
 * @return 查找到的位置下标
 */
private fun bSearch2(items: IntArray, item: Int): Int {
  fun search(items: IntArray, item: Int, low: Int, high: Int): Int {
    if (low > high) return -1

    val mid = low + ((high - low) shr 1)
    return when {
      items[mid] == item -> mid
      items[mid] < item -> search(items, item, mid + 1, high)
      else -> search(items, item, low, mid - 1)
    }
  }

  return search(items, item, 0, items.size - 1)
}

