package com.hsicen.lib.tree

import java.util.*
import kotlin.math.max

/**
 * 作者：hsicen  7/5/21 17:44
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：二叉树:每个节点最多只有两个子节点的树
 * 特殊二叉树：
 *  1.满二叉树：叶子节点全都在最底层，除了叶子节点之外，每个节点都有左右两个子节点
 *  2.完全二叉树：叶子节点都在最底下两层，最后一层的叶子节点都靠左排列，并且除了最后一层，其他层的节点个数都要达到最大
 *
 * 二叉树的存储：
 *  1.链式存储，定义左右子节点
 *  2.顺序存储，完全二叉树适用；i为父节点，2i为左子节点，2i+1为右边子节点(i从1..n)
 *
 * 二叉树的遍历：
 *  1.前序遍历(根左右)
 *  2.中序遍历(左根右)
 *  3.后序遍历(左右根)
 *  4.层序遍历(从上到下，从左到右)
 *
 *  二叉查找树(BST)：二叉查找树要求，在树中的任意一个节点，其左子树中的每个节点的值，都要小于这个节点的值，而右子树节点的值都大于这个节点的值
 */
data class BinaryTree(val data: Int, var left: BinaryTree? = null, var right: BinaryTree? = null)

private fun preOrder(root: BinaryTree?) {
    if (null == root) return
    print("${root.data} ")
    preOrder(root.left)
    preOrder(root.right)
}

private fun inOrder(root: BinaryTree?) {
    if (null == root) return
    inOrder(root.left)
    print("${root.data} ")
    inOrder(root.right)
}

private fun postOrder(root: BinaryTree?) {
    if (null == root) return
    postOrder(root.left)
    postOrder(root.right)
    print("${root.data} ")
}

/**
 * 层序遍历(BFS)
 */
private fun levelOrder(root: BinaryTree?) {
    if (null == root) return
    val queue = LinkedList<BinaryTree>()
    queue.offer(root)

    while (queue.isNotEmpty()) {
        val head = queue.poll()
        print("${head.data} ")
        if (head.left != null) queue.offer(head.left)
        if (head.right != null) queue.offer(head.right)
    }
}

/**
 * 层序遍历(BFS)
 */
private fun levelOrder1(root: BinaryTree?) {
    if (null == root) return
    val queue = LinkedList<BinaryTree>()
    queue.offer(root)

    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val head = queue.poll()
            print("${head.data} ")
            if (head.left != null) queue.offer(head.left)
            if (head.right != null) queue.offer(head.right)
        }
    }
}

/**
 * BST查找
 */
private fun search(tree: BinaryTree, value: Int): BinaryTree? {
    var sorceTree: BinaryTree? = tree

    while (sorceTree != null) {
        when {
            sorceTree.data == value -> return tree
            sorceTree.data > value -> sorceTree = sorceTree.left
            sorceTree.data < value -> sorceTree = sorceTree.right
        }
    }
    return null
}

fun main() {
    val left = BinaryTree(2, BinaryTree(4), BinaryTree(5))
    val right = BinaryTree(3, BinaryTree(6), BinaryTree(7))
    val root = BinaryTree(1, left, right)

    println("前序遍历：")
    preOrder(root)
    println()
    println("中序遍历：")
    inOrder(root)
    println()
    println("后序遍历：")
    postOrder(root)
    println()
    println("层序遍历：")
    levelOrder(root)
}
