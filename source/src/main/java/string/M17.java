package string;

import java.util.ArrayList;
import java.util.List;

/**
17  电话号码的字母组合

 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



 示例:

 输入："23"
 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 说明:
 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

 */
public class M17 {

    public static void main(String[] args) {
        new M17().letterCombinations("");
//        new M6().lengthOfLongestSubstring("tmmzuxt");
    }

    char[][] mappings = new char[][] {{'a','b','c'}, {'d','e','f'},{'g','h','i'},{'j','k','l'},
            {'m','n','o'}, {'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}
    };

    // 3-4-20
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        for (int i = 0; i < digits.length(); i++) {
            letterCombinations(digits.charAt(i)-'2', result);
        }

        return result;
    }

    public void letterCombinations(int idx, List<String> result) {
        List<String> newCombinations = new ArrayList<>();

        if (result.isEmpty()) {
            for (char alpha : mappings[idx]) {
                result.add(alpha + "");
            }
            return;
        }

        for (String text : result) {
            for (char alpha : mappings[idx]) {
                newCombinations.add(text + alpha);
            }
        }
        result.clear();
        result.addAll(newCombinations);
    }

}
