package array;

import java.util.LinkedList;
import java.util.List;

/**
 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

 示例:

 输入: 3
 输出:
 [
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
 ]
 */
public class M59 {

    public static void main(String[] args) {
        new M59().generateMatrix(4);
    }

    // 2020-1-18
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        int direction = 1;
        int[] pos = new int[] {0, 0};
        boolean[][] dp = new boolean[n][n];
        for (int i = 1; i <= n*n; i++) {
            // dp标记已经访问过
            res[pos[0]][pos[1]] = i;
            dp[pos[0]][pos[1]] = true;

            // 计算下一个地址，如果已经访问过，或者越界，那么重新计算
            int[] nextPos = nextPosition(pos, direction);
            if (shouldRedirect(nextPos, dp, n)) {
                direction = (direction == 4) ? 1 : direction+1;
                nextPos = nextPosition(pos, direction);
            }
            pos = nextPos;
        }

        return res;
    }

    public boolean shouldRedirect(int[] nextPos, boolean[][] dp, int n) {
        return nextPos[0] < 0 || nextPos[0] >= n ||
                nextPos[1] < 0 || nextPos[1] >= n ||
                dp[nextPos[0]][nextPos[1]];
    }

    public int[] nextPosition(int[] pos, int direction) {
        switch (direction) {
            case 1: return new int[] {pos[0], pos[1]+1};
            case 2: return new int[] {pos[0]+1, pos[1]};
            case 3: return new int[] {pos[0], pos[1]-1};
            case 4: return new int[] {pos[0]-1, pos[1]};
        }
        return new int[] {};
    }

    public int[][] generateMatrix2(int n) {
        int res[][] = new int[n][n];

        int x = 0, y = 0; // 起始坐标
        int count = 1, direction = 0;  // direction : 0=right, 1=down, 2=left, 3=up
        int dp[] = new int[4];
        while (count <= n * n) {
            res[x][y] = count;
            int next[] = getNextPosition(x, y, direction);
            if (outside(next[0], next[1], n, direction, dp)) {
                dp[direction]++;
                direction = (direction == 3) ? 0 : direction + 1;
                next = getNextPosition(x, y, direction);
            }
            x = next[0];
            y = next[1];
            count++;
        }
        return res;
    }

    public boolean outside(int x, int y, int n, int direction, int dp[]) {
        if (x < 0 || y < 0 || x > n-1 || y > n-1) return true;
        switch (direction) {
            case 0 : return y > n - dp[1] - 1;
            case 1 : return x > n - dp[2] - 1;
            case 2 : return y < dp[3];
            case 3 : return x < dp[0];
        }
        return false;
    }

    public int[] getNextPosition(int x, int y, int direction) {
        if (direction == 0) y++;
        else if (direction == 1) x++;
        else if (direction == 2) y--;
        else if (direction == 3) x--;
        return new int[] {x, y};
    }

}
