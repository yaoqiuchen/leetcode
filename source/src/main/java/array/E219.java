package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 219. 存在重复元素 II
 *
 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。

 示例 1:

 输入: nums = [1,2,3,1], k = 3
 输出: true
 示例 2:

 输入: nums = [1,0,1,1], k = 1
 输出: true
 示例 3:

 输入: nums = [1,2,3,1,2,3], k = 2
 输出: false
 */
public class E219 {

    public static void main(String[] args) {
        new E219().containsNearbyDuplicate(new int[] {1,2,3,1}, 3);
    }


    // 2020-1-25
    // 没半年前写的方法性能好
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k <= 0 || nums.length <= 1) return false;
        int l = 0, h = 1;

        while (l < h && h < nums.length) {
            if (h-l > k) {
                l++;
                h = l+1;
                continue;
            }
            if (nums[l] == nums[h] && h-l <= k) {
                return true;
            }
            if (h == nums.length-1) {
                l++;
            } else {
                h++;
            }
        }
        return false;
    }



    // 简单答案
    public boolean containsNearbyDuplicate_(int[] nums, int k) {
        Set<Integer> filter = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // filter中仅保存k+1个数字
            if (i > k) {
                filter.remove(nums[i-k-1]);
            }
            if (!filter.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    // 有点麻烦的正确答案, 但是性能比最简洁的答案要好
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        if (k == 0) return true;

        Set<Integer> filter = new HashSet<>();
        int copy[] = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        boolean added = false;
        for (int i = 1; i < nums.length; i++) {
            if (copy[i] == copy[i-1]) {
                if (!added) {
                    filter.add(copy[i]);
                    added = true;
                }
            } else {
                added = false;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (!filter.contains(nums[i])) continue;
            for (int j = i+1; j <= i + k; j++ ) {
                if (j < nums.length && nums[i] == nums[j]){
                    return true;
                }
            }
        }

        return false;
    }

    // 正确答案，性能超时
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <= i + k; j++ ) {
                if (j < nums.length && nums[i] == nums[j]){
                    return true;
                }
            }
        }

        return false;
    }

}
