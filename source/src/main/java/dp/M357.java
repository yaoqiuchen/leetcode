package dp;

/**
 357. 计算各个位数不同的数字个数

 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。

 示例:

 输入: 2
 输出: 91
 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。

 */
public class M357 {

    public int countNumbersWithUniqueDigits(int n) {
        if (n <= 0) return 1;

        int[] dp = new int[n+1];
        dp[1] = 10;

        for (int i = 2; i <= (n <= 10 ? n : 10); i++) {
            int sum = 9;
            for (int j = i-1, k = 9; j > 0; j--, k--) {
                sum *= k;
            }
            dp[i] = sum + dp[i-1];
        }

        return (n <= 10) ? dp[n] : dp[10];
    }

}

