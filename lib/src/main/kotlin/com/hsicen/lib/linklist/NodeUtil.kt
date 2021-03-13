package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/7/21 5:24 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表常见操作
 */
object NodeUtil {

    //单链表的常见操作
    fun addNodeHead(node: Node?, data: Int): Node {
        val head = Node(data)
        head.next = node

        return head
    }

    fun addNodeEnd(node: Node?, data: Int): Node {
        val end = Node(data)
        if (null == node) return end

        var head = node
        while (null != head?.next) {
            head = head.next
        }

        head?.next = end

        return node
    }

    fun deleteNode(node: Node?, data: Int): Node? {
        if (node?.value == data) {
            return node.next
        }

        var pre = node
        var head = node?.next
        while (null != head?.next) {
            if (data == head.value) {
                pre?.next = head.next
                return node
            }

            pre = head
            head = head.next
        }

        return node
    }

    fun printData(node: Node?) {
        if (null == node) return

        print("\n开始打印数据: ")
        var head = node
        while (null != head) {
            print("${head.value}\t")
            head = head.next
        }
        println("\n结束打印数据\n")
    }

    //链表的应用
    fun reverseNode(node: Node?): Node? {
        if (null == node) return null

        var head = node
        var newHead: Node? = null
        while (null != head) {
            val next = head.next
            head.next = newHead
            newHead = head
            head = next
        }

        return newHead
    }

}

fun main() {
    var head: Node? = null

    head = NodeUtil.addNodeEnd(head, 1)
    head = NodeUtil.addNodeEnd(head, 2)
    head = NodeUtil.addNodeEnd(head, 3)
    head = NodeUtil.addNodeEnd(head, 4)
    head = NodeUtil.addNodeEnd(head, 4)
    head = NodeUtil.addNodeEnd(head, 5)
    head = NodeUtil.deleteNode(head, 3)

    NodeUtil.printData(head)

    head = NodeUtil.reverseNode(head)
    NodeUtil.printData(head)
}