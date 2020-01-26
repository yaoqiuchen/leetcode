package array;

import dp.M62;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * 621. 任务调度器
 *
 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 任务可以以任意顺序执行，并且
 每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。

 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

 你需要计算完成所有任务所需要的最短时间。

 示例 1：

 输入: tasks = ["A","A","A","B","B","B"], n = 2
 输出: 8
 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 注：

 任务的总个数为 [1, 10000]。
 n 的取值范围为 [0, 100]。
 */
public class M621 {

    public static void main(String[] args) {
        int[] as ;
        char input[] = new char[] { 'A','A','A','B','B','B'};
        new M621().leastInterval(input, 2);
    }

    // 2020-1-26
    public int leastInterval(char[] tasks, int n) {
        int len = tasks.length;
        if (n == 0) return len;

        int[] dp = new int[26];
        // 找出出现频率最高的元素
        for (int i = 0; i < len; i++) {
            int idx = tasks[i] - 'A';
            dp[idx]++;
        }
        Arrays.sort(dp);
        int max = dp[25];
        // 出现频率最高的字母有多少种
        long count = Arrays.stream(dp).filter(e -> e == max).count();
        // 如果出现频率最高的字母只有一种，那么长度就很好算(max-1)*(1+n)+1
        // 如果有n种，那么最后答案就要做修正 + (n-1)
        // (max-1)*(1+n)+1+((int)count-1) 简化= (max-1)*(1+n)+(int)count
        return Math.max(len, (max-1)*(1+n)+(int)count);
    }




    public int leastInterval_(char[] tasks, int n) {
        int dp[] = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            dp[tasks[i] - 'A'] += 1;
        }

        Arrays.sort(dp);
        int max = dp[25];
        long count = Arrays.stream(dp).filter(e->e==max).count();
        return Math.max(tasks.length, (max - 1) * (n + 1) + (int) count);
    }
}
