package array;

import java.util.*;

/**
 Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to
 target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

 Example:

 Given array nums = [-1, 2, 1, -4], and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class M16 {

//    public static void main(String[] args) {
//        new M16().threeSumClosest(new int[] {0,2,1,-3}, 1);
//    }

    // convert to 2-digits, use head-tail
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int sum = nums[0] + nums[1] + nums[2];
        int abs = Math.abs(target - sum);

        int len = nums.length;
        for (int i = 0; i <= len - 3; i++) {
            int h = i + 1, t = len - 1;
            while (h < t) {
                int total = nums[i] + nums[h] + nums[t];
                if (total == target) return total;

                int _abs = Math.abs(target - total);
                if (abs > _abs) {
                    sum = total;
                    abs = _abs;
                }
                if (target > total) h++;
                else t--;
            }
        }
        return sum;
    }

}
