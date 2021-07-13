package com.hsicen.lib.custom

/**
 * 作者：hsicen  7/13/21 11:07
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：利用回溯算法实现正则表达式匹配
 */

// pattern 正则表达式
class Pattern(val pattern: CharArray) {
    private var matched = false
    private val pLen = pattern.size

    fun match(text: CharArray, tLen: Int): Boolean {
        matched = false
        rmatch(0, 0, text, tLen)
        return matched
    }

    private fun rmatch(ti: Int, pj: Int, text: CharArray, tLen: Int) {
        if (matched) return
        if (pj == pLen) { // 正则表达式到末尾了
            if (ti == tLen) matched = true // 文本串到结尾了
            return
        }

        when {
            pattern[pj] == '*' -> { // * 匹配任意个字符
                for (k in 0..(tLen - ti)) {
                    rmatch(ti + k, pj + 1, text, tLen)
                }
            }
            pattern[pj] == '?' -> { // ? 匹配0或1个字符
                rmatch(ti, pj + 1, text, tLen)
                rmatch(ti + 1, pj + 1, text, tLen)
            }

            ti < tLen && pattern[pj] == text[ti] -> { // 纯字符串匹配才行
                rmatch(ti + 1, pj + 1, text, tLen)
            }
        }
    }
}