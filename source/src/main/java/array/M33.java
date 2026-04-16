package array;

import java.util.Arrays;

/**
 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

 你可以假设数组中不存在重复的元素。

 你的算法时间复杂度必须是 O(log n) 级别。

 示例 1:
 输入: nums = [4,5,6,7,0,1,2], target = 0
 输出: 4

 示例 2:
 输入: nums = [4,5,6,7,0,1,2], target = 3
 输出: -1
 */
public class M33 {

    public static void main(String[] args) {
        new M33().search2(new int[] {4,5,6,7}, 4);
    }

    // 2026/4/16
    public int search2(int[] nums, int target) {
        // 5,6, 1, 2, 3, 4
        return search2(nums, target, 0, nums.length-1);
    }

    public int search2(int[] nums, int target, int start, int end) {
        if (start == end) {
            return nums[start] == target ? start : -1;
        }

        if (nums[start] < nums[end]) {
            while (start <= end) {
                int mid = (start + end)/2;
                int val = nums[mid];
                if (target == val) {
                    return mid;
                }
                if (target > val) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            }
            return -1;
        }

        int mid = (start + end)/2;
        return Math.max(search2(nums, target, start, mid), search2(nums, target, mid+1, end));
    }


}
