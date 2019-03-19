package dp;

/**
 801. Minimum Swaps To Make Sequences Increasing

 We have two integer sequences A and B of the same non-zero length.

 We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their
 respective sequences.

 At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and
 only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

 Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that
 the given input always makes it possible.

 Example:
 Input: A = [1,3,5,4], B = [1,2,3,7]
 Output: 1
 Explanation:
 Swap A[3] and B[3].  Then the sequences are:
 A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 which are both strictly increasing.
 Note:

 A, B are arrays with the same length, and that length will be in the range [1, 1000].
 A[i], B[i] are integer values in the range [0, 2000].
 */
public class M801 {

    public int minSwap(int[] A, int[] B) {
        if (A.length <= 1) return 0;
        int len = A.length;
        int dp[][] = new int[len][2];
        dp[0][0] = 0; // not swap
        dp[0][1] = 1; // swap

        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i][1] = Integer.MAX_VALUE;

            if (A[i] > A[i-1] && B[i] > B[i-1]) {
                dp[i][0] = dp[i-1][0];
                dp[i][1] = dp[i-1][1] + 1;
            }

            if (A[i] > B[i-1] && B[i] > A[i-1]) {
                dp[i][1] = Math.min(dp[i][1], dp[i-1][0] + 1);
                dp[i][0] = Math.min(dp[i][0], dp[i-1][1]);
            }
        }

        return Math.min(dp[len-1][0], dp[len-1][1]);
    }
}
