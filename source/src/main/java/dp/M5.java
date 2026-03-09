package dp;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

 示例 1：

 输入: "babad"
 输出: "bab"
 注意: "aba" 也是一个有效答案。
 示例 2：

 输入: "cbbd"
 输出: "bb"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M5 {

    public static String longestPalindrome2(String s) {
        int len = s.length();
        //dp[n][m] means if s.substring(n, m) is a palindromic string
        boolean[][] dp = new boolean[len][len];

        int maxLen = 1;
        String res = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            for (int j = i-1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j][i] = i == j+1 ? true : dp[j+1][i-1];
                    if (dp[j][i] && i-j+1 > maxLen) {
                        maxLen = i-j+1;
                        res = s.substring(j, i+1);
                    }
                }
            }
        }

        return res;
    }


    public static String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }

        int max = 1;
        String result = s.charAt(0) + "";

        // int[i][j]表示从i到j的最长回文子串长度
        int[][] dp = new int[s.length()][s.length()];
        dp[0][0] = 1;

        for (int i = 1; i < s.length(); i++) {
            dp[i][i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                boolean isSame = s.charAt(j) == s.charAt(i);
                if (isSame) {
                    // j和i之间还有别的字
                    if (j != i - 1) {
                        dp[j][i] = dp[j+1][i-1] > 0 ? dp[j+1][i-1] + 2 : 0;
                    }
                    // j和i之间没有别的字
                    else {
                        dp[j][i] = 2;
                    }

                    if (max < dp[j][i]) {
                        max = dp[j][i];
                        result = s.substring(j, i+1);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        longestPalindrome("abcda");
    }
}
