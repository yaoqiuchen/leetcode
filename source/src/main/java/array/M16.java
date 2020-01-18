package array;

import java.util.*;

/**
 16. 最接近的三数之和

 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 返回这三个数的和。假定每组输入只存在唯一答案。

 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/3sum-closest
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class M16 {

    public static void main(String[] args) {
        new M16().threeSumClosest(new int[] {-3, 0, 1, 2}, 1);
    }

    // 转化为2数之和
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) return 0;
        Arrays.sort(nums);

        int res = Integer.MAX_VALUE, delta = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int head = i+1, tail = nums.length-1;

            while (head < tail) {
                int sum = nums[i] + nums[head] + nums[tail];
                int gap = target - sum;

                if (gap == 0) {
                    return target;
                }

                if (gap < 0) tail--;
                else head++;

                if (Math.abs(gap) < delta) {
                    delta = Math.abs(gap);
                    res = sum;
                }
            }
        }

        return res;
    }

    // convert to 2-digits, use head-tail
    public int threeSumClosest2(int[] nums, int target) {
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
