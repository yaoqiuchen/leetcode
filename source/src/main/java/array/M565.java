package array;

import java.util.*;

/**
 565. 数组嵌套

 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到并返回最大的集合S，S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。

 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。

 示例 1:

 输入: A = [5,4,0,3,1,6,2]
 输出: 4
 解释:
 A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

 其中一种最长的 S[K]:
 S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 注意:

 N是[1, 20,000]之间的整数。
 A中不含有重复的元素。
 A中的元素大小在[0, N-1]之间。
 */
public class M565 {

    public static void main(String[] args) {
        new M565().arrayNesting(new int[] {5,4,0,3,1,6,2});
    }

    public int arrayNesting(int[] nums) {
        int max = 0;
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Queue<Integer> queue = new LinkedList<>();
            int next = i, len = 0;
            while (true) {
                int val = nums[next];
                if (count.containsKey(val)) {
                    len += count.get(val) - 1;
                    break;
                }
                len++;
                count.put(val, 1);
                next = nums[next];
                queue.add(val);
            }
            while (!queue.isEmpty()) {
                int val = queue.poll();
                count.put(val, len);
                max = Math.max(max, len);
                len--;
            }
        }

        return max;
    }

    // 正确答案，但是会超时
    public int arrayNesting1(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                max = Math.max(max, 1);
                continue;
            }

            int next = i;
            Set<Integer> filter = new HashSet<>();

            do {
                filter.add(nums[next]);
                next = nums[next];
            } while (!filter.contains(nums[next]));

            max = Math.max(max, filter.size());
        }
        return max;
    }
}
