package array;

/**
 Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 Example 1:

 Given nums = [1,1,2],

 Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

 It doesn't matter what you leave beyond the returned length.
 */
public class E26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;

        int flag = nums[0], len = nums.length;
        for (int i = 1; i < len;) {
            if (nums[i] == flag) {
                remove(nums, i, len - 1);
                len--;
            } else {
                flag = nums[i];
                i++;
            }
        }
        return len;
    }

    public void remove(int[] nums, int delete, int end) {
        for (int i = delete; i < end; i++) {
            nums[i] = nums[i+1];
        }
    }
}
