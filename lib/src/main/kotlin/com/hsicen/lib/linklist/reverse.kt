package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/13/21 3:02 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表反转相关操作
 */


/**
 * 反转单链表
 * 创建一个新的带头结点的链表，然后使用头插法，依次往新链表中插入元素
 * @param listNode ListNode?
 * @return ListNode?
 */
fun reverseNode(listNode: ListNode?): ListNode? {
    //创建一个带头结点的单链表
    val newHead = ListNode(-1)
    var cur = listNode

    while (null != cur) {
        val next = cur.next
        cur.next = newHead.next
        newHead.next = cur
        cur = next
    }

    return newHead.next
}

fun main() {

    var head: ListNode? = ListNode(0)
    head = addNodeEnd(head, 1)
    head = addNodeEnd(head, 2)
    head = addNodeEnd(head, 3)
    head = addNodeEnd(head, 4)
    head = addNodeEnd(head, 5)
    head = addNodeEnd(head, 6)
    head = addNodeEnd(head, 7)

    printListNode(head)
    val newHead = reverseNode(head)
    printListNode(newHead)
}