package dp;

/**
 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

 示例:

 输入:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0

 输出: 4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximal-square
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M221 {


    public static void main(String[] args) {
        maximalSquare(new char[][] {

                {'1','0','1','0','0'}

                ,{'1','0','1','1','1'}
                ,{'1','1','1','1','1'},{'1','0','0','1','0'}
        });
    }

    public static int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length ==0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    int squre = Math.min(dp[i-1][j-1],
                            Math.min(dp[i][j-1], dp[i-1][j]));
                    dp[i][j] = (squre == 0) ? 1 : squre + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max * max;
    }



}
