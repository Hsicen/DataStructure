package com.hsicen.lib.kmp

/**
 * 作者：hsicen  7/9/21 18:17
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：KMP字符串匹配算法
 * 核心思想：主串 a，模式串 b, 在模式串与主串匹配的过程中，当遇到不可匹配的字符的时候，
 *  我们希望找到一些规律，可以将模式串往后多滑动几位，跳过那些肯定不会匹配的情况
 *  坏字符情况：当遇到坏字符的时候，将模式串往后移，在滑动的过程中，只要模式串和好前缀有上下重合，
 *      前面几个字符的比较，就相当于拿好前缀的后缀子串，跟模式串的前缀子串在比较
 *  号前缀情况：找到好前缀的最长可匹配后缀子串与最长可匹配前缀子串，让其上下对齐
 *
 * 空间复杂度：O(m)
 * 时间复杂度：O(m+n)
 */

/*** 在字符串[source]中找到字符串[key]，并返回在[source]中的起始下标*/
private fun kmp(source: String, key: String): Int {
    val n = source.length
    val m = key.length

    val next = getNexts(key, m)
    var j = 0
    repeat(n) { i ->
        while (j > 0 && source[i] != key[j]) {
            j = next[j - 1] + 1
        }
        if (source[i] == key[j]) {
            ++j
        }
        if (j == m) { // 找到匹配模式串
            return i - m + 1
        }
    }

    return -1
}

/*** 计算字符串[key]的每个前缀的最长可匹配前缀子串的结尾字符下标*/
fun getNexts(key: String, m: Int): IntArray {
    val next = IntArray(m)
    next[0] = -1
    var k = -1

    for (i in 1 until m) {
        while (k != -1 && key[k + 1] != key[i]) {
            k = next[k]
        }

        if (key[k + 1] == key[i]) {
            ++k
        }
        next[i] = k
    }

    return next
}

fun main() {

}