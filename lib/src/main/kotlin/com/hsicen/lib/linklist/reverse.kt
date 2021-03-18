package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/13/21 3:02 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表反转相关操作
 */

//反转单链表(不带头结点)
fun reverseNode(listNode: ListNode?): ListNode? {
    var head = listNode
    var newHead: ListNode? = null

    while (null != head) {
        val next = head.next
        head.next = newHead
        newHead = head
        head = next
    }

    return newHead
}


//反转单链表(带头结点)
fun reverseNodeG(listNode: ListNode): ListNode {
    val newHead: ListNode = listNode
    var head: ListNode? = listNode.next

    newHead.next = null
    while (head != null) {
        val next = head.next
        head.next = newHead.next
        newHead.next = head
        head = next
    }

    return newHead
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