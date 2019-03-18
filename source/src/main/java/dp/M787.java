package dp;

import java.util.*;

/**
 There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

 Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

 Example 1:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 1
 Output: 200
 Explanation:
 The graph looks like this:


 The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 Example 2:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 0
 Output: 500
 Explanation:
 The graph looks like this:


 The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 Note:

 The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 The size of flights will be in range [0, n * (n - 1) / 2].
 The format of each flight will be (src, dst, price).
 The price of each flight will be in the range [1, 10000].
 k is in the range of [0, n - 1].
 There will not be any duplicated flights or self cycles.


 8
 [[3,4,7],[6,2,2],[0,2,7],[0,1,2],[1,7,8],[4,5,2],[0,3,2],[7,0,6],[3,2,7],[1,3,10],[1,5,1],[4,1,6],[4,7,5],[5,7,10]]
 4
 3
 7
 */
public class M787 {

    public static void main(String[] args) {
        findCheapestPrice(8, new int[][] {{3,4,7},{6,2,2},{0,2,7},{0,1,2},{1,7,8},{4,5,2},{0,3,2},{7,0,6},{3,2,7},{1,3,10},
            {1,5,1},{4,1,6},{4,7,5},{5,7,10}}, 4, 3, 7);
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst) return 0;

        int path[][] = new int[n][n]; // p[i][j] is the ticket price from city i to j
        Arrays.stream(flights).forEach(e -> path[e[0]][e[1]] = e[2]);

        // dp[i] is the lowest price to reach out to city I
        int dp[] = new int[n];
        dp[dst] = Integer.MAX_VALUE;
        boolean hasNewRoad = false;
        for (int i = 0; i < n; i++) {
            if (path[src][i] > 0) {
                dp[i] = path[src][i];
                hasNewRoad = true;
            }
        }

        for (int l = 0; l < K; l++) {
            if (!hasNewRoad) break;

            int newDp[] = new int[n];
            int lowest = newDp[dst] = dp[dst];
            for (int start = 0; start < n; start++) {
                if (start == dst || dp[start] == 0) continue;

                for (int j = 0; j < n; j++) {
                    int newPrice = dp[start] + path[start][j];
                    if (path[start][j] > 0 && newPrice < lowest) {
                        newDp[j] = newDp[j] == 0 ? newPrice : Math.min(newDp[j], newPrice);
                        hasNewRoad = true;
                    }
                }
            }
            dp = newDp;
        }

        return dp[dst] == Integer.MAX_VALUE ? -1 : dp[dst];
    }

}
