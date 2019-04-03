package array;

import java.util.Stack;

/**
 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class D85 {

//    public static void main(String[] args) {
//        new D85().maximalRectangle(new char[][] {{'1','1'}});
//    }

    public int maximalRectangle(char[][] matrix) {
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
