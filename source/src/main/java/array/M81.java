
package array;

/**
 *  搜索旋转排序数组 II
 *
 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

 ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。

 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

 示例 1:

 输入: nums = [2,5,6,0,0,1,2], target = 0
 输出: true
 示例 2:

 输入: nums = [2,5,6,0,0,1,2], target = 3
 输出: false
 进阶:

 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？

 */
public class M81 {

    public static void main(String[] args) {
//        char input[][] = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        new M81().search(new int[] {1,3,1,1,1}, 3);
    }

    // 用二分的效率，感觉还不如直接遍历一遍来得快
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[low] == target || nums[high] == target || nums[mid] == target) {
                return true;
            }

            // arr1 is sorted
            if (nums[low] < nums[mid] || (nums[low] == nums[mid] && allSame(nums, low, mid))) {
                // in arr 1
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            } else {
                // arr 2 must be sorted
                if (target >= nums[mid+1] && target <= nums[high]) {
                    low = mid+1;
                } else {
                    high = mid;
                }
            }
        }
        return false;
    }

    public boolean allSame(int nums[], int start, int end) {
        int val = nums[start];
        for (int i = start; i <= end; i++) {
            if (val != nums[i]) return false;
        }
        return true;
    }

}
