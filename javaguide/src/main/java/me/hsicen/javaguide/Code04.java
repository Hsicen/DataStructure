package me.hsicen.javaguide;

/******====== 基本类型 ======******/

public class Code04 {

  public static void main(String[] args) {

    range2();
  }

  private static void range3() {
    int i = 130;
    int j = 130;

    compare(i, j);
  }

  private static void compare(Integer i, Integer j) {
    Integer obj3 = i + 1; // 先拆箱，再装箱 没有超过127，指向缓存池中的 Integer 对象
    Integer obj4 = j + 1; // 先拆箱，再装箱 没有超过127

    System.out.println("" + (obj3 == obj4));
  }

  private static void range2() {
    Integer a = 12; // 缓存池 -128~127
    Integer b = 12; // 缓存池 -128~127
    Integer c = new Integer(12); // 直接创建
    Integer d = Integer.valueOf(12); // 缓存池 -128~127

    System.out.println(a == 12);
    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(a == d);
    System.out.println(c == d);
  }

  private static void range() {
    long d = 5000000000000L;
    float f = d;

    System.out.println(d);
    System.out.println(f);
  }
}
