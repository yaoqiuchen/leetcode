package array;

import java.util.Arrays;

/**
 *
 * 918. 环形子数组的最大和
 *
 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。

 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，
 而当 i >= 0 时 C[i+A.length] = C[i]）

 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，
 不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）

  

 示例 1：

 输入：[1,-2,3,-2]
 输出：3
 解释：从子数组 [3] 得到最大和 3
 示例 2：

 输入：[5,-3,5]
 输出：10
 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 示例 3：

 输入：[3,-1,2,-1]
 输出：4
 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 示例 4：

 输入：[3,-2,2,-3]
 输出：3
 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 示例 5：

 输入：[-2,-3,-1]
 输出：-1
 解释：从子数组 [-1] 得到最大和 -1
  

 提示：

 -30000 <= A[i] <= 30000
 1 <= A.length <= 30000

 */
public class M918 {

    public static void main(String[] args) {
        new M918().maxSubarraySumCircular(new int[] {-1,-2,-3});
    }

    // 2020-2-9
    // 最大和在中间，最小和在中间
    public int maxSubarraySumCircular(int[] A) {

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, total = 0, n = A.length;
        for (int i = 0, sum1 = 0, sum2 = 0; i < n; i++) {
            sum1 += A[i];
            // 这是数组在中间的情况，统计中间区域最大值
            max = Math.max(max, sum1);
            // 如果sum<0那么就丢弃了
            sum1 = sum1 < 0 ? 0 : sum1;

            // 对于数组在两头的情况，那么只要统计当前中间这一段的最小值
            total += A[i];
            sum2 += A[i];
            min = Math.min(min, sum2);
            sum2 = sum2 > 0 ? 0 : sum2;
        }

        if (min == total) return max;
        // 如果不是环形，那么最终结果就是i开头
        return Math.max(max, total - min);
    }

}
