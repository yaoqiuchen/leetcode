package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 90. 子集 II
 *
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
        new M90().subsetsWithDup(new int[] {1,2,3});
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 排序，确保相同的数字排在一起
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        subsetsWithDup(nums, 0, res);
        res.add(Arrays.asList());
        return res;
    }

    public void subsetsWithDup(int[] nums, int start, List<List<Integer>> res) {
        int count = 0, idx = start;
        for (int i = start; i <= nums.length; i++) {
            List<List<Integer>> newElements = new ArrayList<>();
            // 如果是相同的数字，什么都不做，只是把count++
            if (i == 0 || (i < nums.length && nums[i] == nums[i-1])) {
                count++;
                continue;
            }
            // 现在数字不同了，开始清算
            // 如果相同数字有3个，那么生成阶乘的组合[[1],[1,1],[1,1,1]]
            List<List<Integer>> tmp = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                List<Integer> _tmp = new ArrayList<>();
                for (int c = 0; c <= j; c++) {
                    _tmp.add(nums[idx]);
                }
                tmp.add(_tmp);
            }
            // 将tmp和res做笛卡尔乘积
            // 然后把结果加到最终结果集里面
            for (List<Integer> element : res) {
                for (List<Integer> _tmp : tmp) {
                    List<Integer> node = new ArrayList<>(element);
                    node.addAll(_tmp);
                    newElements.add(node);
                }
            }
            if (!newElements.isEmpty()) {
                res.addAll(newElements);
            }
            res.addAll(tmp);
            count = 1;
            idx = i;
        }
    }







    public List<List<Integer>> subsetsWithDup_(int[] nums) {
        if (nums.length == 0) return new ArrayList<>(new ArrayList<>());
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        LinkedList<List<Integer>> sameNums = new LinkedList<>();
        int val = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 从0到i所有数中，和nums[i]相同的节点集合
            List<Integer> node = new ArrayList<>();
            node.add(nums[i]);
            // 如果是相同节点，累加到sameNums中
            if (sameNums.size() == 0 || nums[i] == val) {
                if (!sameNums.isEmpty()) {
                    node.addAll(sameNums.getLast());
                }
                val = nums[i];
                sameNums.addLast(node);
            } else {
                // 如果节点不相同，把sameNums与res中的元素做笛卡尔积
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
