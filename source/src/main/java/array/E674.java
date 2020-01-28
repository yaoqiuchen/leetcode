package array;

import java.util.LinkedList;
import java.util.Queue;

/**
 674. 最长连续递增序列

 给定一个未经排序的整数数组，找到最长且连续的的递增序列。

 示例 1:

 输入: [1,3,5,4,7]
 输出: 3
 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 示例 2:

 输入: [2,2,2,2,2]
 输出: 1
 解释: 最长连续递增序列是 [2], 长度为1。
 注意：数组长度不会超过10000。

 */
public class E674 {

    public static void main(String[] args) {
        new E674().findLengthOfLCIS(new int[] {1,3,5,6});
    }


    // 2020-1-28
    public int findLengthOfLCIS(int[] nums) {
        int res = 0, len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i-1]) {
                len++;
            } else {
                len = 1;
            }
            res = Math.max(res, len);
        }
        return res;
    }





    public int findLengthOfLCIS_(int[] nums) {
        int count = nums.length > 0 ? 1 : 0;
        int max = count;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) count++;
            else count = 1;
            max = Math.max(max, count);
        }

        return max;
    }

}
