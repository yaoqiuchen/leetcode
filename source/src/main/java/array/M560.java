package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 560. 和为K的子数组

 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

 示例 1 :

 输入:nums = [1,1,1], k = 2
 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 说明 :

 数组的长度为 [1, 20,000]。
 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class M560 {

    public static void main(String[] args) {
        new M560().subarraySum(new int[] {1,2,3}, 3);
    }


    // 2020-1-25
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int res = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int gap = sum - k;

            res += map.getOrDefault(gap, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }


    // O(n)
    public int subarraySum_(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int delta = sum - k;
            if (map.containsKey(delta)) {
                count += map.get(delta);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // O(n^2)复杂度
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int delta = nums[i];
            if (delta == k) count++;
            for (int j = i+1; j < nums.length; j++) {
                delta += nums[j];
                if (delta == k) {
                    count++;
                }
            }
        }
        return count;
    }

}
