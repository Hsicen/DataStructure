package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/13/21 3:03 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表环相关操作
 */

//检查链表是否有环
fun checkCircle(listNode: ListNode?): Boolean {
    if (null == listNode) return false

    var fastStep = listNode
    var slowStep = listNode
    while (fastStep?.next != null) {
        fastStep = fastStep.next?.next
        slowStep = slowStep?.next

        if (fastStep == slowStep) return true
    }

    return false
}

//寻找两个链表的相交结点
fun twoNodeWithSame(listNodeA: ListNode?, listNodeB: ListNode?): ListNode? {
    if (null == listNodeA || null == listNodeB) return null

    var headA = listNodeA
    var headB = listNodeB

    while (headA != headB) {
        headA = if (null == headA) listNodeB else headA.next
        headB = if (null == headB) listNodeA else headB.next
    }

    return headA
}

fun main() {

    var nodeA = ListNode(1)
    nodeA = addNodeEnd(nodeA, 2)
    nodeA = addNodeEnd(nodeA, 3)
    nodeA = addNodeEnd(nodeA, 4)
    nodeA = addNodeEnd(nodeA, 5)
    printNode(nodeA)

    var nodeB = ListNode(1)
    nodeB = addNodeHead(nodeB, 2)
    nodeB = addNodeHead(nodeB, 3)
    nodeB = addNodeHead(nodeB, 4)
    nodeB = addNodeHead(nodeB, 5)
    printNode(nodeB)

    val sameNode = twoNodeWithSame(nodeA, nodeB)
    printNode(sameNode)
}

