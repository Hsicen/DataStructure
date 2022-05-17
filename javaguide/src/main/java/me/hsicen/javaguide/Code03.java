package me.hsicen.javaguide;

public class Code03 {
  public static void main(String[] args) {
    Integer a = 10;
    fa(a);
    System.out.println(a);
  }

  private static void fa(Integer va) {
    va = 20;
    System.out.println(va);
  }

}
