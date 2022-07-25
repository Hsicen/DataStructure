package me.hsicen.javaguide

import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * @author: hsicen
 * @date: 2022/7/25 15:21
 * @email: codinghuang@163.com
 * description: IO 操作
 */

fun main() {
  val path = "./test.txt"
  writeFile(path)
  readFile(path)
}

fun readFile(path: String) {
  val fos = FileInputStream(path)
  val bytes = ByteArray(1024)
  while (fos.read(bytes) != -1) {
    println(bytes)
  }
}

fun writeFile(path: String) {
  val os = FileOutputStream(path)
  val bytes = ByteArray(123)
  os.write(bytes)
}