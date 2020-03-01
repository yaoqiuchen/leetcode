package tree;

import java.util.*;

/**
102. 二叉树的层次遍历

 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

 例如:
 给定二叉树: [3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 返回其层次遍历结果：

 [
 [3],
 [9,20],
 [15,7]
 ]

 */
public class M102 {

    // 3/1
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Queue<TreeNode> newQueue = new LinkedList<>();
            List<Integer> line = new ArrayList<>();

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                line.add(node.val);
                if (node.left != null) newQueue.add(node.left);
                if (node.right != null) newQueue.add(node.right);
            }
            result.add(line);
            queue = newQueue;
        }

        return result;
    }

}
