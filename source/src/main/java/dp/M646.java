package dp;

import java.util.Arrays;

/**
 You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

 Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

 Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

 Example 1:
 Input: [[1,2], [2,3], [3,4]]
 Output: 2
 Explanation: The longest chain is [1,2] -> [3,4]

 */
public class M646 {

    public int findLongestChain(int[][] pairs) {
        if (pairs.length <= 1) return pairs.length;

        int res = 0;
        Arrays.sort(pairs, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int dp[] = new int[pairs.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < pairs.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = Math.max(dp[i], pairs[j][1] < pairs[i][0] ? dp[j] + 1 : 1);
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
