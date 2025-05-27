package Leetcode_Contests_Problems;

import java.util.*;

public class Leetcode_Contest_Problems {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = sc.nextInt();
        max_product_of_2_digits(n);
    }

    public static void max_product_of_2_digits(int n) {
        // You are given a positive integer n. Return the maximum product of any two
        // digits in n. You may use the same digit twice if it appears more than once in n

        // Brute-Force Solution
        // Convert the number n into a list of Integers
        LinkedList<Integer> list = convert_number_to_int_array(n);
        // Sort the list - Selection/Bubble/Insertion
        selection_sort_practice(list);
        int size = list.size();
        int max_product = list.get(size - 1) * list.get(size - 2);
        System.out.println("Max product: " + max_product);

        // Better Solution - Find the largest & 2nd largest elements in the array
        // & return their product
    }

    private static LinkedList<Integer> convert_number_to_int_array(int n) {
        LinkedList<Integer> list = new LinkedList<>();
        while (n > 0) {
            list.addFirst(n % 10);
            n = n / 10;
        }
        return list;
    }

    // Selection Sort
    private static void selection_sort_practice(LinkedList<Integer> list) {
        // [0 -> n-1], [1 -> n-1], [2 -> n-1] .... [n-2 -> n-1]
        // We'll take the MIN_ELEMENT from each array, then SWAP its position with
        // initial element pos of the given array

        int size = list.size();
        for (int i = 0; i <= size - 2; i++) {
            // Let min element index be i
            int min_index = i;
            // This for loop will determine the index of the MIN_ELEMENT
            for (int j = i; j <= size - 1; j++) {
                if (list.get(j) < list.get(min_index)) {
                    min_index = j;
                }
            }
            // Now, swap the MIN_ELEMENT index pos with the pos of the initial array
            // elements
            swap(list, i, min_index);
            // Keep doing this process for every array until [n-2 -> n-1]
        }
    }

    // Bubble Sort
    private static void bubble_sort_practice() {

    }

    // Insertion Sort
    private static void insertion_sort_practice() {

    }

    // Swapping for primitive data types - int[], double[], etc
    private static void swap(LinkedList<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    // Weekly Contest 449

    // Q1. Minimum Deletions for At Most K Distinct Characters
    public static int minDeletion(String s, int k) {
        // You are given a string s consisting of lowercase English letters, and an integer k.
        // Your task is to delete some (possibly none) of the characters in the string
        // so that the number of distinct characters in the resulting string is at most k.
        // Return the minimum number of deletions required to achieve this.

        // Edge Case
        if (k >= 26) return 0;

        char[] character_array = s.toCharArray();
        // Use HASHING - store no of distinct characters & their frequency
        HashMap<Character, Integer> hashing = new HashMap<>();
        for (char c : character_array) {
            hashing.put(c, hashing.getOrDefault(c, 0) + 1);
        }

        // Now, the hashing map will have all distinct characters stored with their
        // frequency.

        // Edge Case
        if (hashing.size() <= k) {
            return 0;
        }

        // Store frequencies in a list and sort in ascending order
        List<Integer> freq_list = new ArrayList<>(hashing.values());
        Collections.sort(freq_list);

        int number_of_deletions_required = 0;
        int number_of_groups_to_be_removed = hashing.size() - k;
        for (int i = 0; i < number_of_groups_to_be_removed; i++) {
            number_of_deletions_required += freq_list.get(i);
        }
        return number_of_deletions_required;
    }

    // Q2. Equal Sum Grid Partition I
    public boolean canPartitionGrid(int[][] grid) {
        // You are given an m x n matrix grid of positive integers. Your task is to
        // determine if it is possible to make either one horizontal or one vertical
        // cut on the grid such that:
        // Each of the two resulting sections formed by the cut is non-empty.
        // The sum of the elements in both sections is equal.
        // Return true if such a partition exists; otherwise return false.

        // Brute-Force Solution - Make Vertical cuts from cuts = [1 -> col - 1].
        // For each sub-matrix we get, compute sum of that sub-matrix for each cut
        // from scratch. BUT, Constraint: 2<= m * n <= 10^5. This process makes it Too
        // Expensive(Extract sub matrices repeatedly & recompute sum for each cut from
        // scratch) - TLE(Time Limit Exceeded) Exception

        // Better Solution

        int rows = grid.length;
        int cols = grid[0].length;

        // TC - O(m*n) for this step
        long total_sum = get_sum_of_grid(grid);

        // Edge Case
        if (total_sum % 2 != 0) return false;

        // Constraints: 1 <= m == grid.length <= 10^5
        // 1 <= n == grid[i].length <= 10^5
        // 2 <= m * n <= 10^5
        // 1<= grid[i][j] <= 10^5

        // IMPORTANT - Every cell has max value of 10^5, so while calculating sum of the
        // total grid, the max value stored in a variable could be 10^5 * 10^5 => 10^10
        // BUT, an int variable range(~ 2.1 * 10^9) - INTEGER OVERFLOW Issue.
        // FIX - Use long for all sum variables

        // Vertical Line - Don't extract sub matrices. Instead, process the grid
        // COLS by COLS & accumulate sum to find the partition line directly
        // i -> [0, cols - 2] as we need NON-EMPTY Partitions
        long current_sum_vertical = 0;
        // TC - O(m*n) for this step
        for (int i = 0; i < cols - 1; i++) {
            for (int j = 0; j < rows; j++) {
                current_sum_vertical += grid[j][i];
            }
            // Check for every possible cut, if partitions have equal sum
            if (current_sum_vertical == total_sum / 2) {
                return true;
            }
        }

        // Horizontal Line - Don't extract sub matrices. Instead, process the grid
        // ROW by ROW & accumulate sum to find the partition line directly
        // i -> [0, rows - 2] as we need NON-EMPTY Partitions
        long current_sum_horizontal = 0;
        // TC - O(m*n) for this step
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols; j++) {
                current_sum_horizontal += grid[i][j];
            }
            // The moment current_sum = half of total sum, we have found a Horizontal
            // Partition line in the matrix, so return true
            if (current_sum_horizontal == total_sum / 2) {
                return true;
            }
        }
        // If there exists no Vertical or Horizontal cut
        // Overall TC - O(3 * m * n) ~ O(m * n)
        return false;
    }

    private long get_sum_of_grid(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        // Get total sum of all the elements in a grid of rows x cols
        long sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += grid[i][j];
            }
        }
        return sum;
    }

    // Use PREFIX SUMS - speed up vertical & horizontal cut checks(Easy approach)
    /*
    public boolean canPartitionGrid(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        long totalSum = 0;
        long[] rowPrefixSum = new long[rows];
        long[] colPrefixSum = new long[cols];

        // Step 1: Build row-wise and column-wise prefix sums while computing total
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rowPrefixSum[i] += grid[i][j];
                colPrefixSum[j] += grid[i][j];
                totalSum += grid[i][j];
            }
        }

        // Step 2: Quick edge case
        if (totalSum % 2 != 0) return false;
        long target = totalSum / 2;

        // Step 3: Check vertical cuts using column prefix sums
        long currentColSum = 0;
        for (int i = 0; i < cols - 1; i++) {  // non-empty partitions only
            currentColSum += colPrefixSum[i];
            if (currentColSum == target) return true;
        }

        // Step 4: Check horizontal cuts using row prefix sums
        long currentRowSum = 0;
        for (int i = 0; i < rows - 1; i++) {  // non-empty partitions only
            currentRowSum += rowPrefixSum[i];
            if (currentRowSum == target) return true;
        }

        return false;
    }
     */




}
