
package array;

import java.util.Arrays;

import static java.lang.Math.min;

/**
 *
 * 1051. 高度检查器
  
 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。

 请你返回能让所有学生以 非递减 高度排列的最小必要移动人数。

  

 示例：

 输入：heights = [1,1,4,2,1,3]
 输出：3
  

 提示：

 1 <= heights.length <= 100
 1 <= heights[i] <= 100

 */
public class E1051 {

    public static void main(String[] args) {
//        new M1160().maxUncrossedLines(new int[] {3,8,1,3,2,1,8,9,0}, new int[] {3,8,1,3,2,1,8,9,0});
    }

    // 10个石头  9 = 12-4 + 1
    // 2020-2-22
    public int heightChecker(int[] heights) {
        int result = 0;
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int[] copy = Arrays.copyOf(heights, heights.length);
        Arrays.sort(copy);

        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != copy[i]) {
                result++;
            }
        }

        return result;
    }

}
