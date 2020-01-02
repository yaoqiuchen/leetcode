package dp;

/**
 编写一个程序，找出第 n 个丑数。

 丑数就是只包含质因数 2, 3, 5 的正整数。

 示例:

 输入: n = 10
 输出: 12
 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 说明:  

 1 是丑数。
 n 不超过1690。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/ugly-number-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M264 {

    public static int nthUglyNumber(int n) {
        if (n <= 1) {
            return 1;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        int idx2 = 0, idx3 = 0, idx5 = 0;

        int i = 1;
        while (i < n) {
            dp[i] = Math.min(dp[idx2] * 2, Math.min(dp[idx3] * 3, dp[idx5] * 5));
            if (dp[idx2] * 2 == dp[i]) idx2++;
            if (dp[idx3] * 3 == dp[i]) idx3++;
            if (dp[idx5] * 5 == dp[i]) idx5++;
            i++;
        }

        return dp[n-1];
    }

}
