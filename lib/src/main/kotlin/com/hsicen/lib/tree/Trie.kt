package com.hsicen.lib.tree

/**
 * 作者：hsicen  7/12/21 10:35
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Trie树：是一种专门处理字符串匹配的数据结构，用来解决在一组字符串集合中快速查找某个字符串的问题
 * 本质：就是利用字符串之间的公共前缀，将重复的前缀合并在一起
 * 从根节点到红色节点的一条路径表示一个字符串（注意：红色节点并不都是叶子节点）
 * 应用：
 * 1.联想搜索
 * 2.自动输入补全，比如输入法自动补全功能、IDE 代码编辑器自动补全功能、浏览器网址输入的自动补全功能等等
 *
 * 时间复杂度：建树O(n)+查找O(k)
 */
class TrieNode(
    val data: Char,
    val child: Array<TrieNode?> = Array(26) { null },
    var isEndingChar: Boolean = false,
)

class Trie {
    private val root = TrieNode('/')

    fun insert(text: String) {
        var p: TrieNode? = root
        for (i in text) {
            val index = i - 'a'
            if (p?.child?.get(index) == null) {
                val newNode = TrieNode(i)
                p?.child?.set(index, newNode)
            }
            p = p?.child?.get(index)
        }
        p?.isEndingChar = true
    }

    fun search(pattern: String): Boolean {
        var p: TrieNode? = root
        for (i in pattern) {
            val index = i - 'a'
            if (p?.child?.get(index) == null) {
                return false
            }

            p = p.child[index]
        }

        return p?.isEndingChar != false
    }
}