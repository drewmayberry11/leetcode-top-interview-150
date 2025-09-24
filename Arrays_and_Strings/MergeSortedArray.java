import java.util.Arrays;

/**
 * File: LC1_MergeSortedArray.java
 *
 * Purpose:
 * Implementation of LeetCode Problem #88: "Merge Sorted Array".
 *
 * Problem:
 * Given two sorted arrays nums1 and nums2, merge nums2 into nums1
 * as one sorted array in non-decreasing order.
 *
 * Details:
 * - nums1 has a length of m + n, with the first m elements initialized
 * and the last n elements set to 0 to provide extra space.
 * - nums2 has length n.
 * - The merge should be done in-place, modifying nums1 directly.
 *
 * Approaches Provided:
 * 1. mergeOptimal() — In-place, O(m+n) time, O(1) extra space (best approach).
 * 2. mergeBruteForce() — Copy + sort, O((m+n) log(m+n)) time (simpler but
 * slower).
 *
 * Complexity:
 * mergeOptimal():
 * Time: O(m+n)
 * Space: O(1)
 *
 * mergeBruteForce():
 * Time: O((m+n) log (m+n))
 * Space: O(1) (but uses built-in sort which may allocate internally)
 */
public class LC1_MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int m = 3;
        int[] nums2 = { 2, 5, 6 };
        int n = 3;

        LC1_MergeSortedArray solution = new LC1_MergeSortedArray();

        System.out.println("=== Optimal Merge (In-Place) ===");
        solution.mergeOptimal(nums1, m, nums2, n);
        System.out.println("Merged nums1: " + Arrays.toString(nums1));

        // Reset nums1 for brute force example
        nums1 = new int[] { 1, 2, 3, 0, 0, 0 };

        System.out.println("\n=== Brute Force Merge (Copy + Sort) ===");
        solution.mergeBruteForce(nums1, m, nums2, n);
        System.out.println("Merged nums1: " + Arrays.toString(nums1));
    }

    /**
     * Optimal in-place merge.
     * Uses three pointers to fill nums1 from the back, comparing the largest
     * remaining elements and placing the larger one at the end.
     */
    public void mergeOptimal(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // pointer at last initialized element of nums1
        int j = n - 1; // pointer at last element of nums2
        int k = m + n - 1; // pointer at last index of nums1

        // Merge from the back until one array is exhausted
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // Copy any remaining elements from nums2 (if nums1 still has leftovers,
        // they are already in place, so no action needed)
        while (j >= 0) {
            nums1[k] = nums2[j];
            k--;
            j--;
        }
    }

    /**
     * Brute force solution.
     * Copies nums2 into the tail of nums1, then sorts the entire array.
     * Simpler to write, but less efficient.
     */
    public void mergeBruteForce(int[] nums1, int m, int[] nums2, int n) {
        for (int j = 0; j < n; j++) {
            nums1[m + j] = nums2[j]; // put nums2's elements in the free space
        }
        Arrays.sort(nums1); // sort entire array
    }
}
