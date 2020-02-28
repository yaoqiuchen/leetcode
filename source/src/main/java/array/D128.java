package array;

import dp.D123;

import java.util.*;

/**
 * 128. 最长连续序列
 *
 给定一个未排序的整数数组，找出最长连续序列的长度。

 要求算法的时间复杂度为 O(n)。

 示例:

 输入: [100, 4, 200, 1, 3, 2]
 输出: 4
 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。

 */
public class D128 {

    public static void main(String[] args) {
        new D128().longestConsecutive(new int[] {1,2,0,1});
    }

    // 2/27
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> dp = new HashMap<>();
        int result = 0;

        for (int val : nums) {
            if (dp.containsKey(val)) {
                continue;
            }

            int length = 0;
            if (dp.containsKey(val-1) && !dp.containsKey(val + 1)) {
                length = dp.get(val-1) + 1;
                dp.put(val, length);
                dp.put(val - length+1, length);
            } else if (dp.containsKey(val + 1) && !dp.containsKey(val - 1)) {
                length = dp.get(val+1) + 1;
                dp.put(val, length);
                dp.put(val+length-1, length);
            } else if (dp.containsKey(val+1) && dp.containsKey(val-1)) {
                length = dp.get(val-1) + dp.get(val+1) + 1;
                dp.put(val, length);
                dp.put(val - dp.get(val-1), length);
                dp.put(val + dp.get(val+1), length);
            } else {
                length = 1;
                dp.put(val, 1);
            }

            result = Math.max(result, length);
        }

        return result;
    }
}
