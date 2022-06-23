package me.hsicen.kotlinguide

/**
 * @author: hsicen
 * @date: 2022/6/23 14:42
 * @email: codinghuang@163.com
 * description: 泛型的型变
 */

open class Tv {
  open fun turnOn() {}
}

class XiaoMiTV1 : Tv() {
  override fun turnOn() {}
}

class Controller<in T> {
  fun turnOn(tv: T) {
  }
}

fun buy(controller: Controller<in XiaoMiTV1>) {
  val xiaoMiTV1 = XiaoMiTV1()
  controller.turnOn(xiaoMiTV1)
}

fun main() {
  val controller = Controller<Tv>()
  buy(controller)
}



