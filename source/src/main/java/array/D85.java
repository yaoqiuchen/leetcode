package array;

import java.util.Stack;

/**
 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 输入:
 [
 ["1","0","1","0","0"],
 ["1","1","1","1","1"],
 ["1","0","1","1","1"],
 ["1","0","0","1","0"]
 ]
 输出: 6

 1 2 3 x x 4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximal-rectangle
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class D85 {

    public static void main(String[] args) {
        new D85().maximalRectangle(new char[][] {{'1','1','1','0'},
                {'1','0','1','1'}});
    }

    // 3-2-20
    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        if (matrix == null || matrix.length == 0) {
            return max;
        }

        int[] dp = new int[matrix[0].length];
        for (char[] line : matrix) {
            for (int i = 0; i < line.length; i++) {
                if (line[i] == '1') {
                    dp[i]++;
                } else {
                    dp[i] = 0;
                }
            }

            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            int area = dp[0];
            for (int i = 1; i <= dp.length; i++) {
                int height = i == dp.length ? -1 : dp[i];

                // 单调递减栈
                while (!stack.isEmpty() && dp[stack.peek()] >= height) {
                    int idx = stack.pop();
                    int h = dp[idx];
                    int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                    max = Math.max(max, h * width);
                }
                stack.push(i);
            }
        }
        return max;
    }


    // 2020-1-20
    public int maximalRectangle2(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int m = matrix.length, n = matrix[0].length, max = 0;
        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            int[] _dp = new int[n];
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    _dp[j] = dp[j] + 1;
                }
            }
            max = Math.max(max, calculate(_dp));
            dp = _dp;
        }
        return max;
    }

    public int calculate(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int area = height[0];
        for (int i = 1; i <= height.length; i++) {
            int val = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && val <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                area = Math.max(area, h * width);
            }
            stack.push(i);
        }

        return area;
    }




    public int maximalRectangle_(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length, n = matrix[0].length, max = Integer.MIN_VALUE;
        int height[] = new int[n];
        for (int i = 0; i < m; i++) {
            int newHeight[] = new int[n];
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    newHeight[j] = height[j] + 1;
                }
            }
            max = Math.max(max, calculateArea(newHeight));
            height = newHeight;
        }
        return max;
    }

    public int calculateArea(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= height.length; i++) {
            int curheight = i == height.length ? -1 : height[i];
            while (!stack.isEmpty() && curheight <= height[stack.peek()]) {
                int stackHeight = height[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                res = Math.max(res, stackHeight * width);
            }
            stack.push(i);
        }
        return res;
    }

}
