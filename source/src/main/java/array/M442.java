package array;

import java.util.*;

/**
 442. 数组中重复的数据

 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。

 找到所有出现两次的元素。

 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？

 示例：

 输入:
 [4,3,2,7,8,2,3,1]

 输出:
 [2,3]
 */
public class M442 {

//    public static void main(String[] args) {
//        new M15().threeSum(new int[] {-2,0,0,2,2});
//    }

    // 根据所有数字都>=1来做文章，借助负数表示该数字已经出现过
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> res = new HashSet<>();
        for (int val : nums) {
            int idx = Math.abs(val) - 1;
            if (nums[idx] > 0) {
                nums[idx] *= -1;
            } else {
                res.add(Math.abs(val));
            }
        }
        return new ArrayList(res);
    }

    // 不满足O(n)时间复杂度的要求，因为该解法依赖sort
    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);

        boolean added = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                if (!added) {
                    res.add(nums[i]);
                    added = true;
                }
            } else {
                added = false;
            }
        }
        return res;
    }
}
