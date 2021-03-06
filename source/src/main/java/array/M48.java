package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 给定一个 n × n 的二维矩阵表示一个图像。

 将图像顺时针旋转 90 度。

 说明：

 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

 示例 1:

 给定 matrix =
 [
 [1,2,3],
 [4,5,6],
 [7,8,9]
 ],

 原地旋转输入矩阵，使其变为:
 [
 [7,4,1],
 [8,5,2],
 [9,6,3]
 ]
 示例 2:

 给定 matrix =
 [
 [ 5, 1, 9,11],
 [ 2, 4, 8,10],
 [13, 3, 6, 7],
 [15,14,12,16]
 ],

 原地旋转输入矩阵，使其变为:
 [
 [15,13, 2, 5],
 [14, 3, 4, 1],
 [12, 6, 8, 9],
 [16, 7,10,11]
 ]
 */
public class M48 {

    public static void main(String[] args) {
        new M48().rotate(new int[][] {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}});
    }


    public void rotate(int[][] matrix) {
        int times = matrix.length / 2;
        int n = matrix.length - 1;
        // i表示总共要从外到内做几轮旋转
        for (int i=0, len=matrix.length; i < times; i++) {
            // j表示每一排要变换的数字个数，每次变换都有4个数字
            // 要变动的数字个数是边长-1
            for (int j = 0; j < len-2*i-1; j++) {
                int up = matrix[i][i+j];
                int right = matrix[i+j][n-i];
                int bottom = matrix[n-i][n-j-i];
                int left = matrix[n-j-i][i];

                matrix[i][i+j] = left;
                matrix[i+j][n-i] = up;
                matrix[n-i][n-j-i] = right;
                matrix[n-j-i][i] = bottom;
            }
        }
    }






    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        int round = n / 2;

        for (int i = 0; i < round; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int z1 = matrix[i][j];
                int z2 = matrix[j][n-i-1];
                int z3 = matrix[n-i-1][n-j-1];
                int z4 = matrix[n-j-1][i];

                int tmp = z1;
                matrix[i][j] = z4;
                matrix[n-j-1][i] = z3;
                matrix[n-i-1][n-j-1] = z2;
                matrix[j][n-i-1] = tmp;
            }
        }
    }
}
