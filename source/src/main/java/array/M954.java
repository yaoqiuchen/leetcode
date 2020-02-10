package array;

import java.util.*;

/**
 *
 * 954 二倍数对数组
 *
 给定一个长度为偶数的整数数组 A，只有对 A 进行重组后可以满足
 “对于每个 0 <= i < len(A) / 2，都有 A[2 * i + 1] = 2 * A[2 * i]” 时，返回 true；否则，返回 false。

  

 示例 1：

 输入：[3,1,3,6]
 输出：false
 示例 2：

 输入：[2,1,2,6]
 输出：false
 示例 3：

 输入：[4,-2,2,-4]
 输出：true
 解释：我们可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 示例 4：

 输入：[1,2,4,16,8,4]
 输出：false
  

 提示：

 0 <= A.length <= 30000
 A.length 为偶数
 -100000 <= A[i] <= 100000

 */
public class M954 {

    public static void main(String[] args) {
        new M954().canReorderDoubled(new int[] {});
    }

    // 2020-2-10
    public boolean canReorderDoubled(int[] A) {
        Arrays.sort(A);
        Map<Integer, Integer> map = new TreeMap<>();
        // 这一步的关键，
        // 1）是把负数变为正数，因为-2的二倍是-4
        // 2) 使用treeMap从大到小排队
        Arrays.stream(A).forEach(i -> {
            i = i < 0 ? -i : i;
            int count = map.getOrDefault(i, 0) + 1;
            map.put(i, count);
        });

        while (!map.isEmpty()) {
            Integer key = map.keySet().iterator().next();
            int count = map.get(key);

            // 奇数个0肯定失败
            if (key == 0 && count % 2 > 0) return false;

            // 个数不够消除前一个更小的数，则失败
            int target = key * 2, count2 = map.getOrDefault(target, 0);
            if (count2 == 0 || count2 < count) {
                return false;
            }

            map.remove(key);
            if (count2 == count) {
                map.remove(target);
            } else {
                map.put(target, count2 - count);
            }
        }
        return true;
    }

}
