package dp;

import java.util.Arrays;

/**
 You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

 For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to
 one of its adjacent washing machines at the same time .

 Given an integer array representing the number of dresses in each washing machine from left to right on the line,
 you should find the minimum number of moves to make all the washing machines have the same number of dresses.
 If it is not possible to do it, return -1.
 */
public class Leetcode517 {

    /**
     * Step 1: sum elements in the array, and return -1 if the sum % len(array) is not 0
     * Step 2: set target = sum / len(array)
     * Step 3: Declare parameter gap, Loop n in array, set gap = gap + n - target
     * Step 4: The result is the max value of abs(gap) and n - target and the result itself.
     */
    public static int findMinMoves(int[] machines) {
        int sum = Arrays.stream(machines).sum();
        if (sum % machines.length > 0) {
            return -1;
        }

        int target = sum / machines.length;
        int steps = 0, gap = 0;

        for (int m : machines) {
            gap += m - target;
            steps = Math.max(steps, Math.max(Math.abs(gap), m - target));
        }
        return steps;
    }
}
