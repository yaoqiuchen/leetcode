package dp;

/**
 * 乘积最大子序列
 *
 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

 示例 1:

 输入: [2,3,-2,4]
 输出: 6
 解释: 子数组 [2,3] 有最大乘积 6。
 示例 2:

 输入: [-2,0,-1]
 输出: 0
 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class M152 {

    public static void main(String[] args) {
        new M152().maxProduct2(new int[] {2,-5,-2,-4,3});
    }

    public int maxProduct2(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];

        int max = nums[0], min = nums[0], result = nums[0];
        for (int i = 1; i < len; i++) {
            int val = nums[i], tmp = max;
            if (nums[i] > 0) {
                max = Math.max(val, max * val);
                min = Math.min(val, min * val);
            } else if (val < 0) {
                max = Math.max(val, min * val);
                min = Math.min(val, tmp * nums[i]);
            } else {
                max = 0;
                min = 0;
            }
            result = Math.max(result, max);
        }

        return result;
    }



    // 以前的解法
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int min = 1, max = 1, res = Integer.MIN_VALUE;
        for (int val : nums) {
            // 如果是负数，最大的变成最小的，最小变最大
            if (val < 0) {
                int tmp = max;
                max = min;
                min = tmp;
            }
            max = Math.max(val, max * val);
            min = Math.min(val, min * val);
            res = Math.max(max, min);
        }
        return res;
    }

}
