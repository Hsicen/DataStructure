package com.hsicen.lib.kmp

/**
 * 作者：hsicen  7/9/21 15:37
 * 邮箱：codinghuang@163.com
 * 作用：字符串匹配算法
 * 描述：Brute Force 暴力匹配算法(朴素匹配算法),一个串与一个串进行匹配
 * 基本概念：
 *  在字符串[A]中查找字符串[B]，那么字符串[A]为主串，字符串[B]为模式串
 *  主串长度为n，模式串长度为m，n > m.
 *  我们在主串中，检查起始位置分别是 0、1、2...n-m 且长度为 m 的 n-m+1 个子串，看有没有与模式串匹配的
 *  我们会对比 n-m+1 个字符串, 每个字符串的长度为 m, 最坏的情况下时间复杂度为O(m*n)
 */

fun bf(source: String, key: String): Boolean {
    if (source == key) return true

    val n = source.length
    val m = key.length
    for (i in 0..(n - m)) {
        val item = source.substring(i until i + m)
        println("第$i 次匹配: $item == $key  ${item == key}")
        if (item == key) return true
    }

    return false
}

fun main() {
    bf("aaaaaabaaaab", "aab")
    bf("aabaaaaaaaab", "aaa")
}
