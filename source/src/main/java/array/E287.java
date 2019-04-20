package array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 287. 寻找重复数

 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

 示例 1:

 输入: [1,3,4,2,2]
 输出: 2
 示例 2:

 输入: [3,1,3,4,2]
 输出: 3
 说明：

 不能更改原数组（假设数组是只读的）。
 只能使用额外的 O(1) 的空间。
 时间复杂度小于 O(n2) 。
 数组中只有一个重复的数字，但它可能不止重复出现一次。

 */
public class E287 {

    public static void main(String[] args) {
        new E287().findDuplicate(new int[] {1,3,5,6});
    }

    // 最优解法没想出来，借鉴别人答案
    public int findDuplicate(int[] nums) {
        //按照题意，指针在数列上前进时，其路径为长度X的起始部分，外加长度为Y的循环部分(PS：唯一的重复元素造成了循环，除重复元素以外的i = nums[i]和第2个以后的重复元素会被排除于序列之外)
        //例： nums = [1,2,3,4,2], 指针前进的路径为：
        //      i  0 1 2 3 4 2 3 4 2 3 4 ...
        // num[i]  1 2 3 4 2 3 4 2 3 4 2 ...
        //           | ----= ----= ----=            X = 2， Y = 3
        //
        //由上观察可知，所有相等nums[i]在序列中的间隔皆为循环部分的长度Y的倍数，
        //而第一对相等的nums[i]就是循环部分的起始点前一位('|'标识)和循环的终点位置('='标识)，其值也就是本题的解：唯一的重复元素

        //快慢指针法：
        //Step 1：利用前进速度为1和2的两个指针，获得Y的倍数的长度。 原理：当slow和fast指针的值相等时，设slow指针的路程为Z，则fast为2*Z,可知 Z = k*Y
        //Step 2：利用间隔为k*Y的两个指针，找出第一对相同的nums[i]，其值就是本题的解。

        //Step 1：获取循环部分的长度
        int slow = 0;
        int fast = 0;
        while(true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            //当slow指针和fast指针相等时，slow指针所走过的路程就是k倍的循环长度Y
            if (slow == fast) {
                //Step 2：获取第一对间隔为k*Y的值相等的nums[i]，其值即为唯一的重复元素
                fast = slow;
                slow = 0;
                while(nums[slow] != nums[fast]) {
                    slow = nums[slow];
                    fast = nums[fast];
                }
                return nums[slow];
            }
        }
    }

    // 不满足额外空间O(1)的要求
    public int findDuplicate2(int[] nums) {
        boolean dp[] = new boolean[nums.length];

        for (int val : nums) {
            if (dp[val]) return val;
            dp[val] = true;
        }
        return - 1;
    }

    // 不满足"不更改原数组"的条件
    public int findDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) return nums[i];
        }

        return -1;
    }

}
