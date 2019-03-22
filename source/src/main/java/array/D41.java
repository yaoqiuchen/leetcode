package array;

import java.util.Arrays;

/**
 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

 示例 1:

 输入: [1,2,0]
 输出: 3
 示例 2:

 输入: [3,4,-1,1]
 输出: 2
 示例 3:

 输入: [7,8,9,11,12]
 输出: 1
 说明:

 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */
public class D41 {

    public static void main(String[] args) {
        new D41().firstMissingPositive(null);
    }

    // 满足条件的解
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            while (val > 0 && val <= nums.length && nums[val-1] != val) {
                int tmp = nums[val-1];
                nums[val-1] = val;
                val = nums[i] = tmp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) return i + 1;
        }

        return nums.length + 1;
    }

    // 由于有Sort存在，时间复杂度超标，不满足O(n)
    public int firstMissingPositive2(int[] nums) {
        if (nums.length == 0) return 1;
        Arrays.sort(nums);
        int compare = 0;
        for (int val : nums) {
            if (val <= 0 || val == compare) continue;
            if (val - 1 != compare) {
                return compare + 1;
            }
            compare++;
        }
        return compare > 0 ? nums[nums.length-1] + 1 : 1;
    }

    // 空间复杂度超标
    // 第一种解法，nums个数字有两种可能，1. 所有数字从1开始连。 2.中间有隔断
    // 循环nums，如果当前数字>nums.length，则nums中一定有中断的数字。
    // 构造boolean[i]表示数字i是否在nums中出现，找到最小的一个boolean[i]=false返回
    public int firstMissingPositive1(int[] nums) {
        if (nums.length == 0) return 1;

        boolean dp[] = new boolean[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                continue;
            }
            dp[nums[i]] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            if (!dp[i]) {
                return i;
            }
        }

        return nums.length + 1;
    }
}
