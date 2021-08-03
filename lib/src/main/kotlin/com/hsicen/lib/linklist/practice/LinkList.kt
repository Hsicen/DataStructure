package com.hsicen.lib.linklist.practice/** * 作者：hsicen  8/3/21 15:42 * 邮箱：codinghuang@163.com * 作用： * 描述：单链表 */class LinkList(val data: Int, var next: LinkList? = null) {    override fun toString(): String {        val sb = StringBuilder()        sb.append("[$data")        var head = next        while (head != null) {            sb.append(", ${head.data}")            head = head.next        }        sb.append("]")        return sb.toString()    }}private fun addHead(list: LinkList?, data: Int): LinkList {    val head = LinkList(data)    head.next = list    return head}private fun addEnd(list: LinkList?, data: Int): LinkList {    val end = LinkList(data)    return list?.let {        var cur = it        while (cur.next != null) {            cur = cur.next!!        }        cur.next = end        list    } ?: end}private fun addEnd(list: LinkList?, data: LinkList): LinkList {    return list?.let {        var cur = it        while (cur.next != null) {            cur = cur.next!!        }        cur.next = data        list    } ?: data}private fun delete(list: LinkList?, data: Int): LinkList? {    val head = LinkList(-1, list)    var pre = head    var cur = head.next    while (null != cur) {        if (data == cur.data) {            pre.next = cur.next        } else {            pre = cur        }        cur = cur.next    }    return head.next}private fun reverse(list: LinkList?): LinkList? {    val head = LinkList(-1)    var cur = list    while (null != cur) {        val next = cur.next        cur.next = head.next        head.next = cur        cur = next    }    return head.next}private fun merge(sortA: LinkList?, sortB: LinkList?): LinkList? {    return when {        null == sortA -> sortB        null == sortB -> sortA        else -> {            var posA = sortA            var posB = sortB            val data = if (posA.data <= posB.data) {                posA = posA.next                sortA.data            } else {                posB = posB.next                sortB.data            }            var sortC = LinkList(data)            while (null != posA && null != posB) {                if (posA.data <= posB.data) {                    val tmp = posA.data                    posA = posA.next                    sortC = addEnd(sortC, tmp)                } else {                    val tmp = posB.data                    posB = posB.next                    sortC = addEnd(sortC, tmp)                }            }            if (null != posA) {                sortC = addEnd(sortC, posA)            }            if (null != posB) {                sortC = addEnd(sortC, posB)            }            sortC        }    }}private fun middle(list: LinkList?): LinkList? {    if (null == list) return list    var fast = list    var slow = list    while (null != fast?.next) {        fast = fast.next?.next        slow = slow?.next    }    return slow}fun main() {    val ahead = LinkList(1)    addEnd(ahead, 3)    addEnd(ahead, 5)    addEnd(ahead, 7)    addEnd(ahead, 9)    addEnd(ahead, 11)    addEnd(ahead, 13)    addEnd(ahead, 14)    val bhead = LinkList(0)    addEnd(bhead, 2)    addEnd(bhead, 4)    addEnd(bhead, 6)    addEnd(bhead, 8)    val chead = merge(bhead, ahead)    println(chead)    println(chead)    println(middle(ahead))    println(middle(bhead))    println(middle(chead))}