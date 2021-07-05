package com.hsicen.lib.search

/**
 * 作者：hsicen  7/2/21 16:19
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：利用二分查找算法在循环数组中查找指定元素
 */

fun main() {
    val items = intArrayOf(4, 5, 6, 7, 0, 1, 2, 3)
    println("原数组：${items.toList()}")
    println("3在：${search(items, 3)}")
    println("4在：${search(items, 4)}")
    println("0在：${search(items, 0)}")
    println("9在：${search(items, 9)}")
}

/**
 * 将数组一分为2，一部分有序，另一部分可能有序，也可能无序;
 */
private fun search(nums: IntArray, target: Int): Int {
    if (nums.isEmpty()) return -1

    var low = 0
    var high = nums.size - 1
    while (low <= high) {
        val mid = low + ((high - low) shr 1)

        when {
            nums[mid] == target -> return mid
            nums[mid] < nums[high] -> {//右边有序
                if (nums[mid] < target && target <= nums[high]) {//在有序区间中
                    low = mid + 1
                } else {//在无序区间中
                    high = mid - 1
                }
            }
            else -> {//左边有序
                if (nums[mid] > target && target >= nums[low]) {//在有序区间中
                    high = mid - 1
                } else {//在无序区间中
                    low = mid + 1
                }
            }
        }
    }

    return -1
}