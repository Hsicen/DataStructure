package com.hsicen.letcode.tree

import kotlin.math.max

/**
 * 作者：hsicen  7/6/21 18:16
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：求二叉树的最大深度
 */

/**
 * 求二叉树的高度(DFS)
 */
private fun maxDepth(root: BinaryTree?): Int {
    if (null == root) return 0

    return max(maxDepth(root.left), maxDepth(root.right)) + 1
}