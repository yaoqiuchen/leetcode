package dp;

import java.util.HashMap;
import java.util.Map;

/**
 523. 连续的子数组和

 给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，
 其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。

 示例 1:

 输入: [23,2,4,6,7], k = 6
 输出: True
 解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
 示例 2:

 输入: [23,2,6,4,7], k = 6
 输出: True
 解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 说明:

 数组的长度不会超过10,000。
 你可以认为所有数字总和在 32 位有符号整数范围内。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M523 {

    public static void main(String[] args) {
        checkSubarraySum2(new int[] {1,0,1,0,1}, 4);
    }

    // 2020-1-17
    public static boolean checkSubarraySum2(int[] nums, int k) {
        Map<Integer, Integer> dp = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 最近的两个数为0，那任意k都可以满足条件
            if (nums[i] == 0 && i > 0 && nums[i-1] == 0) {
                return true;
            }

            if (k != 0) {
                // 累加总数，如果本次gap为5，那么只要寻找上一个gap同样为5的数字
                // 再看两个数字之间的差距是不是大于等于2，就知道这两个数之间的数字之和已经被整除了
                sum += nums[i];
                int gap = sum % k;

                if (gap == 0 && i > 0) {
                    return true;
                }

                if (dp.containsKey(gap)) {
                    if (i - dp.get(gap) >= 2) {
                        return true;
                    }
                } else {
                    dp.put(gap, i);
                }
            }
        }

        return false;
    }


    /**
     * Pay attention to some edge cases like k=0
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> dp = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // it will cover the case with k=0
            if (nums[i] == 0 && (i>0 && nums[i-1] == 0)) {
                return true;
            }

            if (k != 0) {
                sum += nums[i];
                int balance = sum % k;
                if (balance == 0 && i >= 1) {
                    return true;
                }
                if (dp.containsKey(balance) && i - dp.get(balance) >= 2) {
                    return true;
                }
                dp.putIfAbsent(balance, i);
            }
        }
        return false;
    }
}
