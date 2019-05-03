package array;

/**
 689. 三个无重叠子数组的最大和

 给定数组 nums 由正整数组成，找到三个互不重叠的子数组的最大和。

 每个子数组的长度为k，我们要使这3*k个项的和最大化。

 返回每个区间起始索引的列表（索引从 0 开始）。如果有多个结果，返回字典序最小的一个。

 示例:

 输入: [1,2,1,2,6,7,5,1], 2
 输出: [0, 3, 5]
 解释: 子数组 [1, 2], [2, 6], [7, 5] 对应的起始索引为 [0, 3, 5]。
 我们也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 注意:

 nums.length的范围在[1, 20000]之间。
 nums[i]的范围在[1, 65535]之间。
 k的范围在[1, floor(nums.length / 3)]之间。

 */
public class D689 {

    public static void main(String[] args) {
       new D689().maxSumOfThreeSubarrays(new int[] {7,13,20,19,19,2,10,1,1,19}, 3);
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int dp[] = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = nums[i] + (i < n - 1 ? dp[i+1] : 0);
            dp[i] -= (i+k<n ? nums[i+k] : 0);
        }
        int[] arr =  calculate2(dp, k);
        return arr;
    }

    // 先确定中间的元素，然后在两头只要找最大值即可
    private int[] calculate2(int[] dp, int k) {
        int max = Integer.MIN_VALUE;
        int res[] = new int[] {};
        for (int i = k; i <= dp.length - 2 * k; i++) {
            int sum = dp[i];

            int lmax = Integer.MIN_VALUE, lidx = 0;
            for (int l = 0; l < i - k+1; l++) {
                if (dp[l] > lmax) {
                    lmax = dp[l];
                    lidx = l;
                }
            }
            sum += lmax;

            int rmax = Integer.MIN_VALUE, ridx = i + k;
            for (int r = i + k; r < dp.length - k + 1; r++) {
                if (dp[r] > rmax) {
                    rmax = dp[r];
                    ridx = r;
                }
            }

            sum += rmax;
            if (sum > max) {
                max = sum;
                res = new int[] {lidx, i, ridx};
            }
        }
        return res;
    }

    // 正确答案，但是时间不满足要求
//    private int[] calculate(int[] dp, int k, int start, int size, int[] res) {
//        if (size > 0) {
//            int arr[] = new int[]{};
//            int max = Integer.MIN_VALUE;
//            for (int i = start; i + (k * size-1) < dp.length; i++) {
//                res[3-size] = i;
//                int sum[] = calculate(dp, k, i+k, size-1, res);
//                int tmpSum = 0;
//                for (int j : sum) {
//                    tmpSum += dp[j];
//                }
//                if (max < tmpSum) {
//                    max = tmpSum;
//                    arr = Arrays.copyOf(sum, 3);
//                }
//            }
//            return arr;
//        }
//        return res;
//    }


}