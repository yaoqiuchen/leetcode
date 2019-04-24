package array;

import java.util.Arrays;

/**
 643. 子数组最大平均数 I
 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

 示例 1:

 输入: [1,12,-5,-6,50,3], k = 4
 输出: 12.75
 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75


 注意:

 1 <= k <= n <= 30,000。
 所给数据范围 [-10,000，10,000]。
 */
public class E643 {

    public static void main(String[] args) {
        int[] as ;
        char input[] = new char[] { 'A','A','A','B','B','B'};
        new E643().findMaxAverage(new int[] {-1}, 1);
    }

    public double findMaxAverage(int[] nums, int k) {
        double max = -Double.MAX_VALUE;
        if (nums.length < k) return 0;

        Double sum = null;
        for (int i = 0, j = i+k-1; j < nums.length; i++, j++) {
            if (sum == null) {
                sum = 0d;
                for (int y=i; y<=j; y++) {
                    sum += nums[y];
                }
            } else {
                sum += nums[j] - (i > 0 ? nums[i-1] : 0);
            }
            max = Math.max(max, sum / k);
        }

        return max;
    }
}
