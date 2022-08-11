package me.hsicen.javaguide

import kotlin.concurrent.thread

class IdGenerator {
    private var seq = 1

    @Synchronized
    fun incrementAndGet(): Int {
        seq++
        return seq
    }

    @Synchronized
    fun checkOdd() = seq % 2 == 1
}

fun main() {
    val idGenerator = IdGenerator()

    fun test() {
        if (idGenerator.checkOdd()) {
            println(idGenerator.incrementAndGet())
        }
    }

    val t1 = thread { repeat(1000) { test() } }
    val t2 = thread { repeat(1000) { test() } }
    val t3 = thread { repeat(1000) { test() } }
    val t4 = thread { repeat(1000) { test() } }

    t1.join()
    t2.join()
    t3.join()
    t4.join()
}