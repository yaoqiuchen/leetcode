package array;

import java.util.Arrays;

/**
 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

 必须原地修改，只允许使用额外常数空间。

 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,4,5,2 → 1,5,1
 1,2,1,5
 1, 1, 6, 2
 */
public class M31 {

    public static void main(String[] arg) {

    }

    public static void nextPermutation(int[] nums) {
        if (nums.length <= 1) return;

        // 问题转化为，寻找紧邻的[a,b]，且a<b.
        // 这样就表示，数组肯定可以被重排序
        for (int i = nums.length-1; i>0; i--) {

            // 前一个数字小于后一个数字
            if (nums[i] > nums[i-1]) {
                // 从后面找得到大于num[i-1]的最小数字
                // 然后将其和num[i-1]对调，完成后对i-1后所有数字重排序
                int min = nums[i], minIdx = i;
                for (int j = i+1; j<nums.length; j++) {
                    if (nums[j] > nums[i-1] && min > nums[j]) {
                        minIdx = j;
                        min = nums[j];
                    }
                }

                int tmp = nums[i-1];
                nums[i-1] = min;
                nums[minIdx] = tmp;
                Arrays.sort(nums, i, nums.length);
                return;
            }
        }

        // 没有满足的条件，说明当前数字已经是最大组合，直接重新排序
        Arrays.sort(nums);
    }





    public void nextPermutation2(int[] nums) {
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
