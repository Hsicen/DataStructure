package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/7/21 10:10 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：使用LinkedList实现LRU缓存
 * 不带头结点
 */
class LRUCache(private val size: Int = 10) {
    private var cache: ListNode? = null
    private var len = 0

    private fun insert(data: Int) {
        val head = ListNode(data)
        head.next = cache
        cache = head
        len++
    }

    private fun delete(data: Int): Boolean {
        if (null == cache) return false

        if (data == cache?.value) {
            cache = cache?.next
            len--
            return true
        }

        var cur = cache
        while (null != cur?.next) {
            if (data == cur.next?.value) {
                cur.next = cur.next?.next
                len--
                return true
            }

            cur = cur.next
        }

        return false
    }

    private fun deleteEnd(): Boolean {
        if (null == cache) return false

        if (cache?.next == null) {
            cache = null
            len--
            return true
        }

        var cur = cache
        while (cur?.next?.next != null) {
            cur = cur.next
        }

        cur?.next = null
        len--

        return true
    }

    private fun contain(data: Int): Boolean {
        if (null == cache) return false

        var head = cache
        while (null != head) {
            if (data == head.value) return true
            head = head.next
        }

        return false
    }

    fun add(data: Int) {
        //情况一：当前链表为NULL，直接插入数据
        if (null == cache) {
            cache = ListNode(data)
            len++
            return
        }

        //情况二：当前链表包含插入数据，删除之前的数据，再将数据插入
        if (contain(data) && delete(data)) {
            insert(data)
            return
        }

        //情况三：当前链表不包含插入数据
        if (len < size) {
            insert(data)
        } else {
            deleteEnd()
            insert(data)
        }
    }

    fun printData() {
        printListNode(cache)
    }
}

fun main() {
    val lruCache = LRUCache(10)
    lruCache.add(1)
    lruCache.add(2)
    lruCache.add(3)
    lruCache.add(4)
    lruCache.add(5)
    lruCache.add(6)
    lruCache.add(7)
    lruCache.add(8)
    lruCache.add(9)
    lruCache.add(10)
    lruCache.add(11)
    lruCache.add(2)
    lruCache.add(8)

    lruCache.printData()
}