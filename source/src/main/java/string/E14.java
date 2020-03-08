package string;

/**
 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。

 示例 1:

 输入: ["flower","flow","flight"]
 输出: "fl"
 示例 2:

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。

 */
public class E14 {

    public static void main(String[] args) {
//        new E14().intToRoman(124);
//        new M6().lengthOfLongestSubstring("tmmzuxt");
    }

    // 3-4-20
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i >=0; i++) {
            Character text = null;
            for (String val : strs) {
                if (val.length() < i+1) {
                    return result.toString();
                }
                if (text == null) {
                    text = val.charAt(i);
                }

                if (!text.equals(val.charAt(i))) {
                    return result.toString();
                }
            }
            result.append(text + "");
        }

        return result.toString();
    }

}
