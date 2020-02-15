package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 1002 查找常用字符
  
 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

 你可以按任意顺序返回答案。

 示例 1：

 输入：["bella","label","roller"]
 输出：["e","l","l"]
 示例 2：

 输入：["cool","lock","cook"]
 输出：["c","o"]
  

 提示：

 1 <= A.length <= 100
 1 <= A[i].length <= 100
 A[i][j] 是小写字母

 */
public class E1002 {

    public static void main(String[] args) {
        new E1002().commonChars(new String[] {"bella","label","roller"});
    }

    // 2020-2-15
    public List<String> commonChars(String[] A) {
        List<String> result = new ArrayList<>();
        int[] dp = new int[26];
        int round = 1;
        for (String str : A) {
            int[] dp2 = new int[26];
            for (char c : str.toCharArray()) {
                if (round > 1) {
                    dp2[c-'a']++;
                } else {
                    dp[c-'a']++;
                }
            }
            if (round > 1)
                for (int i = 0; i < 26; i++) {
                    dp[i] = Math.min(dp[i], dp2[i]);
                }
            round++;
        }

        for (int i = 0; i < 26; i++) {
            if (dp[i] == 0) continue;
            char val =  (char) ('a' + i);
            for (int j = 0; j < dp[i]; j++) {
                result.add(val + "");
            }
        }

        return result;
    }

}
