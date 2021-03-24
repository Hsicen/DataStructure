package com.hsicen.lib.queue

/**
 * 作者：hsicen  3/24/21 4:18 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：利用数组实现循环队列
 */
class ArrayCircleQueue(capacity: Int = 10) {
    private var head = 0
    private var tail = 0
    private val capacity = capacity + 1
    private val mItems = IntArray(this.capacity)

    fun enQueue(item: Int) {
        if (full()) {
            throw IllegalStateException("The queue is full")
        }

        mItems[tail] = item
        tail = (tail + 1) % capacity
    }

    fun deQueue(): Int {
        if (empty()) return -1
        val item = mItems[head]
        head = (head + 1) % capacity

        return item
    }

    fun peek(): Int {
        if (empty()) return -1
        return mItems[head]
    }

    fun size(): Int {
        return if (tail > head) {
            tail - head
        } else {
            capacity + (tail - head)
        }
    }

    fun empty(): Boolean {
        return tail == head
    }

    fun full(): Boolean {
        return (tail + 1) % capacity == head
    }

    fun print() {
        println(mItems.contentToString())
    }

}

fun main() {
    val arrayQueue = ArrayCircleQueue(5)
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