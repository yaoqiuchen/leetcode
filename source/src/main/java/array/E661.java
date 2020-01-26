package array;

import java.util.Arrays;

/**
 661. 图片平滑器
 包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，
 平均灰度的计算是周围的8个单
 元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。

 示例 1:

 输入:
 [[1,1,1],
 [1,0,1],
 [1,1,1]]
 输出:
 [[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
 解释:
 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 注意:

 给定矩阵中的整数范围为 [0, 255]。
 矩阵的长和宽的范围均为 [1, 150]。
 */
public class E661 {

    public static void main(String[] args) {
        new E661().imageSmoother(new int[][] { {1,1,1}, {1,0,1},{1,1,1} });
    }


    // 2020-1-26
    // 向量加法反而比动态规划效率更高
    public int[][] imageSmoother(int[][] M) {
        if (M.length == 0) return M;
        int[][] dir = new int[][] {{-1,-1},{-1,0},{-1,1},{0,-1},{0,0},{0,1},{1,-1},{1,0},{1,1}};

        int m = M.length, n = M[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0, total = 0;
                for (int[] node : dir) {
                    int x = i+node[0], y = j+node[1];
                    if (x<0 || y<0 || x>=m || y>=n) continue;
                    total += M[x][y];
                    count++;
                }
                res[i][j] = total/count;
            }
        }
        return res;
    }




    // 动态规划
    public int[][] imageSmoother_(int[][] M) {
        int m = M.length, n = M[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // dp[i][j] = M[i][j] + dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1]
                dp[i][j] = M[i][j] + (j>0?dp[i][j-1]:0) + (i>0?dp[i-1][j]:0) - (i>0 && j>0 ? dp[i-1][j-1] : 0);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x1 = 1 + (i>0 ? 1:0) + (i < m-1 ? 1: 0);
                int y1 = 1 + (j>0 ? 1:0) + (j < n-1? 1: 0);
                // M[i][j] = dp[i+1][j+1] - dp[i-2][j+1] - dp[i+1][j-2] + dp[i-2][j-2j;
                int sum = dp[Math.min(i+1, m-1)][Math.min(j+1, n-1)];
                sum -= (i<=1 ? 0 : dp[i-2][Math.min(n-1, j+1)]);
                sum -= (j<=1 ? 0 : dp[Math.min(m-1, i+1)][j-2]);
                sum += (i > 1 && j > 1 ? dp[i-2][j-2]: 0);
                M[i][j] = sum / (x1 * y1);
            }
        }
        return M;
    }
}
