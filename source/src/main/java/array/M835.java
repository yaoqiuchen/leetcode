package array;

import java.util.Arrays;

/**
 835. 图像重叠

 给出两个图像 A 和 B ，A 和 B 为大小相同的二维正方形矩阵。（并且为二进制矩阵，只包含0和1）。

 我们转换其中一个图像，向左，右，上，或下滑动任何数量的单位，并把它放在另一个图像的上面。之后，该转换的重叠是指两个图像都具有 1 的位置的数目。

 （请注意，转换不包括向任何方向旋转。）

 最大可能的重叠是什么？

 示例 1:

 输入：A =
 [[1,1,0],
 [0,1,0],
 [0,1,0]]
 B =
 [[0,0,0],
 [0,1,1],
 [0,0,1]]
 输出：3
 解释: 将 A 向右移动一个单位，然后向下移动一个单位。
 注意:

 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 0 <= A[i][j], B[i][j] <= 1
 */
public class M835 {

    // 2020-205
    public int largestOverlap(int[][] A, int[][] B) {
        int n = A.length;
        // i是横坐标偏移量，j是纵坐标偏移量
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = calculate(A, B, i, j);
                int count2 = calculate(B, A, i, j);
                max = Math.max(max, Math.max(count, count2));
            }
        }
        return max;
    }

    public int calculate(int[][] A, int[][] N, int x, int y) {
        int count = 0;
        for (int i = x; i < A.length; i++) {
            for (int j = y; j < A.length; j++) {
                if (A[i][j] == 1 && N[i-x][j-y] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    // 不用真正的移动数字，只要设置起始的搜索点，就相当于移动了数字
    // 比如从A[1][0]开始搜索，相当于向上移动一次
    public int largestOverlap_(int[][] A, int[][] B) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                int count1 = check(A, B, i, j);
                // A和B交换顺序，相当于逆向调整
                int count2 = check(B, A, i, j);
                max = Math.max(max, Math.max(count1, count2));
            }
        }
        return max;
    }

    private int check(int[][] A, int[][] B, int r, int l) {
        int count = 0;
        for (int i = r, br = 0; i < A.length; i++, br++) {
            for (int j = l, bl = 0; j < A[0].length; j++, bl++) {
                if (A[i][j] == 1 && A[i][j] == B[br][bl]) {
                    count++;
                }
            }
        }
        return count;
    }

}
