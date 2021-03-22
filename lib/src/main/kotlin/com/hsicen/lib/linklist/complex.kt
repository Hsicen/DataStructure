package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/21/21 11:14 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：复合训练
 */


/**
 * 判断是否为回文字符串
 * 利用快慢指针判断
 */
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

/**
 * 判断是否为回文字符串
 * 利用栈判断
 */
fun isPalindromeStack(node: ListNode?): Boolean {

    return false
}


/**
 * 找到链表的中间结点
 * 使用快慢指针寻找中间结点
 * 偶数结点默认返回后面一个结点
 */
fun middleNode(listNode: ListNode?): ListNode? {
    val newHead = ListNode(-1, listNode)

    var fast = newHead.next
    var slow = newHead.next

    while (null != fast?.next) {
        fast = fast.next?.next
        slow = slow?.next
    }

    return slow
}


fun main() {
    val head = ListNode(1)
    addNodeEnd(head, 2)
    addNodeEnd(head, 3)
    addNodeEnd(head, 4)
    addNodeEnd(head, 5)

    printListNode(head)
    val middle = middleNode(head)
    println(middle?.value)
}