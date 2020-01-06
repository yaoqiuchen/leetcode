package dp;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 309. 最佳买卖股票时机含冷冻期

 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 示例:

 输入: [1,2,3,0,2]
 输出: 3
 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

 */
public class M309 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        int[] freeze = new int[n];

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int price = prices[i];

            // 最后一步操作是买
            // 上一次买 vs 上次冷冻-price
            buy[i] = (i == 0) ? -price : Math.max(buy[i-1], freeze[i-1] - price);
            // 当天这一步操作是sell
            sell[i] = (i == 0) ? 0 : buy[i-1] + price;
            // 最后一步操作是Freeze
            freeze[i] = (i == 0) ? 0 : Math.max(freeze[i-1], sell[i-1]);

            // 注意，前面buy和freeze都是指最后一步操作，但是sell是指当天的操作
            max = Math.max(max, Math.max(sell[i], freeze[i]));
        }
        return Math.max(0, max);
    }
}

