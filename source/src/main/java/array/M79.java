package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 单词搜索
 *

 给定一个二维网格和一个单词，找出该单词是否存在于网格中。

 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格
 。同一个单元格内的字母不允许被重复使用。

 示例:

 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 给定 word = "ABCCED", 返回 true.
 给定 word = "SEE", 返回 true.
 给定 word = "ABCB", 返回 false.

 */
public class M79 {

    public static void main(String[] args) {
        char input[][] = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        new M79().exist(input, "ABKB");
    }

    // 2020-1-20
    public boolean exist(char[][] board, String word) {
        if (board.length == 0) return false;

        int m = board.length, n = board[0].length;
        if (m * n < word.length()) return false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] dp = new boolean[m][n];
                if (exist(board, i, j, word, 0, dp)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, int x, int y, String word, int start, boolean[][] dp) {
        if (start == word.length()) return true;

        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || dp[x][y]) {
            return false;
        }
        if (board[x][y] == word.charAt(start)) {
            dp[x][y] = true;
            if (exist(board, x-1, y, word, start+1, dp) ||
                    exist(board, x, y+1, word, start+1, dp) ||
                    exist(board, x+1, y, word, start+1, dp) ||
                    exist(board, x, y-1, word, start+1, dp)) {
                return true;
            }
            dp[x][y] = false;
        }
        return false;
    }



    public boolean exist_(char[][] board, String word) {
        if (board.length * board[0].length < word.length()) return false;

        boolean dp[][] = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (verify(i, j, board, dp, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verify(int x, int y, char[][] board, boolean[][] dp, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        // 越界，或者字符不相等
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || dp[x][y] || word.charAt(0) != board[x][y]) {
            return false;
        }

        dp[x][y] = true;
        String next = word.substring(1);
        if (verify(x-1, y, board, dp, next) || verify(x+1, y, board, dp, next)
                || verify(x, y-1, board, dp, next) || verify(x, y+1, board, dp, next)) {
            return true;
        }
        dp[x][y] = false;
        return false;
    }
}
