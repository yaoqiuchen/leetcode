package array;

import java.util.Arrays;

/**
 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

 必须原地修改，只允许使用额外常数空间。

 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */
public class M31 {


    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) return;

        for (int i = nums.length - 2; i >= 0; i--) {
            // re-sort it
            if (nums[i] < nums[i+1]) {
                int replace = nums[i+1];
                int index = i+1;

                // find the smallest value that larger than num[i]
                for (int j = i+2; j < nums.length; j++) {
                    if (nums[j] > nums[i] && nums[j] < replace) {
                        replace = nums[j];
                        index = j;
                    }
                }
                int tmp = nums[i];
                nums[i] = nums[index];
                nums[index] = tmp;
                Arrays.sort(nums, i+1, nums.length);
                return;
            }
        }

        Arrays.sort(nums, 0, nums.length);
    }
}
