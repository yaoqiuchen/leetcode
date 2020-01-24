package array;

import java.util.Arrays;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

 示例 1:

 输入: [3, 2, 1]

 输出: 1

 解释: 第三大的数是 1.
 */
public class E414 {

    public static void main(String[] args) {
        new E414().thirdMax(new int[] {-2147483648,1,1,2});
    }

    public int thirdMax(int[] nums) {
        SortedSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int count = set.size() < 3 ? set.size()-1 : set.size() - 3;
        int i = 0;
        for (Integer val : set) {
            if (i == count) {
                return val;
            }
            i++;
        }
        return -1;
    }







    public int thirdMax_(int[] nums) {
        Set<Integer> filter = new TreeSet<>();
        for (int val : nums) {
            filter.add(val);
        }

        int idx = filter.size() >= 3 ? filter.size() - 3 : filter.size() - 1;
        int count = 0;
        for (Integer val : filter) {
            if (idx == count++) return val;
        }
        return -1;
    }

    // 不满足O(n)时间复杂度的解法
    public int thirdMax1(int[] nums) {
        nums = Arrays.stream(nums).distinct().sorted().toArray();
        int len = nums.length;
        return len >= 3 ? nums[len - 3] : nums[len-1];
    }
}
