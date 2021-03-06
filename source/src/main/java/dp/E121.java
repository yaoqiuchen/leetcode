package dp;

/**
 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

 注意你不能在买入股票前卖出股票。

 示例 1:

 输入: [7,1,5,3,6,4]
 输出: 5
 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 示例 2:

 输入: [7,6,4,3,1]
 输出: 0
 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class E121 {

    public static void main(String[] args) {
        new E121().maxProfit(new int[] {7,1,5,3,6,4});
    }

    // 2/29
    public int maxProfit(int[] prices) {
        int max = 0, n = prices.length;
        if (n <= 1) return 0;

        int[] buy = new int[n];
        int[] sell = new int[n];

        for (int i = 0; i < n; i++) {
            int price = prices[i];
            buy[i] = (i==0) ? -price : Math.max(-price, buy[i-1]);
            sell[i] = (i==0) ? 0 : buy[i-1]+price;
            max = Math.max(sell[i], max);
        }

        return max;
    }


    // 2020-1-21
//    public int maxProfit(int[] prices) {
//        int max = 0, n = prices.length;
//        if (n <= 1) return 0;
//
//        int[] buy = new int[n];
//        int[] sell = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            int price = prices[i];
//            if (i == 0) {
//                buy[i] = -price;
//            } else {
//                buy[i] = Math.max(buy[i-1], -price);
//                sell[i] = Math.max(sell[i-1], buy[i-1] + price);
//            }
//            max = Math.max(max, sell[i]);
//        }
//
//        return max;
//    }


//    public int maxProfit(int[] prices) {
//        if (prices.length == 0) {
//            return 0;
//        }
//        int n = prices.length;
//        int[] buy = new int[n];
//        int[] sell = new int[n];
//
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < n; i++) {
//            buy[i] = (i == 0) ? -prices[i] : Math.max(buy[i-1], -prices[i]);
//            sell[i] = (i == 0) ? 0 : Math.max(sell[i-1], buy[i-1] + prices[i]);
//            max = Math.max(max, sell[i]);
//        }
//        return max;
//    }


    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;

        int max = 0;
        int[] buy = new int[prices.length];
        buy[0] = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - buy[i-1];
            max = Math.max(max, profit);
            buy[i] = Math.min(buy[i-1], prices[i]);
        }

        return max;
    }
}
