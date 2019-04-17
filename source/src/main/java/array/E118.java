package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行
 */
public class E118 {

    public static void main(String[] args) {
        new E118().generate(3);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> preLine = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> nextLine = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                int sum = preLine.size() == 0 ? 1 :
                        ((j - 1 < 0 ? 0 : preLine.get(j - 1)) + (j == preLine.size() ? 0 : preLine.get(j)));
                nextLine.add(sum);
            }
            preLine = nextLine;
            res.add(nextLine);
        }
        return res;
    }

}
