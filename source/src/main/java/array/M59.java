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

    public int[][] generateMatrix(int n) {
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
