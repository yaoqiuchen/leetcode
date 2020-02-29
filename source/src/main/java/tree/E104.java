package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 给定一个二叉树，找出其最大深度。

 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

 说明: 叶子节点是指没有子节点的节点。

 示例：
 给定二叉树 [3,9,20,null,null,15,7]，

 3
 / \
 9  20
 /  \
 15   7
 返回它的最大深度 3 。

 */
public class E104 {

    public int maxDepth(TreeNode root) {
        return DFS(root, 0);
    }

    public int DFS(TreeNode root, int deep) {
        if (root == null) return 0;
        return Math.max(DFS(root.left, deep+1),  DFS(root.right, deep+1)) + 1;
    }

}
