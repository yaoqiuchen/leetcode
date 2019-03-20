package dp;

import java.util.Arrays;

/**
 847. 访问所有节点的最短路径

 有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。

 每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。

 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。



 示例 1：

 输入：stones = [3,2,4,1], K = 2
 输出：20
 解释：
 从 [3, 2, 4, 1] 开始。
 合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
 合并 [4, 1]，成本为 5，剩下 [5, 5]。
 合并 [5, 5]，成本为 10，剩下 [10]。
 总成本 20，这是可能的最小值。
 示例 2：

 输入：stones = [3,2,4,1], K = 3
 输出：-1
 解释：任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。.
 示例 3：

 输入：stones = [3,5,1,2,6], K = 3
 输出：25
 解释：
 从 [3, 5, 1, 2, 6] 开始。
 合并 [5, 1, 2]，成本为 8，剩下 [3, 8, 6]。
 合并 [3, 8, 6]，成本为 17，剩下 [17]。
 总成本 25，这是可能的最小值。


 提示：

 1 <= stones.length <= 30
 2 <= K <= 30
 1 <= stones[i] <= 100
 */
public class D {

    public int mergeStones(int[] stones, int K) {
        // every move will remove K-1 stones, len % (K-1) is the number of stones after last move.
        int n = stones.length;
        if (K > n || (K > 2 && n != K && n % (K-1) != 1)) return -1;

        int f[][][] = new int[n][n][2];


        int sum[] = new int[n];
        sum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            sum[i] = stones[i] + sum[i-1];
        }

        for (int len=2;len <=n;++len) {
            for (int l = 1; l + len - 1 <= n; ++l) {
                int r = l + len - 1;
                for (int k = l; k < r; ++k) {
                    for (int i = 2; i <= len; ++i) {
                        f[l][r][i] = Math.min(f[l][r][i], f[l][k][i - 1] + f[k + 1][r][1]);
                    }
                }
                f[l][r][1] = Math.min(f[l][r][K] + sum[r] - sum[l - 1], f[l][r][1]);
            }
        }

        return f[0][n-1][1];
    }
}
