package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 532. 数组中的K-diff数对
 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j),
 其中 i 和 j 都是数组
 中的数字，且两数之差的绝对值是 k.

 示例 1:

 输入: [3, 1, 4, 1, 5], k = 2
 输出: 2
 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 示例 2:

 输入:[1, 2, 3, 4, 5], k = 1
 输出: 4
 解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 示例 3:

 输入: [1, 3, 1, 5, 4], k = 0
 输出: 1
 解释: 数组中只有一个 0-diff 数对，(1, 1)。
 注意:

 数对 (i, j) 和数对 (j, i) 被算作同一数对。
 数组的长度不超过10,000。
 所有输入的整数的范围在 [-1e7, 1e7]。
 */
public class E532 {

    public static void main(String[] ars) {
        new E532().findPairs(new int[] {3,1,1,4,5}, 2);
    }


    // 2020-1-25
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0, l = 0, h = 1;

        Set<Integer> filter = new HashSet<>();
        while (l < h && h < nums.length) {
            int gap = nums[h] - nums[l];
            if (gap == k) {
                if (nums[h] != nums[h-1] && (l == 0 || nums[l] != nums[l-1])) {
                    count++;
                } else if (k == 0 && !filter.contains(nums[h])) {
                    count++;
                    filter.add(nums[h]);
                }
                l++;
                h++;
            }
            else if (gap < k) h++;
            else if (gap > k) {
                l++;
                h = (l==h) ? h+1 : h;
            }
        }

        return count;



//        int[] arr = Arrays.stream(nums).distinct().sorted().toArray();
//        int count = 0;
//
//        int l = 0, h = 1;
//        while (l < h && h < arr.length) {
//            int gap = arr[h] - arr[l];
//            if (gap == k) {
//                count++;
//                l++;
//                h++;
//            }
//            else if (gap < k) h++;
//            else if (gap > k) {
//                l++;
//                h = (l==h) ? h+1 : h;
//            }
//        }
//
//        return count;
    }


    // 有点浪费空间和时间的做法
    public int findPairs_(int[] nums, int k) {
        if (k < 0) return 0;
        Arrays.sort(nums);
        int count = 0;
        Set<Integer> filter = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (filter.contains(nums[i])) continue;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] - nums[i] == k) {
                    filter.add(nums[i]);
                    count++;
                    break;
                }
                if (nums[j] - nums[i] > k) break;
            }
        }
        return count;
    }
}
