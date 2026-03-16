package math;

/**
 Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

 Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



 Example 1:

 Input: x = 123
 Output: 321
 Example 2:

 Input: x = -123
 Output: -321
 Example 3:

 Input: x = 120
 Output: 21


 Constraints:

 -231 <= x <= 231 - 1

 */
public class M7 {

    public static void main(String[] args) {
        new M7().reverse(1534236461);
    }

    // 2026/3/9
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE || x == 0) {
            return 0;
        }

        int max = Integer.MAX_VALUE;

        boolean isNegative = x < 0;
        x = Math.abs(x);
        int xLen = String.valueOf(x).length();
        String maxStr = String.valueOf(Integer.MAX_VALUE);

        int res = 0, idx = 0;
        boolean skip = xLen < maxStr.length();
        while (x > 0) {
            int y = x % 10;
            if (!skip) {
                if (xLen == maxStr.length() && y > maxStr.charAt(idx)-'0') {
                    return 0;
                }
                if (y < maxStr.charAt(idx)-'0') {
                    skip = true;
                }
            }
            res = res * 10 + y;
            x /= 10;
            idx++;
        }

        return isNegative ? res * -1 : res;
    }


}
