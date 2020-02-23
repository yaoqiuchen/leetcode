
package array;

import java.util.Arrays;

/**
 *
 * 1128. 等价多米诺骨牌对的数量
  
 给你一个由一些多米诺骨牌组成的列表 dominoes。

 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。

 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。

 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。

  

 示例：

 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 输出：1
  

 提示：

 1 <= dominoes.length <= 40000
 1 <= dominoes[i][j] <= 9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */
public class E1288 {

    public static void main(String[] args) {
        new E1288().numEquivDominoPairs(new int[][] {
                {1,2},{2,1},{2,1},{3,4},{5,6}
        });
//        new M1160().maxUncrossedLines(new int[] {3,8,1,3,2,1,8,9,0}, new int[] {3,8,1,3,2,1,8,9,0});
    }

    // 2020-2-23
    public int numEquivDominoPairs(int[][] dominoes) {
        // 将顺序变为前小后大
        for (int[] arr : dominoes) {
            if (arr[0] > arr[1]) {
                int tmp = arr[0];
                arr[0] = arr[1];
                arr[1] = tmp;
            }
        }

        // 排序
        Arrays.sort(dominoes, (a,b) -> {
            return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
        });

        int result = 0;
        for (int i = 0, count = 1; i < dominoes.length; i++) {
            if (i == dominoes.length-1 ||
                    dominoes[i+1][0] != dominoes[i][0] ||
                    dominoes[i+1][1] != dominoes[i][1]) {
                while (count >= 2) {
                    result += count-1;
                    count--;
                }
            } else {
                count++;
            }
        }

        return result;
    }

}
