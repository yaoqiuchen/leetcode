package dp;

import java.util.Arrays;

/**
 516. 最长回文子序列

 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。

 示例 1:
 输入:

 "bbbab"
 输出:

 4
 一个可能的最长回文子序列为 "bbbb"。

 示例 2:
 输入:

 "cbbd"
 输出:

 2
 一个可能的最长回文子序列为 "bb"。

 */
public class M516 {

    public static void main(String[] args) {
        longestPalindromeSubseq("aaaba");
    }

    public static int longestPalindromeSubseq(String s) {
        int size = s.length();
        // dp[i][j]表示在i到j之间，最长的回文子序列
        int[][] dp = new int[size][size];
        for (int i = 0; i < size; i++) {
            dp[i][i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j][i] =dp[j+1][i-1] + 2;
                } else {
                    dp[j][i] = Math.max(dp[j][i-1], dp[j+1][i]);
                }
            }
        }
        return dp[0][size - 1];
    }


    // 错误答案 看成了最长回文子串，而不是子集
    public int longestPalindromeSubseq2(String s) {
        int max = 1, len = s.length();
        if (len <= 1) return len;

        // dp[i][j]表示以从i到j是不是回文子串
        boolean[][] dp = new boolean[len][len];
        dp[0][0] = true;

        for (int i = 1; i < len; i++) {
            dp[i][i] = true;
            for (int j = 0; j < i; j++) {
                if (j == i - 1 || j == i - 2) {
                    dp[j][i] = s.charAt(i) == s.charAt(j);
                } else if (dp[j+1][i-1] && s.charAt(i) == s.charAt(j)) {
                    dp[j][i] = true;
                }

                max = dp[j][i] ? Math.max(max, i-j+1) : max;
            }
        }

        return max;
    }

}
