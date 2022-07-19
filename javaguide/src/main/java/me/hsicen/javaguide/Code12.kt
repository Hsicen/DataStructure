package me.hsicen.javaguide

import kotlin.math.roundToInt

/**
 * @author: hsicen
 * @date: 2022/7/19 18:05
 * @email: codinghuang@163.com
 * description: LRC Cache 算法实现
 */
class LRUCache<K, V>(size: Int) :
  LinkedHashMap<K, V>((size / 0.75f).roundToInt() + 1, 0.75f, true) {
  private val maxCacheSize = size

  override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
    return this.size > maxCacheSize
  }

}

class LRUCache1<K, V>(size: Int) {
  private val maxCacheSize: Int = size
  private val cache: LinkedHashMap<K, V>

  init {
    cache = LinkedHashMap((size / 0.75f).roundToInt() + 1, 0.75f, true)
  }

  fun get(key: K): V? {
    return cache[key]
  }

  fun put(key: K, value: V) {
    cache[key] = value
  }

  fun remove(key: K) {
    cache.remove(key)
  }
}
