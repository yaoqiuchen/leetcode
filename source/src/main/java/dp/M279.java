package dp;

/**
 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

 示例 1:

 输入: n = 12
 输出: 3
 解释: 12 = 4 + 4 + 4.
 示例 2:

 输入: n = 13
 输出: 2
 解释: 13 = 4 + 9.

 来源：力扣（LeetCode）
 */
public class M279 {

    public static void main(String[] ar) {
        numSquares(4);
    }

    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        int num = 1;
        for (int i = 1; i <= n; i++) {
            if (i == num * num) {
                dp[i] = 1;
                num++;
                continue;
            }

            dp[i] = Integer.MAX_VALUE;
            int min = 1, max = i - 1;
            while (min <= max) {
                dp[i] = Math.min(dp[i], dp[min] + dp[max]);
                // 这一步能节省一点时间
                if (dp[i] == 2) break;
                min++;
                max--;
            }
        }
        return dp[n];
    }

}
