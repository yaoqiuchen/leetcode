package others;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. 第k个排列
 */
public class M60 {

    public static void main(String[] args) {
        new M60().getPermutation(3, 4);
    }


    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int count = 1;
        for (int i = 1; i <= n; i++) {
            count *= i;
            nums.add(i);
        }

        String result = "";
        // 先从第一个数字开始决定
        for (int i = n; i >= 1; i--) {
            // 每个数字占多少个分量
            int partition = count / i;
            int idx = (k-1) / partition;
//            if (k % partition == 0) {
//                idx = idx == 0? 0 : idx-1;
//            }
            k -= idx * partition;
            count = partition;
            int number = nums.remove(idx);
            result += number;
        }

        return result;
    }


}
