include(":design")
include(":letcode")
include(":javaguide")
include(":thinkjava")
include(":onjava")
include(":kotlinguide")

pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }

  resolutionStrategy {
    eachPlugin {
      when (requested.id.namespace) {
        "com.android" -> useModule("com.android.tools.build:gradle:7.1.2")
        "org.jetbrains.kotlin" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
        "org.gradle" -> {} // println("官方插件:${requested.id}")
        else -> useModule("${requested.id}:${requested.id}.gradle.plugin:${requested.version}")
      }
    }
  }
}