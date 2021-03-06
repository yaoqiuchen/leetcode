package array;

/**
 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

 说明:

 返回的下标值（index1 和 index2）不是从零开始的。
 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 示例:

 输入: numbers = [2, 7, 11, 15], target = 9
 输出: [1,2]
 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class E167 {

    public static void main(String[] args) {
        new E167().twoSum(new int[] {-1,0}, -1);
    }


    // 2020-1-24
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, h = numbers.length -1;
        while (i < h) {
            int val = numbers[i] + numbers[h];
            if (val == target) {
                return new int[] {i+1, h+1};
            }
            if (val < target) {
                i++;
            } else {
                h--;
            }
        }

        return new int[] {-1, -1};
    }





    public int[] twoSum_(int[] numbers, int target) {
        int l = 0, h = numbers.length - 1;
        int[] res = new int[2];

        while (l <= h) {
            int mid = (l + h) / 2;
            if (numbers[mid] > target) {
                h = mid;
                continue;
            }
            if (numbers[l] + numbers[h] > target) {
                h--;
            } else if (numbers[l] + numbers[h] < target) {
                l++;
            } else {
                res[0] = l+1;
                res[1] = h+1;
                return res;
            }
        }
        return res;
    }
}
