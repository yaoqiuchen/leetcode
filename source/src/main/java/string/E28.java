package string;

/**
 Given an array nums and a value val, remove all instances of that value in-place and return the
 new length.

 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra
 memory.

 The order of elements can be changed. It doesn't matter what you leave beyond the new length.

 Example 1:

 Given nums = [3,2,2,3], val = 3,

 Your function should return length = 2, with the first two elements of nums being 2.

 It doesn't matter what you leave beyond the returned length.
 */
public class E28 {

    // 2026/4/13
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int z = 0;
            for (int j = i; j < i+needle.length();j++, z++) {
                if (haystack.charAt(j) != needle.charAt(z)) {
                    break;
                }
            }
            if (z == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
