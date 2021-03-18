package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/7/21 5:24 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表工具类
 */

fun printListNode(listNode: ListNode?) {
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

    printListNode(head)

    head = reverseNode(head)
    printListNode(head)
}