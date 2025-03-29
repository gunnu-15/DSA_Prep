package Sorting_Techniques;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sorting_Merge_Quick {
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

        merge_sort(array, 0,n-1);

    }

    // Important
    public static void merge_sort(int[] array, int low, int high) {

        // Base Case: When to stop recursion calls ? - At last step, each individual
        // element will be in its respective array.
        // E.g : [3], [1], [2], [4], [1], [5], [2], [6], [4]; index = 0 to 8
        if (low >= high) {
            return;
        }

        // Low - starting pt of hypothetical array
        // High - ending pt of hypothetical array
        int middle = (low + high)/2;
        // Sort 1st half of the array
        merge_sort(array, low, middle);
        // Sort last half of the array
        merge_sort(array, middle + 1, high);
        merge(array, low, middle, high);

        // TC - O(n * [log base 2 n]) as n -> n/2 -> n/4 -> n/8 -> n/16 .....
        // SC - 0(n) as it uses an extra array/list to store sorted data
    }

    private static void merge(int[] array, int low, int middle, int high) {

        // Initialize an empty data structure
        List<Integer> resultant_list = new ArrayList<>();

        // [1,1,2,3,4], [2,4,5,6] - Now, we need to merge them into 1 single sorted array
        int left_pointer = low; // Array1
        int right_pointer = middle + 1; // Array2
        //Condition - Elements are still present in both arrays
        while (left_pointer <= middle && right_pointer <= high) {
            if (array[left_pointer] <= array[right_pointer]) {
                // Add the min element in the resultant data structure
                resultant_list.add(array[left_pointer]);
                // Move ahead the pointer
                left_pointer++;
            }
            else {
                resultant_list.add(array[right_pointer]);
                right_pointer++;
            }
        }

        // If elements remain on left, copy all of them
        while (left_pointer <= middle) {
            resultant_list.add(array[left_pointer]);
            left_pointer++;
        }

        //If elements remain on right, copy all of them
        while (right_pointer <= high) {
            resultant_list.add(array[right_pointer]);
            right_pointer++;
        }

        // Pick all the indexes from resultant_list/array & store them in the correct order
        // from low to high in the original array
        for (int i = low; i <= high; i++) {
            array[i] = resultant_list.get(i - low);
        }
        for (int integer : array) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    public static void quick_sort(int[] array, int low, int high) {
        // low, high to assign Pointers
        // Pivot = array[low]
        // i starts from 0 index, j starts from (n-1) index
        // 4,6,2,5,7,9,1,3. Is 4 > 4? No. Is 6 > 4? Yes. So, i pointer stops
        // at 6(1st element larger than Pivot) Now, find the 1st element smaller than Pivot from j
        // Once you find them, swap them
        // arr[low, partition - 1]  & arr[partition + 1, high] - Perform qs on them by recursion

        if (low < high) {
            int partition_index = partition(array, low, high);
            quick_sort(array, low, partition_index - 1);
            quick_sort(array, partition_index + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int i = low;
        int j = high;

        // Loop it until the i pointer doesn't cross the j pointer, because if it crosses
        // the left half & right half of partitions will already be arranged in
        // asc & desc to partition
        while (i < j) {
            // Find the 1st element greater than Pivot from the left. Make sure i
            // pointer doesn't cross high boundary
            while (array[i] <= pivot && i<= high - 1) {
                i++;
            }

            // Find the 1st element smaller than Pivot & make sure j doesn't cross low boundary
            while (array[j] > pivot && j >= low + 1) {
                j--;
            }

            // If they haven't crossed each other
            if (i < j) {
                swap(array, array[i], array[j]);
            }
        }

        //
        swap(array, array[low], array[j]);
        return j;

        // TC - O(n * [log base 2 (n)])
        // SC - O(1)


    }

    // Swapping for primitive data types - int[], double[], etc
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
