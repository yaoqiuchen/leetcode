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


    // 2020-1-26
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length <=2) return 0;

        Arrays.sort(nums);
        int res = 0;
        // 三角形第一个短边，从0到倒数第三个数字
        for (int i = 0; i < nums.length-2; i++) {
            // 三角形第二个短边，从i+1到倒数第二个数字
            for (int j = i+1; j < nums.length-1; j++) {

                // 只要找到最小的大于等于sum的第三条边就可以，用二分查找
                int sum = nums[i] + nums[j];
                int l = j+1, h = nums.length - 1, target = l;
                while (l <= h) {
                    int mid = (l+h)/2;
                    if (nums[mid] == sum) {
                        target = mid;
                        break;
                    }
                    if (nums[mid] < sum) l = mid+1;
                    else h = mid-1;
                    target = l;
                }
                // 如果target正好等于sum，而同时有多个值都和target相同，那么要移动指针
                while (target>j+1 && nums[target-1] == sum) {
                    target--;
                }
                // 如果target小于sum，那么要往右侧移动，找到第一个不满足的数字
                while (target < nums.length && nums[target] < sum) target++;
                res += (target - j - 1);
            }
        }
        return res;
    }



    // 正确答案，但是性能不好
    public int triangleNumber_(int[] nums) {
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
