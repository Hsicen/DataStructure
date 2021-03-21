package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/13/21 3:02 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表反转相关操作
 */

//反转单链表
fun reverseNode(listNode: ListNode?): ListNode? {
    //利用头结点
    val newHead = ListNode(-1, listNode)

    var cur = newHead.next
    newHead.next = null
    while (null != cur) {
        val next = cur.next
        cur.next = newHead.next
        newHead.next = cur
        cur = next
    }

    return newHead.next
}

fun main() {

    var head: ListNode? = ListNode(-1)
    head = addNodeEnd(head, 1)
    head = addNodeEnd(head, 2)
    head = addNodeEnd(head, 3)
    head = addNodeEnd(head, 4)
    head = addNodeEnd(head, 5)

    printListNode(head)
    val newHead = reverseNode(head)
    printListNode(newHead)

    /*printListNode(head)
    val newHead1 = reverseNodeG(head)
    printListNode(newHead1)*/
}