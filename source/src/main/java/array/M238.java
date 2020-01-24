package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 238. 除自身以外数组的乘积
 *
 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

 示例:

 输入: [1,2,3,4]
 输出: [24,12,8,6]
 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

 进阶：
 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

 */
public class M238 {

    public static void main(String[] args) {
        new M238().productExceptSelf(new int[] {1,2,3,4,5,6,6,7,7,7,7,7,7,8,8,8,8,8,9,9,9,9,9,9,9,9,9,9,9});
    }



    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) left[0] = nums[0];
            else left[i] = left[i-1] * nums[i];
        }

        for (int i = n-1; i >= 0; i--) {
            if (i == n-1) right[n-1] = nums[n-1];
            else right[i] = right[i+1] * nums[i];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int l = (i==0) ? 1 : left[i-1];
            int r = (i==n-1) ? 1 : right[i+1];
            res[i] = l * r;
        }
        return res;
    }







    // 从左至右乘一遍，从右到左乘一遍
    // 这个解法，我是想不到，不过很像奇技淫巧，没个卵用
    public int[] productExceptSelf_(int[] nums) {
        int len = nums.length;
        int[] re = new int[len];
        if(len == 0) return new int[]{0};
        int help = 1;
        for(int i = 0; i < len; i++){
            re[i] = help;
            help *= nums[i];
        }
        help = 1;
        for(int i = len-1; i >= 0; i--){
            re[i] *= help;
            help *= nums[i];
        }
        return re;
    }

    // 用了除数
    public int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];
        long zeros = Arrays.stream(nums).filter(e -> e == 0).count();
        int total = zeros == nums.length ? 0 : Arrays.stream(nums).filter(e-> e != 0).reduce((a, b) -> a * b).getAsInt();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                res[i] = zeros > 1 ? 0 : total;
            } else {
                res[i] = zeros > 0 ? 0 : total / nums[i];
            }
        }

        return res;
    }

    // O(n^2) 复杂度
    public int[] productExceptSelf1(int[] nums) {
        int[] res = new int[nums.length];
        int val = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = val;
            val *= nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                res[j] *= nums[i];
            }
        }

        return res;
    }

}
