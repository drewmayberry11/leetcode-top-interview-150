
/**
 * File: LC2_TwoSum.java
 * Source: LeetCode Top 150 Interview Questions
 *
 * Problem:
 * Given an array of integers nums and an integer target, return the indices of the two numbers
 * such that they add up to target. Each input will have exactly one solution, and you may not
 * use the same element twice.
 *
 * Examples:
 * nums = [2,7,11,15], target = 9 → returns [0,1] because nums[0]+nums[1] == 9
 *
 * Constraints:
 * - 2 <= nums.length <= 10^4
 * - -10^9 <= nums[i] <= 10^9
 * - Only one valid answer exists.
 *
 * This class demonstrates:
 * 1) Brute-force O(n^2) solution
 * 2) Optimized HashMap O(n) solution
 * 3) Professional solution returning Optional<int[]> for safer API design
 *
 * @author Drew Mayberry
 * @since 2025-09-15
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LC2_TwoSum {

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;

        System.out.println("Brute-force solution: " + Arrays.toString(twoSumBasic(nums, target)));
        System.out.println("Optimized solution:  " + Arrays.toString(twoSumOptimized(nums, target)));

        twoSum(nums, target).ifPresentOrElse(
                result -> System.out.println("Professional solution: " + Arrays.toString(result)),
                () -> System.out.println("No solution found"));
    }

    /**
     * Brute-force approach:
     * - Check all possible pairs using two nested loops.
     * - Time Complexity: O(n^2)
     * - Space Complexity: O(1)
     *
     * @param nums   input array of integers
     * @param target target sum
     * @return array of two indices or [-1, -1] if no solution
     */
    public static int[] twoSumBasic(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[] { -1, -1 }; // not enough numbers to form a pair
        }

        // Try all possible pairs i, j (with j > i)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j }; // return as soon as we find a pair
                }
            }
        }
        return new int[] { -1, -1 }; // no valid pair found
    }

    /**
     * Optimized approach using HashMap:
     * - Store each number's index as we traverse.
     * - For each number, check if its complement (target - num) is already in the
     * map.
     * - Time Complexity: O(n)
     * - Space Complexity: O(n)
     *
     * @param nums   input array of integers
     * @param target target sum
     * @return array of two indices or [-1, -1] if no solution
     */
    public static int[] twoSumOptimized(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[] { -1, -1 };
        }

        Map<Integer, Integer> seen = new HashMap<>(); // value -> index map

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // If we have already seen the complement, return the stored index and current
            // index
            if (seen.containsKey(complement)) {
                return new int[] { seen.get(complement), i };
            }

            // Otherwise, store current number and its index for future lookups
            seen.put(nums[i], i);
        }

        return new int[] { -1, -1 }; // should never reach here if exactly one solution exists
    }

    /**
     * Professional solution with safer API design:
     * - Same HashMap approach, but returns an Optional<int[]> instead of a "magic
     * value".
     * - Makes it explicit to the caller that the solution might be absent.
     *
     * @param nums   input array (not null)
     * @param target target sum
     * @return Optional containing int[2] with indices, or empty if no solution
     */
    public static Optional<int[]> twoSum(int[] nums, int target) {
        Objects.requireNonNull(nums, "Input array must not be null");

        if (nums.length < 2) {
            return Optional.empty();
        }

        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (seen.containsKey(complement)) {
                return Optional.of(new int[] { seen.get(complement), i });
            }

            seen.put(nums[i], i);
        }

        return Optional.empty();
    }
}
