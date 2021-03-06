package array;

/**
 峰值元素是指其值大于左右相邻值的元素。

 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。

 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。

 你可以假设 nums[-1] = nums[n] = -∞。

 示例 1:

 输入: nums = [1,2,3,1]
 输出: 2
 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 示例 2:

 输入: nums = [1,2,1,3,5,6,4]
 输出: 1 或 5
 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 或者返回索引 5， 其峰值元素为 6。
 */
public class M162 {

    // 2020-1-24
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1;

        int res = 0;
        for (int i = 1; i < n; i++) {
            int val = nums[i];
            if (val < nums[i-1] || (i+1<n && val < nums[i+1])) {
                continue;
            }
            return i;
        }

        return res;
    }





    public int findPeakElement_(int[] nums) {
        if (nums.length <= 1) return 0;
        if (nums.length <= 2) return nums[0] > nums[1] ? 0 : 1;

        boolean meet = nums[1] > nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (meet) return i-1;
            } else {
                meet = true;
            }
        }
        return nums[0] > nums[1] ? 0 : nums[nums.length-1];
    }

}
