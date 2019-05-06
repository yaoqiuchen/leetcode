package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 769. 最多能完成排序的块

 数组arr是[0, 1, ..., arr.length - 1]的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。

 我们最多能将数组分成多少块？

 示例 1:

 输入: arr = [4,3,2,1,0]
 输出: 1
 解释:
 将数组分成2块或者更多块，都无法得到所需的结果。
 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
 示例 2:

 输入: arr = [1,0,2,3,4]
 输出: 4
 解释:
 我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
 然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
 注意:

 arr 的长度在 [1, 10] 之间。
 arr[i]是 [0, 1, ..., arr.length - 1]的一种排列。
 */
public class M769 {

    public static void main(String[] args) {
        new M769().maxChunksToSorted(new int[] {1,4,0,2,3,5});
    }

    // dp[i][0]表示下标i之处最大组数，dp[i][1]表示next下标处
    // 构造动态转移方程即可
    // 如果arr[i] == i，当i>next的时候，组数+1
    // 如果arr[i] > i, 比较i和next的位置，如果i>next, 组数+1， dp[i][0] = dp[i-1][0] + 1
    // 如果arr[i] < i, 比较i和dp[arr[i]-1][1]的位置，如果i大, 组数+1， dp[i][0] = dp[i-1][0] + 1
    public int maxChunksToSorted(int[] arr) {

        int dp[][] = new int[arr.length][2];
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i) {
                dp[i][0] = i <= next ? (i>0?dp[i-1][0]:1) : dp[i-1][0] + 1;
                dp[i][1] = next;
                continue;
            };

            if (arr[i] > i) {
                if (i <= next) {
                    dp[i][0] = i == 0 ? 1 : dp[i-1][0];
                } else {
                    dp[i][0] = dp[i-1][0] + 1;
                }
                next = Math.max(next, arr[i]);
                dp[i][1] = next;
            }

            if (arr[i] < i) {
                dp[i][0] = (arr[i] == 0) ? 1 : (dp[arr[i]-1][1] >= arr[i] ? dp[arr[i]-1][0] : dp[arr[i]-1][0] + 1);
                dp[i][1] = next;
            }
        }

        return dp[arr.length-1][0];
    }
}
