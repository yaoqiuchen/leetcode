package dp;

import java.util.Arrays;

/**
 322. 零钱兑换

 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 如果没有任何一种硬币组合能组成总金额，返回 -1。

 示例 1:

 输入: coins = [1, 2, 5], amount = 11
 输出: 3
 解释: 11 = 5 + 5 + 1
 示例 2:

 输入: coins = [2], amount = 3
 输出: -1
 说明:
 你可以认为每种硬币的数量是无限的。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/coin-change
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class M322 {

    public static void main(String[] args) {
        coinChange(new int[] {2, 4 ,521}, 6);
    }

    // 笨方法
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin >= i) {
                    dp[i] = (coin == i) ? 1 : dp[i];
                    break;
                }

                if (dp[i-coin] > 0) {
                    dp[i] = (dp[i] == 0) ? dp[i-coin] + 1 : Math.min(dp[i], dp[i-coin] + 1);
                }
            }

            dp[i] = (dp[i] == 0) ? -1 : dp[i];
        }

        return dp[amount];
    }


    // 笨方法
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);

        for (int coin : coins) {
            if (coin <= amount) {
                dp[coin] = 1;
            }
        }

        for (int i = 1; i <= amount; i++) {
            if (dp[i] == 1) {
                continue;
            }

            dp[i] = Integer.MAX_VALUE;
            int min = 1, max = i - 1;
            while (min <= max) {
                if (dp[min] > 0 && dp[max] > 0) {
                    dp[i] = Math.min(dp[i], dp[min] + dp[max]);
                }
                min++;
                max--;
            }
            dp[i] = (dp[i] == Integer.MAX_VALUE) ? -1 : dp[i];
        }

        return dp[amount];
    }
}

