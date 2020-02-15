package array;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 989 数组形式的整数加法
  
 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。

 例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。

 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。

  

 示例 1：

 输入：A = [1,2,0,0], K = 34
 输出：[1,2,3,4]
 解释：1200 + 34 = 1234
 示例 2：

 输入：A = [2,7,4], K = 181
 输出：[4,5,5]
 解释：274 + 181 = 455
 示例 3：

 输入：A = [2,1,5], K = 806
 输出：[1,0,2,1]
 解释：215 + 806 = 1021
 示例 4：

 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 输出：[1,0,0,0,0,0,0,0,0,0,0]
 解释：9999999999 + 1 = 10000000000
  

 提示：

 1 <= A.length <= 10000
 0 <= A[i] <= 9
 0 <= K <= 10000
 如果 A.length > 1，那么 A[0] != 0

 */
public class E989 {

    public static void main(String[] args) {
        new E989().addToArrayForm(new int[] {9,9,9,9,9,9,9,9,9,9}, 1);
    }

    // 2020-2-15
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();

        String plus = K + "";
        int i = A.length - 1, j = plus.length() - 1;
        boolean advance = false;

        while (i >= 0 || j >= 0) {
            int val = 0;
            if (i >= 0) {
                val += A[i--];
            }
            if (j >= 0) {
                val += plus.charAt(j--) - '0';
            }
            if (advance) {
                val += 1;
            }
            advance = val >= 10;
            res.add(0, val >= 10 ? val - 10 : val);
        }
        if (advance) {
            res.add(0, 1);
        }
        return res;
    }

}
