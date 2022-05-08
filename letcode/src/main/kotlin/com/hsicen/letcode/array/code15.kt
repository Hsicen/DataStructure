package com.hsicen.letcode.array/** * 作者：hsicen  8/3/21 17:38 * 邮箱：codinghuang@163.com * 作用： * 描述：三数之和 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ， * 使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。 * * 「不重复」的本质是什么？我们保持三重循环的大框架不变，只需要保证： *      第二重循环枚举到的元素不小于当前第一重循环枚举到的元素； *      第三重循环枚举到的元素不小于当前第二重循环枚举到的元素。 *      这就会使得(a,b,c) -> a<=b<=c ==>排序就可以解决 * *      同时，对于每一重循环而言，相邻两次枚举的元素也不能相同 */private fun threeSum(nums: IntArray): List<List<Int>> {    val result = ArrayList<ArrayList<Int>>()    nums.sort()    repeat(nums.size) { i ->        val dest = nums[i]        if (dest > 0) return result        //去除连续的重复元素        if (i > 0 && nums[i] == nums[i - 1]) return@repeat        var start = i + 1        var end = nums.size - 1        while (start < end) {            val tmp = nums[start] + nums[end] + dest            when {                0 == tmp -> {                    result.add(arrayListOf(dest, nums[start], nums[end]))                    while (start < end && nums[end] == nums[end - 1]) end--                    while (start < end && nums[start] == nums[start + 1]) start++                    start++                    end--                    //去除连续的重复元素                }                0 > tmp -> start++                else -> end--            }        }    }    return result}fun main() {    val src = intArrayOf(-2, 0, 0, 0, 0, 2, 2, 2, 2)    println(threeSum(src))}