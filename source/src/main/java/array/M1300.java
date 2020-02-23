
package array;

import java.util.Arrays;
import java.util.TreeSet;

/**
 *
 * 1300. 转变数组后最接近目标值的数组和
  
 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，
 使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。

 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。

 请注意，答案不一定是 arr 中的数字。

  

 示例 1：

 输入：arr = [4,9,3], target = 10
 输出：3
 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 示例 2：

 输入：arr = [2,3,5], target = 10
 输出：5
 示例 3：

 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 输出：11361
  

 提示：

 1 <= arr.length <= 10^4
 1 <= arr[i], target <= 10^5

 */
public class M1300 {

    public static void main(String[] args) {
        new M1300().findBestValue(new int[] {4, 9, 3}, 10);
//        new M1300().replaceElements(new int[] {});
//        new M1160().maxUncrossedLines(new int[] {3,8,1,3,2,1,8,9,0}, new int[] {3,8,1,3,2,1,8,9,0});
    }

    // 2020-2-23
    public int findBestValue(int[] arr, int target) {
        // 从小到大排序
        Arrays.sort(arr);
        int avg = target / arr.length, sum = 0, gap = Integer.MAX_VALUE;
        // dp[i]表示从右边起到i位置所有和
        int[] dp = new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {
            sum += arr[i];
            dp[i] = sum;
        }

        int result = avg;
        do {
            // 求出当前avg的总和
            int total = sum(arr, avg, sum, dp);
            // 如果gap更小，则把avg当做result，并且+1执行下一次
            int _gap = Math.abs(total - target);
            if (_gap < gap) {
                result = avg;
                avg++;
                gap = _gap;
            } else {
                break;
            }
        } while (avg <= arr[arr.length-1]);

        return result;
    }

    public boolean hasNext(int[] arr, int current) {
        return current < arr[arr.length-1];
    }

    // 用二分查找计算和
    public int sum(int[] arr, int target, int sum, int[] dp) {
        int result = 0;
        int l = 0, h = arr.length - 1, idx = 0;
        while (l <= h) {
            int mid = (l+h)/2;
            if (arr[mid] == target) {
                idx = mid;
                break;
            }
            if (arr[mid] > target) {
                h = mid-1;
                idx = (h < 0 ? 0 : h);
            } else {
                l = mid+1;
                idx = (l >= arr.length ? arr.length-1:l);
            }
        }

        while (idx < arr.length && arr[idx] <= target) idx++;

        if (idx >= arr.length) {
            return sum;
        }

        result = sum - dp[idx] + target * (arr.length - idx);
        return result;
    }

}
