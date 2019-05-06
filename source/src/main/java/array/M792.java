package array;

import java.util.*;

/**
 792. 匹配子序列的单词数

 给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。

 示例:
 输入:
 S = "abcde"
 words = ["a", "bb", "acd", "ace"]
 输出: 3
 解释: 有三个是 S 的子序列的单词: "a", "acd", "ace"。
 注意:

 所有在words和 S 里的单词都只由小写字母组成。
 S 的长度在 [1, 50000]。
 words 的长度在 [1, 5000]。
 words[i]的长度在[1, 50]。
 */
public class M792 {

    public static void main(String[] args) {
        new M792().numMatchingSubseq("abcde", new String[] {"a","bb","adc","ace"});

    }

    public int numMatchingSubseq(String S, String[] words) {
        Map<Integer, List<Integer>> filter = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            int idx = S.charAt(i) - 'a';
            if (filter.containsKey(idx)) {
                filter.get(idx).add(i);
            } else {
                List<Integer> arr = new ArrayList<>();
                arr.add(i);
                filter.put(idx, arr);
            }
        }

        int count = 0;
        out : for (String s : words) {
            int step = -1;
            next : for (int i = 0; i < s.length(); i++) {
                List<Integer> list = filter.get(s.charAt(i) - 'a');
                if (list == null || list.isEmpty()) {
                    continue out;
                }
                for (int idx : list) {
                    if (idx > step) {
                        step = idx;
                        continue next;
                    }
                }
                continue out;
            }
            count++;
        }

        return count;
    }

}
