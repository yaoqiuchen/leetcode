package dp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

 示例:

 输入: 3
 输出: 5
 解释:
 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3

 */
public class M96 {
    
    public int numTrees(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] dp = new int[n];
        return buildTree2(1, n, dp);
    }

    // 3-1-20
    public static int buildTree2(int start, int end, int[] dp) {
        if (start > end) {
            return 1;
        }

        if (dp[end-start] > 0) {
            return dp[end-start];
        }

        int result = 0;
        for (int i = start; i <= end; i++) {
            int left = buildTree2(start, i-1, dp);
            int right = buildTree2(i+1, end, dp);
            result += left * right;
        }
        dp[end-start] = result;
        return result;
    }






    public static int buildTree(int start, int end, int[] dp) {
        if (start > end) {
            return 1;
        }

        int gap = end - start;
        if (dp[gap] > 0) {
            return dp[gap];
        }

        int result = 0;
        for (int i = start; i <= end; i++) {
            int left = buildTree(start, i-1, dp);
            int right = buildTree(i+1, end, dp);
            result += left * right;
        }
        dp[gap] = result;
        return result;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
