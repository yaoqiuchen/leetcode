package dp;

/**
 *
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows
 * and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

 A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal
 direction, then one square in an orthogonal direction.
 */
public class M688 {

    public static void main(String[] args) {
        knightProbability(3, 2, 2, 2);
    }

    static int pos[][] = new int[][]{{-2,-1}, {-2,1}, {2,-1}, {2,1}, {-1,-2}, {1,-2}, {-1,2}, {1,2}};

    public static double knightProbability(int N, int K, int r, int c) {
        if (K == 0) return 1;

        double survive = 0;
        double dp[][] = new double[N][N];
        dp[r][c] = 1;

        boolean noSurviver = false;
        for (int i = 1; i <= K; i++) {
            if (noSurviver) return 0;
            noSurviver = true;
            double tmp[][] = new double[N][N];
            double count = 0;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    double val = dp[x][y];
                    if (val == 0) {
                        continue;
                    }
                    for (int[] key : pos) {
                        int nx = x + key[0], ny = y + key[1];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                            noSurviver = false;
                            count += val ;
                            tmp[nx][ny] += val;
                        }
                    }
                }
            }
            dp = tmp;
            survive = count;
        }
        return survive / Math.pow(8, K);
    }

}
