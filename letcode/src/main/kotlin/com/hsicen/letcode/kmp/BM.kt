package com.hsicen.letcode.kmp

import kotlin.math.max

/**
 * 作者：hsicen  7/9/21 16:18
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：BM算法
 * 坏字符规则: 从模式串的末尾往前倒着匹配，当发现某个字符没法匹配的时候，我们把这个没有匹配的字符叫作坏字符(主串中的字符).
 *  当发生不匹配的时候，我们把坏字符对应的模式串中的字符下标记作 si. 如果坏字符在模式串中存在，我们把这个坏字符在模式串中的下标记作 xi.
 *  如果不存在，我们把 xi 记作 -1。那模式串往后移动的位数就等于 si-xi。
 *  注意:
 *  1.这里说的下标，都是字符在模式串的下标
 *  2.如果坏字符在模式串里多处出现，那我们在计算 xi 的时候，选择最靠后的那个，因为这样不会让模式串滑动过多，导致本来可能匹配的情况被滑动略过。
 *
 * 好后缀规则：从模式串的末尾往前倒着匹配，当发现某个字符没法匹配的时候，我们把前面已经匹配的字符串叫做好后缀.
 *  我们把好后缀记作{u}, 我们拿它在模式串中查找,
 *  如果找到了另一个跟{u}相匹配的子串{u*}, 那我们就将模式串滑动到子串{u*}与主串中{u}对齐的位置
 *  如果在模式串中找不到另一个等于{u}的子串，我们就直接将模式串滑动到主串中{u}的后面
 *  注意：
 *  1.当模式串中不存在等于{u}的子串时，我们直接将模式串滑动到主串{u}的后面, 可能会错过模式串和主串可以匹配的情况
 *  2.当模式串滑动到前缀与主串中{u}的后缀有部分重合的时候，并且重合的部分相等的时候，就有可能会存在完全匹配的情况
 *  3.针对这种情况，我们不仅要看好后缀在模式串中，是否有另一个匹配的子串，我们还要考察好后缀的后缀子串，是否存在跟模式串的前缀子串匹配的
 *  4.我们可以分别计算好后缀和坏字符往后滑动的位数，然后取两个数中最大的，作为模式串往后滑动的位数
 *
 *  以字符串 cabcab 为例
 *  后缀子串: b, ab, cab, bcab, abcab
 *  前缀子串: c, ca, cab, cabc, cabca
 */

const val SIZE = 256
private fun bm(source: String, key: String): Int {
    val n = source.length
    val m = key.length

    val bc = IntArray(SIZE) { -1 } // 记录模式串中每个字符最后出现的位置
    generateBC(key, m, bc) // 构建坏字符哈希表

    val suffix = IntArray(m) { -1 }
    val prefix = BooleanArray(m) { false }
    generateGS(key, m, suffix, prefix)

    var i = 0 // i表示主串与模式串对齐的第一个字符
    while (i <= n - m) {
        var j = 0
        for (pos in (m - 1) downTo 0) { // 模式串从后往前匹配
            j = pos
            if (source[i + j] != key[j]) break // 坏字符对应模式串中的下标是j
        }

        if (j < 0) return i // 匹配成功，返回主串与模式串第一个匹配的字符的位置

        val x = j - bc[source[i + j].code]
        var y = 0
        if (j < m - 1) { // 如果有好后缀的话
            y = moveByGS(j, m, suffix, prefix)
        }

        i += max(x, y)
    }

    return -1
}

// j表示坏字符对应的模式串中的字符下标; m表示模式串长度
fun moveByGS(j: Int, m: Int, suffix: IntArray, prefix: BooleanArray): Int {
    val k = m - 1 - j // 好后缀长度
    if (suffix[k] != -1) return j - suffix[k] + 1

    for (r in j + 2 until m) {
        if (prefix[m - r]) return r
    }

    return m
}

private fun generateBC(key: String, m: Int, bc: IntArray) {
    repeat(m) {
        val ascii = key[it].code
        bc[ascii] = it
    }
}

// key表示模式串，m表示长度，suffix，prefix数组事先申请好了
private fun generateGS(key: String, m: Int, suffix: IntArray, prefix: BooleanArray) {
    repeat(m - 1) {
        var j = it
        var k = 0 // 公共后缀子串长度

        while (j >= 0 && key[j] == key[m - 1 - k]) { // 与b[0, m-1]求公共后缀子串
            --j
            ++k
            suffix[k] = j + 1 // j+1表示公共后缀子串在b[0, i]中的起始下标
        }

        if (j == -1) prefix[k] = true // 如果公共后缀子串也是模式串的前缀子串
    }
}

fun main() {
    println(bm("abcdefg", "bc"))
    println(bm("ajskdhakshcbiuew", "ha"))
}
