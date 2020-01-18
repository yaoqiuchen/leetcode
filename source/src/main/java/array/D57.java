package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 插入区间
 *
 给出一个无重叠的 ，按照区间起始端点排序的区间列表。

 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

 示例 1:

 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 输出: [[1,5],[6,9]]
 示例 2:

 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 输出: [[1,2],[3,10],[12,16]]
 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class D57 {

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(6, 9));

        new D57().insert(new int[][] {{1,3},{6,9}}, new int[] {2,5});
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 把两个数组合并成一个
        int n = intervals.length;
        int[][] arr = new int[n+1][2];
        for (int i = 0; i < n; i++) {
            arr[i] = intervals[i];
        }
        arr[n] = newInterval;
        // 再按照先第一个数后第二个数的顺序，做个排序，然后问题就转化为合并区间了
        Arrays.sort(arr, (a, b) -> {
            return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
        });

        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            int[] last = arr[idx];
            int[] current = arr[i];

            // 当前节点的第一个数字小于前一个节点的第二个数字，发生合并
            if (current[0] <= last[1]) {
                last[1] = Math.max(current[1], last[1]);
            } else {
                idx++;
                arr[idx] = current;
            }
        }

        return Arrays.copyOf(arr, idx + 1);
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        LinkedList<Interval> res = new LinkedList<>();
        intervals.add(newInterval);
        intervals.sort((a, b) -> a.start == b.start ? a.end - b.end : a.start - b.start);

        for (Interval i : intervals) {
            if (res.isEmpty()) {
                res.add(i);
                continue;
            }
            Interval tail = res.getLast();

            if (i.start <= tail.end) {
                tail.end = Math.max(i.end, tail.end);
            } else {
                res.add(i);
            }
        }
        return res;
    }

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
