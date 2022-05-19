package me.hsicen.javaguide;

import java.util.Arrays;

/******====== 位运算 ======******/

/**
 * ## 位运算
 * 二进制：满 2 进 1；10010
 * 八进制：满 8 进 1；07656
 * 十进制：满 10 进 1；9976
 * 十六进制：满 16 进 1；0xF08A
 * <p>
 * ### 补码
 * 计算机使用一串二进制数的最高位作为**符号位**，其余位作为**数值位**。符号位为 0 表示正数，符号位为 1 表示负数.
 * 则原码的表示范围为：11111111 ~ 01111111 -> -127 ~ 127；并且有 -0(10000000) 和 +0(00000000)
 * <p>
 * 为了兼容二进制的减法运算，实际上整数在计算机中是使用补码来表示的；**正数的补码与原码相同，负数的补码是在原码的基础上先求反码，然后再 +1，反码的意思是在原码的基础上，符号位不变，数值位按位取反.**
 * <p>
 * - 在补码表示法中, 0 不再像原码那样有 +0 和 -0 的区分，-0 没有对应的补码
 * - 对于长度为n（n 个二进制位）的数据类型，原码的表示范围是 [-2^n+1, 2^n-1], 而补码的表示范围是 [-2^n, 2^n-1]，补码表示范围比原码大 1
 * - 补码的补码就是原码
 * <p>
 * ### 位运算
 * 常见的位运算有：与(&)，或(|)，异或(^)，取反(!)，位移；前 4 个运算是逐位进行逻辑运算，两个数执行位运算，就等于两个数的补码执行位运算.
 * <p>
 * 移位操作分为算术位移(>>,<<)和逻辑位移(>>>,<<<)，两种运算操作的对象也是数据的补码：
 * <p>
 * - 逻辑位移：逻辑位移不区分符号位，整体往左或往右移动，并且在后面或前面补全 0
 * - 算术位移：算术左移与逻辑左移操作相同；算术右移，正数整体右移之后前面补 0，负数整体右移之后前面补 1
 * - 算术左移相当于乘以2，常常利用位运算来替代乘以2的运算，以提高运算速度；当数据被左移之后，超过了可以表示的数据范围时，就有可能导致数据从负数变成正数，或从正数变成负数
 * - 算术右移相当于除以2，对于正数，不停算术右移，最终值为0；对于负数来说，不停算术右移，永远都不会为0，最终值停留在-1不变
 * <p>
 * 不管逻辑位移还是算术位移，超出范围的二进制位会被舍弃.
 */
public class Code05 {
  public static void main(String[] args) {
    System.out.println(countOneBits(-3));
    System.out.println(Arrays.toString(convertToBinaryArray(1011)));
    System.out.println(convertToDecimal(new int[]{1, 0, 1, 1}, 4));
    System.out.println(convertArrayToDecimal(new int[]{2, 3, 1}, 3));
  }

  public static int countOneBits(int num) {
    int count = 0;
    while (num != 0) {
      if ((num & 1) == 1) count++;
      num >>>= 1;
    }
    return count;
  }

  public static int[] convertToDecimalArray(int a) {
    int[] arr = new int[10]; //int类型最大2147483647，10位数
    int i = 0;
    while (a != 0) {
      arr[i] = a % 10; //余数放入数组 {4,9,1,5}
      a = a / 10; //商重新赋值给a
      i++;
    }
    swap(arr, i); //将{4,9,1,5}翻转为{5,1,9,4}
    return arr;
  }

  //a=1011（二进制表示） => {1，0，1，1}
  public static int[] convertToBinaryArray(int a) {
    int[] arr = new int[32]; //int型数据32位
    int i = 0;
    while (a != 0) {
      arr[i] = a % 2; //余数放入数组 {1,1,0,1}
      a = a / 2; //商重新赋值给a
      i++;
    }
    //swap()函数的代码实现见上一段代码
    swap(arr, i); //将{1,1,0,1}翻转为{1,0,1,1}
    return arr;
  }

  // {1, 0，1， 1} -》1011（二进制表示），k=4表示二进制位个数
  public static int convertToDecimal(int[] binaryArr, int k) {
    int a = 0;
    int weight = 1;
    for (int i = k - 1; i >= 0; --i) {
      a += binaryArr[i] * weight;
      weight *= 2;
    }
    return a;
  }

  // {1, 0, 1, 1} -》1011（二进制表示），k=4表示二进制位个数
  public static int convertToDecimal2(int[] binaryArr, int k) {
    int a = 0;
    for (int i = 0; i < k; ++i) {
      a = a * 2 + binaryArr[i];
    }
    return a;
  }

  // 将 10 进制数组转化为 10 进制整数 {2,3,1} -> 132
  public static int convertArrayToDecimal(int[] binaryArr, int k) {
    int a = 0;
    int weight = 1;
    for (int i = 0; i < k; i++) {
      a += binaryArr[i] * weight;
      weight *= 10;
    }
    return a;
  }

  public static void swap(int[] arr, int n) {
    int i = 0;
    int j = n - 1;
    while (i < j) {
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
      i++;
      j--;
    }
  }
}
