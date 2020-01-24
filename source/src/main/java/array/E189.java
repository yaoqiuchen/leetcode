package array;

import java.util.Arrays;

/**
 189. 旋转数组

 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

 示例 1:

 输入: [1,2,3,4,5,6,7] 和 k = 3
 输出: [5,6,7,1,2,3,4]
 解释:
 向右旋转 1 步: [7,1,2,3,4,5,6]
 向右旋转 2 步: [6,7,1,2,3,4,5]
 向右旋转 3 步: [5,6,7,1,2,3,4]
 示例 2:

 输入: [-1,-100,3,99] 和 k = 2
 输出: [3,99,-1,-100]
 解释:
 向右旋转 1 步: [99,-1,-100,3]
 向右旋转 2 步: [3,99,-1,-100]
 说明:

 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 要求使用空间复杂度为 O(1) 的原地算法。
 */
public class E189 {

    public static void main(String[] args) {
        new E189().rotate(new int[] {1,2,3,4,5,6}, 2);
    }

    // 2020-1-24
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1 || k % n == 0) return;

        // dp[i]表示i的数字已经是正确数字了
        boolean[] dp = new boolean[n];
        int step = k % n, tmp = nums[0];
        for (int i = 0; i < n; i++) {
            if (dp[i]) continue;

            int next = (i + step) % n;
            tmp = nums[i];
            // 一旦下一个数字是正确数字，那么后面的数字也一定被移动好了，直接中断即可
            while (!dp[next]) {
                int _tmp = nums[next];
                nums[next] = tmp;
                tmp = _tmp;

                dp[next] = true;
                next = (next + step) % n;
            }
        }
    }







    // 性能不行
    public void rotate_(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return;
        k = k % nums.length;
        if (k == 0) return;

        for (int i = 0; i < k; i++) {
            int tmp = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j-1];
            }
            nums[0] = tmp;
        }
    }

}
