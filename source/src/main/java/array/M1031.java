
package array;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 1031 两个非重叠子数组的最大和
  
 给出非负整数数组 A ，返回两个非重叠（连续）子数组中元素的最大和，
 子数组的长度分别为 L 和 M。（这里需要澄清的是，长为 L 的子数组可以出现在长为 M 的子数组之前或之后。）

 从形式上看，返回最大的 V，而 V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) 并满足下列条件之一：

  

 0 <= i < i + L - 1 < j < j + M - 1 < A.length, 或
 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
  

 示例 1：

 输入：A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 输出：20
 解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
 示例 2：

 输入：A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 输出：29
 解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
 示例 3：

 输入：A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 输出：31
 解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
  

 提示：

 L >= 1
 M >= 1
 L + M <= A.length <= 1000
 0 <= A[i] <= 1000

 */
public class M1031 {

    public static void main(String[] args) {
        new M1031().maxSumTwoNoOverlap(new int[] {3,8,1,3,2,1,8,9,0}, 1, 5);
    }

    // 2020-2-18
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[][] dp = new int[A.length][2];
        int n = A.length - 1;
        dp[n][0] = A[n];
        dp[n][1] = A[n];

        // dp[i][0]表示以i开头长度为L的长度
        // dp[i][1]表示以i开头长度为L的长度
        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] += A[i] + dp[i+1][0];
            dp[i][1] += A[i] + dp[i+1][1];
            if (i <= n-L) {
                dp[i][0] -= A[i+L];
            }
            if (i <= n-M) {
                dp[i][1] -= A[i+M];
            }
        }

        // dp2[i][0]表示在i后面最大的L个数字
        // dp2[i][1]表示在i后面最大的M个数字
        int[][] dp2 = new int[A.length][2];
        dp2[n][0] = A[n];
        dp2[n][1] = A[n];
        for (int i = n - 1; i >= 0; i--) {
            if (i <= n-L) {
                dp2[i][0] = Math.max(dp2[i+1][0], dp[i][0]);
            } else {
                dp2[i][0] = dp[i][0];
            }
            if (i <= n-M) {
                dp2[i][1] = Math.max(dp2[i+1][1], dp[i][1]);
            } else {
                dp2[i][1] = dp[i][1];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= n-M-L+1; i++) {
            int count1 = dp[i][0] + dp2[i+L][1];
            int count2 = dp[i][1] + dp2[i+M][0];
            max = Math.max(max, Math.max(count1, count2));
        }

        return max;
    }

}
