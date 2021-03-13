package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/13/21 3:02 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表反转相关操作
 */

//反转单链表
fun reverseNode(listNode: ListNode?): ListNode? {
    if (null == listNode) return null

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
