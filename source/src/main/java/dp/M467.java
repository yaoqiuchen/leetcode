package dp;

/**
 * 环绕字符串中唯一的子字符串
 *
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s 看起来是这样的：
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....". 

 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，
 你需要输出字符串 s 中 p 的不同的非空子串的数目。 

 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。

  

 示例 1:

 输入: "a"
 输出: 1
 解释: 字符串 S 中只有一个"a"子字符。
  

 示例 2:

 输入: "cac"
 输出: 2
 解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
  

 示例 3:

 输入: "zab"
 输出: 6
 解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/unique-substrings-in-wraparound-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M467 {

    public static int findSubstringInWraproundString2(String p) {
        if (p.length() <= 1) return p.length();
        // 以i结尾的字段最长子串长度
        int[] dp = new int[26];
        dp[p.charAt(0)-'a'] = 1;

        int count = 1, len = 1;
        for (int i = 1; i < p.length(); i++) {
            char c = p.charAt(i);
            char prev = p.charAt(i - 1);
            len = (c == prev + 1 || c == 'a' && prev == 'z') ? len + 1 : 1;

            if (dp[c-'a'] < len) {
                count += len - dp[c-'a'];
                dp[c-'a'] = len;
            }
        }

        return count;
    }

    public static int findSubstringInWraproundString(String p) {
        if (p.length() <= 1) return p.length();

        // the value of dp[i] is the longest length of the String ends with char i
        int dp[] = new int[26];
        dp[p.charAt(0) - 'a'] = 1;

        int result = 1;
        int len = 1;
        for (int i = 1; i < p.length(); i++) {
            int c = p.charAt(i);
            int prev = p.charAt(i-1);
            len = (c == prev + 1 || (c == 'a' && prev == 'z')) ? len + 1 : 1;

            int idx = c - 'a';
            if (dp[idx] < len) {
                result += len - dp[idx];
                dp[idx] = len;
            }
        }
        return result;
    }
}
