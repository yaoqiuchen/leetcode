package array;

/**
 718. 最长重复子数组

 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

 示例 1:

 输入:
 A: [1,2,3,2,1]
 B: [3,2,1,4,7]
 输出: 3
 解释:
 长度最长的公共子数组是 [3, 2, 1]。
 说明:

 1 <= len(A), len(B) <= 1000
 0 <= A[i], B[i] < 100

 */
public class M718 {

    // 2020-1-31
    public int findLength(int[] A, int[] B) {
        int max = 0;
        // dp[i][j]表示以A[i]和B[j]结尾的字符串max长度
        int[][] dp = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] != B[j]) {
                    continue;
                }
                dp[i][j] = (i == 0 || j == 0) ? 1 : dp[i-1][j-1]+1;
                max = Math.max(dp[i][j], max);
            }
        }

        return max;
    }


    public int findLength_(int[] A, int[] B) {
        int max = 0;
        int dp[][] = new int[A.length][B.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] != B[j]) {
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j], (i > 0 && j > 0 ? dp[i-1][j-1] + 1: 1) );
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

}
