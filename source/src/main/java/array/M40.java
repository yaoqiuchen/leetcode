package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

 candidates 中的每个数字在每个组合中只能使用一次。

 说明：

 所有数字（包括目标数）都是正整数。
 解集不能包含重复的组合。
 示例 1:

 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 所求解集为:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 示例 2:

 输入: candidates = [2,5,2,1,2], target = 5,
 所求解集为:
 [
 [1,2,2],
 [5]
 ]
 */
public class M40 {

    public static void main(String[] args) {
        new M40().combinationSum(new int[] { 2,2}, 4);
    }



    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> tmp = new LinkedList<>();

        Arrays.sort(candidates);
        DFS(candidates, target, 0, tmp, res);
        return res;
    }

    // 返回true/false表示是否已经完成了所有的组合
    public boolean DFS(int[] candidates, int target, int start, LinkedList<Integer> tmp, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return true;
        }

        int n = candidates.length;
        if (start >= n || target < 0) {
            return false;
        }

        boolean hit = false;
        for (int i = start; i < n; i++) {
            // 如果已经完成了所有组合（hit=true），那表明以当前数字开头的组合都已经找出来了
            // 因此应该跳过所有重复的数字
            if (hit && candidates[i] == candidates[i-1]) {
                continue;
            }
            tmp.addLast(candidates[i]);
            if (DFS(candidates, target - candidates[i], i+1, tmp, res)) {
                hit = true;
            }
            tmp.removeLast();
        }
        return hit;
    }






    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> tmp = new LinkedList<>();

        Arrays.sort(candidates);
        loop(candidates, 0, target, res, tmp);
        return res;
    }

    public boolean loop(int[] candidates, int start, int target, List<List<Integer>> res, LinkedList<Integer> tmp) {
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return true;
        }

        boolean hit = false;
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                return hit;
            }
            // 去重逻辑。如果hit=true，说明当前数字开头的所有组合都已经满足了，如果数字和上一个循环一样，直接跳过
            if (hit && candidates[i] == candidates[i-1]) {
                continue;
            }
            tmp.addLast(candidates[i]);
            if (loop(candidates, i + 1, target - candidates[i], res, tmp)) {
                hit = true;
            }
            tmp.removeLast();
        }
        return hit;
    }
}
