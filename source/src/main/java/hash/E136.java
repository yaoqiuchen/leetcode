package hash;

import linkedlist.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 136. 只出现一次的数字

 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

 说明：

 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

 示例 1:

 输入: [2,2,1]
 输出: 1
 示例 2:

 输入: [4,1,2,1,2]
 输出: 4

 */
public class E136 {

    public static void main(String[] args) {
        new E136().singleNumber(null);
    }

    // 2/26
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> filter = new HashMap<>();

        Arrays.stream(nums).forEach(e -> {
            filter.put(e, filter.getOrDefault(e, 0) + 1);
        });

        List<Map.Entry> result = filter.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .collect(Collectors.toList());

        return (Integer) result.get(0).getKey();
    }


}
