package array;

import java.util.LinkedList;
import java.util.Queue;

/**
 695. 岛屿的最大面积

 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。

 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

 示例 1:

 [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。

 示例 2:

 [[0,0,0,0,0,0,0,0]]
 对于上面这个给定的矩阵, 返回 0。

 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 */
public class M695 {

    public static void main(String[] args) {
        new M695().maxAreaOfIsland(new int[][] {{0,1}});
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int max = Integer.MIN_VALUE;

        boolean[][] dp = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = count(grid, dp, i, j);
                max = Math.max(max, count);
            }
        }
        return max;
    }

    public int count(int[][] grid, boolean[][] dp, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length
                || dp[x][y] || grid[x][y] == 0) {
            return 0;
        }

        int sum = 1;
        dp[x][y] = true;
        sum += count(grid, dp, x-1, y);
        sum += count(grid, dp, x, y+1);
        sum += count(grid, dp, x+1, y);
        sum += count(grid, dp, x, y-1);
        return sum;
    }


    public int maxAreaOfIsland_(int[][] grid) {
        int max = 0, m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || grid[i][j] == 0) {
                    continue;
                }

                queue.add(i);
                queue.add(j);
                visited[i][j] =true;
                int sum  = visit(visited, grid, queue);
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    public int visit(boolean[][] visited, int[][] grid, Queue<Integer> queue) {
        int max = 1;
        while (!queue.isEmpty()) {
            int i = queue.poll(), j = queue.poll();
            if (i > 0 && !visited[i-1][j] && grid[i-1][j] == 1) {
                max++;
                queue.add(i-1);
                queue.add(j);
                visited[i-1][j] = true;
            }

            if (i < grid.length - 1 && !visited[i+1][j] && grid[i+1][j] == 1) {
                max++;
                queue.add(i+1);
                queue.add(j);
                visited[i+1][j] = true;
            }

            if (j > 0 && !visited[i][j-1] && grid[i][j-1] == 1) {
                max++;
                queue.add(i);
                queue.add(j-1);
                visited[i][j-1] = true;
            }

            if (j < grid[0].length -1 && !visited[i][j+1] && grid[i][j+1] == 1) {
                max++;
                queue.add(i);
                queue.add(j+1);
                visited[i][j+1] = true;
            }
        }
        return max;
    }
}
