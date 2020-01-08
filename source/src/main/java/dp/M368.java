package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 368. 最大整除子集

 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，
 子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。

 如果有多个目标子集，返回其中任何一个均可。

 示例 1:

 输入: [1,2,3]
 输出: [1,2] (当然, [1,3] 也正确)
 示例 2:

 输入: [1,2,4,8]
 输出: [1,2,4,8]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/largest-divisible-subset
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M368 {

    public static void main(String[] args) {
        largestDivisibleSubset(new int[] {3,4,16,8});
    }

    // 感觉有点麻烦，应该还有更简单的做法
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        Arrays.sort(nums);

        int[] dp = new int[n]; // dp[i]表示以i结尾的最长结果
        dp[0] = 1;
        int[] prev = new int[n]; // prev[i]表示最长结果的前置元素
        prev[0] = -1;

        int maxIdx = 0, maxNum = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;

                    if (dp[i] > maxNum) {
                        maxNum = dp[i];
                        maxIdx = i;
                    }
                }
            }
        }

        result.add(0, nums[maxIdx]);
        while (prev[maxIdx] >= 0) {
            result.add(0, nums[prev[maxIdx]]);
            maxIdx = prev[maxIdx];
        }
        return result;
    }

}

