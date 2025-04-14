package Binary_Search;

import java.util.Scanner;

public class BS_on_2D_Arrays {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get matrix dimensions
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        System.out.print("Enter target value: ");
        int target = sc.nextInt();

        // Step 2: Create the matrix
        int[][] matrix = new int[rows][cols];

        // Step 3: Input values into the matrix
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        boolean result = search_in_2D_matrix_I(matrix, target);
        System.out.println(result);

    }

    // Easy
    public static int find_row_with_maximum_number_of_1s(int[][] matrix, int rows, int columns) {
        // A 2-D array with n rows & m columns with each row containing either 0/1.
        // Each row is sorted - Treat every row as an individual array, try finding the
        // first occurrence of 1 in a row, hereafter all the subsequent elements will be 1.

        // First occurrence of 1 in an array - this problem done in BS_on_1D_Arrays
        // Can also use Lower_bound(1) OR Upper_bound(0)

        // To iterate over a 2D matrix - starts with rows first
        int max_1s = 0;
        int row_index_to_return = -1;
        for (int i = 0; i < rows; i++) {
            int first_occurrence_of_1_index = first_occurrence_in_sorted_array_BS
                    (matrix[i], 1);
            int row_1s = columns - first_occurrence_of_1_index;
            if (row_1s > max_1s) {
                max_1s = row_1s;
                row_index_to_return = i;
            }
        }
        // TC - O(rows * log base 2 (columns))
        return row_index_to_return;
    }

    private static int first_occurrence_in_sorted_array_BS(int[] array, int x) {
        // Eg: [2,8,8,8,8,8,11,13]

        int low = 0, high = array.length - 1;
        int first_occurrence = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            // If you find array[mid] >= x, search to the LEFT
            if (array[mid] > x) {
                high = mid - 1;
            }
            // search to the RIGHT
            else if (array[mid] < x) {
                low = mid + 1;
            }
            // The reason this is NOT combined with 'if block' logic is to tackle the
            // case: When given element is NOT present in the array
            // Search to the LEFT to check for next LOWER index
            else {
                first_occurrence = mid;
                high = mid - 1;
            }
        }
        // Similar logic to lower_bound. TC - O(log base 2 (N))
        return first_occurrence;
    }

    // Medium
    public static boolean search_in_2D_matrix_I(int[][] matrix, int target) {
        // The elements of each row are sorted in non-decreasing order. Moreover, the first
        // element of a row is greater than the last element of the previous row (if it
        // exists). You are given an integer ‘target’, and your task is to find if it
        // exists in the given 'matrix' or not.

        int rows = matrix.length;
        int columns = matrix[0].length;

        int low = 0;
        int high = (rows * columns) - 1;


        while (low <= high) {
            int mid = (low + high) / 2;

            int row_number = mid / columns;
            int col_number = mid % columns;

            if (target < matrix[row_number][col_number]) {
                high = mid - 1;
            }
            else if (target > matrix[row_number][col_number]) {
                low = mid + 1;
            }
            else {
                return true;
            }
        }
        // TC - O(log base 2 (rows * columns))
        return false;
    }

    // Medium
    public static boolean search_in_2D_matrix_II(int[][] matrix, int target) {
        // Write an efficient algorithm that searches for a value target in an m x n integer
        // matrix. This matrix has the following properties:
        // 1) Integers in each row are sorted in ascending from left to right.
        // 2) Integers in each column are sorted in ascending from top to bottom.

        int n = matrix.length;
        int m = matrix[0].length;
        int row = 0, col = m - 1;

        // traverse the matrix from (0, m-1):
        while (row < n && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) row++;
            else col--;
        }
        return false;
    }

}
