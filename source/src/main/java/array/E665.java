package array;

/**
 665. 非递减数列
 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。

 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。

 示例 1:

 输入: [4,2,3]
 输出: True
 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 示例 2:

 输入: [4,2,1]
 输出: False
 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 说明:  n 的范围为 [1, 10,000]。
 */
public class E665 {

    public static void main(String[] args) {
        new E665().checkPossibility(new int[] {2,3,3,2,4});
    }

    // 遇到问题两个方案，前一个数字变小，后一个数字变大
    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 0) return true;

        int dp[][] = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= dp[i-1][0]) {
                dp[i][0] = nums[i];
                // 变一个数字，两个情况中取值小的
                // 1. 当前数字， 前一位变过一次后的数字，取大的
                // 2. 直接变成前一个数字
                dp[i][1] = Math.min(Math.max(nums[i], dp[i-1][1]), dp[i-1][0]);
            } else if (nums[i] >= dp[i-1][1]) {
                dp[i][1] = nums[i];
                dp[i][0] = Integer.MAX_VALUE; // 之前已经改过一次了，所以dp[i][0]不可用
            } else {
                if (dp[i-1][0] == Integer.MAX_VALUE) return false;
                dp[i][1] = nums[i-1];
                dp[i][0] = Integer.MAX_VALUE;
            }
        }
        return true;
    }
}
