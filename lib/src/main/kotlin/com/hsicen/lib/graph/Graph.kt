package com.hsicen.lib.graph

import java.util.*

/**
 * 作者：hsicen  7/8/21 16:18
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：图的基本概念
 * 图: 图G(V,E)是由顶点和边组合成的一种图形，分为有向图和无向图，图不能为空，至少有一个顶点
 * 顶点: 途中的元素叫做顶点
 * 边: 顶点与顶点之间的连接叫做边
 * 度: 一个顶点边的条数叫做度
 * 有向图: 与顶点相连的边有方向的图
 * 无向图: 与顶点相连的边无方向的图
 * 入度: 指向顶点的边叫做入度
 * 出度: 由顶点发出的边叫做出度
 * 带权图: 边有权重系数的图
 *
 * 图的存储：
 * 1.邻接矩阵: 依赖于二维数组
 *  对于无向图来说，如果顶点 i 与顶点 j 之间有边，我们就将 A[i][j]和 A[j][i]标记为 1；
 *  对于有向图来说，如果顶点 i 到顶点 j 之间，有一条箭头从顶点 i 指向顶点 j 的边，那我们就将 A[i][j]标记为 1。
 *  同理，如果有一条箭头从顶点 j 指向顶点 i 的边，我们就将 A[j][i]标记为 1。
 *  对于带权图，数组中就存储相应的权重
 *
 * 2.邻接表: 数组(存储顶点)+链表(存储边)
 *  每个顶点对应一条链表，链表中存储的是与这个顶点相连接的其他顶点
 */

/*** 邻接表实现图 [v]为顶点数量 [adj]对应顶点的链表，用来出处该顶点的边信息*/
class Graph(private val v: Int) {
    private val adj = arrayOf<LinkedList<Int>>()
    private var found = false

    init {
        repeat(v) {
            adj[it] = LinkedList<Int>()
        }
    }

    /*** 添加顶点[s]与[e]之间的边信息*/
    fun addEdge(s: Int, e: Int) {
        adj[s].add(e)
        adj[e].add(s)
    }

    /**
     * 利用队列实现顶点[s]到顶点[e]的广度优先搜索算法 得到[s]到[e]的最短路径
     * 时间复杂度: O(V+E)
     * 空间复杂度: O(V)
     * */
    fun bfs(s: Int, e: Int) {
        if (s == e) return

        val visited = BooleanArray(v)// 标记被访问过的顶点
        visited[s] = true
        val queue = LinkedList<Int>()// 存储被 访问过的顶点但相邻顶点未被访问的顶点
        queue.add(s)
        val prev = IntArray(v) { -1 }// 用来记录搜索路径

        while (queue.size != 0) {
            val w = queue.poll()

            for (i in 0 until adj[w].size) {
                val q = adj[w][i]
                if (!visited[q]) {
                    prev[q] = w
                    if (q == e) {
                        print(prev, s, e)
                        return
                    }
                    visited[q] = true
                    queue.add(q)
                }
            }
        }
    }

    /**
     * 寻找顶点[s]到顶点[t]之间的一条路径
     * 时间复杂度: O(E)
     * 空间复杂度: O(V)
     * */
    fun dfs(s: Int, t: Int) {
        found = false
        val visited = BooleanArray(v)
        val prev = IntArray(v) { -1 }

        fun recurDfs(w: Int, t: Int, visited: BooleanArray, prev: IntArray) {
            if (found) return
            visited[w] = true
            if (w == t) {
                found = true
                return
            }

            for (i in 0 until adj[w].size) {
                val q = adj[w][i]
                if (!visited[q]) {
                    prev[q] = w
                    recurDfs(q, t, visited, prev)
                }
            }
        }

        recurDfs(s, t, visited, prev)
        print(prev, s, t)
    }

    /*** 递归打印数组[prev]从[s]到[e]的路径*/
    private fun print(prev: IntArray, s: Int, e: Int) {
        if (prev[e] != -1 && s != e) {
            print(prev, s, prev[e])
        }
        println("$e ")
    }
}
