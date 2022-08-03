package me.hsicen.javaguide

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.system.measureTimeMillis

/**
 * @author: hsicen
 * @date: 2022/8/3 16:24
 * @email: codinghuang@163.com
 * description: 动态代理
 */
interface IController {
  fun login(name: String): String

  fun logout()
}

class UserController : IController {

  override fun login(name: String): String {
    println("hsc $name prepare login.")
    return name
  }

  override fun logout() {
    println("hsc logout.")
  }
}

class ProxyHandler(private val originBean: Any) : InvocationHandler {

  override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
    val result: Any
    val cost = measureTimeMillis {
      result = if (args == null) {
        method?.invoke(originBean) ?: Any()
      } else method?.invoke(originBean, args) ?: Any()
    }

    println("hsc ${originBean.javaClass.simpleName}#${method?.name} cost $cost")
    return result
  }
}

fun main() {
  val controller = UserController()
  val proxyHandler = ProxyHandler(controller)
  val proxyInstance = Proxy.newProxyInstance(proxyHandler.javaClass.classLoader, UserController::class.java.interfaces, proxyHandler)
  (proxyInstance as? IController)?.login("hsicen")
  //(proxyInstance as? IController)?.logout()
}