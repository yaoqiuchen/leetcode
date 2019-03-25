package array;

import java.util.LinkedList;
import java.util.List;

/**
 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

 示例 1:

 输入:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 输出: [1,2,3,6,9,8,7,4,5]
 示例 2:

 输入:
 [
 [1, 2, 3, 4],
 [5, 6, 7, 8],
 [9,10,11,12]
 ]
 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class M54 {

    public static void main(String[] args) {
        new M54().spiralOrder(new int[][] {{1,2,3}, {4,5,6}, {7,8,9}});
    }

    // 使用常熟存储空间
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix.length == 0) return res;

        int dp[] = new int[4];
        int count = 0, direction = 0, m = matrix.length, n = matrix[0].length;
        int pos[] = new int[] {0, 0};
        while (count < m * n) {
            res.add(matrix[pos[0]][pos[1]]);
            int[] newPos = nextPosition(pos, direction);
            if (shouldChangeDirection(newPos, m, n, direction, dp)) {
                dp[direction]++;
                direction = (direction == 3) ? 0 : direction+1;
                newPos = nextPosition(pos, direction);
            }
            pos = newPos;
            count++;
        }

        return res;
    }

    public int[] nextPosition(int pos[], int direction) {
        int res[] = new int[2];
        if (direction == 0) return new int[] {pos[0], pos[1]+1};
        if (direction == 1) return new int[] {pos[0] + 1, pos[1]};
        if (direction == 2) return new int[] {pos[0], pos[1]-1};
        if (direction == 3) return new int[] {pos[0]-1, pos[1]};
        return res;
    }

    public boolean shouldChangeDirection(int pos[], int m, int n, int direction, int dp[]) {
        if (direction == 0) return pos[1] > n - dp[1] - 1;
        if (direction == 1) return pos[0] > m - dp[2] - 1;
        if (direction == 2) return pos[1] < dp[3];
        if (direction == 3) return pos[0] < dp[0];
        return false;
    }


    // 额外使用了m * n的空间，差评
//    public List<Integer> spiralOrder2(int[][] matrix) {
//        List<Integer> res = new LinkedList<>();
//        if (matrix.length == 0) return res;
//
//        int m = matrix.length, n = matrix[0].length;
//        boolean dp[][] = new boolean[m][n];
//        int count = 0, direction = 1;
//        int x = 0, y = 0;
//        while (count < m * n) {
//            dp[x][y] = true;
//            res.add(matrix[x][y]);
//            int pos[] = nextPosition(x, y, direction);
//            if (noWay(pos[0], pos[1], dp)) {
//                direction = (direction < 4) ? direction + 1 : 1;
//                pos = nextPosition(x, y, direction);
//            }
//            x = pos[0];
//            y = pos[1];
//
//            count++;
//        }
//        return res;
//    }
//
//    public int[] nextPosition(int x, int y, int direction) {
//        int [] pos = new int[2];
//        if (direction == 1 || direction == 3) {
//            pos[0] = x;
//            pos[1] = (direction == 1) ? y + 1: y - 1;
//        } else {
//            pos[1] = y;
//            pos[0] = (direction == 2) ? x + 1 : x - 1;
//        }
//        return pos;
//    }
//
//    public boolean noWay(int x, int y, boolean dp[][]) {
//        return x < 0 || y < 0 || x > dp.length - 1 || y > dp[0].length - 1 || dp[x][y];
//    }
}
