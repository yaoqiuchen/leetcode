package string;

import java.util.HashMap;
import java.util.Map;

/**
6 - Z 字形变换

 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

 L   C   I   R
 E T O E S I I G
 E   D   H   N
 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

 请你实现这个将字符串进行指定行数变换的函数：

 string convert(string s, int numRows);
 示例 1:

 输入: s = "LEETCODEISHIRING", numRows = 3
 输出: "LCIRETOESIIGEDHN"
 示例 2:

 输入: s = "LEETCODEISHIRING", numRows = 4
 输出: "LDREOEIIECIHNTSG"
 解释:

 L     D     R
 E   O E   I I
 E C   I H   N
 T     S     G

 */
public class M6 {

    public static void main(String[] args) {
        new M6().convert("LEETCODEISHIRING", 4);
//        new M6().lengthOfLongestSubstring("tmmzuxt");
    }

    // 3-2-20
    public String convert(String s, int numRows) {
        if (s == null || s.isEmpty() || numRows >= s.length() || numRows <= 1) {
            return s;
        }

        StringBuilder text = new StringBuilder();
        int gap = (numRows-1) * 2, gap1 = gap, gap2 = 0;
        for (int i = 0; i < numRows; i++, gap1-=2, gap2+=2) {
            int idx = i;
            boolean toggle = false;
            // 对于第一行，和最后一行的数字
            if (i == 0 || i == numRows-1) {
                while (idx < s.length()) {
                    text.append(s.charAt(idx));
                    idx += gap;
                }
            }
            // 对于其他行的数字
            else {
                while (idx < s.length()) {
                    text.append(s.charAt(idx));
                    idx += (toggle ? gap2 : gap1);
                    toggle = !toggle;
                }
            }
        }

        return text.toString();
    }

}
