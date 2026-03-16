package string;

/**
 8. 字符串转换整数 (atoi)

 请你来实现一个 atoi 函数，使其能将字符串转换成整数。

 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

 在任何情况下，若函数不能进行有效的转换时，请返回 0。

 说明：

 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

 示例 1:

 输入: "42"
 输出: 42
 示例 2:

 输入: "   -42"
 输出: -42
 解释: 第一个非空白字符为 '-', 它是一个负号。
      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 示例 3:

 输入: "4193 with words"
 输出: 4193
 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 示例 4:

 输入: "words and 987"
 输出: 0
 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 因此无法执行有效的转换。
 示例 5:

 输入: "-91283472332"
 输出: -2147483648
 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
      因此返回 INT_MIN (−231) 。

 */
public class M8 {

    public static void main(String[] args) {
        new M8().myAtoi2("+1095502006p8");
    }

    // 2026/3/16 - 人工出品
    public int myAtoi2(String str) {
        if (str.isEmpty() || str.trim().isEmpty()) {
            return 0;
        }
        StringBuilder res = new StringBuilder();
        str = str.trim();

        boolean isNegative = str.charAt(0) == '-';
        if (isNegative || str.charAt(0) == '+') {
            str = str.substring(1);
        }

        int cut = 0;
        boolean started = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0' && !started && (i == 0 || str.charAt(i-1) == '0')) {
                cut++;
            } else {
                break;
            }
        }
        str = str.substring(cut);

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c > '9' || c < '0') {
                break;
            }
            res.append(c);
        }

        System.out.println(res.toString());
        String min = (Integer.MIN_VALUE + "").substring(1);
        String max = Integer.MAX_VALUE + "";

        if (res.length() > min.length()) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        if (res.length() == min.length()) {
            for (int i = 0; i < res.length(); i++) {
                char compare = isNegative ? min.charAt(i) : max.charAt(i);
                if (res.charAt(i) < compare) {
                    break;
                }
                if (res.charAt(i) > compare) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
            }
        }

        if (isNegative && res.length() > 0) {
            res.insert(0, "-");
        }

        return res.length() == 0 ? 0 : Integer.parseInt(res.toString());
    }

    // 2026/3/16 - AI出品，确实优雅
    public int myAtoi3(String str) {
        int i = 0, n = str.length();

        // 跳过前导空格
        while (i < n && str.charAt(i) == ' ') i++;
        if (i == n) return 0;

        // 读取符号
        int sign = 1;
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            if (str.charAt(i) == '-') sign = -1;
            i++;
        }

        // 读取数字，乘10前预判溢出（MAX/10=214748364，末位7/8）
        int result = 0;
        final int LIMIT = Integer.MAX_VALUE / 10;
        while (i < n && Character.isDigit(str.charAt(i))) {
            int digit = str.charAt(i++) - '0';
            if (result > LIMIT || (result == LIMIT && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
        }

        return result * sign;
    }


    // 2/25
    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        Boolean isNegative = null;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char val = str.charAt(i);
            // 是0-9则拼起来
            if (val - '0' >= 0 && val-'0'<=9) {
                number.append(Integer.parseInt(val+""));
//                if (number.length() > 0 || val -'0' > 0) {
//                }
            } else {
                //如果已经有数字了，那就直接中断
                // 或者已经是负数
                // 或者负数后跟空格
                if (number.length() > 0) {
                    break;
                }
                if (val == '-' || val == '+') {
                    if (isNegative != null || number.length() > 0) break;
                    isNegative = val == '-';
                    continue;
                }
                if (val != ' ' || isNegative != null) {
                    break;
                }
            }
        }

        if (number.length() == 0) {
            return 0;
        }

        isNegative = isNegative != null && isNegative;
        String convertedNumber = number.toString().replaceAll("^0+", "");
        if (convertedNumber == null || convertedNumber.length() == 0) {
            return 0;
        }
        if (convertedNumber.length() > 10) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        Long converted = Long.parseLong(convertedNumber);
        if (isNegative && converted-1 > Integer.MAX_VALUE) {
            return Integer.MIN_VALUE;
        } else if (!isNegative && converted > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return Integer.valueOf(isNegative ? "-" + convertedNumber : convertedNumber);
        }
    }


}
