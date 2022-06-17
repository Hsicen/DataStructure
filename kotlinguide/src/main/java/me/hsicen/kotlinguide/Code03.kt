package me.hsicen.kotlinguide

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Item {
  private var count: Int = 0

  var total: Int by ::count
}

val data: String by lazy {
  request()
}

fun request(): String {
  println("执行网络请求")
  return "网络数据"
}

fun main() {
  println("开始")
  println(data)
  println(data)
  println(data)
}

class Owner {
  var name: String by NameDelegate()

  val logMsg by SmartDelegate()
  val normalMsg by SmartDelegate()
}

class SmartDelegate {
  operator fun provideDelegate(
    thisObj: Owner,
    property: KProperty<*>
  ): ReadWriteProperty<Owner, String> {
    return if (property.name.contains("log")) {
      NameDelegate("log")
    } else NameDelegate("normal")
  }
}

class NameDelegate(private var name: String = "Hello") : ReadWriteProperty<Owner, String> {
  override operator fun getValue(thisRef: Owner, property: KProperty<*>): String = name

  override operator fun setValue(thisRef: Owner, property: KProperty<*>, value: String) {
    name = value
  }
}
