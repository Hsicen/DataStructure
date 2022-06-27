plugins {
  java
  kotlin("jvm")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test")
  implementation(kotlin("reflect"))
}
