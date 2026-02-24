package string;

import java.util.*;

/**
 3. 无重复字符的最长子串

 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 示例 1:

 输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 示例 2:

 输入: "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:

 输入: "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 */
public class M3 {

    public static void main(String[] args) {
        new M3().lengthOfLongestSubstring("tmmzuxt");
    }

    // 2026/2/24
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> locator = new HashMap<>();

        int ans = 0;
        for (int i=0, len=0; i < s.length(); i++) {
            char c = s.charAt(i);
            len = locator.containsKey(c) ? Math.min(i-locator.get(c), len+1) : len + 1;
            locator.put(c, i);
            ans = Math.max(ans, len);
        }

        return ans;
    }

    // 2/25
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        Map<Integer, Integer> filter = new HashMap<>();
        for (int i = 0, count = 0; i<s.length(); i++) {
            int c = s.charAt(i);
            Integer idx = filter.get(c);
            if (idx != null) {
                for (int j = idx; j >= i-count; j--) {
                    filter.remove((int) s.charAt(j));
                }
                count = i - idx;
                filter.put(c, i);
            } else {
                filter.put(c, i);
                count++;
                result = Math.max(result, count);
            }
        }

        return result;
    }


}
