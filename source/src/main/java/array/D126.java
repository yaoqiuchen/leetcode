package array;

import java.util.*;

/**
 126. 单词接龙 II

 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：

 每次转换只能改变一个字母。
 转换过程中的中间单词必须是字典中的单词。
 说明:

 如果不存在这样的转换序列，返回一个空列表。
 所有单词具有相同的长度。
 所有单词只由小写字母组成。
 字典中不存在重复的单词。
 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 示例 1:

 输入:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 输出:
 [
 ["hit","hot","dot","dog","cog"],
   ["hit","hot","lot","log","cog"]
 ]
 示例 2:

 输入:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 输出: []

 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/word-ladder-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class D126 {

    public static void main(String[] args) {
//        new D126().findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog"));
        new D126().findLadders("a", "c", Arrays.asList("a","b","c"));

    }

    // 官方答案，我放弃了
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return ans;
        }
        // 利用 BFS 得到所有的邻居节点
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        bfs(beginWord, endWord, wordList, map);
        ArrayList<String> temp = new ArrayList<String>();
        // temp 用来保存当前的路径
        temp.add(beginWord);
        findLaddersHelper(beginWord, endWord, map, temp, ans);
        return ans;
    }

    private void findLaddersHelper(String beginWord, String endWord, HashMap<String, ArrayList<String>> map,
                                   ArrayList<String> temp, List<List<String>> ans) {
        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<String>(temp));
            return;
        }
        // 得到所有的下一个的节点
        ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<String>());
        for (String neighbor : neighbors) {
            temp.add(neighbor);
            findLaddersHelper(neighbor, endWord, map, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }

    //利用递归实现了双向搜索
    private void bfs(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map) {
        Set<String> set1 = new HashSet<String>();
        set1.add(beginWord);
        Set<String> set2 = new HashSet<String>();
        set2.add(endWord);
        Set<String> wordSet = new HashSet<String>(wordList);
        bfsHelper(set1, set2, wordSet, true, map);
    }

    // direction 为 true 代表向下扩展，false 代表向上扩展
    private boolean bfsHelper(Set<String> set1, Set<String> set2, Set<String> wordSet, boolean direction,
                              HashMap<String, ArrayList<String>> map) {
        //set1 为空了，就直接结束
        //比如下边的例子就会造成 set1 为空
    /*	"hot"
		"dog"
		["hot","dog"]*/
        if(set1.isEmpty()){
            return false;
        }
        // set1 的数量多，就反向扩展
        if (set1.size() > set2.size()) {
            return bfsHelper(set2, set1, wordSet, !direction, map);
        }
        // 将已经访问过单词删除
        wordSet.removeAll(set1);
        wordSet.removeAll(set2);

        boolean done = false;

        // 保存新扩展得到的节点
        Set<String> set = new HashSet<String>();

        for (String str : set1) {
            //遍历每一位
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();

                // 尝试所有字母
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if(chars[i] == ch){
                        continue;
                    }
                    chars[i] = ch;

                    String word = new String(chars);

                    // 根据方向得到 map 的 key 和 val
                    String key = direction ? str : word;
                    String val = direction ? word : str;

                    ArrayList<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();

                    //如果相遇了就保存结果
                    if (set2.contains(word)) {
                        done = true;
                        list.add(val);
                        map.put(key, list);
                    }

                    //如果还没有相遇，并且新的单词在 word 中，那么就加到 set 中
                    if (!done && wordSet.contains(word)) {
                        set.add(word);
                        list.add(val);
                        map.put(key, list);
                    }
                }
            }
        }

        //一般情况下新扩展的元素会多一些，所以我们下次反方向扩展  set2
        return done || bfsHelper(set2, set, wordSet, !direction, map);

    }

//    作者：windliang
//    链接：https://leetcode-cn.com/problems/word-ladder-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-3/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。




    // 超出时间限制，但应该是个正确答案
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> filter = new HashSet<>(wordList);

        List<List<String>> res = new ArrayList<>();
        // 如果不用变化，或者字典里不包括endWord，直接返回
        if (beginWord.equals(endWord) || !filter.contains(endWord)) {
            return res;
        }

        // 找出所有可能的变化结果，如果找不到，那就直接中断
        List<String> next = next(filter, beginWord);
        if (next == null || next.isEmpty()) return res;

        // 将第一波返回结果组装成输入参数
        next.stream().forEach(e -> {
            List<String> arr = new ArrayList();
            arr.add(beginWord);
            arr.add(e);
            res.add(arr);
        });

        BFS(endWord, filter, res);
        return res;
    }

    public boolean BFS(String endWord, Set<String> filter, List<List<String>> res) {
        Integer min = Integer.MAX_VALUE;

        // 如果包含endWord
        List<List<String>> replace = new ArrayList<>();
        for (Iterator<List<String>> i = res.iterator(); i.hasNext(); ) {
            List<String> line = i.next();
            // 如果target找到了就跳到下一个
            if (line.get(line.size()-1).equals(endWord)) {
                min = Math.min(min, line.size());
                continue;
            }
            // 如果前面已经有target找到了，而当前节点不满足条件，直接删除
            else if (min != Integer.MAX_VALUE) {
                i.remove();
                continue;
            }

            // 下一个匹配找不到，就跳过当前节点
            String before = line.get(line.size()-1);
            List<String> next = next(filter, before);
            if (next == null) {
                i.remove();
                continue;
            }

            for (String nextTxt : next) {
                if (!line.contains(nextTxt)) {
                    List<String> newLine = new ArrayList<>(line);
                    newLine.add(nextTxt);
                    replace.add(newLine);
                }
            }
            i.remove();
        }

        // 如果最小的结果算出来了，那么去除所有当前list当中size大于这个数的结果
        if (min < Integer.MAX_VALUE) {
            Integer len = min;
            res.stream().filter(e -> e.size() > len);
            return true;
        }

        res.clear();
        res.addAll(replace);
        return BFS(endWord, filter, res);
    }

    public List<String> next(Set<String> filter, String before) {
        List<String> next = new ArrayList<>();
        for (String target : filter) {
            if (before.length() != target.length()) {
                continue;
            }
            int diffCount = 0;
            for (int i = 0; i < target.length(); i++) {
                if (target.charAt(i) != before.charAt(i)) {
                    diffCount++;
                }
                if (diffCount > 1) {
                    break;
                }
            }
            if (diffCount == 1)
                next.add(target);
        }
        return next.isEmpty() ? null : next;
    }

}
