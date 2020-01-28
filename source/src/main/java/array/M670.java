package array;

import java.util.Arrays;

/**
 670. 最大交换

 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。

 示例 1 :

 输入: 2736
 输出: 7236
 解释: 交换数字2和数字7。
 示例 2 :

 输入: 9973
 输出: 9973
 解释: 不需要交换。
 注意:

 给定数字的范围是 [0, 108]
 */
public class M670 {

    public static void main(String[] args) {
        new M670().maximumSwap(2736);
    }


    // 2020-1-28
    public int maximumSwap(int num) {
        char input[] = new String(num + "").toCharArray();
        int len = input.length;
       ;
        char[] sorted = Arrays.copyOf(input, len);
        Arrays.sort(sorted);

        int first = Integer.MAX_VALUE, last = Integer.MAX_VALUE, target = 0;
        boolean found = false;
        for (int j = 0; j < len; j++) {
            if (!found && input[j] != sorted[len-j-1]) {
                first = j;
                found = true;
                target = sorted[len-j-1];
                continue;
            }
            if (found && input[j] == target) {
                last = j;
            }
        }

        if (found) {
            char tmp = input[first];
            input[first] = input[last];
            input[last] = tmp;
        }

        return Integer.parseInt(new String(input));
    }


    // 动态规划的思想，
    // 第一次循环，找到比dp[i]表示比当前数字最大并且处于最远端的数字下标
    // 第二次循环，交换，如果input[i] == input[dp[i]]被认为跳过
    public int maximumSwap_(int num) {
        char input[] = new String(num + "").toCharArray();
        int n = input.length - 1;

        int[] dp = new int[n+1];
        dp[n] = n;
        for (int i = n - 1; i >= 0; i--) {
            if (input[i] > input[dp[i+1]]) {
                dp[i] = i;
            } else {
                dp[i] = dp[i+1];
            }
        }

        for (int i = 0; i < n; i++) {
            if (dp[i] != i && input[i] != input[dp[i]]) {
                char change = input[i];
                input[i] = input[dp[i]];
                input[dp[i]] = change;
                break;
            }
        }
        return Integer.parseInt(new String(input));
    }
}
