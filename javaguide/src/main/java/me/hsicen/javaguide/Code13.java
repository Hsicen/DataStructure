package me.hsicen.javaguide;

/**
 * 作者：hsicen  7/24/22 21:36
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：DataStructure
 */
public class Code13 {

  public static void main(String[] args) {
    div(5, 0);
  }

  public static double div(int a, int b) {
    try {
      double res = a / b;
      System.out.println("in try block.");
      return res;
    } catch (ArithmeticException e) {
      System.out.println("in ATE catch block.");
      throw new RuntimeException("whoiam");
    } finally {
      System.out.println("in finally block.");
    }
  }
}
