package dp;

/**
 * Created by 半仙.
 */
public class M70 {

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i] = (i - 1 >= 0 ? dp[i-1] : 0)
                    + (i - 2 >= 0 ? dp[i-2] : 0);
        }

        return dp[n];
    }

}
