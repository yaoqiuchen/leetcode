package dp;

/**
 Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

 Example 1:
 Input: s1 = "sea", s2 = "eat"
 Output: 231
 Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 Deleting "t" from "eat" adds 116 to the sum.
 At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.

 Example 2:
 Input: s1 = "delete", s2 = "leet"
 Output: 403
 Explanation: Deleting "dee" from "delete" to turn the string into "let",
 adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
 At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 */
public class M714 {
    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) return 0;
        int len = prices.length;

        // buy/sell[i] is the highest price when the last action is buy/sell at day i
        int buy[] = new int[len];
        int sell[] = new int[len];
        buy[0] = - prices[0];

        for (int i = 1; i < prices.length; i++) {
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i] - fee);
            buy[i] = Math.max(sell[i-1] - prices[i], buy[i-1]);
        }

        return sell[prices.length - 1];
    }

}
