package array;

import java.util.*;
import java.util.stream.Collectors;

/**
 448. 找到所有数组中消失的数字

 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

 找到所有在 [1, n] 范围之间没有出现在数组中的数字。

 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

 示例:

 输入:
 [4,3,2,7,8,2,3,1]

 输出:
 [5,6]
 */
public class E448 {


    // 2020-1-25
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] > 0) {
                nums[idx] *= -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i+1);
            }
        }
        return res;
    }



    public List<Integer> findDisappearedNumbers_(int[] nums) {
        for (int val : nums) {
            int idx = Math.abs(val) - 1;
            if (nums[idx] > 0) {
                nums[idx] *= -1;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) res.add(i+1);
        }
        return res;
    }
}
