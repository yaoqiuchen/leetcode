package array;

/**
 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

 说明:

 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素
 */
public class E88 {

    public static void main(String[] args) {
        new E88().merge(new int[] {4,5,6,0,0,0}, 3, new int[] {1,2,3}, 3);
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2.length == 0) return;

        int len1 = 0, len2 = 0, total = 0;
        while (len2 < n) {
            // 插入到前面
            if (nums2[len2] < nums1[total]) {
                // 后移动
                for (int i = m+len2; i >= total+1; i--) {
                    nums1[i] = nums1[i-1];
                }
                nums1[total] = nums2[len2];
                len2++;
            } else {
                // 到头了
                if (len1 == m) {
                    nums1[total] = nums2[len2];
                    len2++;
                } else {
                    len1++;
                }
            }
            total++;
        }
    }



    public void merge_(int[] nums1, int m, int[] nums2, int n) {
        int i1 = 0, i2 = 0;
        next : while (i2 < n) {
            int val = nums2[i2];
            for (int j = i1; j < m + i2; j++) {
                if (nums1[j] > val ) {
                    // 从nums1[j]开始，所有元素向后移动一格
                    for (int k = m + i2; k > j; k--) {
                        nums1[k] = nums1[k-1];
                    }
                    nums1[j] = val;
                    i1 = j + 1;
                    i2++;
                    continue next;
                }
            }
            // 到达nums1终点，此时需要插入最后
            for (;i2 < n; i2++) {
                nums1[m+i2] = nums2[i2];
            }
        }
    }
}
