package Binary_Search;

import java.util.Scanner;

public class BS_on_1D_Arrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user for the size of the array
        System.out.print("Enter the number of elements in array: ");
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

        // Ask user for the value of target
        System.out.print("Enter the value of x: ");
        int x = sc.nextInt();

        int result = count_occurrences_in_sorted_array_BS(array, x);
        System.out.println("Total occurrences: " + result);

    }

    // Binary Search is an algorithm which is always applicable whenever we're
    // performing a search in a SORTED area

    // Real-Life Example - Let's say we have a dictionary & we want to search the
    // word "Raj". Linear search will be time-consuming here. So, if you could split
    // the dictionary into portions/regions - eg: left portion with words starting with
    // 's',then the right portion will definitely have words >= 'S.....'. So, 'Raj' won't
    // be found in the right half. Essentially, we've trimmed down the search space.

    // In binary search, we generally divide the search space into two equal halves
    // and then try to locate which half contains the target. According to that, we
    // shrink the search space size.

    // Eg: [3,4,6,7,9,12,16,17]. Target = 6

    public static int find_X_in_sorted_array_iterative(int[] array, int target) {
        // Objective is to shrink the search space using low & high pointers. Use
        // mid-index to divide the search space into halves.
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (target > array[mid]) {
                low = mid + 1;
            } else if (target < array[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        // If element is NOT found
        return -1;
    }

    public static int find_X_in_sorted_array_recursive(int[] array, int target,
                                                       int low, int high) {

        int mid = (low + high) / 2;
        // Base Condition
        if (low > high) {
            return -1;
        }
        if (target == array[mid]) {
            return mid;
        }
        if (target > array[mid]) {
            return find_X_in_sorted_array_recursive(array, target, mid + 1, high);
        }
        return find_X_in_sorted_array_recursive(array, target, low, mid - 1);

        // TC - O(log base 2 (n)) as in every iteration, the search size gets approx.
        // halved. Eg: 32(2^5) -> 16 -> 8 -> 4 -> 2 -> 1. => 6 steps.

        // Overflow Case - An array can have size of ~ 10^5/10^6/10^7. What if the high
        // index has Integer.INT_MAX ? mid = (low + high)/2. While trimming the search
        // portion, what if the low reaches INT_MAX, along with INT_MAX, in that case
        // mid = 2 * INT_MAX, which CANNOT be stored in an int variable. It'll OVERFLOW.
        // To tackle this - 1) Assign long to low, high 2) write mid as
        // mid = [2*low + (high - low)]/2 => low + (high - low)/2.
    }

    public static int implement_lower_bound(int[] array, int x) {
        // Given a sorted array of N integers and an integer x, write a program to
        // find the lower bound of x, i.e. smallest index s.t
        // Lower Bound => array[ind] >= x

        int low = 0;
        int high = array.length - 1;
        int lower_bound = array.length;

        while (low <= high) {
            int mid = (low + high) / 2;
            // If array[mid] >= x, array[mid] MAY be the answer, trim down the search
            // space
            if (array[mid] >= x) {
                lower_bound = mid;
                // look for small index on the LEFT
                high = mid - 1;
            } else if (array[mid] < x) {
                // look on the RIGHT
                low = mid + 1;
            }
        }
        // TC - O(log base 2 (N))
        return lower_bound;
    }

    public static int implement_upper_bound(int[] array, int x) {
        // Given a sorted array of N integers and an integer x, write a program to
        // find the lower bound of x, i.e. smallest index s.t
        // Upper Bound => array[ind] > x. (= sign is NOT PRESENT here, unlike lower bound)

        int low = 0;
        int high = array.length - 1;
        int upper_bound = array.length;

        while (low <= high) {
            int mid = (low + high) / 2;
            // If array[mid] >= x, array[mid] MAY be the answer, trim down the search
            // space
            if (array[mid] > x) {
                upper_bound = mid;
                // look for small index on the LEFT
                high = mid - 1;
            } else if (array[mid] <= x) {
                // look on the RIGHT
                low = mid + 1;
            }
        }
        // TC - O(log base 2 (N))
        return upper_bound;
    }

    public static int search_insert_position(int[] array, int target) {
        // You are given a sorted array arr of distinct values and a target value x.
        // You need to search for the index of the target value in the array.
        // If the value is present in the array, then return its index. Otherwise,
        // determine the index where it would be inserted in the array while
        // maintaining the sorted order.

        int low = 0;
        int high = array.length - 1;
        int search_index = array.length - 1;

        // If the element is indeed present
        while (low <= high) {
            int mid = (low + high) / 2;

            if (low == high) {
                search_index = low;
            }

            // search to the RIGHT
            if (target > array[mid]) {
                low = mid + 1;
            }
            // search to the LEFT
            else if (target < array[mid]) {
                high = mid - 1;
            } else {
                search_index = mid;
            }
        }
        return search_index;
    }

    public static int floor_ceil_in_sorted_array(int[] array, int x) {
        // Floor - largest no (in an array) <= x  [Floor(bottom) - Max]
        // Ceil - smallest no (in an array) >= x  [Ceiling(top) -
        // (Floor : <=x ) ..... x ..... (Ceil : >=x )

        // Ceil - similar to lower bound: smallest element s.t array[ind] >= x

        // In case of multiple occurrences of ceil/floor of x, return the index
        // of the last occurrence.

        int low = 0, high = array.length - 1;
        // If 'floor' element DOES NOT exist, return -1
        int floor = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // this MAY be the floor, search to the RIGHT
            if (array[mid] <= x) {
                floor = array[mid];
                low = mid + 1;
            }
            // search to the LEFT
            else if (array[mid] > x) {
                high = mid - 1;
            }
        }
        return floor;
    }

    public static int[] first_last_occurrence_in_sorted_array(int[] array, int x) {
        // Eg: [2,4,6,8,8,8,11,13].
        // Case - I: For 8: first occurrence = index 3. Last occurrence = index 5.
        // Case - II: For 10(not present) OR 14(hypothetical index) - {-1, -1}
        // Case - III: For 11. {6, 6} - Occurs only once

        // Lower bound = will return the first occurrence(if element is present)
        // Upper bound - 1 = will return the last occurrence(if element is present)

        int lower_bound = implement_lower_bound(array, x);
        int upper_bound = implement_upper_bound(array, x);

        int[] result = new int[2];

        if (lower_bound == array.length || array[lower_bound] != x) {
            result[0] = -1; result[1] = -1;
            return result;
        }
        else {
            result[0] = lower_bound; result[1] = upper_bound - 1;
            return result;
        }
        // TC - 2 * O(log base 2(N))

    }

    // Disclaimer: The below 2 functions consume less memory & are better solutions
    // compared to above function
    public static int[] first_last_occurrence_in_sorted_array_BS(int[] array, int x) {
        int[] result = new int[2];

        int first_occurrence = first_occurrence_in_sorted_array_BS(array, x);

        if (first_occurrence == -1) {
            result[0] = -1; result[1] = -1;
            return result;
        }
        else {
            int last_occurrence = last_occurrence_in_sorted_array_BS(array, x);
            result[0] = first_occurrence; result[1] = last_occurrence;
            return result;
        }
    }

    public static int count_occurrences_in_sorted_array_BS(int[] array, int x) {
        int[] result = new int[2];

        int first_occurrence = first_occurrence_in_sorted_array_BS(array, x);

        if (first_occurrence == -1) {
            return 0;
        }
        else {
            int last_occurrence = last_occurrence_in_sorted_array_BS(array, x);
            return (last_occurrence - first_occurrence  + 1);
        }
    }

    private static int first_occurrence_in_sorted_array_BS(int[] array, int x) {
        // Eg: [2,8,8,8,8,8,11,13]

        int low = 0, high = array.length - 1;
        int first_occurrence = -1;

        while (low <= high) {
            int mid = (low + high)/2;
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

    private static int last_occurrence_in_sorted_array_BS(int[] array, int x) {
        // Eg: [2,8,8,8,8,8,11,13]

        int low = 0, high = array.length - 1;
        int last_occurrence = -1;

        while (low <= high) {
            int mid = (low + high)/2;
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
            // search to the RIGHT to find next HIGHER index
            else {
                last_occurrence = mid;
                low = mid + 1;
            }
        }
        // Similar logic to lower_bound. TC - O(log base 2 (N))
        return last_occurrence;
    }

    public static void search_element_in_rotated_sorted_array_I() {

    }


}
