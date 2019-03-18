package dp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which
 are 0. What is the largest axis-aligned plus sign of 1s contained in the grid? Return the order of the plus sign.
 If there is none, return 0.

 An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1 going up,
 down, left, and right, and made of 1s. This is demonstrated in the diagrams below. Note that there could be 0s or 1s
 beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1s.

 */
public class M764 {

    int result = 0;
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int dp[][] = new int[N][N];
        Arrays.stream(dp).forEach(e -> Arrays.fill(e, N));
        Arrays.stream(mines).forEach(e -> dp[e[0]][e[1]] = 0);

        for (int i = 0; i < N; i++) {
            for (int j = 0, k = N-1, left = 0, right = 0; j < N; j++, k--) {
                dp[i][j] = Math.min(dp[i][j], left = (dp[i][j] > 0 ? ++left : 0));
                dp[i][k] = Math.min(dp[i][k], right = (dp[i][k] > 0 ? ++right : 0));
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0, k = N-1, up = 0, down = 0; j < N; j++, k--) {
                dp[j][i] = Math.min(dp[j][i], up = (dp[j][i] > 0 ? ++up : 0));
                dp[k][i] = Math.min(dp[k][i], down = (dp[k][i] > 0 ? ++down : 0));
            }
        }
        Arrays.stream(dp).forEach(e -> Arrays.stream(e).forEach(a -> result = Math.max(result, a)));
        return result;
    }
}
