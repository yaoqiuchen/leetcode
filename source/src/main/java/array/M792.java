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
        new M792().numMatchingSubseq("abcde", new String[] {"a","bb","acd","ace"});

    }

    // 2020-2-5
    public int numMatchingSubseq(String S, String[] words) {
        // key是字母，value是在string里该字母出现的每一个位子
        Map<Integer, List<Integer>> idx = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            int val = S.charAt(i) - 'a';
            List<Integer> line = idx.getOrDefault(val, new ArrayList<>());
            line.add(i);
            idx.put(val, line);
        }

        int count = 0;
        nextWord : for (String str : words) {
            // 如果str长度本身就大于S，那么直接丢弃，节省时间
            if (str.length() > S.length()) continue;

            int pos = 0;
            boolean match = true;
            nextChar: for (char character : str.toCharArray()) {
                // 如果找不到就直接跳到nextword，或者pos已经比数组最后一个元素下标还大了，也跳过
                List<Integer> line = idx.get(character-'a');
                if (line == null || pos > line.get(line.size()-1)) {
                    continue nextWord;
                }

                // 二分查找，找到第一个>=pos的数字
                int l = 0, h = line.size()-1;
                while (l <= h) {
                    int mid = (l+h)/2, val = line.get(mid);
                    if (val == pos) {
                        pos = val+1;
                        continue nextChar;
                    }
                    if (val < pos) {
                        l = mid+1;
                    } else {
                        h = mid-1;
                    }
                }
                // 结果只可能是l,h，或者是h+1
                int target = Integer.MAX_VALUE;
                if (l < line.size() && pos < line.get(l)) target = line.get(l);
                if (h >= 0 && pos < line.get(h)) target = Math.min(target, line.get(h));
                if (h+1 < line.size() && pos < line.get(h+1)) target = Math.min(target, line.get(h+1));
                if (target == Integer.MAX_VALUE) continue nextWord;
                pos = target+1;
            }
            count = match ? count + 1 : count;
        }
        return count;
    }

    public int numMatchingSubseq_(String S, String[] words) {
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
