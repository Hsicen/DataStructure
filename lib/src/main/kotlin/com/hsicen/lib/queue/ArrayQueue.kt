package com.hsicen.lib.queue

/**
 * 作者：hsicen  3/24/21 3:35 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：利用数组实现队列数据结构
 *
 * 队空：head == tail
 * 队满：tail == capacity && 0 == head
 */
class ArrayQueue(private val capacity: Int = 10) {
    private var head = 0
    private var tail = 0
    private val mItems = IntArray(capacity)

    fun enQueue(item: Int) {
        if (tail == capacity) {
            if (0 == head) {
                throw IllegalStateException("The queue is full")
            }

            for (i in head until tail) {
                mItems[i - head] = mItems[i]
            }
            tail -= head
            head = 0
        }

        mItems[tail] = item
        tail++
    }

    fun deQueue(): Int {
        if (empty()) return -1
        val item = mItems[head]
        head++

        return item
    }

    fun peek(): Int {
        if (empty()) return -1
        return mItems[head]
    }

    fun size() = (tail - head)

    fun empty() = (tail == head)

    fun full() = (tail == capacity && head == 0)

    fun print() {
        println(mItems.contentToString())
    }

}

fun main() {
    val arrayQueue = ArrayQueue(5)
    arrayQueue.enQueue(1)
    arrayQueue.enQueue(2)
    arrayQueue.enQueue(3)
    arrayQueue.enQueue(4)
    arrayQueue.enQueue(5)

    arrayQueue.deQueue()
    println(arrayQueue.full())
    arrayQueue.print()
    arrayQueue.enQueue(7)
    println(arrayQueue.peek())
    println(arrayQueue.size())
    println(arrayQueue.full())
    println(arrayQueue.empty())
    arrayQueue.print()
}
