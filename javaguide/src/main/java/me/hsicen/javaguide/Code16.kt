package me.hsicen.javaguide

import kotlin.concurrent.thread

@Volatile
var count = 0

var ready = false
var value = 1

fun main() {
    test2()
}

private fun test1() {
    val t1 = thread(start = false) {
        while (!ready) {
            println("wait for signal")
        }

        println("the value=$value")
    }

    val t2 = thread(start = false) {
        ready = true
        value = 2
    }

    t1.start()
    t2.start()
    t1.join()
    t2.join()
}

private fun test2() {
    val t1 = thread(start = false) {
        repeat(10000) {
            count++
        }
    }

    val t2 = thread(start = false) {
        repeat(10000) {
            count++
        }
    }

    t1.start()
    t2.start()
    t1.join()
    t2.join()

    println("The result is $count")
}