package array;

import java.util.Arrays;

/**
 * 矩阵置零
 *
 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

 示例 1:

 输入:
 [
 [1,1,1],
 [1,0,1],
 [1,1,1]
 ]
 输出:
 [
 [1,0,1],
 [0,0,0],
 [1,0,1]
 ]
 示例 2:

 输入:
 [
 [0,1,2,0],
 [3,4,5,2],
 [1,3,1,5]
 ]
 输出:
 [
 [0,0,0,0],
 [0,4,5,0],
 [0,3,1,0]
 ]
 进阶:

 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 你能想出一个常数空间的解决方案吗？

 */
public class M73 {

    public static void main(String[] args) {
        new M73().setZeroes(new int[][] {{0,1,1},{1,1,1},{1,1,1} });
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0) return;

        boolean firstLineZero = false;
        boolean firstRowZero = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0 && j == 0) {
                        firstLineZero = true;
                        firstRowZero = true;
                        continue;
                    }
                    if (i == 0) firstLineZero = true;
                    if (j == 0) firstRowZero = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 重置第一行
        if (firstLineZero) {
            Arrays.fill(matrix[0], 0);
        }
        if (firstRowZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }







    // no additional spaces
    public void setZeroes_(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length, n = matrix[0].length;

        // matrix 数组每行第一个数字表示该行状态，第一行表示列状态
        boolean firstLineZero = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        firstLineZero = true;
                    } else {
                        matrix[i][0] = 0;
                    }
                    matrix[0][j] = 0;
                }
            }
        }

        // reset each line exception first line
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // reset each row
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        // reset first line
        if (firstLineZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    // Bad solution: O(m+n) space
//    public void setZeroes(int[][] matrix) {
//
//        if (matrix.length == 0 || matrix[0].length == 0) return;
//        int m = matrix.length, n = matrix[0].length;
//
//        boolean line[] = new boolean[m];
//        boolean row[] = new boolean[n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (matrix[i][j] == 0) {
//                    line[i] = true;
//                    row[j] = true;
//                }
//            }
//        }
//
//        for (int i = 0; i < m; i++) {
//            if (line[i]) {
//                for (int j = 0; j < n; j++) {
//                    matrix[i][j] = 0;
//                }
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            if (row[i]) {
//                for (int j = 0; j < m; j++) {
//                    matrix[j][i] = 0;
//                }
//            }
//        }
//    }
}
