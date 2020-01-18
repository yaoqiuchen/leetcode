package array;

/**
 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，
 返回它将会被按顺序插入的位置。

 你可以假设数组中无重复元素。

 示例 1:

 输入: [1,3,5,6], 5
 输出: 2
 示例 2:

 输入: [1,3,5,6], 2
 输出: 1
 示例 3:

 输入: [1,3,5,6], 7
 输出: 4
 示例 4:

 输入: [1,3,5,6], 0
 输出: 0
 */
public class E35 {

    public static void main(String[] args) {
        new E35().searchInsert(new int[] {1,3,5,6}, 2);
    }

    // 2020/1/18
    public int searchInsert(int[] nums, int target) {
        int l = 0, h = nums.length - 1, pos = 0;
        while (l <= h) {
            int mid = (l+h)/2;

            if (nums[mid] == target) {
                return mid;
            }

            if (target < nums[mid]) {
                pos = mid;
                h = mid-1;
            } else {
                pos = mid+1;
                l = mid + 1;
            }
        }

        return pos;
    }





    public int searchInsert2(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int low = 0, high = nums.length-1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) >>> 1;
            if (target > nums[mid]) {
                low = mid + 1;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        if (target < nums[mid]) return mid == 0 ? 0 : mid - 1;
        if (target > nums[mid]) return mid+1;

        return mid;
    }

}
