package com.hsicen.letcode.tree

/**
 * 作者：hsicen  7/7/21 09:25
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：删除二叉搜索树中的节点
 */

private fun deleteNode(root: BinaryTree?, data: Int): BinaryTree? {
    if (null == root) return root
    var head = root
    var parent: BinaryTree? = null

    //查找待删除节点
    while (null != head && head.data != data) {
        parent = head
        head = if (head.data > data) {
            head.left
        } else head.right
    }

    //删除节点逻辑
    if (null == head) return root
    if (head.left != null && head.right != null) {
        //删除的有左右子节点 找到左子树的最大节点或右子树的最小节点


    } else {
        //删除的只有左节点或右节点或叶子节点
        val child = when {
            head.left != null -> head.left
            head.right != null -> head.right
            else -> null
        }

        when {
            parent?.left?.data == data -> parent.left = child
            parent?.right?.data == data -> parent.right = child
            else -> parent = child
        }
    }

    return root
}

fun main() {
    val node1 = BinaryTree(3, BinaryTree(2), BinaryTree(4))
    val node2 = BinaryTree(6, null, BinaryTree(7))
    val root = BinaryTree(5, node1, node2)

    deleteNode(root, 8)
}
