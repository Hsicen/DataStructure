package com.hsicen.lib.advance

import java.util.*

/**
 * 作者：hsicen  7/20/21 16:46
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：拓扑排序
 *  算法：拓扑排序算法  数据结构：有向无环图
 *  通过局部顺序来推导全局顺序的，一般都能用拓扑排序来解决
 */

/*** [v]为顶点数量 [adj]对应顶点的链表*/
class Graph(private val v: Int) {
    private val adj = arrayOf<LinkedList<Int>>()

    init {
        repeat(v) {
            adj[it] = LinkedList<Int>()
        }
    }

    /*** 添加顶点[s]与[e]之间的边信息*/
    fun addEdge(s: Int, e: Int) {
        adj[s].add(e)
    }

    // 通过 Kahn 算法实现拓扑排序 输出拓扑序列
    // 时间复杂度O(V+E)
    fun topoSortByKahn() {
        val inDegree = IntArray(v) // 统计每个顶点的入度
        repeat(v) { i ->
            repeat(adj[i].size) { j ->
                val w = adj[i][j]
                inDegree[w]++
            }
        }

        val queue = LinkedList<Int>()
        repeat(v) { i ->
            if (0 == inDegree[i]) queue.add(i)
        }

        while (queue.isNotEmpty()) {
            val i = queue.remove()
            print("->$i")

            repeat(adj[i].size) { j ->
                val k = adj[i][j]
                inDegree[k]--
                if (0 == inDegree[k]) queue.add(k)
            }
        }
    }

    // 通过深度优先遍历实现拓扑排序
    // 时间复杂度O(V+E)
    fun topoSortByDfs() {
        fun dfs(vertes: Int, inverseAdj: Array<LinkedList<Int>>, visited: BooleanArray) {
            repeat(inverseAdj[vertes].size) { i ->
                val w = inverseAdj[vertes][i]
                if (!visited[w]) {
                    visited[w] = true
                    dfs(w, inverseAdj, visited)
                }
            }

            print("->$vertes")
        }

        // 构建逆邻接表 s->t 表示s依赖于t, t先于s
        val inverseAdj = Array<LinkedList<Int>>(v) { LinkedList() }
        repeat(v) { i ->
            repeat(adj[i].size) { j ->
                val w = adj[i][j] // i -> w
                inverseAdj[w].add(i) // w -> i
            }
        }

        val visited = BooleanArray(v)
        repeat(v) { i ->
            if (!visited[i]) {
                visited[i] = true
                dfs(i, inverseAdj, visited)
            }
        }
    }
}