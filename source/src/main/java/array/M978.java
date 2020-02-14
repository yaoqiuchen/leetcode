package array;

import java.util.Arrays;

/**
 *
 * 978 最长湍流子数组
 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：

 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。

 返回 A 的最大湍流子数组的长度。

  

 示例 1：

 输入：[9,4,2,10,7,8,8,1,9]
 输出：5
 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 示例 2：

 输入：[4,8,12,16]
 输出：2
 示例 3：

 输入：[100]
 输出：1
  

 提示：

 1 <= A.length <= 40000
 0 <= A[i] <= 10^9

 */
public class M978 {

    public static void main(String[] args) {
        new M978().maxTurbulenceSize(new int[] {9,4,2,10,7,8,8,1,9});
    }

    // 2020-2-13
    public int maxTurbulenceSize(int[] A) {
        if (A.length <= 1) {
            return A.length;
        }

        // dp1表示当前数字是奇数，
        // dp2表示当前数字是偶数
        // dp[i][0]表示下一个数字比我小，1表示下一个个数字比我大
        int[][] dp1 = new int[A.length][2];
        int[][] dp2 = new int[A.length][2];
        int res = 1;
        for (int i = 0; i < A.length - 1; i++) {
            dp1[i] = new int[]{1, 1};
            dp2[i] = new int[]{1, 1};
            if (A[i] == A[i+1]) {
                continue;
            }

            // 当前数字是偶数
            if (i % 2 == 0 || i == 0) {
                // 偶数大 则上一个奇数小
                if (A[i] > A[i+1]) {
                    dp2[i][0] = (i == 0) ? 2 : (dp1[i-1][1] + 1);
                    res = Math.max(res, dp2[i][0]);
                } else if (A[i] < A[i+1]) {
                    dp2[i][1] = (i == 0) ? 2 : (dp1[i-1][0] + 1);
                    res = Math.max(res, dp2[i][1]);
                }
            }
            // 当前数字是奇数
            else {
                if (A[i] > A[i+1]) {
                    dp1[i][0] = (i == 0) ? 2 : (dp2[i-1][1] + 1);
                    res = Math.max(res, dp1[i][0]);
                } else if (A[i] < A[i+1]) {
                    dp1[i][1] = (i == 0) ? 2 : (dp2[i-1][0] + 1);
                    res = Math.max(res, dp1[i][1]);
                }
            }
        }

        return res;
    }

}
