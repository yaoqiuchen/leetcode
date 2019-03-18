package dp;

import java.util.Arrays;

/**
 Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

 Example 1:
 Input:
 A: [1,2,3,2,1]
 B: [3,2,1,4,7]
 Output: 3
 Explanation:
 The repeated subarray with maximum length is [3, 2, 1].

 */
public class M718 {
    public int findLength(int[] A, int[] B) {

        int res = 0;
        int dp[][] = new int[A.length][B.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] != B[j]) continue;

                dp[i][j] = Math.max(dp[i][j], (i > 0 && j > 0) ? dp[i-1][j-1] + 1: 1);
                res = Math.max(dp[i][j], res);
            }
        }

        return res;
    }

}
