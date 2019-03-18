package dp;

import java.util.Arrays;

/**
 Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k
 non-empty subsets whose sums are all equal.



 Example 1:

 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.


 Note:

 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.


 */
public class M698 {

    public static void main(String[] args) {
        canPartitionKSubsets(new int[] {4,3,2,3,5,2,1}, 4);
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int total = Arrays.stream(nums).sum();
        if (total % k > 0) {
            return false;
        }

        int target = total / k;
        if (Arrays.stream(nums).max().getAsInt() > target) {
            return false;
        }

        boolean dp[] = new boolean[nums.length];
        return loop(nums, dp, target, k, target);
    }

    public static boolean loop(int[] nums, boolean[] visited, int target, int group, int gap) {
        if (group == 0) return true;
        if (gap == 0) return loop(nums, visited, target, group - 1, target);

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;

            if (nums[i] <= gap && loop(nums, visited, target, group, gap - nums[i])) {
                return true;
            } else {
                visited[i] = false;
            }
        }

        return false;
    }

}
