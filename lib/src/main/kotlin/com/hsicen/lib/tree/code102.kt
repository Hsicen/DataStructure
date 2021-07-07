package com.hsicen.lib.tree

import java.util.*

/**
 * 作者：hsicen  7/6/21 17:57
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：二叉树的层序遍历
 */
private fun levelOrder(root: BinaryTree?): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    if (null == root) return result

    val queue = LinkedList<BinaryTree>()
    queue.offer(root)

    while (queue.isNotEmpty()) {
        val level = ArrayList<Int>()

        repeat(queue.size) {
            val head = queue.poll()
            if (head.left != null) queue.offer(head.left)
            if (head.right != null) queue.offer(head.right)
            level.add(head.data)
        }

        result.add(level)
    }

    return result
}

fun main() {
    val level2 = BinaryTree(20, BinaryTree(15), BinaryTree(7))
    val root = BinaryTree(3, BinaryTree(9), level2)
    println(levelOrder(root))
}