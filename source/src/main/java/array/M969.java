package array;

import java.util.*;

/**
 *
 * 969 煎饼排序
 给定数组 A，我们可以对其进行煎饼翻转：我们选择一些正整数 k <= A.length，然后反转 A 的前 k 个元素的顺序。
 我们要执行零次或多次煎饼翻转（按顺序一次接一次地进行）以完成对数组 A 的排序。

 返回能使 A 排序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * A.length
 范围内的有效答案都将被判断为正确。

  

 示例 1：

 输入：[3,2,4,1]
 输出：[4,2,4,3]
 解释：
 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 初始状态 A = [3, 2, 4, 1]
 第一次翻转后 (k=4): A = [1, 4, 2, 3]
 第二次翻转后 (k=2): A = [4, 1, 2, 3]
 第三次翻转后 (k=4): A = [3, 2, 1, 4]
 第四次翻转后 (k=3): A = [1, 2, 3, 4]，此时已完成排序。
 示例 2：

 输入：[1,2,3]
 输出：[]
 解释：
 输入已经排序，因此不需要翻转任何内容。
 请注意，其他可能的答案，如[3，3]，也将被接受。
  

 提示：

 1 <= A.length <= 100
 A[i] 是 [1, 2, ..., A.length] 的排列

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/pancake-sorting
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class M969 {

    public static void main(String[] args) {
        new M969().pancakeSort(new int[] {3,2,4,1});
    }

    // 2020-2-11
    public List<Integer> pancakeSort(int[] A) {
        // pos[i]表示应该排在第i位的数字，现在在第几位
        int[] pos = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            pos[A[i]-1] = i;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = A.length-1; i >= 0; i--) {
            int nowPos = pos[i];
            if (nowPos == i) continue;

            if (nowPos != 0) {
                res.add(nowPos+1);
                // 对pos的0-nowPos排序，让这个数字到第一位
                // 分别对A和pos进行数字交换
                for (int k = 0; k<=nowPos/2; k++) {
                    int tmp = A[k];
                    pos[A[k]-1] = nowPos - k;
                    A[k] = A[nowPos-k];

                    pos[A[nowPos-k]-1] = k;
                    A[nowPos-k] = tmp;

                }
            }

            res.add(i+1);
            // 对pos的0-i排序
            // 分别对A和pos进行数字交换
            for (int k = 0; k<=i/2; k++) {
                int tmp = A[k];
                pos[A[k]-1] = i-k;
                A[k] = A[i-k];

                pos[A[i-k]-1] = k;
                A[i-k] = tmp;
            }
        }

        return res;
    }

}
