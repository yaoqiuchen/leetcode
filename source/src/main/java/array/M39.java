package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

 candidates 中的数字可以无限制重复被选取。

 说明：

 所有数字（包括 target）都是正整数。
 解集不能包含重复的组合。
 示例 1:

 输入: candidates = [2,3,6,7], target = 7,
 所求解集为:
 [
 [7],
 [2,2,3]
 ]
 示例 2:

 输入: candidates = [2,3,5], target = 8,
 所求解集为:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]
 */
public class M39 {


    public static void main(String[] args) {
        new M39().combinationSum(new int[] {2,3,6,7}, 7);
    }



    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> tmp = new LinkedList<>();
        DFS(candidates, 0, target, res, tmp);

        return res;
    }

    public void DFS(int[] candidates, int start, int target, List<List<Integer>> res, LinkedList<Integer> tmp ) {
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        if (start >= candidates.length || candidates[start] > target) {
            return;
        }

        for (int i = start; i < candidates.length && candidates[i]<=target; i++) {
            tmp.addLast(candidates[i]);
            DFS(candidates, i, target-candidates[i], res, tmp);
            tmp.removeLast();
        }
    }




    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> tmp = new LinkedList<>();
        DFS2(candidates, target, 0, res, tmp);
        return res;
    }

    public void DFS2(int[] nums, int target, int start, List<List<Integer>> res, LinkedList<Integer> tmp) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(tmp));
            return;
        }
        if (nums[0] > target) {
            return;
        }

        for (int i = start; i < nums.length && nums[i] <= target; i++) {
            tmp.addLast(nums[i]);
            DFS2(nums, target - nums[i], i, res, tmp);
            tmp.removeLast();
        }
    }
}
