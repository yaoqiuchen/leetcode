package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 101. 对称二叉树

 给定一个二叉树，检查它是否是镜像对称的。

 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

 1
 / \
 2   2
 / \ / \
 3  4 4  3
 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

 1
 / \
 2   2
 \   \
 3    3

 */
public class E101 {


    public static void main(String[] args) {

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(2);
        n1.left = n2;
        n1.right = n3;

        n2.left = new TreeNode(3);
//        n2.right = new TreeNode(4);

        n3.left = new TreeNode(4);
        n3.right = new TreeNode(3);

        new E101().isSymmetric(n1);
    }

    // 3/1
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        List<TreeNode> line = new ArrayList<>();
        line.add(root.left);
        line.add(root.right);

        while (!line.isEmpty()) {
            int i = 0, l = line.size() - 1;

            List<TreeNode> newLine = new ArrayList<>();
            while (i <= l) {
                TreeNode n1 = line.get(i);
                TreeNode n2 = line.get(l);

                i++;
                l--;
                if (!equals(n1, n2)) {
                    return false;
                }

                if (n1 == null) {
                    continue;
                }

                int length = newLine.size() / 2;
                newLine.add(length++, n1.left);
                newLine.add(length, n2.right);

                newLine.add(length++, n1.right);
                newLine.add(length, n2.left);
            }
            line = newLine;
        }
        return true;
    }

    public boolean equals(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }

        return (n1 != null && n2 != null) && n1.val == n2.val;
    }

}
