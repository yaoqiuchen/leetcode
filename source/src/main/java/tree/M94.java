package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
96 - 二叉树的中序遍历

 给定一个二叉树，返回它的中序 遍历。

 示例:

 输入: [1,null,2,3]
 1
 \
 2
 /
 3

 输出: [1,3,2]

 */
public class M94 {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.right = n2;
        n2.left = new TreeNode(3);
        new M94().inorderTraversal(n1);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        stack.push(root);

        // 将所有左子树都压进去
        while (node.left != null) {
            stack.push(node.left);
            node = node.left;
        }

        inorderTraversal(result, stack);
        return result;
    }

    public void inorderTraversal(List<Integer> list, Stack<TreeNode> stack) {
        while (!stack.isEmpty()) {
            // 可以认为每次弹出都已经处理完了左边分支
            TreeNode node = stack.pop();
            // 访问节点
            list.add(node.val);

            if (node.right == null) {
                continue;
            }
            stack.push(node.right);
            node = node.right;
            while (node.left != null) {
                stack.push(node.left);
                node = node.left;
            }
        }
    }

    public void inorderTraversal2(List<Integer> list, Stack<TreeNode> stack) {
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            // 将所有左子树都压进去
            while (node.left != null) {
                stack.push(node.left);
                node = node.left;
            }

            // 这个节点一定该访问
            node = stack.pop();
            list.add(node.val);

            if (node.right != null) {
                stack.add(node.right);
            }
        }
    }
}
