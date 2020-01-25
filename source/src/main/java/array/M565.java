package array;

import java.util.*;

/**
 565. 数组嵌套

 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到并返回最大的集合S，
 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。

 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]...
 以此类推，不断添加直到S出现重复的元素。

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

    // 2020-1-26
    public int arrayNesting(int[] nums) {
        int res = 0, n = nums.length;
        if (n == 0) return 0;

        // 解题点：每个环只会循环一次，一旦某个节点访问过了，那就不用在访问
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int len = 0, next = i;
            if (visited.contains(i)) continue;

            while (!visited.contains(next)) {
                visited.add(next);
                len++;
                next = nums[next];
            }
            res = Math.max(res, len);
         }
        return res;
    }





    public int arrayNesting_(int[] nums) {
        int max = 0;
        // dp[i]表示从数值i为起点，达到回路的长度
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            Queue<Integer> queue = new LinkedList<>();
            int next = i, len = 0;
            while (true) {
                int val = nums[next];
                if (dp[val] > 0) {
                    // dp大于0表示这个节点曾经被访问过，那么由于dp[i]是包含这个节点的长度，所以这次是再次访问，
                    // 应该减去1
                    len += dp[val] - 1;
                    break;
                }
                len++;
                dp[val] = 1;
                next = nums[next];
                queue.add(val);
            }
            while (!queue.isEmpty()) {
                int val = queue.poll();
                dp[val] = len--;
                max = Math.max(max, len);
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
