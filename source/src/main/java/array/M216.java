package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 216. 组合总和 III

 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

 说明：

 所有数字都是正整数。
 解集不能包含重复的组合。
 示例 1:

 输入: k = 3, n = 7
 输出: [[1,2,4]]
 示例 2:

 输入: k = 3, n = 9
 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class M216 {

    public static void main(String[] args) {
        new M216().DFS(3, 7);
    }


    public List<List<Integer>> DFS(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n <= 0) return res;

        LinkedList<Integer> tmp = new LinkedList();
        return DFS(k, n, 1, tmp);
    }


    public List<List<Integer>> DFS(int k, int n, int start, LinkedList<Integer> tmp) {
        List<List<Integer>> _tmp = new ArrayList<>();
        if (n == 0 && k == 0) {
            _tmp.add(new ArrayList(tmp));
            return _tmp;
        }
        // 如果数字大于9了说明前面9个数字都用完了
        // 如果n<0说明超了
        // 如果n<start，说明后面的数字都会超
        if (k > 9 || n <= 0 || n < start) {
            return null;
        }

        for (int i = start; i <= 9; i++) {
            tmp.addLast(i);
            List<List<Integer>> conb = DFS(k-1, n-i, i+1, tmp);
            if (conb != null) {
                _tmp.addAll(conb);
            }
            tmp.removeLast();
        }
        return _tmp;
    }







    public List<List<Integer>> combinationSum3_(int k, int n) {
        if (k <= 0 || n <= 0) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        find(1, k, n, new LinkedList<>(), res);
        return res;
    }

    public void find(int start, int k, int n, LinkedList<Integer> input, List<List<Integer>> res) {
        if (k < 0) return;
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(input));
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (n < i) return;

            input.addLast(i);
            find(i + 1, k-1, n - i, input, res);
            input.removeLast();
        }
    }

}
