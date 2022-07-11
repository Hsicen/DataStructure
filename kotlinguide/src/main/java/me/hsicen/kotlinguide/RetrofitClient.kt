package me.hsicen.kotlinguide

import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object RetrofitClient {

  val moshi: Moshi by lazy {
    Moshi.Builder()
      .add(NullStringAdapter)
      .add(KotlinJsonAdapterFactory())
      .build()
  }
}

// 解析到 null 时返回空字符串
object NullStringAdapter {

  @FromJson
  fun fromJson(reader: JsonReader): String {
    if (reader.peek() != JsonReader.Token.NULL) {
      return reader.nextString()
    }

    reader.nextNull<Unit>()
    return ""
  }

  @ToJson
  fun toJson(writer: JsonWriter, value: String?) {
    writer.value(value)
  }
}