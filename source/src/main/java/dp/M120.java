package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

 例如，给定三角形：

 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

 说明：

 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class M120 {

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(2));
        input.add(Arrays.asList(3,4));
        input.add(Arrays.asList(6,5,7));
        input.add(Arrays.asList(4,1,8,3));

        new M120().minimumTotal(input);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;

        int result = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> last = triangle.get(i-1);
            List<Integer> cur = triangle.get(i);
            int j = 0;
            int min = Integer.MAX_VALUE;
            for (Integer val : cur) {
                int sum = val + Math.min(
                        (j > 0 ? last.get(j - 1) : Integer.MAX_VALUE),
                        (j == last.size() ? Integer.MAX_VALUE : last.get(j)));

                cur.set(j, sum);
                min = Math.min(min, sum);
                j++;
            }
            result = min;
        }
        return result;
    }
}
