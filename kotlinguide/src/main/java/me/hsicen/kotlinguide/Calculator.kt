package me.hsicen.kotlinguide

import kotlin.system.exitProcess

/******====== 计算器 ======******/

const val EXIT = "exit"
val HELP = """
  -----------------------------------------
  使用说明：                               
    1.输入 1 + 1，按回车，即可使用计算器      
    2.注意：数字与符号之间要有空格            
    3.想要退出程序，请输入：exit             
  -----------------------------------------""".trimIndent()

fun main() {
  val calculator = Calculator()
  calculator.start()
}

enum class Operator(val value: String) {
  ADD("+"),
  MINUS("-"),
  MULTI("*"),
  DIVIDE("/")
}

data class Expression(
  val left: String,
  val operator: Operator,
  val right: String
)

class Calculator {
  fun start() {
    while (true) {
      println(HELP)
      val input = readLine() ?: continue
      val result = calculate(input)

      if (null == result) {
        println("输入格式错误")
        continue
      } else println("$input = $result")
    }
  }

  fun calculate(input: String): String? {
    if (shouldExit(input)) exitProcess(0)
    val (left, ope, right) = parseExpression(input) ?: return null
    return when (ope) {
      Operator.ADD -> addString(left, right)
      Operator.MINUS -> minusString(left, right)
      Operator.MULTI -> multiString(left, right)
      Operator.DIVIDE -> divideString(left, right)
    }
  }

  private fun divideString(left: String, right: String): String {
    val result = left.toInt() / right.toInt()
    return result.toString()
  }

  private fun multiString(left: String, right: String): String {
    val result = left.toInt() * right.toInt()
    return result.toString()
  }

  private fun minusString(left: String, right: String): String {
    val result = left.toInt() - right.toInt()
    return result.toString()
  }

  private fun addString(left: String, right: String): String {
    val result = StringBuilder()
    var leftIndex = left.length - 1
    var rightIndex = right.length - 1
    var carry = 0

    while (leftIndex >= 0 || rightIndex >= 0) {
      val leftVal = if (leftIndex >= 0) left[leftIndex].digitToInt() else 0
      val rightVal = if (rightIndex >= 0) right[rightIndex].digitToInt() else 0
      val sum = leftVal + rightVal + carry

      carry = sum / 10
      result.append(sum % 10)
      leftIndex--
      rightIndex--
    }

    if (carry != 0) result.append(carry)
    return result.reversed().toString()
  }

  private fun parseExpression(input: String): Expression? {
    val operator = parseOperator(input) ?: return null
    val list = input.split(operator.value)
    if (2 != list.size) return null

    return Expression(list.first().trim(), operator, list.last().trim())
  }

  private fun parseOperator(input: String): Operator? {
    Operator.values().forEach {
      if (input.contains(it.value)) return it
    }

    return null
  }

  private fun shouldExit(input: String): Boolean {
    return input == EXIT
  }
}

