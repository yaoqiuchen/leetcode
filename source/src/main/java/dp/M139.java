package dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

 说明：

 拆分时可以重复使用字典中的单词。
 你可以假设字典中没有重复的单词。
 示例 1：

 输入: s = "leetcode", wordDict = ["leet", "code"]
 输出: true
 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 示例 2：

 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 输出: true
 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
      注意你可以重复使用字典中的单词。
 示例 3：

 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 输出: false

 */
public class M139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> filter = wordDict.stream().collect(Collectors.toSet());

        int len = s.length();
        boolean[] dp = new boolean[len];

        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                // 如果是第一个数字，或者从0到j-1都是符合要求的
                // 那么只要j到i出现在字典类就可以
                if ((j==0 || dp[j-1]) && filter.contains(s.substring(j, i+1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len-1];
    }
}
