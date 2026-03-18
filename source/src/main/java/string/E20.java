package string;

import java.util.*;

/**
20 有效的括号

 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。

 示例 1:

 输入: "()"
 输出: true
 示例 2:

 输入: "()[]{}"
 输出: true
 示例 3:

 输入: "(]"
 输出: false
 示例 4:

 输入: "([)]"
 输出: false
 示例 5:

 输入: "{[]}"
 输出: true

 */
public class E20 {

    public static void main(String[] args) {
        new E20().isValid("");
//        new M6().lengthOfLongestSubstring("tmmzuxt");
    }

    // 2026/3/18
    public boolean isValid2(String s) {
        int len = s.length();
        if (len % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }
            char c2 = stack.pop();
            if (c == '}' && c2 != '{' || c == ']' && c2 != '[' || c == ')' && c2 != '(') {
                return false;
            }
        }

        return stack.isEmpty();
    }

    // 3-5-20
    public boolean isValid(String s) {
        List<Character> left = Arrays.asList('{', '(', '[');
        List<Character> right = Arrays.asList('}', ')', ']');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char val = s.charAt(i);
            if (left.indexOf(val) >= 0) {
                stack.push(val);
            } else {
                if (stack.isEmpty()) return false;

                int idx = right.indexOf(val);
                char pair = stack.pop();
                if (pair != left.get(idx)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
