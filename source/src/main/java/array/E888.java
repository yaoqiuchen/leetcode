package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 888. 公平的糖果交换

 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 块糖的大小，B[j] 是鲍勃拥有的第 j 块糖的大小。

 因为他们是朋友，所以他们想交换一个糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）

 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。

 如果有多个答案，你可以返回其中任何一个。保证答案存在。

 */
public class E888 {

    // 2020-2-7
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int gap = (sumA - sumB)/2;

        Set<Integer> pos = new HashSet<>();
        Arrays.stream(B).forEach(e -> pos.add(e));

        for (int i = 0; i < A.length; i++) {
            int target = A[i] - gap;
            if (target > 0 && pos.contains(target)) {
                return new int[] {A[i], target};
            }
        }

        return null;
    }


    // 性能稍好
    public int[] fairCandySwap_(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();

        int gap = (sumA - sumB) / 2;
        int a = 0, b = 0;
        while (a < A.length && b < B.length) {
            if (A[a] - B[b] < gap) {
                a++;
            } else if (A[a] - B[b] > gap) {
                b++;
            } else {
                return new int[] {A[a], B[b]};
            }
        }

        return A;
    }


    // 时间略长
    public int[] fairCandySwap2(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();

        int gap = (sumA - sumB) / 2;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] - B[j] == gap) {
                    return new int[] {A[i], B[j]};
                }
            }
        }

        return A;
    }

}
