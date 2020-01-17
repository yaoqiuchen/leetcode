package dp;

import java.util.Arrays;

/**
 486. 预测赢家

 给定一个表示分数的非负整数数组。 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，
 然后玩家1拿，……。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。
 最终获得分数总和最多的玩家获胜。

 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。

 示例 1:

 输入: [1, 5, 2]
 输出: False
 解释: 一开始，玩家1可以从1和2中进行选择。
 如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。
 所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为 5。
 因此，玩家1永远不会成为赢家，返回 False。
 示例 2:

 输入: [1, 5, 233, 7]
 输出: True
 解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
 最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。
 注意:

 1 <= 给定的数组长度 <= 20.
 数组里所有分数都为非负数且不会大于10000000。
 如果最终两个玩家的分数相等，那么玩家1仍为赢家。

 */
public class M486 {

    public static void main(String[] args) {
        PredictTheWinner(new int[] {1, 1, 1, 1, 1});
    }

    public static boolean PredictTheWinner3(int[] nums) {
        return calculate(nums, 0, nums.length - 1) >= 0;
    }

    public static int calculate(int[] nums, int start, int end) {
        if (start == end) return nums[start];
        return Math.max(nums[start] - calculate(nums, start+1, end),
                    nums[end] - calculate(nums, start, end-1)
        );
    }


    // 答案最优解
//    这一道题也是典型极小化极大的算法。两个玩家都要使自己的利益最大化，使对手的利益最小化。
//  Minimax是一个零和博弈中的算法。两人依次拿，如果Player1赢，则Player1拿的>Player2拿的。
//  把Player1拿的视为"+"，把Player2拿的视为"-"，如果最后结果大于等于0则Player1赢。
    public static boolean PredictTheWinner(int[] nums) {

//   建立dp[len][len]数组，dp[i][j]表示nums[i, j]下标间玩家1能够获得的分数减去玩家2能够获得的最大分数差，
//  最后看dp[0][len-1]的正负来判断1是否能赢。
        int len=nums.length;
        int[][] dp=new int[len][len];
        dp[0][0]=nums[0];
        for (int i = 1; i <len ; i++) {
            dp[i][i]=nums[i];
            for (int j = 0; j <len-i ; j++) {
                int k=i+j;
                dp[j][k]=Math.max(nums[j]-dp[j+1][k],nums[k]-dp[j][k-1]);
            }
        }
        return dp[0][len-1]>=0;
    }

    // 我的解法，运行时间比较长
    public static boolean PredictTheWinner2(int[] nums) {
        int n = nums.length;
        // dp[i][j]表示从i开始，长度为j的范围内，可以获取到的最大数字
        int[][] dp = new int[n][n+1];

        int sum = Arrays.stream(nums).sum();
        // 如果能达到这个target，就算做胜出
        int target = sum % 2 == 0 ? sum / 2 : sum / 2 + 1;
        return PredictTheWinner(nums, 0, n, dp) >= target;
    }

    public static int PredictTheWinner(int[] nums, int i, int l, int[][] dp) {
        // 超过边界返回0
        if (i >= nums.length) {
            return 0;
        }

        if (l == 1) {
            dp[i][l] = nums[i];
            return dp[i][l];
        }

        int total = Arrays.stream(nums, i, i+l).sum();
        // 我拿第一个节点，然后假设对手同样聪明，那后面我能拿到的总数应该是total - 对手能拿到的最大数字
        int first = nums[i+l-1] + total - PredictTheWinner(nums, i, l-1, dp);
        int last = nums[i] + total - PredictTheWinner(nums, i+1, l-1, dp);
        dp[i][l] = Math.max(
                last,
                first
        );
        return dp[i][l];
    }
}
