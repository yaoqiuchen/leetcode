package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 795. 区间子数组个数

 给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。

 求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数。

 例如 :
 输入:
 A = [2, 1, 4, 3]
 L = 2
 R = 3
 输出: 3
 解释: 满足条件的子数组: [2], [2, 1], [3].
 注意:

 L, R  和 A[i] 都是整数，范围在 [0, 10^9]。
 数组 A 的长度范围在[1, 50000]。
 */
public class M795 {
    public static void main(String[] args) {
        new M795().numSubarrayBoundedMax(new int[] {801,341,668,598,98,241}, 658, 719);
    }

    // 2020-2-5
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        // last是距离最近的满足的数字下标
        int l = 0, h = 0, res = 0, max = A[0], last = -1;
        while (l <= h && h < A.length) {
            // 如果满足条件，就+1
            // 满足条件
            if (max >= L && max <= R) {
                // 如果高位元素满足情况，那就和之前的数字都可以累加
                if (A[h] >= L && max <= R) {
                    res+=h-l+1;
                    last = h;
                } else {
                    res+= last-l+1;
                }
                h++;
                max = (h < A.length) ? Math.max(max, A[h]) : 0;
                continue;
            }

            // 如果不满足，则把l和h通通重置
            // 如果max大于R，那么l和h都自增，否则只有h自增
            if (max > R) {
                l = h+1;
                last = h;
            }
            h++;
            max = (h < A.length) ? A[h] : 0;
        }

        return res;
    }

    // 挨个遍历，如果i>R则重置
    // len是从上一个>R的数字之后遍历过的数组长度
    // 当循环到i时，算出在i之前（包括i）最近的一个满足条件的下标j，如果max满足要求，则count += (len - (i - j)
    public int numSubarrayBoundedMax_(int[] A, int L, int R) {
        int max = Integer.MIN_VALUE, maxIdx = 0, count = 0, len = 0;
        for (int i =0; i < A.length; i++) {
            if (A[i] > R) {
                len = 0;
                max = Integer.MIN_VALUE;
                continue;
            }

            len++;
            max = Math.max(max, A[i]);
            if (A[i] >= L && A[i] <= R) {
                maxIdx = i;
            }
            if (max >= L && max <= R) {
                count += (len - (i-maxIdx));
            }
        }

        return count;
    }
}
