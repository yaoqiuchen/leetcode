
package array;

/**
 *
 * 1053 交换一次的先前排列
  
 给你一个正整数的数组 A（其中的元素不一定完全不同），
 请你返回可在 一次交换（交换两数字 A[i] 和 A[j] 的位置）后得到的、按字典序排列小于 A 的最大可能排列。

 如果无法这么操作，就请返回原数组。


 示例 1：

 输入：[3,2,1]
 输出：[3,1,2]
 解释：
 交换 2 和 1
  

 示例 2：

 输入：[1,1,5]
 输出：[1,1,5]
 解释：
 这已经是最小排列
  

 示例 3：

 输入：[1,9,4,6,7]
 输出：[1,7,4,6,9]
 解释：
 交换 9 和 7
  

 示例 4：

 输入：[3,1,1,3]
 输出：[1,3,1,3]
 解释：
 交换 1 和 3
  

 提示：

 1 <= A.length <= 10000
 1 <= A[i] <= 10000

 */
public class M1053 {

    public static void main(String[] args) {
        new M1053().prevPermOpt1(new int[] {9,10,4,5});
//        new M1160().maxUncrossedLines(new int[] {3,8,1,3,2,1,8,9,0}, new int[] {3,8,1,3,2,1,8,9,0});
    }

    // 2020-2-23
    public int[] prevPermOpt1(int[] A) {

        if (A.length <= 1) return A;

        // 记录0-9的最大下标位置
        for (int i = A.length-1; i > 0; i--) {
            // 前一个数字更小，不管他
            if (A[i-1] <= A[i]) {
                continue;
            }

            int max = A[i], pos = i;
            for (int j = i; j < A.length; j++) {
                if (A[j] < A[i-1] && max < A[j]) {
                    max = A[j];
                    pos = j;
                }
            }
            int tmp = A[i-1];
            A[i-1] = A[pos];
            A[pos] = tmp;
            return A;
        }
        return A;
    }

}
