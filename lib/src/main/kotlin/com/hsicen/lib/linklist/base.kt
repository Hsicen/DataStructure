package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/7/21 5:24 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表常见操作(增删改查)
 */

//单链表的常见操作
fun addNodeHead(listNode: ListNode?, data: Int): ListNode {
    val head = ListNode(data)
    head.next = listNode

    return head
}

fun addNodeEnd(listNode: ListNode?, data: Int): ListNode {
    val end = ListNode(data)
    if (null == listNode) return end

    var head = listNode
    while (null != head?.next) {
        head = head.next
    }

    head?.next = end

    return listNode
}

fun addNodeHead(listNode: ListNode?, node: ListNode): ListNode {
    if (null == listNode) return node

    val newHead = node
    addNodeEnd(newHead, listNode)
    return newHead
}

fun addNodeEnd(listNode: ListNode?, node: ListNode): ListNode {
    if (null == listNode) return node

    var head = listNode
    while (null != head?.next) {
        head = head.next
    }

    head?.next = node

    return listNode
}

fun printNode(listNode: ListNode?) {
    if (null == listNode) return

    print("\n开始打印数据: ")
    var head = listNode
    while (null != head) {
        print("${head.value}\t")
        head = head.next
    }
    println("\n结束打印数据\n")
}

fun main() {
    var head: ListNode? = null

    head = addNodeEnd(head, 1)
    head = addNodeEnd(head, 2)
    head = addNodeEnd(head, 3)
    head = addNodeEnd(head, 4)
    head = addNodeEnd(head, 4)
    head = addNodeEnd(head, 5)
    head = deleteNode(head, 3)

    printNode(head)

    head = reverseNode(head)
    printNode(head)
}