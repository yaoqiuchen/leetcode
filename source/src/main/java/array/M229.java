package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 229. 求众数 II
 *
 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

 示例 1:

 输入: [3,2,3]
 输出: [3]
 示例 2:

 输入: [1,1,1,3,3,2,2,2]
 输出: [1,2]

 */
public class M229 {

    public static void main(String[] args) {
        new M229().majorityElement(new int[] {1,2,3,4,5,6,6,7,7,7,7,7,7,8,8,8,8,8,9,9,9,9,9,9,9,9,9,9,9});
    }

    // 2020-1-25
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) return res;

        Arrays.sort(nums);
        int start = 0, target = nums.length/3;
        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];
            if (val == nums[start]) {
                continue;
            }
            if (i-start > target) {
                res.add(nums[i-1]);
            }
            start = i;
        }

        if (nums.length-start > target) {
            res.add(nums[nums.length-1]);
        }
        return res;
    }




    public List<Integer> majorityElement_(int[] nums) {
        List<Integer> res = new ArrayList<>();

        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i <= nums.length; i++) {
            if (i < nums.length && (i == 0 || nums[i] == nums[i-1])) {
                count++;
            } else  {
                if (count > nums.length / 3) {
                    res.add(nums[i-1]);
                }
                count = 1;
            }
        }
        return res;
    }

}
