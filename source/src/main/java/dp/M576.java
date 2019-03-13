package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent
 cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times.
 Find out the number of paths to move the ball out of grid boundary. The answer may be very large,
 return it after mod 109 + 7.

 Example 1:

 Input: m = 2, n = 2, N = 2, i = 0, j = 0
 Output: 6
 Explanation:

 Example 2:

 Input: m = 1, n = 3, N = 3, i = 0, j = 1
 Output: 12
 Explanation:
 */
public class M576 {

    // well I have to confess that it is not a good solution
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N == 0) return 0;

        int dp[][] = new int[m][n];
        dp[i][j] = 1; // the seed data

        int total = 0, mod = 1000000007;
        for (int r = 1; r <= N; r++) {
            int newDp[][] = new int[m][n];
            for (int i1 = 0; i1 < m; i1++) {
                for (int j1 = 0; j1 < n; j1++) {
                    // if there is exit paths, add the count to total
                    int count = dp[i1][j1];
                    total = (total + count * countExitWays(i1, j1, m, n) % mod) % mod;

                    if (r == N) {
                        continue;
                    }

                    // count next steps
                    if (i1 > 0) newDp[i1-1][j1] = (newDp[i1-1][j1] + count) % mod;
                    if (i1 < m - 1) newDp[i1+1][j1] = (newDp[i1+1][j1] + count) % mod;
                    if (j1 > 0) newDp[i1][j1-1] = (newDp[i1][j1-1] + count) % mod;
                    if (j1 < n - 1) newDp[i1][j1+1] = (newDp[i1][j1+1] + count) % mod;
                }
            }
            dp = newDp;
        }
        return total;
    }

    public int countExitWays(int i, int j, int m, int n) {
        return (i == 0 ? 1 : 0) + (i == m -1 ? 1 : 0) + (j == 0 ? 1 : 0) + (j == n - 1 ? 1:0);
    }
}
