package dp;

import java.util.Arrays;

/**
 494. 目标和

 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。

 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

 示例 1:

 输入: nums: [1, 1, 1, 1, 1], S: 3
 输出: 5
 解释:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 一共有5种方法让最终目标和为3。
 注意:

 数组非空，且长度不会超过20。
 初始的数组的和不会超过1000。
 保证返回的最终结果能被32位整数存下。

 */
public class M494 {

    public static void main(String[] args) {
        findTargetSumWays(new int[] {1, 1,1}, 1);
    }

    // 原问题等同于：找到nums一个正子集和一个负子集，使得总和等于target。
    // 找到nums的一个子集P，使得sum(P) = (target + sum(nums))/2

    /**
     *      * <p>
     * sum(P) 前面符号为+的集合；sum(N) 前面符号为减号的集合
     * 所以题目可以转化为
     * sum(P) - sum(N) = target
     * sum(nums) = sum(P) + sum(N)
     * => 2 * sum(P) = target + sum(nums)
     * => sum(P) = (target + sum(nums)) / 2
     * 因此题目转化为01背包，也就是能组合成容量为sum(P)的方式有多少种
     */
    public static int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int num : nums)
            sum += num;
        if(sum < S || (S + sum) % 2 != 0)
            return 0;
        return subsetSum(nums, (S + sum) / 2);
    }

    public static int subsetSum(int[] nums, int s) {  //以s这个数为和的子集有多少个
        int[] dp = new int[s + 1];
        dp[0] = 1;  //以0为和的子集有一个，是空集
        for (int num : nums)
            for (int i = s; i >= num; i--)
                dp[i] += dp[i - num];
        return dp[s];
    }

    // 递归解法，时间复杂度高
    public static int findTargetSumWays2(int[] nums, int S) {
        return findTargetSumWays(nums, 0, 0, S);
    }

    public static int findTargetSumWays(int[] nums, int start, int count, int target) {
        if (target == 0 && (start == nums.length)) {
            // 如果target = 0 并且走到了最后一个节点，则返回count+1
            return count + 1;
        }

        if (start >= nums.length) return 0;

        return findTargetSumWays(nums, start + 1, count, target - nums[start]) +
                findTargetSumWays(nums, start + 1, count, target + nums[start]);
    }
}
