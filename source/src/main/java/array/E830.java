package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 830. 较大分组的位置

 在一个由小写字母构成的字符串 S 中，包含由一些连续的相同字符所构成的分组。

 例如，在字符串 S = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。

 我们称所有包含大于或等于三个连续字符的分组为较大分组。找到每一个较大分组的起始和终止位置。

 最终结果按照字典顺序输出。

 示例 1:

 输入: "abbxxxxzzy"
 输出: [[3,6]]
 解释: "xxxx" 是一个起始于 3 且终止于 6 的较大分组。
 示例 2:

 输入: "abc"
 输出: []
 解释: "a","b" 和 "c" 均不是符合要求的较大分组。
 示例 3:

 输入: "abcdddeeeeaabbbcd"
 输出: [[3,5],[6,9],[12,14]]
 说明:  1 <= S.length <= 1000
 */
public class E830 {

    public static void main(String[] args) {
        new E830().largeGroupPositions("abcdddeeeeaabbbcd");
    }

    // 2020-2-5
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0, len = 0, target = S.charAt(0); i < S.length(); i++) {
            int val = S.charAt(i);
            if (val == target) {
                len++;
                // 下一个如果还是连续数字，那么先不做动作
                if (i+1 < S.length() && S.charAt(i+1) == target) {
                    continue;
                }
                if (len >= 3) {
                    res.add(Arrays.asList(i-len+1, i));
                }
                len = 0;
            } else {
                len = 1;
                target = val;
            }
        }

        return res;
    }


    public List<List<Integer>> largeGroupPositions_(String S) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0, count = 0, c = S.charAt(0); i <= S.length(); i++) {
            if (i < S.length() && c == S.charAt(i)) {
                count++;
            } else {
                if (count >= 3) {
                    List<Integer> entry = new ArrayList<>();
                    entry.add(i-count);
                    entry.add(i-1);
                    res.add(entry);
                }
                count = 1;
                c = i < S.length() ? S.charAt(i) : 1;
            }
        }

        return res;
    }
}
