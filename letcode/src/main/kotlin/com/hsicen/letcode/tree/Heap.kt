package com.hsicen.letcode.tree

/**
 * 作者：hsicen  7/8/21 10:56
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：堆(默认大顶堆)
 * 1.堆是一棵完全二叉树
 * 2.堆中的每一个节点的值都必须大于等于(或小于等于)其子树中每个节点的值
 *
 * 堆的应用：
 * 1.优先级队列
 * 2.Top K
 * 3.中位数
 */
class Heap(capacity: Int) {
    private val a = IntArray(capacity + 1)
    private val size = capacity
    private var count = 0

    fun insert(data: Int) {
        if (count >= size) return

        ++count
        a[count] = data
        heapify(a, count)
    }

    fun deleteMax() {
        if (0 == count) return
        a[1] = a[count]
        count--

        heapify(a, count, 1)
    }

    /**
     * 根据数组[a]中的[n]个数据来创建一个满足堆的2个条件的数组
     * @param a IntArray
     * @param n Int
     */
    fun buildHeap(a: IntArray, n: Int) {
        for (i in n / 2 downTo 1) {
            heapify(a, n, i)
        }
    }

    /**将大顶堆数组[a]中的[n]个数据升序排列*/
    fun sort(a: IntArray, n: Int) {
        buildHeap(a, n)

        var k = n
        while (k > 1) {
            a.swap(1, k)
            --k
            heapify(a, k, 1)
        }
    }

    /*** 将数组[a]从上往下堆化 [count]为父节点 [pos]为子节点所在层数 */
    private fun heapify(a: IntArray, count: Int, pos: Int) {
        var i = pos
        while (true) {
            var maxPos = i
            if (2 * i < count && a[maxPos] < a[2 * i]) maxPos = 2 * i
            if (2 * i + 1 < count && a[maxPos] < a[2 * i + 1]) maxPos = 2 * i + 1
            if (maxPos == i) return
            a.swap(i, maxPos)
            i = maxPos
        }
    }

    /*** 将数组[a]从下往上堆化 [count]为堆化开始点*/
    private fun heapify(a: IntArray, count: Int) {
        var i = count
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            a.swap(i, i / 2)
            i /= 2
        }
    }
}

fun IntArray.swap(one: Int, other: Int) {
    val tmp = this[one]
    this[one] = this[other]
    this[other] = tmp
}
