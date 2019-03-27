package array;

import com.sun.tools.corba.se.idl.InterfaceGen;

import java.util.*;

/**
 Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note:

 The solution set must not contain duplicate triplets.

 Example:

 Given array nums = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 */
public class M15 {

//    public static void main(String[] args) {
//        new M15().threeSum(new int[] {-2,0,0,2,2});
//    }

    public List<List<Integer>> threeSum(int[] nums) {
        int last = nums.length - 1, len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        Integer filter = null;
        for (int i = 0; i <= last - 2; i++) {
            if (filter != null && filter == nums[i]) {
                continue;
            }

            filter = nums[i];
            int head = i + 1, tail = last;
            int target = -nums[i];

            Integer filter2 = null;
            while (head < tail) {
                int sum = nums[head] + nums[tail];
                if (sum == target && (filter2 == null || filter2 != nums[head])) {
                    res.add(Arrays.asList(nums[i], nums[head], nums[tail]));
                    filter2 = nums[head];
                    tail--;
                    head++;
                    continue;
                }
                if (sum > target) tail--;
                else head++;
            }
        }
        return res;
    }
}