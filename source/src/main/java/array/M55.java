package array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 给定一个非负整数数组，你最初位于数组的第一个位置。

 数组中的每个元素代表你在该位置可以跳跃的最大长度。

 判断你是否能够到达最后一个位置。

 示例 1:

 输入: [2,3,1,1,4]
 输出: true
 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 示例 2:

 输入: [3,2,1,0,4]
 输出: false
 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class M55 {

    public static void main(String[] args) {
        new M55().canJump(new int[] {1,1,1,0});
    }

    // 2020-1-18
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) return true;
        int n = nums.length - 1;
        int last = n;

        // 从倒数第二个下标开始
        for (int i = n; i >= 0; i--) {
            int next = nums[i] + i;
            if (next >= last) {
                last = i;
            }
        }

        return last == 0;
    }




    // 较优解法，O(n)，存储空间=1
    public boolean canJump3(int[] nums) {
        if (nums.length <= 1) return true;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max < i) return false;
            max = Math.max(max, nums[i] + i);
            if (max >= nums.length - 1) return true;
        }
        return max >= nums.length - 1;
    }

    // 动态规划，消耗额外O(n)存储空间
    public boolean canJump2(int[] nums) {
        if (nums.length <= 1) return true;
        boolean dp[] = new boolean[nums.length];
        dp[0] = true;

        for (int i = 0; i < nums.length-1; i++) {
            // 从开头到终点的所有点都应该是可达的，否则中断
            if (!dp[i]) {
                return false;
            }
            int steps = nums[i];
            for (int j = steps; j > 0; j--) {
                int next = i + j;
                if (next >= nums.length - 1) {
                    return true;
                }
                dp[next] = true;
            }
        }
        return dp[nums.length-1];
    }
}
