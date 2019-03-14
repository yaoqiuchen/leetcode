package dp;

/**
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this:
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

 Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s.
 In particular, your input is the string p and you need to output the number of different non-empty substrings of p in
 the string s.

 Note: p consists of only lowercase English letters and the size of p might be over 10000.
 */
public class M467 {

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
