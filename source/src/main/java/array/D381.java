package array;

import java.util.*;

/**
 381. O(1) 时间插入、删除和获取随机元素 - 允许重复

 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。

 注意: 允许出现重复元素。

 insert(val)：向集合中插入元素 val。
 remove(val)：当 val 存在时，从集合中移除一个 val。
 getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 示例:

 // 初始化一个空的集合。
 RandomizedCollection collection = new RandomizedCollection();

 // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 collection.insert(1);

 // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 collection.insert(1);

 // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 collection.insert(2);

 // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 collection.getRandom();

 // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 collection.remove(1);

 // getRandom 应有相同概率返回 1 和 2 。
 collection.getRandom();

 */
public class D381 {

    public static void main(String[] args) {
        D381 a = new D381();
        a.insert(1);
        a.insert(10);
        a.insert(10);
        a.insert(100);
        System.out.println(a.getRandom());
        System.out.println(a.getRandom());
        System.out.println(a.getRandom());
        System.out.println(a.getRandom());
//        new M380().gameOfLife(new int[][] {{0,1,0},{0,0,1},{1,1,1},{0,0,0}});
    }

    /** Initialize your data structure here. */
    public D381() {
    }

    private Map<Integer, Integer> data = new HashMap<>();
    private int size = 0;

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        int occurs = data.get(val) == null ? 1 : data.get(val) + 1;
        data.put(val, occurs);
        size++;
        return occurs == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!data.containsKey(val)) {
            return false;
        }
        int occurs = data.get(val);
        size--;
        if (occurs == 1) {
            data.remove(val);
        } else {
            data.put(val, occurs - 1);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        if (data.isEmpty()) return -1;
        int num = new Random().nextInt(size);

        int count = -1;
        for (Map.Entry<Integer, Integer> entry : data.entrySet()) {
            if (count + entry.getValue() >= num) {
                return entry.getKey();
            }
            count += entry.getValue();
        }
        return 1;
    }
}
/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */