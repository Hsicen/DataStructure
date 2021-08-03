package com.hsicen.lib.arrayimport kotlin.IntArray/** * 作者：hsicen  8/3/21 15:19 * 邮箱：codinghuang@163.com * 作用： * 描述：有序数组的合并 *//** * 有序数组的合并 * @param sortA 有序数组A * @param sortB 有序数组B * @return 合并后的有序数组 */private fun merge(sortA: IntArray, sortB: IntArray): IntArray {    return when {        sortA.isEmpty() -> sortB        sortB.isEmpty() -> sortA        else -> {            val sizeA = sortA.size            val sizeB = sortB.size            val sortC = IntArray(sizeA + sizeB)            var posA = 0            var posB = 0            while (posA < sizeA && posB < sizeB) {                if (sortA[posA] <= sortB[posB]) {                    sortC[posA + posB] = sortA[posA++]                } else {                    sortC[posA + posB] = sortB[posB++]                }            }            while (posA < sizeA) {                sortC[posA + posB] = sortA[posA++]            }            while (posB < sizeB) {                sortC[posA + posB] = sortB[posB++]            }            sortC        }    }}fun main() {    val arrA = intArrayOf(1, 3, 5, 7, 9)    val arrB = intArrayOf(0, 2, 4, 6, 8, 10)    println(merge(arrA, arrB).asList())}