package me.hsicen.kotlinguide

import kotlin.reflect.full.declaredMembers

/**
 * @author: hsicen
 * @date: 2022/6/27 10:19
 * @email: codinghuang@163.com
 * description: 注解与反射
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
public annotation class Hsicen(
  val name: String,
  val age: Int,
  val address: String
)


fun readMembers(obj: Any) {
  obj::class.declaredMembers.forEach {
    println("${it.name}: ${it.isOpen}")
  }
}

fun main() {
  val hsicen = Hsicen::class.java
  println(hsicen.annotations)
  println(hsicen.declaredAnnotations)
  println(hsicen.annotations.size)
  println(hsicen.declaredAnnotations.size)
  println(hsicen.annotations.first())
  println(hsicen.declaredAnnotations.first())
  println(hsicen.annotations.first().annotationClass)
  println(hsicen.declaredAnnotations.first().annotationClass)
  println(hsicen.annotations.first().annotationClass.simpleName)
  println(hsicen.declaredAnnotations.first().annotationClass.simpleName)
  println(hsicen.annotations.first().annotationClass.qualifiedName)
  println(hsicen.declaredAnnotations.first().annotationClass.qualifiedName)
}