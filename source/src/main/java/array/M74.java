package array;

/**
 * 搜索二维矩阵
 *
 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

 每行中的整数从左到右按升序排列。
 每行的第一个整数大于前一行的最后一个整数。
 示例 1:

 输入:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 3
 输出: true
 示例 2:

 输入:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 13
 输出: false

 */
public class M74 {

    public static void main(String[] args) {
        new M74().searchMatrix(new int[][] {{1,1} }, 2);
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0 || matrix[0].length == 0) return false;

        int n = matrix[0].length;
        int l = 0, h = m*n-1;

        while (l <= h) {
            int mid = (l + h)/2;
            int x = mid / n;
            int y = mid % n;
            int val = matrix[x][y];
            if (target == val) {
                return true;
            }

            if (target < val) h = mid - 1;
            else l = mid + 1;
        }

        return false;
    }



    public boolean searchMatrix_(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;

        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int val = getVal(matrix, mid);
            if (target < val) {
                high = mid - 1;
            } else if (target > val) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public int getVal(int[][] matrix, int idx) {
        int x = idx / matrix[0].length;
        int y = idx % matrix[0].length;
        return matrix[x][y];
    }
}
