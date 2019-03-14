package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * A chess knight can move as indicated in the chess diagram below:


 This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

 Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

 How many distinct numbers can you dial in this manner?

 Since the answer may be large, output the answer modulo 10^9 + 7.



 Example 1:

 Input: 1
 Output: 10
 Example 2:

 Input: 2
 Output: 20
 Example 3:

 Input: 3
 Output: 46


 Note:

 1 <= N <= 5000
 */
public class M935 {

    /**
     * I believe there's a lot of room to improve
     */
    public int knightDialer(int N) {
        if (N <= 0) return 0;
        if (N == 1) return 10;

        int result = 0, mod = 1000000007;
        // int[a] is the keys that can be jumped to from key a. There is no next keys for key 5
        int next[][] = new int[][] {{4,6}, {8,6}, {7,9}, {4,8}, {3,9,0}, {}, {1,7,0}, {2,6}, {1,3}, {2,4}};

        for (int i = 0; i<=9; i++) {
            if (i == 5) continue; 
            int count[] = new int[10];
            count[i] = 1;

            int sum = 1;
            for (int j = 1; j < N; j++) {
                int newCount[] = new int[10];
                for (int key = 0; key < count.length; key++) {
                    int val = count[key];
                    if (val == 0) continue;

                    for (Integer nextKey : next[key]) {
                        newCount[nextKey] = (newCount[nextKey] + val) % mod;
                    }
                    sum = (sum + (next[key].length - 1) * val % mod) % mod;
                }
                count = newCount;
            }
            result = (result + sum) % mod;
        }
        return result;
    }
}
