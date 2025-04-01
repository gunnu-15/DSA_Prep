package Problems_on_Arrays;

import java.util.*;

public class Easy_Problems {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user for the size of the array
        System.out.print("Enter the number of elements in array1: ");
        int n1 = sc.nextInt();

        System.out.print("Enter the elements of array: ");
        // Declare an array of size n
        int[] array1 = new int[n1];

        // Taking input for the array
        for (int i = 0; i < n1; i++) {
            int integer;
            integer = sc.nextInt();
            array1[i] = integer;
        }

        System.out.println("Maximum consecutive ones is: " + maximum_consecutive_ones(array1));

        // Print the array
//        for (Integer integer : array1) {
//            System.out.print(integer + " ");
//        }
    }

    // In an Interview, drive the interview -> ask about Test Cases, give the brute-force solution,
    // then optimise the solution(if possible), then try finding the most Optimal solution
    // Brute -> Better -> Optimal

    public static void largest_element_in_an_array(int[] array, int n) {
        // Step I: Sort the array [Merge/Quick sort -> TC - O(n * log(n))); SC - O(1) for Quick sort]
        // Print the last element of the array
        // Optimise n * log(n)

        // int largest = array[0], if array[i] > largest; largest = array[i]
        int largest = array[0];
        // TC - O(n), better than O(n * log(N))
        for (int i = 0; i < n; i++) {
            if (array[i] > largest) largest = array[i];
        }
        System.out.println("Largest element in the array is: " + largest);
    }

    // Important
    public static void second_largest_element_in_an_array(int[] array, int n) {
        // Sort the array. TC - O(n * log(N))
        // Find the largest element
        /*
        int second_largest = 0;
        for (int i = n-2; i>=0;i--) {
            if (array[i] != largest) {
                    second_largest = array[i];
                    break;
            }
        }
        */
        // Worst Case - [1,7,7,7,7]; TC - O(n*log(N)) + O(n)

        // Better Solution
        /*
        int largest = array[0];
        for (int i = 0; i < n; i++) {
            if (array[i] > largest) largest = array[i];
        }
        // Disclaimer: Assuming all elements are positive
        // second_largest = Integer.MIN_INT if negatives are there
        int second_largest = -1;
        for (int i = 0; i < n; i++) {
            if (array[i] > second_largest && array[i] != largest) second_largest = array[i];
        }

        // TC - O(N + N) - O(2N)
        System.out.println("Second largest element is: " + second_largest);
        */

        // Optimal Solution
        int largest = array[0];
        int second_largest = -1; // OR second_largest = Integer.MIN_INT
        for (int i = 0; i < n; i++) {
            if (array[i] > largest) {
                second_largest = largest;
                largest = array[i];
            }
            // Eg: [1,2,4,7,7,5] - Why the below piece of code is required
            else if (array[i] < largest && array[i] > second_largest) {
                second_largest = array[i];
            }
        }
        System.out.println("The largest element is: " + largest);
        System.out.println("The second largest element is: " + second_largest);
    }

    public static void second_smallest_element_in_an_array(int[] array, int n) {
        int smallest = array[0];
        // Initialize the second_smallest variable to the largest -ve integer value
        int second_smallest = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (array[i] < smallest) {
                second_smallest = smallest;
                smallest = array[i];
            } else if (array[i] > smallest && array[i] < second_smallest) {
                second_smallest = array[i];
            }
        }
        System.out.println("The smallest value is: " + smallest);
        System.out.println("The second smallest value is: " + second_smallest);
    }

    public static void check_if_array_is_sorted(int[] array, int n) {
        int counter = 0;
        for (int i = 0; i < n - 1; i++) {
            if (array[i + 1] < array[i]) {
                counter++;
            }
        }
        if (counter == 0) System.out.println("Array is SORTED in non-decreasing order");
        else System.out.println("Array is NOT sorted");
    }

    // Important
    public static void remove_duplicates_from_sorted_array(int[] array, int n) {
        // Brute-Force Solution
        // Add the elements of the array into a Set. Set - only contains unique elements

        // Store only the unique elements in the array. LinkedHashSet - stores elements in an orderly fashion
        Set<Integer> unique_elements_set = new LinkedHashSet<>();
        // TC - O(n * log(N))
        for (int i = 0; i < n; i++) {
            unique_elements_set.add(array[i]);
        }

        // Repopulate a new array with only the unique elements from the LinkedHashSet
        int[] unique_elements_array = new int[unique_elements_set.size()];
        int index = 0;
        // TC - O(n)
        for (Integer integer : unique_elements_set) {
            unique_elements_array[index] = integer;
            index++;
        }

        // Print the array with only unique elements
        for (int i : unique_elements_array) {
            System.out.print(i + " ");
        }

        // Overall TC - O(n) + O(n * log(N))

        // Optimal Solution
        // 2 Pointer approach: The first element will always be unique in itself
        // Keep a pointer i at 1st element. Now, move the j pointer from index 1(2nd element)
        // If the element is different than i pointer element, move that element to (i+1)th position
        // .Increment i pointer now. Loop the j pointer till the whole array is iterated

        // TC - O(n); SC - O(1)
        int i = 0;
        for (int j = 1; j < n; j++) {
            if (array[j] != array[i]) {
                array[i + 1] = array[j];
                i++;
            }
        }
        System.out.println(i + 1);
    }

    public static void left_rotate_an_array_by_one_place(int[] array, int n) {
        // Eg: [1,2,3,4,5] -> Left rotate by 1 place means -> [2,3,4,5,1]
        // TC - O(n). SC - O(1): as there's no new array created - Extra space
        int temp = array[0];
        for (int i = 1; i < n; i++) {
            array[i - 1] = array[i];
        }
        array[n - 1] = temp;

    }

    // Important
    public static void left_rotate_an_array_by_D_places(int[] array, int n, int d) {
        // Eg: [1,2,3,4,5] -> D = 8. (8 % 7) = 1. (D % 7) rotations
        // TC - [1,2,3,4,5,6,7]; D = d; [0th index, 1st index, .... (k-1)th index | kth index....]

        // Extract a sub-array of first d elements from an existing array
        int[] temp = Arrays.copyOfRange(array, 0, d);
        for (int i = d; i < n; i++) {
            array[i - d] = array[i];
        }
        // Populate the last d elements of the array with the initial 'd' elements stored in temp
        // Derive mathematical relation b/w temp & array indices
        // temp[0/1/2] -> array[(n-d)/(n-d+1)/(n-d+2)]
        for (int j = n - d; j < n; j++) {
            array[j] = temp[j - (n - d)];

        }
        // TC - O(d) + O(n-d) + O(d). SC - extra space - O(d)

        // Optimal Solution

        // [1,2,3,4,5,6,7] -> Reverse 1,2,3(d) & 4,5,6,7(n-d) -> [3,2,1,7,6,5,4]
        // Reverse the entire array again -> [4,5,6,7,1,2,3]
        // TC - O(d) + O(n-d) + O(n) = O(2n). SC - No extra space used - O(1)

        reverse(array, 0, d - 1);
        reverse(array, d, n - 1);

    }


    // Important
    public static void move_zeroes_to_the_end(int[] array, int n) {
        // Eg: [1,0,2,3,2,0,0,4,5,1] -> [1,2,3,2,4,5,1,0,0,0]

        // Brute-Force Solution
        /*
        List<Integer> temp_array = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (array[i] != 0) {
                temp_array.add(array[i]);
            }
        }
        // Add all the non-zero numbers at the front of the array
        for (int j = 0; j < temp_array.size(); j++) {
            array[j] = temp_array.get(j);
        }

        // Add all the zero-elements at the end of the array/remaining positions
        for (int k = temp_array.size(); k < n; k++) {
            array[k] = 0;
        }
        */


        // TC - O(n) + O(x) + O(n-x) = O(2n). SC - extra space - O(n), Worst-Case

        // Optimal Solution
        // 2-Pointer Approach: Keep the j-pointer at 0 element, keep the i-pointer right after the
        // j-pointer index. If the i-pointer points at non-zero element, swap i & j pointers.
        // If not, increment the i-pointer. The j-pointer always points at 0 element.

        // Initialize j to any -ve integer
        int j = -1;
        for (int k = 0; k < n; k++) {
            if (array[k] == 0) {
                j = k;
                // Break the iteration here once you find the 1st 0 element
                break;
            }
        }
        // Start iterating from the next element after the 0 element found previously
        // Check if the i-pointer element is non-zero, if it is, swap them
        for (int i = j + 1; i < n; i++) {
            if (array[i] != 0) {
                swap(array, i, j);
                // The 0 element after swapping would move 1 step forward, hence the need
                // to increment j
                j++;
            }
        }

        // TC - O(x) + O(n-x) = O(n), better than Brute-Force. SC - O(1), no extra space used


    }

    public static int linear_search(int[] array, int n, int number) {
        // [6,7,8,4,1]; num = 4. Find 1st occurrence of the num in the array, return that index

        for (int i = 0; i < n; i++) {
            if (array[i] == number) {
                return i;
            }
        }
        //If no such element is present in the array
        return -1;
    }

    // Important
    public static LinkedList<Integer> find_the_union(int[] array1, int[] array2) {
        // Find the Union of 2 sorted arrays(might have duplicates)
        // Eg: arr1 - [1,1,2,3,4,5]. arr2 - [2,3,4,4,5]. union[] - [1,2,3,4,5]

        // Brute-Force Solution
        // Unique - Set or Map is the ideal DS

        // Maintains insertion order
        /*
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        for (int i : array1) {
            // TC - O(n1 * log(n)); n - size of the set
            linkedHashSet.add(i);
        }
        for (int i : array2) {
            // TC - O(n2 * log(n)); n - size of the set
            linkedHashSet.add(i);
        }
        // TC - O(n1 + n2) - Worst-Case
        Integer[] union = linkedHashSet.toArray(new Integer[0]);

        // Overall TC - O((n1*log(n) + n2*log(n)). Overall SC - O(n1 + n2)
        return union;
        */

        // Optimal Solution

        // 2-Pointer Approach: Keep i-pointer at array1 & j-pointer at array2
        // Compare their respective 1st elements & pick min element(assuming arrays are sorted)
        // Move the pointer forward whose element has been picked, now if the next element
        // is same as the one picked, just ignore & move pointer forward. Once the iteration
        // on 1 of the arrays is over, iterate in the remaining portion of the other array

        int n1 = array1.length;
        int n2 = array2.length;
        // The 2 pointers initialized
        int i = 0;
        int j = 0;
        LinkedList<Integer> union = new LinkedList<>();
        // While both the pointers are somewhere in b/w
        while (i < n1 && j < n2) {
            //
            if (array1[i] <= array2[j]) {
                // Find the last element added in union LinkedList & compare it with array1[i]
                // If they are equal, ignore it. Also, if there's no element in union LinkedList
                // yet, then take that element since it'd be the 1st element
                if (union.isEmpty() || union.getLast() != array1[i]) {
                    union.add(array1[i]);
                }
                i++;
            } else {
                if (union.isEmpty() || union.getLast() != array2[j]) {
                    union.add(array2[j]);
                }
                j++;
            }
        }

        // Once either of the 2 arrays gets exhausted, iterate over the remaining elements
        // of the other array

        // If array1 is remaining
        while (i < n1) {
            if (union.isEmpty() || union.getLast() != array1[i]) {
                union.add(array1[i]);
            }
            i++;
        }

        // If array2 is remaining
        while (j < n2) {
            if (union.isEmpty() || union.getLast() != array2[j]) {
                union.add(array2[j]);
            }
            j++;
        }
        // TC - O(n1 + n2)
        return union;

    }

    // Important
    public static LinkedList<Integer> find_the_intersection(int[] array1, int[] array2) {
        // Intersection of 2 sorted arrays

        // Brute-Force Solution

        int n1 = array1.length;
        int n2 = array2.length;
        int[] visit = new int[n2];
        LinkedList<Integer> intersection = new LinkedList<>();
        /*
        for (int i = 0; i < n1; i++) {
            // For every ith element in array1, loop through the entire array2
            for (int j = 0; j < n2; j++) {
                // Make sure that you're adding an element only if it's been encountered once only
                if (array2[j] == array1[i] && visit[j] == 0) {
                    intersection.add(array2[j]);
                    // Increment the frequency of the element found
                    visit[j]++;
                    // Break out when a particular element has been found in array2
                    break;
                }
                // Since, both arrays are sorted, once you encounter an element > array1[i],
                // break out of the for loop
                if (array2[j] > array1[i]) {
                    break;
                }
            }
        }*/
        // TC - O(n1 * n2). SC - O(n2)

        // Optimal Solution
        // 2-Pointer Approach:
        int i = 0;
        int j = 0;
        while (i < n1 && j < n2) {
            if (array1[i] > array2[j]) {
                j++;
            } else if (array1[i] < array2[j]) {
                i++;
            } else {
                intersection.add(array1[i]);
                i++;
                j++;
            }
        }
        // TC - O(n1 + n2). SC - O(1) as no extra space is used
        return intersection;
    }

    // Important
    public static int find_missing_number_in_an_array(int[] array, int number) {

        // Brute-Force Solution
        /*int flag = 0;
        // Iterate for the number from num = 1 to n
        for (int i = 1; i <=number; i++) {
            // For any i = 1 to N, iterate over the entire array & check if it exists
            for (int j = 0; j < number-1; j++) {
                if (array[j] == i) {
                    flag = 1;
                    break;
                }
            }

            // If the number is not found after iterating over the whole array
            if (flag == 0) {
                return i;
            }
        }
        */

        // TC - O(n*n). SC - O(1)

        // Better Solution

        // Hashing
        /*
        int[] hash = new int[number+1];
        for (int i = 0; i < number; i++) {
            hash[array[i]]++;
        }
        for (int j = 1; j <= number; j++) {
            // Frequency of jth element/number is 0 in hash array, so return j
            if (hash[j] == 0) {
                return j;
            }
        }
        // TC - O(2n). SC - O(n), as we're using a hash array
        */

        // Optimal Solution - 1

        // Sum of first n natural numbers - n(n+1)/2. Now, sum up the elements in the given
        // array. n(n+1)/2 - [Sum of given array elements] = Missing element

        // Optimal Solution - 2 ??

        // XOR - a^a = 0. 0^a = a ?? NO CLUE WHATSOEVER OF THIS APPROACH, COME BACK AGAIN

        int xor1 = 0;
        int xor2 = 0;
        for (int i = 0; i < number - 1; i++) {
            xor2 = xor2 ^ array[i];
            xor1 = xor1 ^ (i + 1);
        }
        xor1 = xor1 ^ number;
        return xor1 ^ xor2;


    }

    // Important
    public static int maximum_consecutive_ones(int[] array) {
        // Eg: [1,1,0,1,1,1,0,1,1] - Max 3 times consecutively 1 occurs

        int counter = 0;
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                counter++;
            }
            if (array[i] == 0) {
                counter = 0;
            }
            if (counter > max) {
                max++;
            }
        }
        return max;

        // TC - O(n)

    }

    public static int find_number_appearing_once_and_other_numbers_twice(int[] array, int n) {
        // Eg: [1,1,2,3,3,4,4] - 2 is the only number occurring once

        // Brute-Force solution
        /*
        for (int i = 0; i < n; i++) {
            int num = array[i];
            int counter = 0;
            for (int j = 0; j < n; j++) {
                if (array[j] == num) {
                    counter++;
                }
            }
            if (counter == 1) {
                return num;
            }
        }
        // TC - O(n^2)
        return -1;
         */


        // Better Solution
        // Hashing

        // Find the max element of the array - using Math.max() function iterating over an array
        // O(n)
        /*
        int max = array[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, array[i]);
        }

        // Update the hash array with relevant frequencies
        // O(n)
        int[] hash = new int[max + 1];
        for (int i = 0; i < n; i++) {
            hash[array[i]]++;
        }

        // Find out the element which occurs only once
        // O(n)
        for (int i = 0; i < n; i++) {
            if (hash[array[i]] == 1) {
                return array[i];
            }
        }
        // Overall TC - O(3n).
        // Issue: But hashing using an array becomes a problem when the max element might be
        // of the order of 10^9 or more
        return -1;
         */

        // Hashing using a Map
        /*

        // TC - O(n * log(M)) - Ordered Map; n - size of array; m - size of the map
        // TC for this step - O(n * log(n/2 + 1))
        HashMap<Integer, Integer> numberVsFrequencyMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // How to update HashMap with Key -> Number & Value -> Incrementing if number is found
            numberVsFrequencyMap.put(array[i], numberVsFrequencyMap.getOrDefault(array[i], 0) + 1);
        }

        // How to iterate over a Map
        // TC for this step - O(n/2 + 1) - except 1 number, most of the elements occur twice
        for (Map.Entry<Integer, Integer> entry : numberVsFrequencyMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        // Else, return an arbitrary value
        return -1;
         */

        // Total TC - O(n * log(n/2 + 1)) + O(n/2 + 1)

        // Optimal Solution
        // XOR Approach ?? a^a = 0. 0^a = a
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor = xor ^ array[i];
        }
        return xor;

        // TC O(n). SC - O(1)

    }

    private static void swap(int[] array, int starting_index, int ending_index) {
        int temp = array[starting_index];
        array[starting_index] = array[ending_index];
        array[ending_index] = temp;
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            // Swap elements at start and end indices
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            // Move towards the center
            start++;
            end--;
        }
    }


}


