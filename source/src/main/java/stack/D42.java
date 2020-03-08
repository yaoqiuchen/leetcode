package stack;

import java.util.PriorityQueue;

/**
 * 42. 接雨水
 *
 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。



 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
 感谢 Marcos 贡献此图。

 示例:


 */
public class D42 {

    public static void main(String[] args) {
        new D42().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1});
    }

    // 3/8
    public int trap(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        // 求每一列的水
        // 左边和右边最高的墙，取最小值x，那么当前列能存的水就是x-height[i]
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

}
