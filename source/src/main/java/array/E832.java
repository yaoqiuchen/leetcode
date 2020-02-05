package array;

import java.util.ArrayList;
import java.util.List;

/**
 832. 翻转图像

 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。

 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。

 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。

 示例 1:

 输入: [[1,1,0],[1,0,1],[0,0,0]]
 输出: [[1,0,0],[0,1,0],[1,1,1]]
 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 示例 2:

 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 说明:

 1 <= A.length = A[0].length <= 20
 0 <= A[i][j] <= 1
 */
public class E832 {

    public static void main(String[] args) {
        new E832().flipAndInvertImage(new int[][] {{1,0,0,1}});
    }

    // 2020-2-5
    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] line : A) {
            int[] copy = line.clone();
            for (int i = 0, n = line.length-1; i < line.length; i++) {
                line[i] = copy[n-i] == 0 ? 1 : 0;
            }
        }
        return A;
    }

    public int[][] flipAndInvertImage_(int[][] A) {
        int len = A[0].length;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j <= len/2; j++) {
                int tmp = A[i][j] == 0 ? 1 : 0;
                A[i][j] = A[i][len-j-1] == 0 ? 1 : 0;
                A[i][len-j-1] = tmp;
            }
            if (len % 2 == 1) {
                A[i][len/2] = A[i][len/2] == 0 ? 1 : 0;
            }
        }
        return A;
    }
}
