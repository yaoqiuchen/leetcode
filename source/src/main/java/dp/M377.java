package dp;

/**
 377. 组合总数

 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

 示例:

 nums = [1, 2, 3]
 target = 4

 所有可能的组合为：
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)

 请注意，顺序不同的序列被视作不同的组合。

 因此输出为 7。
 进阶：
 如果给定的数组中含有负数会怎么样？
 问题会产生什么变化？
 我们需要在题目中添加什么限制来允许负数的出现？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/combination-sum-iv
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class M377 {

//    public static void main(String[] args) {
//        wiggleMaxLength(new int[] {0, 0});
//    }

    // dp[i]表示到i为止，所有组合的数量
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= 0) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++)
            for (int j = 0; j < nums.length; j++)
                if (i >= nums[j]) dp[i] += dp[i - nums[j]];
        return dp[target];
    }
}

