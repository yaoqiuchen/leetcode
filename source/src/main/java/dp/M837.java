package dp;

/**
 837. New 21 Game

 Alice plays the following game, loosely based on the card game "21".

 Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer
 number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have
 equal probabilities.

 Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?

 Example 1:

 Input: N = 10, K = 1, W = 10
 Output: 1.00000
 Explanation:  Alice gets a single card, then stops.
 Example 2:

 Input: N = 6, K = 1, W = 10
 Output: 0.60000
 Explanation:  Alice gets a single card, then stops.
 In 6 out of W = 10 possibilities, she is at or below N = 6 points.
 Example 3:

 Input: N = 21, K = 17, W = 10
 Output: 0.73278

 */
public class M837 {

    // TODO 
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[N + W + 1];
        // dp[x] = the answer when Alice has x points
        for (int k = K; k <= N; ++k)
            dp[k] = 1.0;

        double S = Math.min(N - K + 1, W);
        // S = dp[k+1] + dp[k+2] + ... + dp[k+W]
        for (int k = K - 1; k >= 0; --k) {
            dp[k] = S / W;
            S += dp[k] - dp[k + W];
        }
        return dp[0];
    }

}
