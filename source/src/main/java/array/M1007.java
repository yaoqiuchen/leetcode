package array;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 1007 行相等的最少多米诺旋转
  
 在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。
 （一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）

 我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。

 返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。

 如果无法做到，返回 -1.

  

 示例 1：



 输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 输出：2
 解释：
 图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
 如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
 示例 2：

 输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
 输出：-1
 解释：
 在这种情况下，不可能旋转多米诺牌使一行的值相等。

 提示：

 1 <= A[i], B[i] <= 6
 2 <= A.length == B.length <= 20000

 */
public class M1007 {

    public static void main(String[] args) {
        new M1007().minDominoRotations(new int[] {1,2,1,1,1,2,2,2}, new int[] {2,1,2,2,2,2,2,2});
    }

    // 2020-2-15
    public int minDominoRotations(int[] A, int[] B) {
        int x = -1, y = -1;
        for (int i = 0; i < A.length; i++) {
            if (i == 0) {
                x = A[0];
                y = B[0];
            } else {
                x = (x != A[i] && x != B[i]) ? -1 : x;
                y = (y != A[i] && y != B[i]) ? -1 : y;
            }
        }

        if (x < 0 && y < 0) return -1;
        int[] dpX = new int[2];
        int[] dpY = new int[2];
        for (int i = 0; i < A.length; i++) {
            if (x > 0) {
                if (A[i] != x) dpX[0]++;
                if (B[i] != x) dpX[1]++;
            }
            if (y > 0) {
                if (A[i] != y) dpY[0]++;
                if (B[i] != y) dpY[1]++;
            }
        }

        return Math.min((x < 0 ? Integer.MAX_VALUE : Math.min(dpX[0], dpX[1])),
                (y < 0 ? Integer.MAX_VALUE : Math.min(dpY[0], dpY[1])));
    }

}
