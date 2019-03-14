package dp;

import java.util.ArrayList;
import java.util.List;

/**
 Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:

 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".


 Example 2:

 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class M647 {

    public int countSubstrings(String s) {
        if (s.length() <= 1) return s.length();

        int total = 1, len = s.length();
        // if dp[i][j] = true, it means that the sub-string from index i to j is palindromic string,
        boolean dp[][] = new boolean[len][len];
        dp[0][0] = true;

        for (int i = 1; i < s.length(); i++) {
            total++;
            dp[i][i] = true;
            char c = s.charAt(i);

            // the letter is same with previous one
            if (s.charAt(i - 1) == c) {
                total++;
                dp[i - 1][i] = true;
            }

            for (int j = i - 1; j > 0; j--) {
                if (dp[j][i - 1] && c == s.charAt(j-1)) {
                    total++;
                    dp[j-1][i] = true;
                }
            }
        }

        return total;
    }
}
