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
       new D689().maxSumOfThreeSubarrays(new int[] {17,8,14,11,13,9,4,19,20,6,1,20,6,5,19,8,5,16,20,17}, 5);
    }


    // 2020-1-28 不如之前的解法好
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if (3*k > nums.length) return new int[]{};

        int n = nums.length;
        int[] sumDp = new int[n];

        // 从左往右，ldp[i]表示截止i为止，靠左边最大的组合的下标位置
        int[] ldp = new int[n];
        for (int i=0, max=0, sum=0; i<n; i++) {
            sumDp[i] += ((i==0?0:sumDp[i-1]) + nums[i]);
            if (i<k) {
                sum = sumDp[i];
                max = sum;
            } else {
                sum += nums[i] - nums[i-k];
                if (sum > max) {
                    ldp[i] = i-k+1;
                    max = sum;
                } else ldp[i] = ldp[i-1];
            }
        }

        // 从右往左，rdp[i]表示截止i为止，靠右边最大的组合的下标位置
        int[] rdp = new int[n];
        for (int i=n-1, max=0, sum=0; i>=0+k*2; i--) {
            if (i>=n-k) {
                sum += nums[i];
                rdp[i] = n-k;
                max = sum;
            } else {
                sum += nums[i] - nums[i+k];
                if (sum >= max) {
                    rdp[i] = i;
                    max = sum;
                } else rdp[i] = rdp[i+1];
            }
        }

        int max = Integer.MIN_VALUE;
        int[] res = new int[3];
        // 先选中了中间的元素，然后往两头找
        for (int i = k; i <= n-k*2; i++) {
            int sum = sumDp[i+k-1] - sumDp[i-1];
            int left = ldp[i-1];
            int right = rdp[i+k];
            sum += sumDp[left+k-1] - (left>0 ? sumDp[left-1]:0);
            sum += sumDp[right+k-1] - sumDp[right-1];
            if (sum > max) {
                max = sum;
                res = new int[] {ldp[i-1], i, rdp[i+k]};
            }
        }

        return res;
    }




    public int[] maxSumOfThreeSubarrays_(int[] nums, int k) {
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