package dp;

/**
 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

 说明：每次只能向下或者向右移动一步。

 示例:

 输入:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 输出: 7
 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class M64 {

    public static void main(String[] atgs) {
        minPathSum(new int[][] {{1,3,1}, {1,5,1}, {4,2,1}});
    }

    public static  int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                    continue;
                }

                if (i == 0) dp[i][j] = dp[i][j-1];
                else if (j == 0) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]);

                dp[i][j] += grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }

    public int minPathSum_(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length, n = grid[0].length;
        int dp[][] = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int up = i > 0 ? dp[i-1][j] : Integer.MAX_VALUE;
                int left = j > 0 ? dp[i][j-1] : Integer.MAX_VALUE;
                dp[i][j] = Math.min(up, left) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    // 2019-11-27
    public int minPathSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int left = (j == 0) ? Integer.MAX_VALUE : dp[i][j-1];
                int up = (i == 0) ? Integer.MAX_VALUE : dp[i-1][j];

                dp[i][j] = (i == 0 && j == 0) ? 0 : Math.min(left, up);
                dp[i][j] += grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }
}
