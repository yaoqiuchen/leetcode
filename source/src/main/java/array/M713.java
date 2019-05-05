package array;

/**
 713. 乘积小于K的子数组

 给定一个正整数数组 nums。

 找出该数组内乘积小于 k 的连续的子数组的个数。

 示例 1:

 输入: nums = [10,5,2,6], k = 100
 输出: 8
 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 说明:

 0 < nums.length <= 50000
 0 < nums[i] < 1000
 0 <= k < 10^6
 */
public class M713 {

    public static void main(String[] args) {
        new M713().numSubarrayProductLessThanK(new int[] {10, 5, 2, 6}, 100);
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n=nums.length;
        int l=0,r=0;
        int ans=0;
        int sum=1;
        while(r < n){
            sum *= nums[r++];
            while (l < r && sum >= k){
                sum/=nums[l++];
            }
            ans += r-l;
        }
        return ans;
    }

    // 正确答案，但是超出时间限制
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        int sum = 0;
        int total = 1;
        for (int i = 0; i < nums.length; i++) {
            total = 1;
            for (int j = i; j < nums.length; j++) {
                total *= nums[j];
                if (total >= k) {
                    break;
                }
                sum++;
            }
        }

        return sum;
    }
}
