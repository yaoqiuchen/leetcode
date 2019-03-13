package dp;

import java.util.HashMap;
import java.util.Map;

/**
 Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous
 subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

 Example 1:
 Input: [23, 2, 4, 6, 7],  k=6
 Output: True
 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

 Example 2:
 Input: [23, 2, 6, 4, 7],  k=6
 Output: True
 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 */
public class M523 {

    /**
     * Pay attention to some edge cases like k=0
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> dp = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // it will cover the case with k=0
            if (nums[i] == 0 && (i>0 && nums[i-1] == 0)) {
                return true;
            }

            if (k != 0) {
                sum += nums[i];
                int balance = sum % k;
                if (balance == 0 && i >= 1) {
                    return true;
                }
                if (dp.containsKey(balance) && i - dp.get(balance) >= 2) {
                    return true;
                }
                dp.putIfAbsent(balance, i);
            }
        }
        return false;
    }
}
