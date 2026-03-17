package array;


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


 15. 三数之和

 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 找出所有满足条件且不重复的三元组。

 注意：答案中不可以包含重复的三元组。

  

 示例：

 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

 满足要求的三元组集合为：
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 */
public class M15 {

//    public static void main(String[] args) {
//        new M15().threeSum(new int[] {-2,0,0,2,2});
//    }

    // 2026/3/17
    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length-2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int l = i+1, r=nums.length-1, target = -nums[i];

            while (l < r) {
                while (l < r && l > i+1 && nums[l] == nums[l-1]) {
                    l++;
                }
                while (l < r && r < nums.length-1 && nums[r] == nums[r+1]) {
                    r--;
                }

                if (l >= r) continue;

                int val = nums[l] + nums[r];
                if (val == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    continue;
                } else if (val < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return res;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) return new ArrayList<>();

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        Integer head = null;
        for (int i = 0; i < nums.length; i++) {
            // 第一个节点数字不能重复
            if (head != null && nums[i] == head) {
                continue;
            }

            int first = nums[i], target = 0 - first;
            head = nums[i];

            int l = i+1, h = nums.length-1;
            Integer tail = null;
            while (l < h) {
                if (tail != null && tail == nums[h]) {
                    h--;
                    continue;
                }
                int sum = nums[l] + nums[h];
                if (nums[l] + nums[h] == target) {
                    res.add(Arrays.asList(first, nums[l], nums[h]));
                    tail = nums[h];
                    h--;
                    l++;
                    continue;
                }

                if (sum > target) h--;
                else l++;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
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
