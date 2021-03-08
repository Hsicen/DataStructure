package com.hsicen.lib.linklist

/**
 * 作者：hsicen  3/7/21 5:24 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：单链表常见操作
 */
object NodeUtil {


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


}