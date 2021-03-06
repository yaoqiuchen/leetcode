package array;

/**
 896. 单调数列

 如果数组是单调递增或单调递减的，那么它是单调的。

 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。

 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 */
public class E896 {

    // 2020-2-7
    public boolean isMonotonic(int[] A) {
        if (A.length <= 2) return true;
        int gap = A[1] - A[0];
        for (int i = 2; i < A.length; i++) {
            int _gap = A[i] - A[i-1];
            if (gap == 0) {
                gap = _gap;
            } else if (gap > 0 && _gap < 0 || gap < 0 && _gap > 0) {
                return false;
            }
        }

        return true;
    }




    public boolean isMonotonic_(int[] A) {
        Boolean increase = null;
        for (int i = 1; i < A.length; i++) {
            int gap = A[i] - A[i-1];
            if (gap != 0) {
                if (increase != null && increase != gap > 0) {
                    return false;
                }
                increase = gap > 0;
            }
        }
        return true;
    }
}
