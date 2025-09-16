
/**
 * LC2_RemoveElement.java
 * LeetCode Top Interview 150 | Remove Element
 *
 * Problem Statement:
 * Given an integer array nums and an integer val, remove all occurrences of val
 * in nums in-place. The order of the elements may be changed. Then return the
 * number of elements in nums which are not equal to val.
 *
 * Approaches implemented in this class:
 *  - Basic Way: Overwrite non-val elements to the front (keeps order, O(n) time, O(1) space)
 *  - Optimized Way: Swap with the last element and shrink logical size (does not preserve order, O(n) time, O(1) space)
 *
 * @author Drew Mayberry
 * @see https://leetcode.com/problems/remove-element/
 * @since 09-16-20205
 */
import java.util.Arrays;

public class LC2_RemoveElement {

    public static void main(String[] args) {

        int[] nums1 = { 3, 2, 2, 3 };
        int val1 = 3;

        // ==== Basic Way (Keep order, overwrite in place) ====
        int k1 = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] != val1) {
                nums1[k1] = nums1[i];
                k1++;
            }
        }

        System.out.println("Basic Way:");
        System.out.println("k = " + k1);
        System.out.println("Array after removal: " + Arrays.toString(nums1));
        System.out.println("First k elements: " + Arrays.toString(Arrays.copyOfRange(nums1, 0, k1)));
        System.out.println();

        int[] nums2 = { 0, 1, 2, 2, 3, 0, 4, 2 };
        int val2 = 2;

        // ==== Optimized Way (Swap with end, order not preserved) ====
        int n = nums2.length;
        int i = 0;
        while (i < n) {
            if (nums2[i] == val2) {
                nums2[i] = nums2[n - 1]; // move last element to this spot
                n--; // shrink logical size
            } else {
                i++;
            }
        }

        System.out.println("Optimized Way:");
        System.out.println("k = " + n);
        System.out.println("Array after removal: " + Arrays.toString(nums2));
        System.out.println("First k elements: " + Arrays.toString(Arrays.copyOfRange(nums2, 0, n)));
    }
}
