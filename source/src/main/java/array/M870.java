package array;

import java.util.*;

/**
 870. 优势洗牌
 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。

 返回 A 的任意排列，使其相对于 B 的优势最大化。

 示例 1：

 输入：A = [2,7,11,15], B = [1,10,4,11]
 输出：[2,11,7,15]
 示例 2：

 输入：A = [12,24,8,32], B = [13,25,32,11]
 输出：[24,32,8,12]


 提示：

 1 <= A.length = B.length <= 10000
 0 <= A[i] <= 10^9
 0 <= B[i] <= 10^9
 */
public class M870 {

//    public static void main(String[] args) {
//        new M15().threeSum(new int[] {-2,0,0,2,2});
//    }

    // 2020-2-7
    public int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> map = new TreeMap();
        Arrays.stream(A).forEach(i -> {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        });

        int[] res = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            Map.Entry<Integer, Integer> entry = map.ceilingEntry(B[i]+1);
            if (entry == null) {
                entry = map.ceilingEntry(0);
            }

            res[i] = entry.getKey();
            if (entry.getValue().equals(1)) {
                map.remove(entry.getKey());
            } else {
                map.put(entry.getKey(), entry.getValue() - 1);
            }
        }

        return res;
    }

    public int[] advantageCount_(int[] A, int[] B) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Arrays.stream(A).forEach(e -> {
            if (map.containsKey(e)) map.put(e, map.get(e) + 1);
            else map.put(e, 1);
        });

        int[] res = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            Map.Entry<Integer, Integer> entry = map.ceilingEntry(B[i] + 1);
            if (entry == null) {
                entry = map.ceilingEntry(0);
            }

            res[i] = entry.getKey();
            if (entry.getValue() == 1) {
                map.remove(entry.getKey());
            } else {
                map.put(entry.getKey(), entry.getValue() - 1);
            }
        }
        return res;
    }
}
