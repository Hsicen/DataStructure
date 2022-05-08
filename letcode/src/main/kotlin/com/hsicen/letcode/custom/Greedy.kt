package com.hsicen.letcode.custom

/**
 * 作者：hsicen  7/12/21 14:38
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：贪心算法
 * 1.针对一组数据，我们定义了[限制值]和[期望值]，希望从中选出几个数据，在满足限制值的情况下，期望值最大
 * 2.每次选择当前情况下，在对限制值同等贡献量的情况下，对期望值贡献最大的数据
 * 3.举几个例子看下贪心算法产生的结果是否是最优的
 *
 * 案例：
 *  1.分糖果：从对糖果重量需求最小的孩子开始分配，从糖果中最小的重量开始寻找；
 *  然后从剩下的孩子中，找出对糖果大小需求最小的，然后发给他剩下的糖果中能满足他最小的糖果
 *  2.钱币找零：在贡献相同期望值（纸币数目）的情况下，我们希望多贡献点金额，这样就可以让纸币数更少
 *  3.区间覆盖：从这 n 个区间中选出一部分区间，这部分区间满足两两不相交（端点相交的情况不算相交），最多能选出多少个区间呢？
 *  我们每次选择的时候，左端点跟前面的已经覆盖的区间不重合的，右端点又尽量小的，这样可以让剩下的未覆盖区间尽可能的大，就可以放置更多的区间
 *  4.
 */

/**
 * 从非负整数[num]中移除[k]个数字，让其剩下的数字组成的数最小
 */
fun removeKdigits(num: String, k: Int): String {
    if (num.length == k) return "0"
    val sb = StringBuilder(num)
    for (i in 0 until k) {
        var idx = 0
        var j = 1
        while (j < sb.length && sb[j] >= sb[j - 1]) {
            idx = j
            j++
        }

        sb.delete(idx, idx + 1)
        while (sb.length > 1 && sb[0] == '0') {
            sb.delete(0, 1)
        }
    }

    return sb.ifEmpty { "0" }.toString()
}

fun main() {
    println(removeKdigits("1432219", 3))
    println(removeKdigits("10200", 1))
    println(removeKdigits("112", 1))
}
