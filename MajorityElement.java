/*
 * ============================================================================
 *  Problem: LeetCode 169 — Majority Element
 *  Author: Drew Mayberry
 *  Link: https://leetcode.com/problems/majority-element/ 
 *
 *  Description:
 *    Given an integer array nums of length n, return the element that appears
 *    more than floor(n/2) times. The problem guarantees that such an element
 *    exists.
 *
 *  Approaches:
 *    1) Frequency Map (HashMap)
 *       - Count occurrences of each value in one pass.
 *       - Track the key with the highest running frequency.
 *       - Time:  O(n)
 *       - Space: O(k) where k = number of distinct elements
 *
 *    2) Boyer–Moore Majority Vote (Optimal)
 *       - Maintain a candidate and a counter.
 *       - Increment counter when current equals candidate; otherwise decrement.
 *       - Reset candidate when counter hits zero.
 *       - Time:  O(n)
 *       - Space: O(1)
 *       - Note: If a majority were NOT guaranteed, add a second pass to verify.
 *
 *  Notes:
 *    - This file includes both implementations for comparison.
 *    - In interviews, explain Boyer–Moore’s cancellation intuition and,
 *      if no guarantee exists, show the verification pass idea.
 * ============================================================================
 */

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public static void main(String[] args) {
        // Minimal sanity checks (two test sets)
        int[] a1 = { 3, 2, 3 }; // majority = 3
        int[] a2 = { 2, 2, 1, 1, 1, 2, 2 }; // majority = 2

        System.out.println("Map         a1 -> " + majorityElement(a1)); // 3
        System.out.println("BoyerMoore  a1 -> " + majorityElementBoyerMoore(a1)); // 3

        System.out.println("Map         a2 -> " + majorityElement(a2)); // 2
        System.out.println("BoyerMoore  a2 -> " + majorityElementBoyerMoore(a2)); // 2
    }

    /**
     * Majority element via frequency map (HashMap).
     *
     * Rationale:
     * - Single pass to build counts; track the current best as we go to avoid a
     * second scan.
     * - Uses Map.merge(k,1,sum) to express "insert-or-increment" succinctly.
     *
     * Complexity:
     * - Time: O(n) (amortized O(1) per map op)
     * - Space: O(k) (k distinct elements)
     *
     * @param nums input array (n >= 1; problem guarantees a majority exists)
     * @return the majority element
     */
    public static int majorityElement(int[] nums) {
        // Pre-size the map to reduce rehashing (capacity ≈ expectedSize / loadFactor)
        Map<Integer, Integer> freq = new HashMap<>((int) (nums.length / 0.75f) + 1);

        // Track the element with the highest observed count so far
        int bestKey = nums[0];
        int bestCnt = 0;

        for (int num : nums) {
            // Absent -> 1; Present(k) -> k+1. The returned value is the NEW count for this
            // key.
            int c = freq.merge(num, 1, Integer::sum);

            // Update leader if this key just took the lead
            if (c > bestCnt) {
                bestCnt = c;
                bestKey = num;
            }
        }
        return bestKey;
    }

    /**
     * Boyer–Moore Majority Vote (optimal).
     *
     * Rationale:
     * - Treat mismatches as mutual cancellations. A true majority (> n/2)
     * cannot be fully canceled, so it survives as the final candidate.
     *
     * Complexity:
     * - Time: O(n) single pass
     * - Space: O(1)
     *
     * If majority were not guaranteed, add:
     * if (!isMajority(nums, candidate)) throw new IllegalStateException("No
     * majority");
     *
     * @param nums input array (n >= 1; majority guaranteed)
     * @return the majority element
     */
    public static int majorityElementBoyerMoore(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int x : nums) {
            // If counter is empty, adopt current value as the new candidate
            if (count == 0) {
                candidate = x;
            }
            // Vote: +1 for candidate, -1 otherwise
            count += (x == candidate) ? 1 : -1;
        }
        return candidate;
    }

    // Optional verifier if the majority is NOT guaranteed (kept private for
    // reference)
    @SuppressWarnings("unused")
    private static boolean isMajority(int[] nums, int cand) {
        int cnt = 0;
        for (int x : nums)
            if (x == cand)
                cnt++;
        return cnt > nums.length / 2;
    }
}
