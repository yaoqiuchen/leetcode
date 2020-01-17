package dp;

import java.util.Arrays;

/**
 474. 一和零
 在计算机界中，我们总是追求用有限的资源获取最大的收益。

 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。

 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。

 注意:

 给定 0 和 1 的数量都不会超过 100。
 给定字符串数组的长度不会超过 600。
 示例 1:

 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 输出: 4

 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 示例 2:

 输入: Array = {"10", "0", "1"}, m = 1, n = 1
 输出: 2

 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M474 {

    public static void main(String[] args) {
        findMaxForm(new String[] {"10","0001","111001","1","0"}, 5, 3);
    }

    // 0-1背包问题
    public static int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j]表示，使用i个m和j个n所能达到的最大个数
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int one = 0, zero = 0;
            for (char c : str.toCharArray()) {
                if (c == '1') one++;
                else zero++;
            }

            if (zero > m || one > n) continue;

            for (int m1 = m; m1 >= zero; m1--) {
                for (int n1 = n; n1 >= one; n1--) {
                    dp[m1][n1] = Math.max(dp[m1][n1], dp[m1-zero][n1-one] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
