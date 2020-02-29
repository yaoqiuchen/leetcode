package tree;

import java.util.ArrayList;
import java.util.List;

/**
 114. 二叉树展开为链表

 给定一个二叉树，原地将它展开为链表。

 例如，给定二叉树

     1
    / \
   2  5
  / \  \
 3  4   6
 将其展开为：

 1
  \
  2
   \
   3
   \
   4
    \
    5
    \
    6

 */
public class M114 {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        List<TreeNode> list = new ArrayList<>();
        flatten(root, list);

        TreeNode node = root;
        for (int i = 1; i < list.size(); i++) {
            node.left = null;
            node.right = null;
            node.right = list.get(i);
            node = node.right;
        }
    }

    public void flatten(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        list.add(root);

        if (root.left != null) {
            flatten(root.left, list);
        }
        if (root.right != null) {
            flatten(root.right, list);
        }
    }

}
