package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/7/21 10:10 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：使用LinkedList实现LRU缓存
 * 不带头结点
 */
class LRUCache(private val size: Int = 10) {
    private var cache: Node? = null
    private var len = 0

    fun add(data: Int) {
        if (null == cache) {
            cache = Node(data)
            len++
            return
        }

        if (contain(data) && delete(data)) {
            insert(data)
        }

        if (len < size) {
            insert(data)
        } else {
            deleteEnd()
            insert(data)
        }
    }

    private fun insert(data: Int) {
        val head = Node(data)
        head.next = cache
        len++
    }

    private fun delete(data: Int): Boolean {
        if (null == cache) return false

        if (data == cache?.value) {
            len--
            cache = cache?.next
            return true
        }

        var pre = cache
        while (null != pre?.next) {
            if (data == pre.next?.value) {
                pre.next = pre.next?.next
                len--
                return true
            }

            pre = pre.next
        }

        return false
    }

    private fun deleteEnd(): Boolean {

        return false
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
}