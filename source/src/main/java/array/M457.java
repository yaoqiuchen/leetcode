package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 457. 环形数组循环

 给定一个含有正整数和负整数的环形数组 nums。 如果某个索引中的数 k 为正数，则向前移动 k 个索引。相反，如果是负数 (-k)，则向后移动 k 个
 索引。因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素。

 确定 nums 中是否存在循环（或周期）。循环必须在相同的索引处开始和结束并且循环长度 > 1。此外，一个循环中的所有运动都必须沿着同一方向进行。
 换句话说，一个循环中不能同时包括向前的运动和向后的运动。


 示例 1：

 输入：[2,-1,1,2,2]
 输出：true
 解释：存在循环，按索引 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 示例 2：

 输入：[-1,2]
 输出：false
 解释：按索引 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 示例 3:

 输入：[-2,1,-1,-2,-2]
 输出：false
 解释：按索引 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为按索引 1 -> 2 的运动是向前的运动，而按索引 2 -> 1 的运动是向后的运动。一个
 循环中的所有运动都必须沿着同一方向进行。


 提示：

 -1000 ≤ nums[i] ≤ 1000
 nums[i] ≠ 0
 1 ≤ nums.length ≤ 5000
 */
public class M457 {

    public static void main(String[] args) {
        new M457().circularArrayLoop(new int[] {3,1,2});
    }

    public boolean circularArrayLoop(int[] nums) {
        if (nums.length <= 1) return false;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(i);
        }

        while (!set.isEmpty()) {
            int start = set.iterator().next();
            if (findWay(nums, start, set)) {
                return true;
            }
        }
        return false;
    }

    private boolean findWay(int[] nums, int start, Set<Integer> set) {
        boolean forward = nums[start] > 0;
        Set<Integer> circle = new HashSet<>();

        int idx = start;
        circle.add(start);
        while (true) {
            set.remove(idx);
            boolean direction = nums[idx] > 0;
            int nextIdx = calculate(idx, nums[idx], nums.length);

            // 下个节点和当前节点一样，表示这是个，长度为1的循环数组, 或者nums[i] = 0导致原地踏步
            if (nextIdx == idx) return false;
            if (direction == forward) {
                // 走到了当前循环中的节点
                if (circle.contains(nextIdx)) return true;
            } else {
                circle.clear();
                circle.add(idx);
                forward = direction;
            }
            // 无论顺序是否相同, 如果曾经走过了，那一定重蹈覆辙，坚决退出
            if (!set.contains(nextIdx)) {
                return false;
            }
            circle.add(nextIdx);
            idx = nextIdx;
        }
    }

    private int calculate(int pos, int val, int len) {
        if (val == 0) return pos;
        if (val > 0) {
            return pos + val >= len ? (pos + val) % len : pos + val;
        }
        return pos + val < 0 ? (len - Math.abs(pos+val) % len) : pos + val;
    }
}
