
package array;

import java.util.Arrays;

/**
 *
 * 1299 - 将每个元素替换为右侧最大元素
  
 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。

 完成所有替换操作后，请你返回这个数组。

  

 示例：

 输入：arr = [17,18,5,4,6,1]
 输出：[18,6,6,6,1,-1]
  

 提示：

 1 <= arr.length <= 10^4
 1 <= arr[i] <= 10^5

 */
public class E1299 {

    public static void main(String[] args) {
        new E1299().replaceElements(new int[] {});
//        new M1160().maxUncrossedLines(new int[] {3,8,1,3,2,1,8,9,0}, new int[] {3,8,1,3,2,1,8,9,0});
    }

    // 2020-2-23
    public int[] replaceElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int n = arr.length-1;
        for (int max = arr[n], i = n-1; i >= 0; n--) {
            int current = arr[i];
            arr[i] = max;
            max = Math.max(current, max);
        }
        arr[n] = -1;
        return arr;
    }

}
