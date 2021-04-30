package com.hsicen.datastructure

/**
 * 作者：hsicen  4/17/21 4:07 下午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：自定义排序
 * 按照指定的顺序进行排序
 */


fun main() {
    val sortRule = ArrayList<String>()
    sortRule.add("B")
    sortRule.add("C")
    sortRule.add("A")

    val originList = ArrayList<String>()
    originList.add("A")
    originList.add("B")
    originList.add("C")
    originList.add("DD")
    originList.add("B")
    originList.add("FFFF")
    originList.add("A")
    originList.add("B")
    originList.add("C")
    originList.add("222")

    val comparator = Comparator<String> { o1, o2 ->
        val first = sortRule.indexOf(o1)
        val second = sortRule.indexOf(o2)

        second - first
    }

    originList.asSequence().sortedWith(comparator).toList().let {
        println(it.toString())
    }

}
