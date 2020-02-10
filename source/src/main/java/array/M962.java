package array;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 *
 * 962 最大宽度坡
 *
 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。

 找出 A 中的坡的最大宽度，如果不存在，返回 0 。

  

 示例 1：

 输入：[6,0,8,2,1,5]
 输出：4
 解释：
 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 示例 2：

 输入：[9,8,1,0,1,9,4,0,4,1]
 输出：7
 解释：
 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
  

 提示：

 2 <= A.length <= 50000
 0 <= A[i] <= 50000
  
 */
public class M962 {

    public static void main(String[] args) {
        new M962().maxWidthRamp(new int[] {});
    }

    // 2020-2-10
    public int maxWidthRamp(int[] A) {
        int max = 0;
        next: for (int i = 0; i < A.length-1; i++) {
            for (int j = A.length-1; j > i; j--) {
                if (A[j] >= A[i]) {
                    max = Math.max(max, j-i);
                    continue next;
                }
            }
        }
        return max;
    }


    // 标准答案
    public int maxWidthRamp2(int[] A) {
        Stack<Integer> stacks = new Stack<>();
        stacks.push(0);
        int length = A.length;
        for (int i = 1; i < length; i++) {
            if (A[i] <= A[stacks.peek()]) {
                stacks.push(i);
            }
        }
        int max = 0;
        for (int i = length - 1; i >= 0; i--) {
            while (!stacks.isEmpty() && A[i] >= A[stacks.peek()]) {
                max = Math.max(max, i - stacks.pop());
            }
        }
        return max;
    }

}
