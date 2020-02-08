package array;

import java.util.*;

/**
 907. 子数组的最小值之和

 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。

 由于答案可能很大，因此返回答案模 10^9 + 7。


 示例：

 输入：[3,1,2,4]
 输出：17
 解释：
 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。


 提示：

 1 <= A <= 30000
 1 <= A[i] <= 30000
 */
public class M907 {

    public static void main(String[] args) {
        new M907().sumSubarrayMins(new int[] {3,1,2,4});
    }


    // 2020-2-8
    public int sumSubarrayMins(int[] A) {
        int MOD = 1_000_000_007;
        int N = A.length;

        // stack是单调递增的序列，prev表示前面第一个比他小的数字下标
        Stack<Integer> stack = new Stack();
        int[] prev = new int[N];
        for (int i = 0; i < N; ++i) {
            while (!stack.isEmpty() && A[i] <= A[stack.peek()])
                stack.pop();
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // stack依然是单调递增序列，next表示后面第一个比他小的数字下标
        stack = new Stack();
        int[] next = new int[N];
        for (int k = N-1; k >= 0; --k) {
            while (!stack.isEmpty() && A[k] < A[stack.peek()])
                stack.pop();
            next[k] = stack.isEmpty() ? N : stack.peek();
            stack.push(k);
        }

        // Use prev/next array to count answer
        long ans = 0;
        for (int i = 0; i < N; ++i) {
            ans += (i - prev[i]) * (next[i] - i) % MOD * A[i] % MOD;
            ans %= MOD;
        }
        return (int) ans;
    }


    public int sumSubarrayMins2(int[] A) {
        if (A.length == 1) return A[0];
        int len = A.length;
        int[] flag = Arrays.copyOf(A, A.length);
        for (int i = 1; i < len; i++) {
            int tmp = 0;
            int minFlag = A[i];
            for (int j = i; j >= 0; j--) {//往前扫描计算包含A[N~N]子数组的和
                minFlag = Math.min(minFlag, A[j]);
                tmp += minFlag;
            }
            int a = flag[i - 1] + tmp;
            flag[i] = (int) (a % 1000000007);
        }
        return (flag[len - 1]);
    }

    public int sumSubarrayMins_(int[] A) {
        int[] dp = new int[A.length];
        Stack<Integer> increase = new Stack();

        int idx = 0, sum = 0;
        for (int i = 0; i < A.length; i++) {
            idx = (i == 0) ? 0 : i - 1;
            while (idx > 0 && !increase.isEmpty() && A[i] < A[increase.get(idx)]) {
                idx--;
            }
            int nidx = idx == 0 && i > 0 ? idx + 1 : idx;

            increase.add(nidx, i);
            dp[i] = (idx > 0 ? dp[idx] : 0) + (i - increase.get(idx<i?idx+1:0) + 1) * A[i];
//            dp[i] = (nidx > 0 ? dp[idx] : 0) + (i - increase.get(nidx) + 1) * A[i];
            sum += dp[i];
        }

        return sum % 1000000007;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */
