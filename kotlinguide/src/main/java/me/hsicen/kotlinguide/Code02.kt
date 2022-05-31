package me.hsicen.kotlinguide

/******====== kotlin 中的单例 ======******/
class User private constructor(name: String) {
  companion object {
    fun create(name: String): User {
      return User(name)
    }
  }
}

// 懒加载模式
object UserManager {
  val user by lazy { loadUser() }

  private fun loadUser(): User {
    return User.create("hsicen")
  }

  fun login() {

  }
}

// double check 模式
class UserManager2 private constructor(name: String) {
  companion object {
    @Volatile
    private var INSTANCE: UserManager2? = null

    fun getInstance(name: String): UserManager2 =
      // 第一次判空
      INSTANCE ?: synchronized(this) {
        // 第二次判空
        INSTANCE ?: UserManager2(name).also { INSTANCE = it }
      }
  }
}

// 抽象类模式
abstract class BaseSingleton<in P, out T> {
  @Volatile
  private var instance: T? = null

  protected abstract fun creator(param: P): T

  fun getInstance(param: P): T =
    instance ?: synchronized(this) {
      instance ?: creator(param).also { instance = it }
    }
}

class UserManager3 private constructor(name: String) {
  companion object : BaseSingleton<String, UserManager3>() {
    override fun creator(param: String): UserManager3 {
      return UserManager3(param)
    }
  }
}

class UserManager4 private constructor(age: Int) {
  companion object : BaseSingleton<Int, UserManager4>() {
    override fun creator(param: Int): UserManager4 {
      return UserManager4(param)
    }
  }
}

// 接口模板
interface ISingleton<P, T> {
  var instance: T?

  fun creator(param: P): T

  fun getInstance(p: P): T =
    instance ?: synchronized(this) {
      instance ?: creator(p).also { instance = it }
    }
}

class UserManager5 private constructor(name: String) {
  companion object : ISingleton<String, UserManager5> {
    @Volatile
    override var instance: UserManager5? = null

    override fun creator(param: String): UserManager5 {
      return UserManager5(param)
    }
  }
}



