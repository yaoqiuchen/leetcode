package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 合并区间

 给出一个区间的集合，请合并所有重叠的区间。

 示例 1:

 输入: [[1,3],[2,6],[8,10],[15,18]]
 输出: [[1,6],[8,10],[15,18]]
 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 示例 2:

 输入: [[1,4],[4,5]]
 输出: [[1,5]]
 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class M56 {

    public static void main(String[] args) {
        new M56().merge(null);
    }

    // 先按照start升序再end升序对数组进行排序，逐个合并即可
    public List<Interval> merge(List<Interval> intervals) {
        LinkedList<Interval> res = new LinkedList<>();
        intervals.sort((a, b) -> a.start == b.start ? a.end - b.end : a.start - b.start);

        for (Interval i : intervals) {
            if (res.isEmpty()) res.add(i);

            Interval last = res.getLast();
            if (i.start <= last.end) {
                last.end = Math.max(i.end, last.end);
            } else {
                res.add(i);
            }
        }
        return res;
    }

    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
