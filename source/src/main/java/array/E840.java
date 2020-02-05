package array;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 840. 矩阵中的幻方

 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。

 给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。

 示例：

 输入:
 [[4,3,8,4],
 [9,5,1,9],
 [2,7,6,2]]
 输出: 1
 解释:
 下面的子矩阵是一个 3 x 3 的幻方：
 438
 951
 276

 而这一个不是：
 384
 519
 762

 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
 提示:

 1 <= grid.length <= 10
 1 <= grid[0].length <= 10
 0 <= grid[i][j] <= 15
 */
public class E840 {

    public static void main(String[] args) {
        new E840().numMagicSquaresInside(new int[][] {{4,3,8,4},{9,5,1,9},{2,7,6,2}});
    }

    // 2020-2-5
    public int numMagicSquaresInside(int[][] grid) {
        if (grid.length <3 || grid[0].length < 3) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < grid.length-2; i++) {
            boolean lastGood = false;
            for (int j = 0; j < grid[0].length-2; ) {
                int target = grid[i][j] + grid[i+1][j] + grid[i+2][j];
                if (good(grid, i, j, target)) {
                    count++;
                    j++;
                    lastGood = true;
                } else {
                    j = lastGood ? j + 2 : j+1;
                }
            }
        }
        return count;
    }

    public boolean good(int[][] grid, int x, int y, int target) {
        int[] dp = new int[9];
        for (int i = 0; i < 3; i++) {
            if (grid[x+i][y] + grid[x+i][y+1] + grid[x+i][y+2] != target
                    || grid[x+i][y] <= 0 || grid[x+i][y] > 9
                    || grid[x+i][y+1] <= 0 || grid[x+i][y+1] > 9
                    || grid[x+i][y+2] <= 0 || grid[x+i][y+2] > 9) {
                return false;
            }
            if (grid[x][y+i] + grid[x+1][y+i] + grid[x+2][y+i] != target) {
                return false;
            }
            dp[grid[x+i][y]-1] = 1;
            dp[grid[x+i][y+1]-1] = 1;
            dp[grid[x+i][y+2]-1] = 1;
        }
        if (grid[x][y] + grid[x+1][y+1] + grid[x+2][y+2] != target
            || grid[x+2][y] + grid[x+1][y+1] + grid[x][y+2] != target) {
            return false;
        }

        if (Arrays.stream(dp).filter(i -> i == 0).count() > 0) {
            return false;
        }
        return true;
    }

    // 方法笨死了
    public int numMagicSquaresInside_(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m < 3 || n < 3) return 0;

        int count = 0;
        for (int i = 0; i < m-2; i++) {
            for (int j = 0; j < n-2; j++) {
                if (check(grid, i, j))
                    count++;
            }
        }
        return count;
    }

    public boolean check(int[][] grid, int i, int j) {
        int line = grid[i][j] + grid[i][j+1] + grid[i][j+2];
        int[] dp = new int[10];
        for (int c = 0; c < 3; c++) {
            int l = 0, r = 0;
            for (int y = 0; y < 3; y++) {
                if (grid[i+c][j+y] >= 10 || dp[grid[i+c][j+y]] > 0) return false;
                if (grid[i+y][j+c] >= 10) return false;
                dp[grid[i+c][j+y]]++;
                l += grid[i+c][j+y];
                r += grid[i+y][j+c];
            }
            if (l != line || r != line) return false;
        }

        if (line != grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2]
                || line != grid[i+2][j] + grid[i+1][j+1] + grid[i][j+2] ){
            return false;
        }

        long count = Arrays.stream(dp).filter(e-> e == 0).count();
        return count == 0 || (count == 1 && dp[0] == 0);
    }
}
