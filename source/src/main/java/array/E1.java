package array;

import java.util.*;

/**
 Given an array of integers, return indices of the two numbers such that they add up to a specific
 target.

 You may assume that each input would have exactly one solution, and you may not use the same
 element twice.

 Example:

 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class E1 {

    // 2026/2/24
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, List<Integer>> locator = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = locator.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            locator.put(nums[i], list);
        }

        Arrays.sort(nums);

        int l = 0, r = nums.length-1;
        while (l <= r) {
            int sum = nums[l] + nums[r];
            if (sum == target) {
                break;
            }
            if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        int lIdx = locator.get(nums[l]).get(0);
        int rIdx = locator.get(nums[r]).get(nums[l] == nums[r] ? 1 : 0);
        return new int[] {lIdx, rIdx};
    }

    // 2020/1/17
    public int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] == target - val) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }




    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {};
    }
}
