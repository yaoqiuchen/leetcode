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

    public int search(int[] nums, int target) {
        int l = 0, h = nums.length - 1;

        while (l <= h) {
            int mid = (l + h) / 2;
            if (target == nums[mid]) return mid;
            if (target == nums[l]) return l;
            if (target == nums[h]) return h;

            // 左边是排序的
            if (nums[l] <= nums[mid]) {
                // 如果范围落在左侧，那么就在左侧找
                if (target > nums[l] && target < nums[mid]) {
                    h = mid - 1;
                } else {
                    // 否则去右侧找
                    l = mid + 1;
                }
            } else {
                // 如果左侧是不排序的，那同样的方式在右侧找下
                if (target > nums[mid] && target < nums[h]) {
                    l = mid + 1;
                } else {
                    // 否则去右侧找
                    h = mid - 1;
                }
            }
        }
        return -1;
    }






    public int search2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l+r) / 2;

            if (nums[l] == target) return l;
            if (nums[mid] == target) return mid;
            if (nums[r] == target) return r;

            // first arr is sorted
            if (nums[l] <= nums[mid]) {
                // target in l...mid
                if (nums[l] < target && nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            // first arr is unsorted, so second arr must be sorted
            else {
                if (nums[mid+1] <= target && nums[r] > target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

}
