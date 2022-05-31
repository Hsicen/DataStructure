package me.hsicen.kotlinguide

import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorTest {

  @Test
  fun testCalculate() {
    val calculator = Calculator()

    assertEquals("3", calculator.calculate("1+2"))
    assertEquals("11", calculator.calculate("9+2"))
    assertEquals("4999999999999999999999999", calculator.calculate("4999999999999999999999998+1"))
    assertEquals("4999999999999999999999998", calculator.calculate("4999999999999999999999999-1"))
  }
}