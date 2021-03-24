package com.hsicen.datastructure

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 作者：hsicen  3/8/21 9:33 上午
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：DataStructure
 */
class Test {


}

val sum: Int.(Int, Int) -> Int = { a, b ->
    plus(a).plus(b)
}

inline fun Int.sum(other: Int): Int {
    return plus(other)
}

open class TypeToken<T> {
    var type: Type = Any::class.java

    init {
        val superClass = this.javaClass.genericSuperclass
        type = (superClass as ParameterizedType).actualTypeArguments[0]
    }
}

inline fun <reified T> getType() = T::class.java

fun main() {
    val type = object : TypeToken<Map<Int, String>>() {}.type
    println(type)

    val type1 = getType<Map<Int, String>>()
    println(type1)

    2.sum(3, 4)
    2.sum(1)
    2.sum(1)

    println(ArrayList<String>().javaClass.name)
}
