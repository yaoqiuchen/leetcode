package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

 说明：解集不能包含重复的子集。

 示例:

 输入: [1,2,2]
 输出:
 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]
 */
public class M90 {

    public static void main(String[] args) {
        new M90().subsetsWithDup(new int[] {1,3,4});
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) return new ArrayList<>(new ArrayList<>());
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        LinkedList<List<Integer>> sameNums = new LinkedList<>();
        int val = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 当前相同数字的最大节点数
            List<Integer> node = new ArrayList<>();
            node.add(nums[i]);
            if (sameNums.size() == 0 || nums[i] == val) {
                if (!sameNums.isEmpty()) {
                    node.addAll(sameNums.getLast());
                }
                val = nums[i];
                sameNums.addLast(node);
            } else {
                combine(sameNums, res);
                sameNums.add(node);
                val = nums[i];
            }
        }

        if (!sameNums.isEmpty()) {
            combine(sameNums, res);
        }

        res.add(new ArrayList<>());
        return res;
    }

    private void combine(List<List<Integer>> sameNums, List<List<Integer>> res) {
        List<List<Integer>> newArr = new ArrayList<>();
        newArr.addAll(sameNums);
        for (List<Integer> arr1 : sameNums) {
            for (List<Integer> arr2 : res) {
                List<Integer> newElement = new ArrayList<>(arr1.size() + arr2.size());
                newElement.addAll(arr1);
                newElement.addAll(arr2);
                newArr.add(newElement);
            }
        }
        res.addAll(newArr);
        sameNums.clear();
    }

}
