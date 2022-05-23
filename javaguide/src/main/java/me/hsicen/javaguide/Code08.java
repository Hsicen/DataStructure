package me.hsicen.javaguide;

/******====== 字符串 ======******/
public class Code08 {
  public static void main(String[] args) {
    testLen2();
  }

  public static void testLen2() {
    String latinS = "abc";
    System.out.println("" + latinS.getBytes().length);
    String utf16S = "王abc争";
    System.out.println("" + utf16S.getBytes().length);
  }

  private static void testStruct() {
    String s1 = "abc"; //字面常量赋值
    String s2 = new String("abc");
    String s3 = new String(new char[]{'a', 'b', 'c'});
    String s4 = new String(s3);
  }

  public static void testSub() {
    String s = "abcde";
    String substr1 = s.substring(1, 4); //substr1为"bcd"
    String substr2 = s.substring(0, 5); //substr2为"abcde"
    System.out.println("" + (s == substr2)); //打印true
  }

  public static void testLen() {
    String sd = "a我b你c";
    int len = sd.length();
    System.out.println(len);
  }
}
