package array;

import java.util.*;

/**
 * 颜色分类
 *
 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

 说明：解集不能包含重复的子集。

 示例:

 输入: nums = [1,2,3]
 输出:
 [
     [3],
     [1],
     [2],
     [1,2,3],
     [1,3],
     [2,3],
     [1,2],
     []
 ]

 */
public class M78 {

    public static void main(String[] args) {
    // 202110
        new M78().subsets(new int[] {1,2,3});
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> list = new ArrayList<>();
            for (int j = 0; j < res.size(); j++) {
                List<Integer> arr = new LinkedList<>(res.get(j));
                arr.add(nums[i]);
                list.add(arr);
            }
            if (!list.isEmpty()) {
                res.addAll(list);
            }
            res.add(Arrays.asList(nums[i]));
        }

        res.add(new ArrayList<>());
        return res;
    }
}
