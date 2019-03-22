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
        new M40().combinationSum2(new int[] { 1,1,2,5,6,7,10}, 8);
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
