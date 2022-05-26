package me.hsicen.javaguide

interface ICallable {
  fun add()
}

class Code10 {

  fun test() {
    var a = 1 // bytecode 发现创建了一个 IntRef

    val callback = object : ICallable {
      override fun add() {
        a++
        println("内部改变：$a")
      }
    }

    callback.add()
    println("外部读取：$a")
  }
}

class OutClass {

  init {
    println("out class init.")
  }

  fun sayHello() {
    println("Out class say hello world.")
  }

  class InnerClass {
    init {
      println("inner class init.")
    }
  }
}

fun main() {
  val code = Code10()
  code.test()

  val outClass = OutClass()
  outClass.sayHello()
}
