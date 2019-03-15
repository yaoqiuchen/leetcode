package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Given an unsorted array of integers, find the number of longest increasing subsequence.

 Example 1:
 Input: [1,3,5,4,7]
 Output: 2
 Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].

 Example 2:
 Input: [2,2,2,2,2]
 Output: 5
 Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1,
 so output 5.
 Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */
public class M673 {

    public int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;

        int res = 1, longest = 1;
        // dp[i] is the longest increasing subsequence that ends with nums[i]
        int dp[] = new int[nums.length];
        // count[i] is the occurrence of the longest sequence ends with nums[i]
        int count[] = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        for (int i = 1; i < nums.length; i++) {
            int maxLen = 1;
            for (int j=i-1; j>=0; j--) {
                if (nums[i] > nums[j] && dp[j] + 1 >= maxLen) {
                    if (dp[j] + 1 == maxLen) {
                        count[i] += count[j];
                    } else {
                        count[i] = count[j];
                        maxLen = dp[j] + 1;
                    }
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > longest) {
                longest = dp[i];
                res = count[i];
            } else if (dp[i] == longest) {
                res += count[i];
            }
        }
        return res;
    }
}
