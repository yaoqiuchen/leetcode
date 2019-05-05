package array;

import java.util.Arrays;

/**
 724. 寻找数组的中心索引

 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。

 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。

 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。

 示例 1:

 输入:
 nums = [1, 7, 3, 6, 5, 6]
 输出: 3
 解释:
 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 同时, 3 也是第一个符合要求的中心索引。
 示例 2:

 输入:
 nums = [1, 2, 3]
 输出: -1
 解释:
 数组中不存在满足此条件的中心索引。
 说明:

 nums 的长度范围为 [0, 10000]。
 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 */
public class E724 {

    public static void main(String[] args) {
//        new E566().arrayNesting(new int[] {5,4,0,3,1,6,2});
    }

    public int pivotIndex(int[] nums) {
        if (nums.length == 0) return -1;

        int sum = Arrays.stream(nums).sum();
        int l = 0, r = sum;
        for (int i = 0; i < nums.length; i++) {
            l += (i > 0 ? nums[i-1] : 0);
            r -= nums[i];

            if (l == r) return i;
        }

        return -1;
    }
}
