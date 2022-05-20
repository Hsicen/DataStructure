package me.hsicen.javaguide;

/******====== 字符串 ======******/
public class Code08 {
  public static void main(String[] args) {
    String s1 = "abc"; //字面常量赋值
    String s2 = new String("abc");
    String s3 = new String(new char[]{'a', 'b', 'c'});
    String s4 = new String(s3);


  }
}
