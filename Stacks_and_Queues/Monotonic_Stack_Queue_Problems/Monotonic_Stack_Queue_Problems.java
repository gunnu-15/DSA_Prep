package Stacks_and_Queues.Monotonic_Stack_Queue_Problems;

import java.util.Stack;

public class Monotonic_Stack_Queue_Problems {

    // Monotonic - If elements are stored in a specific order(increasing/decreasing)
    // in a stack/queue.

    public static void main(String[] args) {

    }

    // Easy - Found it Difficult. Back Traversal with Monotonic Stack(Light Poles)
    public int[] next_greater_element_NGE(int[] array) {
        //
        int n = array.length;
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            // Remove the smaller elements from top of the stack < arr[i]
            while (!stack.isEmpty() && stack.peek() <= array[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                nge[i] = -1;
            } else {
                nge[i] = stack.peek();
            }

            stack.push(array[i]);
        }
        // TC - O(2N). SC - O(N) + O(N)
        return nge;

    }

    // Easy - Found it Difficult. Back Traversal with Monotonic Stack(Light Poles)
    public int[] next_greater_element_NGE_2(int[] array) {
        // Brute-Force Solution: Circular Array
        /*
        int n = array.length;
        int[] nge = new int[n];
        for (int i = 0; i < n; i++) {
            // Circular Array
            for (int j = i; j < i + n; j++) {
                int index = j % n;

                if (array[index] > array[i]) {
                    nge[i] = array[index];
                    break;
                }
            }
        }
        // TC - O(N^2)
        return nge;
        */

        // Optimal Solution - Monotonic Stack

        int n = array.length;
        int[] nge = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i++) {

            // TC - O(2N) here, at max we could remove 2N elements here
            while (!stack.isEmpty() && stack.peek() <= array[i % n]) {
                stack.pop();
            }

            if (i < n) {
                nge[i] = stack.isEmpty() ? -1 : stack.peek();
            }

            // at max we could push 2N elements here
            stack.push(array[i % n]);
        }
        // TC - O(4N)
        return nge;
    }

    // Easy - Found it Difficult. Front Traversal with Monotonic Stack(Light Poles)
    public int[] previous_smallest_element_PSE(int[] array) {

        int n = array.length;
        int[] pse = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= array[i]) {
                stack.pop();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(array[i]);
        }
        // TC - O(2N)
        return pse;
    }

    // Medium - from here onwards, everything's going above my head, so will come back
    // later to Stacks & Queues
    public void sum_of_subarray_minimums(int[] array) {

        // Brute-Force Solution: Generate all the sub arrays. Find minimum of each of
        // those subarrays, then find the total sum & return it.
        // TC - O(N^2)
    }
}
