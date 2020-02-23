
package array;

import java.util.Arrays;

/**
 *
 * 1287. 有序数组中出现次数超过25%的元素
  
 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。

 请你找到并返回这个整数

  

 示例：

 输入：arr = [1,2,2,6,6,6,6,7,10]
 输出：6
  

 提示：

 1 <= arr.length <= 10^4
 0 <= arr[i] <= 10^5


 */
public class E1287 {

    public static void main(String[] args) {
//        new M1160().maxUncrossedLines(new int[] {3,8,1,3,2,1,8,9,0}, new int[] {3,8,1,3,2,1,8,9,0});
    }

    // 2020-2-23
    public int findSpecialInteger(int[] arr) {
        if (arr.length <= 1) {
            return arr[0];
        }

        Arrays.sort(arr);
        double target = arr.length / 4d;
        for (int i = 1, count = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                count++;
            } else {
                count = 1;
            }
            if (count > target) {
                return arr[i];
            }
        }

        return 1;
    }

}
