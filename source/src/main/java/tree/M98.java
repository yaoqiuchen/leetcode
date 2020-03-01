package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
98.  验证二叉搜索树

 给定一个二叉树，判断其是否是一个有效的二叉搜索树。

 假设一个二叉搜索树具有如下特征：

 节点的左子树只包含小于当前节点的数。
 节点的右子树只包含大于当前节点的数。
 所有左子树和右子树自身必须也是二叉搜索树。
 示例 1:

 输入:
 2
 / \
 1   3
 输出: true
 示例 2:

 输入:
 5
 / \
 1   4
      / \
     3   6
 输出: false
 解释: 输入为: [5,1,4,null,null,3,6]。
      根节点的值为 5 ，但是其右子节点值为 4 。

 */
public class M98 {

    // 3/1
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBST(root.left, root.val, Long.MIN_VALUE)
                && isValidBST(root.right, Long.MAX_VALUE, root.val);
    }

    public boolean isValidBST(TreeNode root, long smaller, long bigger) {
        if (root == null) return true;
        if (root.val >= smaller || root.val <= bigger) {
            return false;
        }

        return isValidBST(root.left, Math.min(smaller, root.val), bigger)
                && isValidBST(root.right, smaller, Math.max(bigger, root.val));
    }

}
