package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that
 a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

 Note:

 The solution set must not contain duplicate quadruplets.

 Example:

 Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

 A solution set is:
 [
 [-1,  0, 0, 1],
 [-2, -1, 1, 2],
 [-2,  0, 0, 2]
 ]
 */
public class M18 {

    public static void main(String[] args) {
        new M18().fourSum(new int[] {-5,-4,-3,-2,-1,0,0,1,2,3,4,5}, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        int len = nums.length;
        for (int i = 0, i1 = 0; i <= len - 4; i1 = nums[i], i++) {
            // skip duplicated leading number
            if (i > 0 && nums[i] == i1) continue;
            for (int j = i + 1, j1 = 0; j <= len - 3; j1 = nums[j], j++) {
                if (j > i + 1 && nums[j] == j1) continue;

                int goal = target - nums[i] - nums[j];
                int l = j + 1, r = len - 1;
                Integer lval = null, rval = null;
                while (l < r) {
                    if (lval != null && lval == nums[l]) {
                        l++;
                        continue;
                    }
                    if (rval != null && rval == nums[r]) {
                        r--;
                        continue;
                    }

                    int sum = nums[l] + nums[r];
                    if (goal == sum) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        lval = nums[l];
                        rval = nums[r];
                        l++;
                        r--;
                    } else if (goal < sum) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }

        return res;
    }

}
