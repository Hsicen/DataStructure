package com.hsicen.letcode.array/** * 作者：hsicen  8/12/21 18:04 * 邮箱：codinghuang@163.com * 作用： * 描述：有序数组的平方 * Given an array of integers A sorted in non-decreasing order, * return an array of the squares of each number, also in sorted non-decreasing order. * * 思路：由于数组是有序数组，故数组平方的最大值一定在数组的两端产生， * 则可以从数组的两端往中间比较，得出最大的值由新数组的末尾往头开始添加 */fun sortedSquares(nums: IntArray): IntArray {    var start = 0    var end = nums.size - 1    val newNums = IntArray(nums.size)    for (i in nums.size - 1 downTo 0) {        if (-nums[start] > nums[end]) {            newNums[i] = nums[start] * nums[start]            start++        } else {            newNums[i] = nums[end] * nums[end]            end--        }    }    return newNums}