package array;

/**
 667. 优美的排列 II

 给定两个整数 n 和 k，你需要实现一个数组，这个数组包含从 1 到 n 的 n 个不同整数，同时满足以下条件：

 ① 如果这个数组是 [a1, a2, a3, ... , an] ，那么数组 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|]
 中应该有且仅有 k 个不同整数；.

 ② 如果存在多种答案，你只需实现并返回其中任意一种.

 示例 1:

 输入: n = 3, k = 1
 输出: [1, 2, 3]
 解释: [1, 2, 3] 包含 3 个范围在 1-3 的不同整数， 并且 [1, 1] 中有且仅有 1 个不同整数 : 1


 示例 2:

 输入: n = 3, k = 2
 输出: [1, 3, 2]
 解释: [1, 3, 2] 包含 3 个范围在 1-3 的不同整数， 并且 [2, 1] 中有且仅有 2 个不同整数: 1 和 2


 提示:

 n 和 k 满足条件 1 <= k < n <= 104.
 */
public class M667 {

    public static void main(String[] args) {
        new M667().constructArray(3, 2);
    }


    // 2020-1-27
    // 第一直觉想不出来，要用纸演算
    public int[] constructArray(int n, int k) {
        if (n <= 0) return new int[] {};
        int[] res = new int[n];
        int count = 1;
        for (int i = 0; i <= k; i += 2) {
            res[i] = count++;
        }
        int j = (k%2==1) ? k : k-1;
        for (; j>0; j-=2) {
            res[j] = count++;
        }
        while (count<=n) {
            res[count-1] = count++;
        }

        return res;
    }



    // 首先根据k来设置 1 X 2 X 3 X 4 X...
    // 再根据k确定回头的节点，设置X的数字
    // 最后把没有参与排列的数字放到数组尾部
    public int[] constructArray_(int n, int k) {
        int[] res = new int[n];
        int count = 1;
        for (int i = 0; i <= k; i += 2) {
            res[i] = count++;
        }

        int j = (k%2==1) ? k : k - 1;
        for (; j > 0; j -= 2) {
            res[j] = count++;
        }

        while (count <= n) {
            res[count-1] = count++;
        }
        return res;
    }
}
