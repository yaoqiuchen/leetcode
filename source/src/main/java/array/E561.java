package array;

import java.util.Arrays;

/**
 561. 数组拆分 I

 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
 使得从1 到 n 的 min(ai, bi) 总和最大。

 示例 1:

 输入: [1,4,3,2]

 输出: 4
 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 提示:

 n 是正整数,范围在 [1, 10000].
 数组中的元素范围在 [-10000, 10000].
 */
public class E561 {

    public static void main(String[] args) {
        new E561().arrayPairSum(new int[] {1,2,3,4,5,6});
    }


    // 2020-1-20
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i +=2) {
            res += nums[i];
        }
        return res;
    }




    public int arrayPairSum_(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i+=2) {
            int min = Math.min(nums[i+1], nums[i]);
            sum += min;
        }
        return sum;
    }

}
