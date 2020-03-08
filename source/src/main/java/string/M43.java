package string;

import java.util.HashMap;
import java.util.Map;

/**
43.  字符串相乘

 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

 示例 1:

 输入: num1 = "2", num2 = "3"
 输出: "6"
 示例 2:

 输入: num1 = "123", num2 = "456"
 输出: "56088"
 说明：

 num1 和 num2 的长度小于110。
 num1 和 num2 只包含数字 0-9。
 num1 和 num2 均不以零开头，除非是数字 0 本身。
 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

 */
public class M43 {

    public static void main(String[] args) {
        new M43().multiply("8", "32");
//        new E14().intToRoman(124);
//        new M6().lengthOfLongestSubstring("tmmzuxt");
    }

    // 3-8-20
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String result = "";
        for (int i = 1, n = num2.length(); i <= n; i++) {
            StringBuilder sum = new StringBuilder(num1);
            Integer num = num2.charAt(n-i) - '0'; // 当前要乘以的数字
            if (num == 0) continue;

            // 将数字挨个乘起来
            int increase = 0;
            for (int j = 1, m = num1.length(); j <= m; j++) {
                String val = (Integer.parseInt(sum.charAt(m-j)+"") * num + increase) + "";
                if (val.length() > 1) {
                    increase = val.charAt(0) - '0';
                    sum.replace(m-j, m-j+1, val.charAt(1) + "");
                } else {
                    increase = 0;
                    sum.replace(m-j, m-j+1, val.charAt(0) + "");
                }
            }
            if (increase > 0) {
                sum.insert(0, increase + "");
            }

            for (int j = 1; j < i; j++) {
                sum.append(0);
            }
            result = sum(result, sum.toString());
        }
        return result.isEmpty() ? "0" : result;
    }

    public String sum(String val1, String val2) {
        if (val1.isEmpty()) {
            return val2;
        }
        if (val2.isEmpty()) {
            return val1;
        }

        int idx1 = 1, increase = 0;
        String result = "";
        while (idx1 <= val1.length() || idx1 <= val2.length()) {
            int val = (idx1 <= val1.length() ? val1.charAt(val1.length() - idx1) - '0' : 0) +
                    (idx1 <= val2.length() ? val2.charAt(val2.length() - idx1) - '0' : 0) +
                    increase;
            increase = val / 10;
            idx1++;
            result = (val % 10) + result;
        }
        if (increase > 0) {
            result = increase + result;
        }
        return result;
    }
}
