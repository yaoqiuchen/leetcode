package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 283. 移动零

 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

 示例:

 输入: [0,1,0,3,12]
 输出: [1,3,12,0,0]
 说明:

 必须在原数组上操作，不能拷贝额外的数组。
 尽量减少操作次数。

 */
public class E283 {

    public static void main(String[] args) {
        new E283().moveZeroes(new int[] {1,3,5,6});
    }

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }


    // 复杂写法
    public void moveZeroes2(int[] nums) {
        // 把所有0的下标加入queue
        Queue<Integer> zeros = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) zeros.add(i);
        }

        // 每次把当前0和下个0之间的所有数字移动step步长
        int steps = 1;
        while (!zeros.isEmpty()) {
            int idx = zeros.poll();
            if (idx >= nums.length - 1) break;

            int end = zeros.peek() == null ? nums.length : zeros.peek();
            for (int i = idx + 1; i < end; i++) {
                nums[i-steps] = nums[i];
                nums[i] = 0;
            }
            steps++;
        }
        return ;
    }

}
