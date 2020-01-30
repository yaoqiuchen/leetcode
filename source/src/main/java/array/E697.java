package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 697. 数组的度

 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。

 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

 示例 1:

 输入: [1, 2, 2, 3, 1]
 输出: 2
 解释:
 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 连续子数组里面拥有相同度的有如下所示:
 [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 最短连续子数组[2, 2]的长度为2，所以返回2.
 示例 2:

 输入: [1,2,2,3,1,4,2]
 输出: 6
 注意:

 nums.length 在1到50,000区间范围内。
 nums[i] 是一个在0到49,999范围内的整数。

 */
public class E697 {

    public static void main(String[] args) {
        new E697().findShortestSubArray(new int[] {1,2,2,3,1,4,2});
    }


    // 2020-1-30
    public int findShortestSubArray(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        int max = 0, len = Integer.MAX_VALUE;
        // dp[i][0]表示数字i出现的次数
        // dp[i][1]表示第一次出现时候的位置
        int[][] dp = new int[50000][2];
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            if (dp[val][0] == 0) {
                dp[val][1] = i;
            }
            dp[val][0]++;

            if (max < dp[val][0]) {
                max = dp[val][0];
                len = i-dp[val][1]+1;
            } else if (max == dp[val][0]) {
                len = Math.min(len, i-dp[val][1]+1);
            }
        }

        return len;
    }


    // 空间负责度高，想法比较简单省事
    public int findShortestSubArray_(int[] nums) {
        int max = 1;

        int[][] dp = new int[50000][3];
        // dp[i][0] 是数字i的个数
        // dp[i][1] 是数字i第一次出现的下标
        // dp[i][2] 是数字i最后一次出现的下标
        dp[nums[0]][0] = 1;
        dp[nums[0]][1] = 0;
        dp[nums[0]][2] = 0;
        for (int i = 1; i < nums.length; i++) {
            if (dp[nums[i]][0] == 0) {
                dp[nums[i]][1] = i;
            }
            dp[nums[i]][2] = i;
            dp[nums[i]][0]++;
            max = Math.max(max, dp[nums[i]][0]);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 50000; i++) {
            if (dp[i][0] == max) {
                res = Math.min(res, dp[i][2] - dp[i][1] + 1);
            }
        }

        return res;
    }
}
