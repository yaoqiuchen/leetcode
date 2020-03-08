package string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 567. 字符串的排列

 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

 换句话说，第一个字符串的排列之一是第二个字符串的子串。

 示例1:

 输入: s1 = "ab" s2 = "eidbaooo"
 输出: True
 解释: s2 包含 s1 的排列之一 ("ba").
  

 示例2:

 输入: s1= "ab" s2 = "eidboaoo"
 输出: False
  

 注意：

 输入的字符串只包含小写字母
 两个字符串的长度都在 [1, 10,000] 之间

 */
public class M557 {

    public static void main(String[] args) {
        new M557().checkInclusion("adc", "dcda");
//        new E14().intToRoman(124);
//        new M6().lengthOfLongestSubstring("tmmzuxt");
    }

    // 3-8-20
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> s1Counter = new HashMap<>();
        for (char c : s1.toCharArray()) {
            int count = s1Counter.getOrDefault(c, 0) + 1;
            s1Counter.put(c, count);
        }

        int l = 0, h = 0;
        boolean expand = true;
        Map<Character, Integer> s2Counter = new HashMap<>();
        while (l < s2.length() && h < s2.length()) {
            if (expand) {
                char val = s2.charAt(h);
                // s2中的数字没出现过
                if (!s1Counter.containsKey(val)) {
                    h++;
                    l = h;
                    s2Counter.clear();
                    continue;
                }
                int count = s2Counter.getOrDefault(val, 0) + 1;
                s2Counter.put(val, count);
                // 如果计数超出范围，那么就缩减窗口
                if (s1Counter.get(val) >= count) {
                    h++;
                    if (h - l == s1.length()) {
                        return true;
                    }
                } else {
                    expand = false;
                }

            } else {
                char val = s2.charAt(l);
                int count = s2Counter.get(val) - 1;
                s2Counter.put(val, count);
                if (count == s1Counter.get(val)) {
                    l++;
                    h++;
                    expand = true;
                } else {
                    l++;
                }
            }
        }

        return false;
    }

}
