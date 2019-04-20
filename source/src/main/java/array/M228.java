package array;

import java.util.*;

/**
 * 228. 汇总区间
 *
 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。

 示例 1:

 输入: [0,1,2,4,5,7]
 输出: ["0->2","4->5","7"]
 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
 示例 2:

 输入: [0,2,3,4,6,8,9]
 输出: ["0","2->4","6","8->9"]
 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
 */
public class M228 {

    public static void main(String[] args) {
        new M228().summaryRanges(new int[] {1,2,3,1});
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) return res;

        int start = nums[0];
        for (int i = 0; i < nums.length; i++) {
            start = (start == Integer.MAX_VALUE) ? nums[i] : start;
            if (i == nums.length - 1 || nums[i] + 1 < nums[i+1]) {
                String append = start == nums[i] ? "" : "->" + nums[i];
                res.add(start + append);
                start = Integer.MAX_VALUE;
            }
        }

        return res;
    }

}
