package string;

import java.util.ArrayList;
import java.util.List;

/**
93 复原IP地址

 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

 示例:

 输入: "25525511135"
 输出: ["255.255.11.135", "255.255.111.35"]

 */
public class M93 {

    public static void main(String[] args) {
        new M93().restoreIpAddresses("25525511135");
//        new E14().intToRoman(124);
//        new M6().lengthOfLongestSubstring("tmmzuxt");
    }


    public List<String> restoreIpAddresses(String s) {
        return restoreIpAddresses(s, 0, 4, "");
    }

    public List<String> restoreIpAddresses(String s, int idx, int left, String start) {
        List<String> result = new ArrayList<>();
        if (left == 0) {
            if (idx == s.length()) {
                result.add(start);
            }
            return result;
        }

        for (int i = idx; i < s.length() && i < idx+3; i++) {
            int num = Integer.parseInt(s.substring(idx, i+1));
            if (s.charAt(idx) == '0' && i - idx > 0) {
                return result;
            }
            if (num <= 255) {
                result.addAll(restoreIpAddresses(s, i+1, left-1,
                        start.isEmpty() ? num+"" : start+"."+num));
            }
        }
        return result;
    }
}
