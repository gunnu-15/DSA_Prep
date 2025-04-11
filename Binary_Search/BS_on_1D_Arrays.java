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

        int result = find_minimum_in_rotated_sorted_array(array);
        System.out.println("Index is: " + result);

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
            result[0] = -1;
            result[1] = -1;
            return result;
        } else {
            result[0] = lower_bound;
            result[1] = upper_bound - 1;
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
            result[0] = -1;
            result[1] = -1;
            return result;
        } else {
            int last_occurrence = last_occurrence_in_sorted_array_BS(array, x);
            result[0] = first_occurrence;
            result[1] = last_occurrence;
            return result;
        }
    }

    public static int count_occurrences_in_sorted_array_BS(int[] array, int x) {
        int[] result = new int[2];

        int first_occurrence = first_occurrence_in_sorted_array_BS(array, x);

        if (first_occurrence == -1) {
            return 0;
        } else {
            int last_occurrence = last_occurrence_in_sorted_array_BS(array, x);
            return (last_occurrence - first_occurrence + 1);
        }
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

    private static int last_occurrence_in_sorted_array_BS(int[] array, int x) {
        // Eg: [2,8,8,8,8,8,11,13]

        int low = 0, high = array.length - 1;
        int last_occurrence = -1;

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
            // search to the RIGHT to find next HIGHER index
            else {
                last_occurrence = mid;
                low = mid + 1;
            }
        }
        // Similar logic to lower_bound. TC - O(log base 2 (N))
        return last_occurrence;
    }

    public static int search_element_in_rotated_sorted_array_distinct(int[] array,
                                                                      int target) {
        // Given an array of size N, sorted in ascending order
        // (with distinct values) and a target value k. Now the array is rotated at
        // some pivot point unknown to you. Find the index at which k is present and
        // if k is not present return -1.

        // Eg: [7,8,9,1,2,3,4,5,6] - This arrays is rotated at element 7.
        // Eg: [4,5,1,2,3] - This arrays is rotated at element 4.

        // Brute-Force Solution - LS: Iterate over the entire array - if element is
        // found, return the particular index, else return -1. TC - O(n)

        // Optimal Solution - Identify which half is sorted - left/right

        int low = 0, high = array.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // If target is found, return its index straightaway
            if (array[mid] == target) {
                return mid;
            }

            // Check if LEFT half is sorted ?
            if (array[low] <= array[mid]) {
                // Check whether target falls in this sorted range - If it does,
                // continue BS as done previously
                if (array[low] <= target && target <= array[mid]) {
                    // continue searching in the LEFT half as target lies here
                    high = mid - 1;
                }
                // If NOT, reject this portion & move to RIGHT half
                else {
                    low = mid + 1;
                }

            }

            // Check if RIGHT half is sorted ?
            else if (array[mid] <= array[high]) {
                // Check whether target falls in this sorted range - If it does,
                // continue BS as done previously
                if (array[mid] <= target && target <= array[high]) {
                    // continue searching in the RIGHT half as target lies here
                    low = mid + 1;
                }
                // If NOT, reject this portion & move to the LEFT half
                else {
                    high = mid - 1;
                }
            }

        }
        // TC: Worst Case ~ O(n/2) since we've been shrinking the array
        return result;
    }

    // Difficult
    public static boolean search_element_in_rotated_sorted_array_duplicates(int[] array,
                                                                            int target) {

        // Given an integer array sorted in ascending order (may contain duplicate
        // values) and a target value k. Now the array is rotated at some pivot point
        // unknown to you. Return True if k is present and otherwise, return False.

        // Eg: [1,0,1,1,1]. target = 0. This example proves why above code won't work
        // for some of the duplicate cases

        // Issue: When array[low] = array[mid] = array[high], this poses a challenge
        // in successfully identifying the sorted half. So, shrink the search space -
        // increment low & decrement high & continue

        int low = 0, high = array.length - 1;
        boolean result = false;

        while (low <= high) {
            int mid = (low + high) / 2;

            // If target is found, return its index straightaway
            if (array[mid] == target) {
                result = true;
                return result;
            }

            // Extra condition for Duplicates - SHRINK the search space & continue
            if (array[low] == array[mid] & array[mid] == array[high]) {
                // Increment low
                low = low + 1;
                // Decrement high
                high = high - 1;
                continue;
            }

            // Check if LEFT half is sorted ?
            if (array[low] <= array[mid]) {
                // Check whether target falls in this sorted range - If it does,
                // continue BS as done previously
                if (array[low] <= target && target <= array[mid]) {
                    // continue searching in the LEFT half as target lies here
                    high = mid - 1;
                }
                // If NOT, reject this portion & move to RIGHT half
                else {
                    low = mid + 1;
                }

            }

            // Check if RIGHT half is sorted ?
            else if (array[mid] <= array[high]) {
                // Check whether target falls in this sorted range - If it does,
                // continue BS as done previously
                if (array[mid] <= target && target <= array[high]) {
                    // continue searching in the RIGHT half as target lies here
                    low = mid + 1;
                }
                // If NOT, reject this portion & move to the LEFT half
                else {
                    high = mid - 1;
                }
            }

        }
        // TC - O(log base 2 (N))
        return result;
    }

    // Difficult
    public static int find_minimum_in_rotated_sorted_array(int[] array) {

        // Given an integer array arr of size N, sorted in ascending order (with
        // distinct values). Now the array is rotated between 1 to N times which is
        // unknown. Find the minimum element in the array.

        // Brute-Force Solution - LS

        // Optimal Solution - The sorted array MIGHT have the min element. So, pick the
        // min from the sorted half & eliminate it.
        // Eg: [4,5,6,7,0,1,2]

        int low = 0, high = array.length - 1;
        int minimum = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Check if LEFT half is sorted ?
            if (array[low] <= array[mid]) {

                // Compare previously stored minimum with subsequent mid to see
                // if there's a LOWER element present in the array
                minimum = Math.min(minimum, array[low]);
                // Discard this half & move to the RIGHT half
                low = mid + 1;
            }
            // Check if RIGHT half is sorted ?
            else if (array[mid] <= array[high]) {
                // Compare previously stored minimum with subsequent mid to see
                // if there's a LOWER element present in the array
                minimum = Math.min(minimum, array[mid]);
                // Discard this & move on to the LEFT half
                high = mid - 1;
            }
        }
        // TC - O(log base 2 (N))
        return minimum;
    }

    public static int find_out_how_many_times_array_has_been_rotated(int[] array) {
        // From the previous function, if you can keep TRACK of the INDEX of the
        // minimum element, the index would represent - count of no of times array
        // has been rotated

        int low = 0, high = array.length - 1;
        int minimum = Integer.MAX_VALUE;
        int count_index = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Check if LEFT half is sorted ?
            if (array[low] <= array[mid]) {

                // Compare previously stored minimum with subsequent mid to see
                // if there's a LOWER element present in the array
                if (array[low] < minimum) {
                    minimum = array[low];
                    count_index = low;
                }
                // Discard this half & move to the RIGHT half
                low = mid + 1;
            }
            // Check if RIGHT half is sorted ?
            else if (array[mid] <= array[high]) {
                // Compare previously stored minimum with subsequent mid to see
                // if there's a LOWER element present in the array
                if (array[mid] < minimum) {
                    minimum = array[mid];
                    count_index = mid;
                }
                // Discard this & move on to the LEFT half
                high = mid - 1;
            }
        }
        // TC - O(log base 2 (N))
        return count_index;

    }

    // Difficult
    public static int single_element_in_sorted_array(int[] array) {
        // Given an array of N integers. Every number in the array except one appears
        // twice. Find the single number in the array.

        // Eg: [1,1,2,2,3,3,4,5,5,6,6]
        // (even, odd), (even, odd)....(element)....(odd, even), (odd, even)
        // (even, odd) -> element is on the RIGHT half
        // (odd, even) -> element is on the LEFT half

        // x (element) x -> Edge cases of 0th & last index - Try eliminating these edge
        //  or conditional cases: Trim down search space

        int n = array.length;

        if (n == 1) {
            return array[0];
        }
        // Check for 1st element
        if (array[0] != array[1]) {
            return array[0];
        }
        // Check for last element
        if (array[n - 1] != array[n - 2]) {
            return array[n - 1];
        }

        int low = 0, high = n - 1;
        int single_element = Integer.MAX_VALUE;

        // Now, after trimming down the search space - Eliminate that half where
        // element is NOT present
        while (low <= high) {
            int mid = (low + high) / 2;

            // If the immediate left & immediate right elements don't match
            if (array[mid] != array[mid - 1] && array[mid] != array[mid + 1]) {
                single_element = array[mid];
                return single_element;
            }
            // If on Odd index, & on immediate left element(even) are same (even, odd)
            // -> element is on RIGHT half, so eliminate the LEFT half
            // OR
            // If on Even index, & on immediate right element(odd) are same (even, odd)
            // -> element is on RIGHT half, so eliminate the LEFT half
            if ((mid % 2 == 1 && array[mid] == array[mid - 1]) || (mid % 2 == 0 &&
                    array[mid] == array[mid + 1])) {
                low = mid + 1;
            }
            // else, element is on LEFT half, so eliminate the RIGHT half
            else {
                high = mid - 1;
            }
        }

        // If no such single element is present.
        // TC - O(log base 2(N))
        return -1;
    }

}
