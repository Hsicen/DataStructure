package com.hsicen.letcode.linklistimport com.hsicen.letcode.linklist.practice.LinkList/** * 作者：hsicen  8/12/21 18:20 * 邮箱：codinghuang@163.com * 作用： * 描述：find the same node of the two linkedlist */fun getNode(headA: LinkList?, headB: LinkList?): LinkList? {    var a = headA    var b = headB    while (a != b) {        a = a?.next ?: headB        b = b?.next ?: headA    }    return a}