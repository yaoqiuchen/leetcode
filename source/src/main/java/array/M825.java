package array;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 825. 适龄的朋友

 人们会互相发送好友请求，现在给定一个包含有他们年龄的数组，ages[i] 表示第 i 个人的年龄。
1, 13
 当满足以下条件时，A 不能给 B（A、B不为同一人）发送好友请求：
A 13.5 B 13
 age[B] <= 0.5 * age[A] + 7
 age[B] > age[A]
 age[B] > 100 && age[A] < 100
 否则，A 可以给 B 发送好友请求。

 注意如果 A 向 B 发出了请求，不等于 B 也一定会向 A 发出请求。而且，人们不会给自己发送好友请求。

 求总共会发出多少份好友请求?

 示例 1:
 输入: [16,16]
 输出: 2
 解释: 二人可以互发好友申请。
 示例 2:

 输入: [16,17,18]
 输出: 2
 解释: 好友请求可产生于 17 -> 16, 18 -> 17.
 示例 3:

 输入: [20,30,100,110,120]
 输出: 3
 解释: 好友请求可产生于 110 -> 100, 120 -> 110, 120 -> 100.

 说明:
 1 <= ages.length <= 20000.
 1 <= ages[i] <= 120.
 */
public class M825 {

    public static void main(String[] args) {
        new M825().numFriendRequests(new int[] {2,1,4,3});
    }

    // 有点慢
    public int numFriendRequests(int[] ages) {
        Map<Integer, Integer> count = new TreeMap<>();
        int sum = 0;
        Arrays.stream(ages).forEach(e -> {
            if (count.containsKey(e)) count.put(e, count.get(e)+1);
            else count.put(e, 1);
        });

        for (Map.Entry<Integer, Integer> e1 : count.entrySet()) {
            for (Map.Entry<Integer, Integer> e2 : count.entrySet()) {
                if (isFriend(e1.getKey(), e2.getKey())){

                    if(e1.getKey() == e2.getKey())
                        sum += e1.getValue() * (e1.getValue()-1);
                    else
                        sum += e1.getValue() * e2.getValue();
                }
            }
        }

        return sum;
    }

    private boolean isFriend(int a1, int a2) {
        return Float.valueOf(a2) > (0.5f * a1 + 7) && a2 <= a1;
    }
}
