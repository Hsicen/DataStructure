package com.hsicen.lib.stack

/**
 * 作者：hsicen  3/23/21 12:11 下午
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：利用数组实现栈
 *
 * 说明：数据结构中的栈和内存中的栈的本质是一样的，都被称为方法栈
 *
 *      内存空间划分：在逻辑上划分为代码区，静态数据区，动态数据区(栈+堆)
 *      代码区：存储方法体的二进制代码，高级调度(作业调度),中级调度(内存调度),低级调度(进程调度)控制代码区执行代码的切换
 *      静态数据区：存储全局变量，静态变量，常量( final 修饰的常量, String 常量)；由系统自动分配和回收
 *      栈：存储运行方法的形参，局部变量，返回值；由系统自动分配和回收
 *      堆：new一个对象的引用或者地址存储在栈区，指向该对象存储在堆区的真实数据
 */
class ArrayStack(private var capacity: Int = 10) {
    private var size = 0
    private var mItems = IntArray(capacity)

    /**
     * put the item into the stack
     */
    fun push(item: Int) {
        if (size == capacity) {
            capacity *= 2
            mItems = mItems.copyOf(capacity)
        }

        mItems[size] = item
        size++
    }

    /**
     * return the top element of the stack and remove it
     * -1 will be returned if no element in the stack
     */
    fun pop(): Int {
        if (0 == size) return -1
        val item = mItems[size - 1]
        mItems[size - 1] = 0
        size--

        return item
    }

    /**
     * return the top element of the stack without remove
     * -1 will be returned if no element in the stack
     */
    fun peek(): Int {
        if (0 == size) return -1

        return mItems[size - 1]
    }

    /**
     * return the stack is empty or not
     */
    fun empty(): Boolean {
        return 0 == size
    }

    fun print() {
        println(mItems.contentToString())
    }
}


fun main() {
    val arrayStack = ArrayStack(5)
    arrayStack.push(1)
    arrayStack.push(2)
    arrayStack.push(3)
    arrayStack.push(4)
    arrayStack.push(5)
    arrayStack.push(6)
    println(arrayStack.pop())
    println(arrayStack.peek())
    println(arrayStack.peek())

    arrayStack.print()
}