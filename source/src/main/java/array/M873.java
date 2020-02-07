package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 873. 最长的斐波那契子序列的长度
 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：

 n >= 3
 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。

 （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是
 [3, 4, 5, 6, 7, 8] 的一个子序列）

 示例 1：

 输入: [1,2,3,4,5,6,7,8]
 输出: 5
 解释:
 最长的斐波那契式子序列为：[1,2,3,5,8] 。
 示例 2：

 输入: [1,3,7,11,12,14,18]
 输出: 3
 解释:
 最长的斐波那契式子序列有：
 [1,11,12]，[3,11,14] 以及 [7,11,18] 。


 提示：

 3 <= A.length <= 1000
 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 （对于以 Java，C，C++，以及 C# 的提交，时间限制被减少了 50%）
 */
public class M873 {

    public static void main(String[] args) {
        new M873().lenLongestFibSubseq(new int[] {2,4,10,14,15,18,23,32,50});
    }


    // 2020-2-7
    public int lenLongestFibSubseq(int[] A) {
        int max = 0, n = A.length;
        // dp[i][j]表示，上一个元素是i，这个元素是j，它的数列长度
        int[][] dp = new int[n][n];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(A[i], i);
        }

        for (int i = 2; i < A.length; i++) {
            for (int j = i-1; j > 0; j--) {
                int gap = A[i] - A[j];
                Integer idx = map.get(gap);
                if (idx == null || idx > j) {
                    continue;
                }
                dp[j][i] = dp[idx][j] == 0 ? 3 : dp[idx][j] + 1;
                max = Math.max(max, dp[j][i]);
            }
        }

        return max;
    }


    public int lenLongestFibSubseq_(int[] A) {
        int max = 0, l = A.length;
        int[][] dp = new int[l][l];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < l; i++) {
            map.put(A[i], i);
        }

        for (int i = 2; i < l; i++) {
            int val = A[i];
            for (int j = 1; j < i; j++) {
                int preVal = A[j], gap = val - preVal;
                Integer idx = map.get(gap);
                if (idx != null && idx < j) {
                    int count  = dp[idx][j];
                    dp[j][i] = count == 0 ? 3 : count + 1;
                }
                max = Math.max(max, dp[j][i]);
            }
        }
        return max;
    }

    // 超时了
    public int lenLongestFibSubseq2(int[] A) {
        int max = 0;

        int[][] dp = new int[A.length][A.length];
        dp[1][0] = 2;

        for (int i = 2; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    dp[i][0] = 2;
                    continue;
                }

                int gap = A[i] - A[j];
                int l = 0, r = j-1;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if (A[mid] == gap) {
                        l = mid;
                        break;
                    } else if (A[mid] > gap) r--;
                    else l++;
                }
                dp[i][j] = A[l] == gap ? dp[j][l] + 1 : 2;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max == 2 ? 0 : max;
    }

}
