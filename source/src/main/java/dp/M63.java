package dp;

/**
 * 不同路径 II
 *
 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 */
public class M63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    int path = (i > 0 ? dp[i-1][j] : 0) + (j > 0 ? dp[i][j-1] : 0);
                    dp[i][j] = (path == 0 && i == 0 && j == 0) ? 1 : path;
                }
            }
        }
        return dp[m-1][n-1];
    }


    // 2019-11-27
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                int upPath = (i == 0 ? 0 : dp[i-1][j]);
                int leftPath = (j == 0 ? 0 : dp[i][j-1]);
                dp[i][j] = upPath + leftPath;
            }
        }

        return dp[m-1][n-1];
    }
}
