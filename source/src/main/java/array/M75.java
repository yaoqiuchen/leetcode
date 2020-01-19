package array;

/**
 * 颜色分类
 *
 定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，
 并按照红色、白色、蓝色顺序排列。

 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

 注意:
 不能使用代码库中的排序函数来解决这道题。

 示例:

 输入: [2,0,2,1,1,0]
 输出: [0,0,1,1,2,2]
 进阶：

 一个直观的解决方案是使用计数排序的两趟扫描算法。
 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 你能想出一个仅使用常数空间的一趟扫描算法吗？

 */
public class M75 {

    public static void main(String[] args) {
    // 202110
        new M75().sortColors(new int[] {2,0,2,1,1,0});
    }

    public void sortColors(int[] nums) {
        int len0 = 0, len1 = 0, len2 = 0;
        boolean n1 = false, n2 = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 2) {
                n2 = true;
                nums[len2] = 2;
                len2++;
            }
            else  {
                if (nums[i] == 0) {
                    nums[len0] = 0;
                    len0++;
                    if (n1) {
                        nums[len1] = 1;
                    }
                    if (n2) {
                        nums[len2] = 2;
                    }
                    len2++;
                    len1++;
                }
                else if (nums[i] == 1) {
                    n1 = true;
                    nums[len1] = 1;
                    len1++;
                    if (n2) {
                        nums[len2] = 2;
                    }
                    len2++;
                }
            }
        }

    }




    public void sortColors_(int[] nums) {
        int start = sortColorFromIdx(nums, 0, 0);
        sortColorFromIdx(nums, start, 1);
    }

    public int sortColorFromIdx(int[] nums, int start, int color) {
        int res = start;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == color) {
                int tmp = nums[res];
                nums[res] = nums[i];
                nums[i] = tmp;
                res++;
            }
        }
        return res;
    }
}
