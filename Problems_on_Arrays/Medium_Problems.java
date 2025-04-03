package Problems_on_Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Medium_Problems {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user for the size of the array
        System.out.print("Enter the number of elements in array1: ");
        int n1 = sc.nextInt();

        System.out.print("Enter the elements of array: ");
        // Declare an array of size n
        int[] array = new int[n1];

        // Taking input for the array
        for (int i = 0; i < n1; i++) {
            int integer;
            integer = sc.nextInt();
            array[i] = integer;
        }

        /*System.out.print("Enter the target sum: ");
        int target = sc.nextInt();*/

        System.out.println("Majority element is: " + majority_element_n_by_2_times(array));;

        /*for (Integer i : array) {
            System.out.print(i + " ");
        }*/
    }

    public static String two_sum_problem(int[] array, int target) {
        // Eg: [2,6,5,8,11]. Target = 14
        // Brute-Force
        /*
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                // We can't pick the same element
                if (i == j) {continue;}
               if (array[i] + array[j] == target) {
                   System.out.println("Yes");
                   System.out.println("Indexes are: " + i + " and " + j);
               }
            }
        }
        // TC - O(n^2)
         */

        // Better Solution - Hashing
        /*
        int[] ans = new int[2];
        Map <Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int remaining_number = target - array[i];
            if (hash.containsKey(remaining_number)) {
               System.out.println("Yes");
               ans[0] = i;
               ans[1] = hash.get(remaining_number);
               return ans;
            }
            hash.put(array[i], i);
        }
        // TC - O(N)
        return ans;
         */

        // Optimal Solution - Slightly better, not much, without using Map DS
        // 2 Pointer Approach:

        // Sort the array - [2,5,6,8,11]. Left pointer at 2, Right-pointer at 11.
        // 2 + 11 = 13, 1 short of 14. 2 + 8 = 10, well less than 14.
        // Move the left pointer. 5 + 11 = 16. >, so reduce. 5 + 8 = 13. <, so increase
        // 6 + 8 = 14. If left & right cross & you couldn't find 2 elements, answer is
        // NO.

        int left_pointer = 0;
        int right_pointer = array.length - 1;
        // Sort an array using Arrays.sort() function - TC = O(n * log(N))
        Arrays.sort(array);
        // Make sure that both pointers don't cross each other
        while (left_pointer < right_pointer) {
            if (array[left_pointer] + array[right_pointer] > target) {
                right_pointer--;
            } else if (array[left_pointer] + array[right_pointer] < target) {
                left_pointer++;
            } else {
                return "Yes";
            }
        }
        return "No";

        // TC - O(n) + O(n * log(n))
    }

    public static void sort_an_array_of_0s_1s_2s(int[] array) {
        // Brute-Force Solution - Merge Sort, but TC - O(n * log(N)) & SC - O(n)

        // Better Solution
        /*
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        // Increment respective counts by iterating over the whole array
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                count0++;
            } else if (array[i] == 1) {
                count1++;
            } else {
                count2++;
            }
        }

        for (int i = 0; i < count0; i++) {
            array[i] = 0;
        }
        for (int i = count0; i < count0 + count1; i++) {
            array[i] = 1;
        }
        for (int i = count0 + count1; i < array.length; i++) {
            array[i] = 2;
        }
         */

        // TC - O(2n). SC - O(1)

        // Optimal Solution - Dutch National Flag Algorithm

        // we have placed the mid-pointer in the 1st index and the high pointer in
        // the last index. The low is also pointing to the first index as we have
        // no other index before 0. Here, we are mostly interested in placing the
        // ‘mid’ pointer and the ‘high’ pointer as they represent the unsorted part
        // in the hypothetical array.

        // There can be three different values of mid-pointer i.e. arr[mid]:
        // 1) If arr[mid] == 0, we will swap arr[low] and arr[mid] and will increment
        // both low and mid. Now the sub array from 0 to (low-1) only contains 0.

        // 2) If arr[mid] == 1, we will just increment the mid-pointer and then the
        // index (mid-1) will point to 1 as it should according to the rules.

        // 3) If arr[mid] == 2, we will swap arr[mid] and arr[high] and will
        // decrement high. Now the sub array from index high+1 to (n-1) only
        // contains 2.

        int low = 0;
        int mid = 0;
        int high = array.length - 1;
        while (mid <= high) {
            if (array[mid] == 0) {
                swap(array, low, mid);
                low++;
                mid++;
            } else if (array[mid] == 1) {
                mid++;
            } else {
                swap(array, mid, high);
                high--;
            }
        }

        // TC - O(n). SC - O(1)
    }

    public static Integer majority_element_n_by_2_times(int[] array) {
        // Eg: [2,2,3,3,1,2,2]. n/2 = 3. 2 occurs 4 times, so output = 2
        // Brute-Force Solution
        /*
        for (int i = 0; i < array.length; i++) {
            int count = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == array[i]) {
                    count++;
                }
            }
            if (count > array.length/2) {
                System.out.println(array[i]);
            }
        }
         */
        // TC - O(n^2)

        // Better Solution - Hashing
        /*
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (hash.containsKey(array[i])) {
                hash.put(array[i], hash.getOrDefault(array[i], 0) + 1);
            }
            else {
                hash.put(array[i], 0);
            }
        }
        for (Map.Entry<Integer, Integer> entrySet : hash.entrySet()) {
            if (entrySet.getValue() > (array.length / 2)) {
                return entrySet.getKey();
            }
        }
        // If no element occurs > (n/2) times, return a random value, e.g -1
        return -1;
         */

        // TC - O(n) + O(n). SC - O(n)

        // Optimal Solution - Optimize Space - Moore's Voting Algorithm
        // Eg: [7,7,5,7,5,1,5,7,5,5,7,7,5,5,5,5]

        // 1. Apply Moore's Voting Algo
        int element = 0;
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            // If count = 0, store current element of the array as Element
            if (count == 0) {
                element = array[i];
                count = 1;
            }
            // If the current element(array[i]) & Element(element) are same,increment
            else if (array[i] == element) {
                count++;
            }
            // If not, decrement
            else {
                count--;
            }
        }

        // 2. Verify if the element you got is the majority or not - Iterate &
        // find count
        int count_of_element = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                count_of_element++;
            }
        }
        if (count_of_element > (array.length / 2)) {
            System.out.println("Count of majority element is: " + count_of_element);
            return element;
        }
        return -1;

        // TC - O(n). SC - O(1)




    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}

