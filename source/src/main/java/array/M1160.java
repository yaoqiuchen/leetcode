
package array;

import java.util.Arrays;

/**
 *
 * 1160 拼写单词
  
 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。

 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。

 注意：每次拼写时，chars 中的每个字母都只能用一次。

 返回词汇表 words 中你掌握的所有单词的 长度之和。

  

 示例 1：

 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 输出：6
 解释：
 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 示例 2：

 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 输出：10
 解释：
 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
  

 提示：

 1 <= words.length <= 1000
 1 <= words[i].length, chars.length <= 100
 所有字符串中都仅包含小写英文字母

 */
public class M1160 {

    public static void main(String[] args) {
//        new M1160().maxUncrossedLines(new int[] {3,8,1,3,2,1,8,9,0}, new int[] {3,8,1,3,2,1,8,9,0});
    }

    // 2020-2-22
    public int countCharacters(String[] words, String chars) {
        int[] dp = new int[26];
        for (char alpha : chars.toCharArray()) {
            dp[alpha-'a']++;
        }

        int result = 0;
        skip: for (int i = 0; i < words.length; i++) {
            int[] counter = new int[26];
            for (char alpha : words[i].toCharArray()) {
                counter[alpha-'a']++;
                if (counter[alpha-'a'] > dp[alpha-'a'])
                    continue skip;
            }
            result += words[i].length();
        }

        return result;
    }

}
