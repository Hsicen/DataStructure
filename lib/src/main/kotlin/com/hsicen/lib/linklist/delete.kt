package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/16/21 12:14 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表常见的删除操作
 */

//删除链表指定值结点
fun deleteNode(listNode: ListNode?, data: Int): ListNode? {
    if (listNode?.value == data) {
        return listNode.next
    }

    var pre = listNode
    var head = listNode?.next
    while (null != head?.next) {
        if (data == head.value) {
            pre?.next = head.next
            return listNode
        }

        pre = head
        head = head.next
    }

    return listNode
}

//删除链表倒数第 n 个结点  快慢指针
fun deleteNthFromEnd(listNode: ListNode?, index: Int): ListNode? {
    if (null == listNode) return listNode

    //快指针先移动 index 个结点
    var fast: ListNode? = listNode
    repeat(index) {
        fast = fast?.next
    }

    //如果 fast 移动到了末尾，则删除第一个结点
    if (null == fast?.next) {
        return listNode.next
    }

    //同时移动 fast,slow
    var slow: ListNode? = listNode
    while (null != fast?.next) {
        fast = fast?.next
        slow = slow?.next
    }

    //删除 slow 的 next
    slow?.next = slow?.next?.next

    return listNode
}

fun main() {
    val head = ListNode(1)
    addNodeEnd(head, 2)
    addNodeEnd(head, 3)
    addNodeEnd(head, 4)
    addNodeEnd(head, 5)
    addNodeEnd(head, 6)
    addNodeEnd(head, 7)

    printNode(head)
    deleteNthFromEnd(head, 1)
    printNode(head)
}

