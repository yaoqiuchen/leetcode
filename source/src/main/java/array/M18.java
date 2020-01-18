package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 18. 四数之和
 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

 注意：

 答案中不可以包含重复的四元组。

 示例：

 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

 满足要求的四元组集合为：
 [
 [-1,  0, 0, 1],
 [-2, -1, 1, 2],
 [-2,  0, 0, 2]
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/4sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M18 {

    public static void main(String[] args) {
        new M18().fourSum(new int[] {1,0,-1,0,-2,2}, 0);
    }

    // 2020/1/18
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        int i1 = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 如果第一个数字和前面一样，直接跳过
            if (i1 == nums[i]) continue;
            i1=nums[i];

            int i2 = Integer.MAX_VALUE;
            for (int j = i+1; j < nums.length-2; j++) {
                // 第二个数字和前面一样，直接跳过
                if (i2 == nums[j]) continue;
                i2=nums[j];

                int h = j+1, t = nums.length-1;
                int hval = Integer.MAX_VALUE, tval = Integer.MAX_VALUE;
                while (h < t) {
                    // 第三四个数字和前面一样就跳过
                    if (hval == nums[h]) {
                        h++;
                        continue;
                    }
                    if (tval == nums[t]) {
                        t--;
                        continue;
                    }

                    int sum = i1 + i2 + nums[h] + nums[t];
                    if (sum == target) {
                        hval = nums[h];
                        tval = nums[t];
                        res.add(Arrays.asList(i1, i2, hval, tval));
                        h++;
                        t--;
                    }
                    else if (sum > target) t--;
                    else h++;
                }
            }
        }
        return res;
    }



    public List<List<Integer>> fourSum2(int[] nums, int target) {
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
