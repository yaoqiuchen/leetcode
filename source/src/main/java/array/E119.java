package array;

import java.util.ArrayList;
import java.util.List;

/**
 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 */
public class E119 {

    public static void main(String[] args) {
        new E119().getRow(3);
    }



    public List<Integer> getRow(int rowIndex) {
        List<Integer> lastLine = new ArrayList<>();
        for (int i = 1; i <= rowIndex+1; i++) {
            List<Integer> newLine = new ArrayList<>(i);
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i-1) {
                    newLine.add(1);
                    continue;
                }
                int val = lastLine.get(j-1) + lastLine.get(j);
                newLine.add(val);
            }
            lastLine = newLine;
        }
        return lastLine;
    }


    public List<Integer> getRow_(int rowIndex) {
        List<Integer> prev = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();

        for (int i = 1; i <= rowIndex + 1; i++) {
            cur = new ArrayList<>();
            for (int j =0; j < i; j++) {
                int sum = prev.size() == 0 ? 1 :
                        ((j-1<0 ? 0:prev.get(j-1)) + (j == prev.size()? 0 : prev.get(j)));
                cur.add(sum);
            }
            prev = cur;
        }
        return cur;
    }

}
