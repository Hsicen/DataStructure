package me.hsicen.javaguide;

import org.openjdk.jol.info.ClassLayout;

public class Code09 {

  public static void main(String[] args) {
    ClassLayout.parseInstance(new Student()).toPrintable();
  }

  public static class Student {
    private static final int s = 1;
    private long a;
    private int b;
    private Integer c;

    public void printInfo() {
      System.out.println(a);
      System.out.println(b);
      System.out.println(c);
    }
  }
}
