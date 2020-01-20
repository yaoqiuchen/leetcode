package array;

import java.util.Arrays;
import java.util.Stack;

/**
 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class D84 {

    public static void main(String[] args) {
        new D84().largestRectangleArea(new int[] {1,2,4,9,9,8,9,10, 10});
    }

    public int largestRectangleArea(int[] heights) {
        if (heights.length <= 0) return 0;

        int n = heights.length, max = heights[0];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i <= n; i++) {
            int curHeight = i == n ? -1 : heights[i];

            while (!stack.isEmpty() && curHeight <= heights[stack.peek()]) {
                int val = heights[stack.pop()];
                int len = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, val * len);
            }
            stack.push(i);

            // 1. 保证stack上方的元素永远是整个栈当中最大的！！！
            // 2. 每次循环把所有大于当前数字的元素从Stack里通通弹出，那么stack中剩余元素都一定是小于当前元素的
            // 3. 所有弹出的数字是依次递减的
            //
            // 计算面积，就可以转化为，当前弹出的元素value * 距离len，对于一个弹出的元素来说，
            // 从它在stack里上一个元素到当前heights[i]之间的距离就是len，而在这段距离当中，heights[i]是最小的
            //
        }
        return max;
    }



    public int largestRectangleArea_(int[] heights) {
        if (heights.length <= 0) return 0;

        int n = heights.length, max = heights[0];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i <= n; i++) {
            int curHeight = i == n ? -1 : heights[i];

            while (!stack.isEmpty() && curHeight <= heights[stack.peek()]) {
                int lowerHeight = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, width * lowerHeight);
            }
            stack.push(i);
        }
        return max;
    }

    // right answer but timeout
    public int largestRectangleArea2(int[] heights) {
        if (heights.length <= 0) return 0;

        int n = heights.length;
        int dp[][] = new int[heights.length][heights.length];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp[i][i] = heights[i];
            for (int j = i + 1; j < n; j++) {
                    int factor = Math.min(dp[i][j-1], heights[j]);
                    dp[i][j] = factor;
                    max = Math.max(max, (j-i + 1) * factor);
            }
            max = Math.max(dp[i][i], max);
        }
        return max;
    }

}
