package dp;

/**
 304. 二维区域和检索 - 矩阵不可变

 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。


 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。

 示例:

 给定 matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 sumRegion(1, 1, 2, 2) -> 11
 sumRegion(1, 2, 2, 4) -> 12
 说明:

 你可以假设矩阵不可变。
 会多次调用 sumRegion 方法。
 你可以假设 row1 ≤ row2 且 col1 ≤ col2。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class M304 {

    public static void main(String[] ar) {
//        lengthOfLIS(new int[] {});
    }

    int[][] dp = null;

    public M304(int[][] matrix) {
        if (matrix.length == 0) return;

        int m = matrix.length, n = matrix[0].length;
        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];

                if (i == 0 && j == 0) {
                    dp[i][j] = val;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = dp[i][j-1] + val;
                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j] + val;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + val;
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2][col2]
                // 减上面的
                - (row1 == 0 ? 0 : dp[row1-1][col2])
                // 减去左面的
                - (col1 == 0 ? 0 : dp[row2][col1-1])
                // 加上重复减掉的
                + (col1 == 0 || row1 == 0 ? 0 : dp[row1-1][col1-1]);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
