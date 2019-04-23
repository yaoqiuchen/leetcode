package array;

import java.util.Arrays;

/**
 611. 有效三角形的个数
 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。

 示例 1:

 输入: [2,2,3,4]
 输出: 3
 解释:
 有效的组合是:
 2,3,4 (使用第一个 2)
 2,3,4 (使用第二个 2)
 2,2,3
 注意:

 数组长度不超过1000。
 数组里整数的范围为 [0, 1000]。
 */
public class M611 {

    public static void main(String[] args) {
        new M611().triangleNumber(new int[] {2,2,3,4});
    }

    // 正确答案，但是性能不好
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length - 1; j++) {
                int max = nums[j] + nums[i] - 1;
                for (int z = j + 1; z < nums.length; z++) {
                    if (nums[z] > max) break;
                    count++;
                }
            }
        }

        return count;
    }
}
