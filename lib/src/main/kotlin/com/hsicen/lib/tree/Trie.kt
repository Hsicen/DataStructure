package com.hsicen.lib.tree

import java.util.*

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
    var isEndingChar: Boolean = false
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

/**
 * AC 自动机(多模式串匹配算法)和 Trie 树实现敏感词过滤
 *
 */
class AcNode(
    val data: Char,
    val child: Array<AcNode?> = Array(26) { null },
    var isEndingChar: Boolean = false, // 是否为结尾字符串
    var length: Int = -1, //当 isEndingChar=true 时记录模式串长度
    var fail: AcNode? = null // 失败指针
)

class AcTrie {
    private val root = AcNode('/')

    fun insert(text: String) {
        var p: AcNode? = root
        for (i in text) {
            val index = i - 'a'
            if (p?.child?.get(index) == null) {
                val newNode = AcNode(i)
                p?.child?.set(index, newNode)
            }
            p = p?.child?.get(index)
        }
        p?.isEndingChar = true
    }

    fun search(pattern: String): Boolean {
        var p: AcNode? = root
        for (i in pattern) {
            val index = i - 'a'
            if (p?.child?.get(index) == null) {
                return false
            }

            p = p.child[index]
        }

        return p?.isEndingChar != false
    }

    fun buildFailurePointer() {
        val queue = LinkedList<AcNode>()
        root.fail = null
        queue.add(root)

        while (queue.isNotEmpty()) {
            val p = queue.remove()
            repeat(26) { i ->
                p.child[i]?.let { pc ->
                    if (p == root) {
                        pc.fail = root
                    } else {
                        var q = p.fail
                        while (q != null) {
                            val qc = q.child[pc.data - 'a']
                            if (qc != null) {
                                pc.fail = qc
                                break
                            }
                            q = q.fail
                        }

                        if (q == null) {
                            pc.fail = root
                        }
                    }
                    queue.add(pc)
                }
            }
        }
    }

    /*** 在主串[source]中进行匹配*/
    fun match(source: String) {
        val n = source.length
        var p: AcNode? = root

        repeat(n) { i ->
            val idx = source[i] - 'a'
            while (p?.child?.get(idx) == null && p != root) {
                p = p?.fail
            }

            p = p?.child?.get(idx)
            if (p == null) p = root
            var tmp = p
            while (tmp != root) {
                if (tmp?.isEndingChar == true) {
                    val pos = i - tmp.length + 1
                    println("匹配起始下标: $pos , 长度: ${tmp.length}")
                }
                tmp = tmp?.fail
            }
        }
    }
}