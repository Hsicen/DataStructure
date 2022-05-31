package me.hsicen.kotlinguide

fun main() {

  val ints = arrayOf(1, 3, 5, 7, 9)
  ints[3]

  IntArray(3)
}

interface Human {
  val name: String
  var age: Int

  fun info()
}

class Man : Human {
  override var age: Int = 23
    get() = 12
    set(value) {
      field = 12
    }

  override val name = "Mike"

  override fun info() {
    println(name)
  }
}


