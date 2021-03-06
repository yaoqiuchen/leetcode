package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

 请找出其中最小的元素。

 你可以假设数组中不存在重复元素。

 示例 1:

 输入: [3,4,5,1,2]
 输出: 1
 示例 2:

 输入: [4,5,6,7,0,1,2]
 输出: 0
 */
public class M153 {

    public static void main(String[] args) {
        new M153().findMin(new int[] {2,1});
    }


    // 2020-1-22
    public int findMin(int[] nums) {
        int l = 0, h = nums.length-1;

        int min = Integer.MAX_VALUE;
        while (l <= h) {
            int mid = (l+h)/2;
            min = Math.min(min, nums[mid]);

            // 左侧一定是排序的
            if (nums[l] <= nums[mid]) {
                min = Math.min(min, nums[l]);
                l = mid+1;
            }
            // 右侧一定是排序的
            else if (nums[mid] <= nums[h]){
                min = Math.min(min, (mid < h) ? nums[mid+1] : nums[h]);
                h = mid-1;
            } else {
                h--;
                l++;
            }
        }

        return min;
    }



    public int findMin_(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (nums[0] <= nums[len-1]) return nums[0];

        int l = 0, h = len-1, res = nums[h];
        while (l <= h) {
            int mid = (l + h) / 2;
            if (nums[mid] > res) {
                l = mid + 1;
            } else if (nums[mid] < res) {
                h = mid - 1;
            } else {
                break;
            }
            res = Math.min(res, nums[mid]);
        }

        return res;
    }

}
