package com.hsicen.letcode.nowcoderimport java.util.*/** * 作者：hsicen  9/24/21 09:18 * 邮箱：codinghuang@163.com * 作用： * 描述：HJ1 字符串最后一个单词的长度 */fun main() {  val scanner = Scanner(System.`in`)  val sourceStr = scanner.nextLine()  println(lastWordLen(sourceStr))}fun lastWordLen(str: String): Int {  if (str.isEmpty()) return 0  val last = str.split(" ").last()  return last.length}