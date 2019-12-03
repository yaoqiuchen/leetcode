package array;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。

 示例:

 输入: 3
 输出:
 [
   [1,null,3,2],
   [3,2,null,1],
   [3,1,null,null,2],
   [2,1,3],
   [1,null,2,null,3]
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by 半仙.
 */
public class M95 {

    public static void main(String[] args) {
        generateTrees(3);
    }

    public static List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        return buildTree(1, n);
    }

    public static List<TreeNode> buildTree(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = buildTree(start, i-1);
            List<TreeNode> right = buildTree(i+1, end);
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    node.right = rightNode;
                    result.add(node);
                }
            }
        }

        return result;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
