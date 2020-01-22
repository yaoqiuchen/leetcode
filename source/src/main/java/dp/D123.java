package dp;

/**
 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 示例 1:

 输入: [3,3,5,0,0,3,1,4]
 输出: 6
 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 示例 2:

 输入: [1,2,3,4,5]
 输出: 4
 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 示例 3:

 输入: [7,6,4,3,1]
 输出: 0
 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */
public class D123 {

    public static void main(String[] args) {
        new D123().maxProfit(new int[] {1,4,2,7});
    }


    // 2020-1-20
    public int maxProfit(int[] prices) {
        if (prices.length <=1) return 0;

        int n = prices.length;
        // 第二个参数代表是第一次buy，还是第二次buy
        int[][] buy = new int[n][2];
        int[][] sell = new int[n][2];

        int max = 0;
        for (int i = 0; i < n; i++) {
            int price = prices[i];
            if (i == 0) {
                buy[0][0] = -price;
                sell[0][0] = -price;
                continue;
            }
            // 第一次购买，最大的价格是上一次买，或者这次买
            buy[i][0] = Math.max(buy[i-1][0], -price);
            // 第二次购买，最大价格是上次第二回买，或者上回第一次卖-当次买
            buy[i][1] = (i < 2) ? Integer.MIN_VALUE :
                    Math.max((i == 2 ? Integer.MIN_VALUE : buy[i-1][1]), sell[i-1][0] - price);

            sell[i][0] = Math.max(sell[i-1][0], buy[i-1][0]+price);
            sell[i][1] = (i<3) ? Integer.MIN_VALUE :
                    Math.max((i==3 ? Integer.MIN_VALUE : sell[i-1][1]), buy[i-1][1]+price);

            max = Math.max(max, Math.max(sell[i][0], sell[i][1]));
        }

        return max;
    }



//
//
//    public int maxProfit(int[] prices) {
//        int n = prices.length;
//        if (n == 0) {
//            return 0;
//        }
//
//        int[][] buy = new int[n][3];
//        int[][] sell = new int[n][3];
//
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < n; i++) {
//            // 截止前一天只买一次 （如果i>0，前一天只买一次 vs 当天买）else
//            buy[i][1] = (i > 0) ? Math.max(buy[i-1][1], -prices[i]) : -prices[i];
//            // 截止前一天买2次
//            buy[i][2] = (i >= 2) ? Math.max(buy[i-1][2], sell[i-1][1] - prices[i]) : Integer.MIN_VALUE;
//
//            sell[i][1] = (i > 0) ? Math.max(sell[i-1][1], buy[i-1][1] + prices[i]) : 0;
//            sell[i][2] = (i > 2) ? Math.max(sell[i-1][2], buy[i-1][2] + prices[i]) : 0;
//
//            max = Math.max(max, Math.max(sell[i][1], sell[i][2]));
//        }
//
//        return max;
//    }







    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;

        int buy[][] = new int[prices.length][2];
        int sell[][] = new int[prices.length][2];

        buy[0][0] = -prices[0];
        sell[0][0] = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {
            // 只买/卖一次
            sell[i][0] = Math.max(sell[i-1][0], buy[i-1][0] + prices[i]);
            buy[i][0] = Math.max(buy[i-1][0], -prices[0]);
            max = Math.max(max, sell[i][0]);

            if (i >= 2) {

                // 买/卖两次
                if (i == 2) {
                    // 初始化第二次买 (此时还不能第二次卖)
                    buy[i][1] = sell[i-1][0] - prices[i];
                }
                else {
                    // 初始化第二次卖
                    if (i == 3) {
                        sell[i][1] = buy[i-1][1] + prices[i];
                    } else {
                        sell[i][1] = Math.max(sell[i-1][1], buy[i-1][1] + prices[i]);
                    }
                    buy[i][1] = Math.max(buy[i-1][1], sell[i-1][0] - prices[i]);
                }
            }
            max = Math.max(max, sell[i][1]);
        }

        return max < 0 ? 0 : max;
    }
}
