
package array;

/**
 *
 * 1089 复写零
  
 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。

 注意：请不要在超过该数组长度的位置写入元素。

 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。

  

 示例 1：

 输入：[1,0,2,3,0,4,5,0]
 输出：null
 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 示例 2：

 输入：[1,2,3]
 输出：null
 解释：调用函数后，输入的数组将被修改为：[1,2,3]
  

 提示：

 1 <= arr.length <= 10000
 0 <= arr[i] <= 9

 */
public class E1089 {

    public static void main(String[] args) {
        new E1089().duplicateZeros(new int[] {9,10,4,5});
//        new M1160().maxUncrossedLines(new int[] {3,8,1,3,2,1,8,9,0}, new int[] {3,8,1,3,2,1,8,9,0});
    }

    // 2020-2-23
    public void duplicateZeros(int[] arr) {
        // 找到写满数组的下标
        int idx = 0, count = 0;
        for (; idx < arr.length;) {
            count += (arr[idx] == 0) ? 2 : 1;
            if (count >= arr.length) {
                break;
            }
            idx++;
        }

        // 最后一个数字是0，超出来了
        int i = arr.length - 1;
        if (count > arr.length) {
            arr[i] = 0;
            idx--;
            i--;
        }

        for (; i >= 0;) {
            arr[i--] = arr[idx];
            if (arr[idx] == 0) {
                arr[i--] = 0;
            }
            idx--;
        }
    }

}
