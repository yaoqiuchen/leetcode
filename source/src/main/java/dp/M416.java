package dp;

import java.util.Arrays;

/**
 413. 分割等和子集

 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

 注意:

 每个数组中的元素不会超过 100
 数组的大小不会超过 200
 示例 1:

 输入: [1, 5, 11, 5]

 输出: true

 解释: 数组可以分割成 [1, 5, 5] 和 [11].
  

 示例 2:

 输入: [1, 2, 3, 5]

 输出: false

 解释: 数组不能分割成两个元素和相等的子集.

 */
public class M416 {


    public static void main(String[] args) {
        canPartition(new int[] {2,2,3,5});
    }

    public static boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 > 0) {
            return false;
        }

        int target = sum / 2;
        // 最大的一个数字比target还大，肯定不能满足
        if (target < nums[nums.length - 1]) {
            return false;
        }

        int[] dp = new int[sum];
        return canPartition2(0, nums.length - 1, nums, target, dp);
    }

    public static boolean canPartition2(int start, int end, int[] nums, int target, int[] dp) {
        if (target == 0) {
            return true;
        }
        // 最小的数字都比target大，没救了
        if (start > end || nums[start] > target) {
            return false;
        }

        if (dp[target] != 0) {
            return dp[target] > 0;
        }

        boolean success =
                canPartition2(start+1, end, nums, target - nums[start], dp) ||
                canPartition2(start+1, end, nums, target, dp);
        dp[target] = success ? 1 : -1;
        return success;
    }


    // 超出时间限制
    public static boolean canPartition2(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 > 0) {
            return false;
        }

        int target = sum / 2;
        // 最大的一个数字比target还大，肯定不能满足
        if (target < nums[nums.length - 1]) {
            return false;
        }

        return canPartition(0, nums.length - 1, nums, target);
    }

    public static boolean canPartition(int start, int end, int[] nums, int target) {
        if (target == 0) {
            return true;
        }
        // 最小的数字都比target大，没救了
        if (start > end || nums[start] > target) {
            return false;
        }

        while (start <= end) {
            if (canPartition(start+1, end, nums, target - nums[start])) {
                return true;
            }
            start++;
        }
        return false;
    }

}
