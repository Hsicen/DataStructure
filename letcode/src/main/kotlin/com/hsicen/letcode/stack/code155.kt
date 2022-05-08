package com.hsicen.letcode.stackimport java.util.*/** * 作者：hsicen  8/19/21 15:31 * 邮箱：codinghuang@163.com * 作用： * 描述：最小栈 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 */class MinStack {  private val mItems = Stack<Int>()  private val mMinItem = Stack<Int>()  fun push(`val`: Int) {    mItems.push(`val`)    if (mMinItem.isEmpty()) {      mMinItem.push(`val`)    } else {      if (`val` > mMinItem.peek()) {                mMinItem.push(mMinItem.peek())            } else {                mMinItem.push(`val`)            }        }    }    fun pop() {        mItems.pop()        mMinItem.pop()    }    fun top(): Int {        return mMinItem.peek()    }    fun getMin(): Int {        return mMinItem.peek()    }}