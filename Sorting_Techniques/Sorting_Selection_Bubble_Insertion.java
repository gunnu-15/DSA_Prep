package Sorting_Techniques;

import java.util.Scanner;

public class Sorting_Selection_Bubble_Insertion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user for the size of the array
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();

        System.out.print("Enter the elements of array: ");
        // Declare an array of size n
        int[] array = new int[n];

        // Taking input for the array
        for (int i = 0; i < n; i++) {
            int integer;
            integer = sc.nextInt();
            array[i] = integer;
        }

        insertion_sort(array, n);


    }

    // Important
    public static void selection_sort(int[] array, int n) {

        // [0 till n-1],[1 till n-1],[2 till n-1],.....[n-2 till n-1]
        // TC ~ O(n(n+1)/2) ~ O(n^2) - Best, Average & Worst TC for this algo
        for (int i = 0; i <= n - 2; i++) {
            // Find out the min element index from an array
            int min_index = i;
            for (int j = i; j <= n - 1; j++) {
                if (array[j] < array[min_index]) {
                    min_index = j;
                }
            }

            swap(array, i, min_index);
        }

        for (int integer : array) {
            System.out.print(integer + " ");
        }
    }

    // Important
    public static void bubble_sort(int[] array, int n) {
        // [0 to n-1],[0 to n-2],[0 to n-3],[0 to n-4],[0 to n-5],
        // TC - O(n^2) - Worst Complexity
        // Can we optimize it even further ?
        for (int i = n - 1; i >= 0; i--) {
            int didSwap = 0;
            for (int j = 0; j < i; j++) {
                if (array[j + 1] < array[j]) {
                    swap(array, j, j + 1);
                    didSwap = 1;
                }
            }
            // Exit the next set of iterations if no swap happened in the 1st round
            // Meaning the array is already sorted
            if (didSwap == 0) {
                break;
            }
        }

        // Print the sorted array
        for (int integer : array) {
            System.out.print(integer + " ");
        }
    }

    public static void insertion_sort(int[] array, int n) {
        // [0 to 1],[0 to 2],[0 to 3],[0 to 4],....[0 to (n-1)],
        // TC ~ O(n(n+1)/2) ~ O(n^2) - Worst Case, Best Case - O(n)
        for (int i = 1; i<n; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i - j] < array[i - j - 1]) {
                    swap(array, i-j-1, i-j);
                }
            }
        }

        // Print the sorted array
        for (int integer : array) {
            System.out.print(integer + " ");
        }
    }

    // Swapping for primitive data types - int[], double[], etc
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
