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
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

  // okhttp3
  implementation("com.squareup.okhttp3:okhttp:4.3.1")
  // gson
  implementation("com.google.code.gson:gson:2.8.5")
  // moshi
  implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
  implementation("com.squareup.moshi:moshi-adapters:1.13.0")
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
}
