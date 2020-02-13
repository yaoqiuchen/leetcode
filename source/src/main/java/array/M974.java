package array;

/**
 *
 * 974 和可被 K 整除的子数组

 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。

  

 示例：

 输入：A = [4,5,0,-2,-3,1], K = 5
 输出：7
 解释：
 有 7 个子数组满足其元素之和可被 K = 5 整除：
 [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
  

 提示：

 1 <= A.length <= 30000
 -10000 <= A[i] <= 10000
 2 <= K <= 10000

 */
public class M974 {

    public static void main(String[] args) {
//        new E509().pancakeSort(new int[] {3,2,4,1});
    }

    // 2020-2-11
    // 思路
//    通常，涉及连续子数组问题的时候，我们使用前缀和来解决它们。
//  我们令 P[i+1] = A[0] + A[1] + ... + A[i]。
//  那么，每个连续子数组就可以写成 P[j] - P[i] （其中 j > i） 的形式。
//  因此，我们将 P[j] - P[i] 模 K 等于 0 等价于 P[i] 与 P[j] 在模 K 的意义下同余。
//  2  2   2   1  ->
    public int subarraysDivByK(int[] A, int K) {
        int N = A.length;
        int[] P = new int[N+1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + A[i];

        int[] count = new int[K];
        for (int x: P)
            count[(x % K + K) % K]++;

        int ans = 0;
        for (int v: count)
            ans += v * (v - 1) / 2;
        return ans;
    }

}
