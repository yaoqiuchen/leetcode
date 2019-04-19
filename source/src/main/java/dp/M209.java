package dp;

import java.util.*;

/**
 209. 长度最小的子数组

 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

 示例:

 输入: s = 7, nums = [2,3,1,2,4,3]
 输出: 2
 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 进阶:

 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class M209 {

    public static void main(String[] args) {
        new M209().minSubArrayLen(7, new int[] {1,1});
    }

    // 二刷解法
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;

        int l = 0, h = 0, count = nums[0], min = Integer.MAX_VALUE;
        while (l <= h && h < nums.length) {
            if (count >= s) {
                min = Math.min(min, h - l + 1);
                count -= nums[l];
                l++;
            } else {
                h++;
                count += (h < nums.length) ? nums[h] : 0;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // 半年前的解法
    public static int minSubArrayLen2(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        int gaps = s;
        int size = 0;

        for (int i=0; i<nums.length; i++) {
            int val = nums[i];
            if (val >= s) {
                return 1;
            }

            gaps -= val;
            queue.add(val);

            while (gaps < 0 && !queue.isEmpty()) {
                size = size < queue.size() && size != 0 ? size : queue.size();

                int removeIt = queue.poll();
                gaps += removeIt;
            }

            if (gaps <= 0) {
                size = size < queue.size() && size != 0 ? size : queue.size();
            }
        }
        return size;
    }
}
