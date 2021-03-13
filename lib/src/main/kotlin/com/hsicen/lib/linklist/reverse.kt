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

//判断是否为回文字符串  利用快慢指针判断
fun isPalindrome(node: ListNode?): Boolean {
    if (null == node?.next) return true

    var newHead: ListNode? = null
    var fast = node
    var slow = node

    while (null != fast?.next) {
        fast = fast.next?.next
        //反转slow链表
        val next = slow?.next
        slow?.next = newHead
        newHead = slow
        slow = next
    }

    //链表长度奇偶处理
    if (null != fast) {
        slow = slow?.next //链表长度为偶数
    }

    //链表前后比较  slow继续往前  newHead从头开始
    while (null != slow && null != newHead) {
        if (slow.value != newHead.value) return false

        slow = slow.next
        newHead = newHead.next
    }

    return true
}
