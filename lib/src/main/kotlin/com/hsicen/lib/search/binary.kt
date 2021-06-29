package com.hsicen.lib.search

import kotlin.math.abs

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
 *
 * 二分查找形变问题：
 * 1.查找第一个等于给定值的元素
 * 2.查找最后一个等于给定值的元素
 * 3.查找第一个大于等于给定值的元素
 * 4.查找最后一个小于等于给定值的元素
 */

fun main() {
  val items = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
  println("10的下标：${bSearch1(items, 10)}")
  println("10的下标：${bSearch2(items, 10)}")
  println("15的下标：${bSearch1(items, 15)}")
  println("15的下标：${bSearch2(items, 15)}")

  println("1的平方根：${mySqrt2(1f, 1)}")
  println("2的平方根：${mySqrt2(2f, 2)}")
  println("3的平方根：${mySqrt2(3f, 3)}")
  println("8的平方根：${mySqrt2(8f, 4)}")
  println("9的平方根：${mySqrt2(9f, 5)}")

  val items2 = intArrayOf(1, 2, 3, 4, 5, 6, 8, 8, 8, 8, 8, 11, 18)
  println(findFirstEquals(items2, 3))
  println(findFirstEquals(items2, 8))
  println(findFirstEquals(items2, 22))
}

/**
 * 二分查找非递归实现(数组有序，无重复元素)
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
      items[mid] == item -> return mid
      items[mid] > item -> high = mid - 1
      items[mid] < item -> low = mid + 1
    }
  }

  return -1
}

/**
 * 二分查找递归实现(数组有序，无重复元素)
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


/**
 * 求一个数的平方根(保留整数)
 */
private fun mySqrt(item: Int): Int {
  if (item <= 0) return -1
  if (1 == item) return item

  var low = 0
  var high = item
  var result = -1
  while (low <= high) {
    val mid = low + ((high - low) / 2)

    if (mid <= item / mid) {
      result = mid
      low = mid + 1
    } else {
      high = mid - 1
    }
  }

  return result
}

/**
 * 求一个数的平方根(保留整数)
 * 牛顿迭代法
 */
private fun mySqrt2(x: Float, count: Int): Float {
  if (x <= 0) return 0f
  if (x == 1f) return 1f

  //精度
  val e = 1 shr (if (count < 0) 0 else count)
  var ret = x
  var t = (ret + x / ret) / 2
  while (abs(ret - t) > e) {
    ret = t
    t = (ret + x / ret) / 2
  }

  return ret
}

/**
 * 查找第一个等于给定值的元素
 * @param items 数据
 * @param item 被查找的元素
 * @return 返回数组下标
 */
private fun findFirstEquals(items: IntArray, item: Int): Int {
  if (items.isEmpty()) return -1
  var low = 0
  var high = items.size - 1

  while (low <= high) {
    val mid = low + ((high - low) shr 1)
    when {
      items[mid] > item -> high = mid - 1
      items[mid] < item -> low = mid + 1
      else -> {
        if (0 == mid || items[mid - 1] != item) {
          return mid
        } else {
          high = mid - 1
        }
      }
    }
  }

  return -1
}

/**
 * 查找最后一个等于给定值的元素
 * @param items 数据
 * @param item 被查找的元素
 * @return 返回数组下标
 */
private fun findLastEquals(items: IntArray, item: Int): Int {


  return -1
}