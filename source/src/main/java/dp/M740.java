package dp;

import java.util.*;

/**
 Given an array nums of integers, you can perform operations on the array.

 In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element
 equal to nums[i] - 1 or nums[i] + 1.

 You start with 0 points. Return the maximum number of points you can earn by applying such operations.

 Example 1:

 Input: nums = [3, 4, 2]
 Output: 6
 Explanation:
 Delete 4 to earn 4 points, consequently 3 is also deleted.
 Then, delete 2 to earn 2 points. 6 total points are earned.


 Example 2:

 Input: nums = [2, 2, 3, 3, 3, 4]
 Output: 9
 Explanation:
 Delete 3 to earn 3 points, deleting both 2's and the 4.
 Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 9 total points are earned.

 */
public class M740 {

    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int len = Arrays.stream(nums).max().getAsInt();
        int money[] = new int[len + 1];
        int dp[] = new int[len + 1];
        dp[1] = money[1];

        Arrays.stream(nums).forEach(e -> money[e] += e);
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        return dp[len];
    }
}
