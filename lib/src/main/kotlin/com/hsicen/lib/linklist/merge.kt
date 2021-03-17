package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/13/21 3:06 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表合并操作
 */

//合并两个有序单链表(无头结点)
fun mergeListNode(nodeA: ListNode?, nodeB: ListNode?): ListNode? {
    if (null == nodeA || null == nodeB) {
        return nodeA ?: nodeB
    }

    var headA = nodeA
    var headB = nodeB

    var newHead = if (headA.value < headB.value) {
        headA = headA.next
        ListNode(nodeA.value)
    } else {
        headB = headB.next
        ListNode(nodeB.value)
    }

    while (null != headA && null != headB) {
        if (headA.value < headB.value) {
            addNodeEnd(newHead, headA.value)
            headA = headA.next
        } else {
            addNodeEnd(newHead, headB.value)
            headB = headB.next
        }
    }

    if (null != headA) {
        addNodeEnd(newHead, headA)
    }

    if (null != headB) {
        addNodeEnd(newHead, headB)
    }

    return newHead
}

fun main() {
    val nodeA = ListNode(1)
    addNodeEnd(nodeA, 3)
    addNodeEnd(nodeA, 5)
    addNodeEnd(nodeA, 7)
    addNodeEnd(nodeA, 9)
    addNodeEnd(nodeA, 12)
    addNodeEnd(nodeA, 18)

    printNode(nodeA)

    val nodeB = ListNode(2)
    addNodeEnd(nodeB, 4)
    addNodeEnd(nodeB, 6)
    addNodeEnd(nodeB, 8)
    addNodeEnd(nodeB, 10)

    printNode(nodeB)

    val newNode = mergeListNode(nodeA, nodeB)
    printNode(newNode)
    printNode(nodeA)
    printNode(nodeB)
}