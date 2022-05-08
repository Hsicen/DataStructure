package com.hsicen.letcode.stack

import com.hsicen.letcode.linklist.ListNode
import com.hsicen.letcode.linklist.addNodeHead
import com.hsicen.letcode.linklist.printListNode

/**
 * 作者：hsicen  3/24/21 9:22 上午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：利用链表实现队列
 */
class ListStack {
    private val head = ListNode(-1)

    fun push(item: Int) {
        head.next = addNodeHead(head.next, item)
    }

    fun pop(): Int {
        if (empty()) return -1

        val next = head.next
        head.next = next?.next
        return next?.value ?: -1
    }

    fun peek(): Int {
        return head.next?.value ?: -1
    }

    fun clear() {
        head.next = null
    }

    fun empty(): Boolean {
        return head.next == null
    }

    fun print() {
        printListNode(head.next)
    }

}

fun main() {
    val arrayStack = ListStack()
    arrayStack.push(1)
    arrayStack.push(2)
    arrayStack.push(3)
    arrayStack.push(4)
    arrayStack.push(5)
    arrayStack.push(6)
    println(arrayStack.pop())
    println(arrayStack.peek())
    println(arrayStack.peek())

    arrayStack.print()
}