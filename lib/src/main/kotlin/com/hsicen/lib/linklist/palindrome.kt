package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/16/21 12:16 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：回文串相关操作
 */

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


//判断是否为回文字符串  利用栈判断
fun isPalindromeStack(node: ListNode?): Boolean {

    return false
}