package array;

/**
 *
 * 926. 将字符串翻转到单调递增
 *
 如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，
 那么该字符串是单调递增的。

 我们给出一个由字符 '0' 和 '1' 组成的字符串 S，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。

 返回使 S 单调递增的最小翻转次数。

  

 示例 1：

 输入："00110"
 输出：1
 解释：我们翻转最后一位得到 00111.
 示例 2：

 输入："010110"
 输出：2
 解释：我们翻转得到 011111，或者是 000111。
 示例 3：

 输入："00011000"
 输出：2
 解释：我们翻转得到 00000000。
  

 提示：

 1 <= S.length <= 20000
 S 中只包含字符 '0' 和 '1'

 */
public class M926 {

    public static void main(String[] args) {
        new M926().minFlipsMonoIncr("123");
    }

    // 2020-2-9
    public int minFlipsMonoIncr(String S) {
        int n = S.length();
        // one是前面的1， zero是后面的0
        int[] one = new int[n];
        int[] zero = new int[n];

        // 求出i前面的1
        for (int i = 0, count = 0; i < n; i++) {
            one[i] = count;
            if (S.charAt(i) == '1') {
                count++;
            }
        }

        // 求出i后面的0
        for (int i = n-1, count = 0; i >= 0; i--) {
            zero[i] = count;
            if (S.charAt(i) == '0') {
                count++;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, one[i] + zero[i]);
        }

        return min;
    }

}
