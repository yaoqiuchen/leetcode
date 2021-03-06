package dp;

import array.E35;

/**
 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 示例:

 输入: [-2,1,-3,4,-1,2,1,-5,4],
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 进阶:

 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解
 */
public class E53 {

    public static void main(String[] args) {
        new E53().maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4});
    }

    public int maxSubArray(int[] nums) {
        if (nums.length < 1) return 0;

        // val是截止到上一个数(包含)，最大的连续子数组的和
        int val = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果val小于0，那么就不用累加了
            // 包含i的连续子数组的和最大为nums[i]
            if (val <= 0) {
                val = nums[i];
            } else {
                val += nums[i];
            }
            res = Math.max(res, val);
        }

        return res;
    }



    // a better solution
    public int maxSubArray4(int[] nums) {
        if (nums.length <= 0) return 0;

        int sum = nums[0], max = nums[0];
        int min = nums[0] > 0 ? 0 : nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, Math.max(sum, sum - min));
            min = Math.min(min, sum);
        }

        return max;
    }

    // O(n^2)
    public int maxSubArray2(int[] nums) {
        if (nums.length <= 0) return 0;

        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1] + nums[i];
            for (int j = i - 1; j >= 0; j--) {
                max = Math.max(max, Math.max(dp[i] - dp[j], dp[i]));
            }
        }

        return max;
    }


    // 2019-11-27
    public int maxSubArray3(int[] nums) {
        if (nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1] > 0 ? dp[i-1] + nums[i] : nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
