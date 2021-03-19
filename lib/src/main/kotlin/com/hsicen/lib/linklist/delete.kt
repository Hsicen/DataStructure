package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/16/21 12:14 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表常见的删除操作
 */


/**
 * 删除链表指定值结点
 * 删除链表的头结点时，链表头结点位置会改变
 */
fun deleteNode(listNode: ListNode?, data: Int): ListNode? {
    //利用头结点哨兵简化代码逻辑
    val newHead = ListNode(-1, listNode)

    var pre = newHead
    var cur = newHead.next

    while (null != cur) {
        if (data == cur.value) {
            pre.next = cur.next
        } else {
            pre = cur
        }

        cur = cur.next
    }

    return newHead.next
}

/**
 * 利用快慢指针删除链表倒数第 n 个结点；
 * 删除链表的头结点时，链表头结点位置会改变
 */
fun deleteNthFromEnd(listNode: ListNode?, index: Int): ListNode? {
    //利用头结点哨兵简化代码逻辑
    val newHead = ListNode(-1, listNode)

    //快指针先移动 index 个结点
    var fast: ListNode? = newHead
    repeat(index) {
        fast = fast?.next
    }

    //同时移动 fast,slow
    var slow: ListNode? = newHead
    while (null != fast?.next) {
        fast = fast?.next
        slow = slow?.next
    }

    //删除 slow 的 next
    slow?.next = slow?.next?.next

    return newHead.next
}

fun main() {
    var head: ListNode? = ListNode(1)
    addNodeEnd(head, 2)
    addNodeEnd(head, 3)
    addNodeEnd(head, 4)
    addNodeEnd(head, 5)
    addNodeEnd(head, 6)
    addNodeEnd(head, 7)

    printListNode(head)
    head = deleteNthFromEnd(head, 1)
    printListNode(head)
}

