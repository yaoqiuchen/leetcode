package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 354. 俄罗斯套娃信封问题

 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，
 这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

 说明:
 不允许旋转信封。

 示例:

 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 输出: 3
 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。

 */
public class D354 {


    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;

        int[] dp = new int[envelopes.length];
        Arrays.sort(envelopes, (a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);

        int result = 0;
        for (int i=0; i<envelopes.length; i++) {
            int count = 0;
            int[] cur = envelopes[i];
            for (int j=i-1; j>=0; j--) {
                int[] prev = envelopes[j];
                if (cur[0] > prev[0] && cur[1]>prev[1] && dp[j] + 1 > count) {
                    count = dp[j] + 1;
                }
            }
            count = count == 0? 1 : count;
            dp[i] = count;
            result = result < count? count : result;
        }
        return result;
    }


}
