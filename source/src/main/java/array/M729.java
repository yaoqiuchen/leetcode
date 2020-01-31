package array;

import java.util.*;

/**
 729. 我的日程安排表 I

 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。

 MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，
 即 [start, end), 实数 x 的范围为，  start <= x < end。

 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。

 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。

 请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

 示例 1:

 MyCalendar();
 MyCalendar.book(10, 20); // returns true
 MyCalendar.book(15, 25); // returns false
 MyCalendar.book(20, 30); // returns true
 解释:
 第一个日程安排可以添加到日历中.  第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预定了。
 第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20 。
 说明:

 每个测试用例，调用 MyCalendar.book 函数最多不超过 100次。
 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。

 */
public class M729 {

//    public static void main(String[] args) {
//        new M16().threeSumClosest(new int[] {0,2,1,-3}, 1);
//    }



    Map<Integer, Integer> cal = new TreeMap<>();

    // 数组int[i][0]表示start,int[i][1]表示end
    int[][] cal2 = new int[1000][2];
    int n = 0;

    // TODO
    public boolean book(int start, int end) {
        if (n == cal2.length) {
            int[][] tmp = new int[n*2][2];
            Integer i = 1;

        }
        if (n == 0) {
            cal2[0] = new int[] {start, end};
            n++;
            return true;
        }

        // 用二分查找
        int l = 0, h = n-1, res = l;
        while (l <= h) {
            int mid = (l+h)/2, val = cal2[mid][0];
            if (val == start) {
                return false;
            }
            if (val < start) l = mid+1;
            else h = mid-1;
            res = mid;
        }

        // res如果落在范围内，扔掉
        if (!(cal2[res][1] <= start || cal2[res][0] >= end)) {
            return false;
        }
        // res前一个数字，如果落在范围内，扔掉
        if (res>0 && !(cal2[res-1][1] <= start || cal2[res-1][0] >= end)) {
            return false;
        }
        // res后一个数字，如果落在范围内，扔掉
        if (res<n && !(cal2[res+1][1] <= start || cal2[res+1][0] >= end)) {
            return false;
        }

        cal2[n] = new int[] {start, end};
        n++;
        Arrays.sort(cal2, 0, n, (a,b) -> a[0] - b[0]);
        return true;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {{47,50},{33,41},{25,32},{19,25}};
        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        M729 s = new M729();
        s.book(47, 50);
        s.book(33, 41);
        s.book(25, 32);
        s.book(19, 25);
        return;
    }

    public boolean book_(int start, int end) {
        Iterator<Map.Entry<Integer, Integer>> i = cal.entrySet().iterator();
        boolean included = false;
        while (i.hasNext()) {
            Map.Entry<Integer, Integer> entry = i.next();
            // 头部交叉
            if (entry.getKey() <= start && (entry.getValue() > start)) {
                return false;
            }
            // 尾部交叉
            if (entry.getKey() < end && entry.getValue() >= end) {
                return false;
            }

            if (entry.getKey() >= start && entry.getValue() < end) {
                return false;
            }
        }

        cal.put(start, end);
        return true;
    }

}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
