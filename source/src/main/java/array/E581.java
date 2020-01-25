package array;

import java.util.Arrays;

/**
 581. 最短无序连续子数组

 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

 你找到的子数组应是最短的，请输出它的长度。

 示例 1:

 输入: [2, 6, 4, 8, 10, 9, 15]
 输出: 5
 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 说明 :

 输入的数组长度范围在 [1, 10,000]。
 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */
public class E581 {

    public static void main(String[] args) {
//        new E566().arrayNesting(new int[] {5,4,0,3,1,6,2});
    }

    // 2020-1-26
    public int findUnsortedSubarray(int[] nums) {
        int[] arr = Arrays.stream(nums).sorted().toArray();

        int l = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (arr[i] != nums[i]) {
                l = i;
                break;
            }
        }

        int h = Integer.MAX_VALUE;
        for (int i = nums.length-1; i >= 0; i--) {
            if (arr[i] != nums[i]) {
                h = i;
                break;
            }
        }

        return l != h ? h - l + 1 : 0;
    }








    // 正确答案，但是时间和空间都不好，时间上用了sort，空间上用了O(n)额外空间
    public int findUnsortedSubarray2(int[] nums) {
        int[] copy = Arrays.stream(nums).sorted().toArray();

        Integer low = 0, high = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != copy[i]) {
                low = i;
                break;
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != copy[i]) {
                high = i;
                break;
            }
        }

        return high == low ? 0 : high - low + 1;
    }
}
