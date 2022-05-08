package com.hsicen.letcode.linklist

/**
 * 作者：hsicen  3/18/21 9:25 上午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：链表的新增操作
 */

/**
 * 链表头地址已变  需要将旧头地址指向新头地址
 */
fun addNodeHead(listNode: ListNode?, data: Int): ListNode {
  val head = ListNode(data)
  head.next = listNode

  return head
}

/**
 * 链表头地址有可能改变
 */
fun addNodeEnd(listNode: ListNode?, data: Int): ListNode {
  val end = ListNode(data)
  if (null == listNode) return end

  var cur = listNode
  while (null != cur?.next) {
    cur = cur.next
  }

  cur?.next = end

  return listNode
}

/**
 * 链表头地址已变  需要将旧头地址指向新头地址
 */
fun addNodeHead(listNode: ListNode?, node: ListNode): ListNode {
  if (null == listNode) return node

  addNodeEnd(node, listNode)
  return node
}

/**
 * 链表头地址有可能改变
 */
fun addNodeEnd(listNode: ListNode?, node: ListNode): ListNode {
  if (null == listNode) return node

  var head = listNode
  while (null != head?.next) {
    head = head.next
  }

  head?.next = node

  return listNode
}

fun main() {
  var headA = ListNode(1)
  headA = addNodeHead(headA, 2)
  headA = addNodeHead(headA, 3)
  headA = addNodeHead(headA, 4)
  printListNode(headA)

  var headB = ListNode(5)
  headB = addNodeHead(headB, 6)
  headB = addNodeHead(headB, 7)
  headB = addNodeHead(headB, 8)
  printListNode(headB)

  val nodeC = addNodeHead(headA, headB)
  printListNode(headA)
  printListNode(headB)
  printListNode(nodeC)
}