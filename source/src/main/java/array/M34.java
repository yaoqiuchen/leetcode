package array;

import java.util.Arrays;

/**
 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

 你的算法时间复杂度必须是 O(log n) 级别。

 如果数组中不存在目标值，返回 [-1, -1]。

 示例 1:

 输入: nums = [5,7,7,8,8,10], target = 8
 输出: [3,4]
 示例 2:

 输入: nums = [5,7,7,8,8,10], target = 6
 输出: [-1,-1]
 */
public class M34 {



    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        // 如果target超出范围，直接中断
        if (n == 0 || target < nums[0] || target > nums[n-1]) {
            return new int[] {-1, -1};
        }

        int l = 0, h = n - 1, idx = -1;
        while (l <= h) {
            int mid = (l + h)/2;
            if (target == nums[mid]) {
                idx = mid;
                break;
            }

            if (nums[mid] > target) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (idx >= 0) {
            l = idx;
            h = idx;

            while (l > 0) {
                if (nums[l-1] == target) l--;
                else break;
            }
            while (h < n-1) {
                if (nums[h+1] == target) h++;
                else break;
            }
            return new int[] {l, h};
        }

        return new int[] {-1, -1};
    }






    public int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) return new int[] {-1, -1};

        int low = 0, high = nums.length - 1;
        int index = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                index = mid;
                break;
            }
        }

        low = index;
        while (low != -1 && nums[low] == target) {
            if (low == 0 || nums[low-1] != target) break;
            low--;
        }

        high = index;
        while (high != -1 && nums[high] == target) {
            if (high == nums.length - 1 || nums[high+1] != target) break;
            high++;
        }
        return new int[] {low, high};
    }

}
