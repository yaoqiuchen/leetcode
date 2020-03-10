package array;

import java.util.Arrays;

/**
4.寻找两个有序数组的中位数

 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

 你可以假设 nums1 和 nums2 不会同时为空。

 示例 1:

 nums1 = [1, 3]
 nums2 = [2]

 则中位数是 2.0
 示例 2:

 nums1 = [1, 2]
 nums2 = [3, 4]

 则中位数是 (2 + 3)/2 = 2.5

 */
public class D4 {

    public static void main(String[] args) {
        new D4().findMedianSortedArrays(new int[] {1,3}, new int[] {2});
    }

    // 3/10
    // O(n)的方案，已经超过100%用户了，O(log(m+n))怎么做
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;

        int idx1 = 0, idx2 = 0;
        int sum = 0;
        while (idx1 < nums1.length || idx2 < nums2.length) {
            int target = 0;
            if (idx1 == nums1.length) {
                target = nums2[idx2];
                idx2++;
            } else if (idx2 == nums2.length) {
                target = nums1[idx1];
                idx1++;
            } else {
                target = Math.min(nums1[idx1], nums2[idx2]);
                if (target == nums1[idx1]) idx1++;
                else idx2++;
            }

            int num = idx1 + idx2 - 1;
            if (n % 2 == 1 && num == n / 2) {
                return target;
            }
            if (n % 2 == 0) {
                if (num == n/2-1)
                    sum += target;
                else if (num == n/2) {
                    sum += target;
                    return sum / 2d;
                }

            }
        }

        return 1;
    }
}
