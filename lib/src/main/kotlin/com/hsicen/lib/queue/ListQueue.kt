package com.hsicen.lib.queue

import com.hsicen.lib.linklist.ListNode
import com.hsicen.lib.linklist.addNodeEnd
import com.hsicen.lib.linklist.printListNode

/**
 * 作者：hsicen  3/24/21 5:12 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：使用链表实现队列
 *  不存在队列满的情况
 */
class ListQueue {
    private val head = ListNode(-1)

    fun enQueue(item: Int) {
        head.next = addNodeEnd(head.next, item)
    }

    fun deQueue(): Int {
        if (empty()) return -1

        val next = head.next
        head.next = next?.next
        return next?.value ?: -1
    }

    fun peek(): Int {
        return head.next?.value ?: -1
    }

    fun empty(): Boolean {
        return null == head.next
    }

    fun print() {
        printListNode(head.next)
    }
}

fun main() {
    val listQueue = ListQueue()
    listQueue.enQueue(1)
    listQueue.enQueue(2)
    listQueue.enQueue(3)
    listQueue.enQueue(4)

    listQueue.print()
    listQueue.deQueue()
    listQueue.deQueue()
    listQueue.deQueue()
    listQueue.enQueue(8)
    listQueue.deQueue()
    listQueue.print()
}