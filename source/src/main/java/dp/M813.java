package dp;

import java.util.Arrays;

/**
 We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?

 Note that our partition must use every number in A, and that scores are not necessarily integers.

 Example:
 Input:
 A = [9,1,2,3,9]
 K = 3
 Output: 20
 Explanation:
 The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.


 Note:

 1 <= A.length <= 100.
 1 <= A[i] <= 10000.
 1 <= K <= A.length.
 Answers within 10^-6 of the correct answer will be accepted as correct.

 */
public class M813 {
    public double largestSumOfAverages(int[] A, int K) {
        double dp[][] = new double[A.length + 1][K+1];
        dp[1][1] = A[0];

        double sum[] = new double[A.length+1];
        sum[1] = A[0];

        for (int i = 2; i <= A.length; i++) {
            double val = A[i-1];
            sum[i] = sum[i-1] + val;
            dp[i][1] = new Double(sum[i]) / i;

            for (int j = 2; j <= K && j <= i; j++) {
                for (int y = j-1; y <= i-1; y++) {
                    dp[i][j] = Math.max(dp[i][j], dp[y][j-1] + (sum[i] - sum[y]) / (i-y));
                }
            }
        }
        return dp[A.length][K];
    }

    // timeout
//    public double partition(int[] A, int start, int K) {
//        double total = 0;
//        if (K == 1) {
//            for (int i = start; i < A.length; i++) {
//                total += A[i];
//            }
//            return total / (A.length - start);
//        }
//
//        total = A[start] + partition(A, start + 1, K-1);
//        int sum = A[start];
//        for (int i = start + 1, count = 2; i < A.length; i++, count++) {
//            if (A.length - i < K) {
//                break;
//            }
//            sum += A[i];
//            total = Math.max(total,  new Double(sum) / new Double(count) + partition(A, i + 1, K-1));
//        }
//
//        return total;
//    }
}
