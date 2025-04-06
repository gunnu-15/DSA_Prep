package Problems_on_Arrays;

import java.util.*;

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
        List<Integer> leaders = leaders_in_an_array(array);
        System.out.println("Sorted array by sign - ");
        for (Integer i : leaders) {
            System.out.print(i + " ");
        }
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

    // ??
    public static void kadanes_algorithm_max_sub_array_sum(int[] array) {
        // Eg: [-2,-3,4,-1,-2,1,5,-3] Max sub array sum = 7

        // Brute-Force Solution - Generate all sub arrays. 2-Pointer Approach: Keep i-pointer at
        // the start, keep moving j-pointer until the end. Once j reaches the end, move the
        // i pointer and now j starts from exactly the ith position pointer

        /*
        int longest_sub_array_length = 0;
        // Initially, keep the max as the lowest possible value
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                // Now, we need to find the sum of all the elements of every sub array
                int sum = 0;
                // Every sub array generation
                // [0th -> 0th], [0th -> 1st], [0th -> 2nd], [0th -> 3rd], ...etc
                for (int k = i; k <= j; k++) {
                        sum+=array[k];
                    }
                max = Math.max(sum, max);
                }
            }
        */
        // TC - O(n^3). SC - O(1)

        // Better Solution - Instead of 3 loops
        /*
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
            }
            max = Math.max(sum, max);
        }
        */

        // TC - O(n^2). SC - O(1)

        // Optimal Solution - Kadane's Algorithm

        int max = Integer.MIN_VALUE;
        int start = 0;
        int ansStart = -1;
        int ansEnd = -1;
        // If, at any point, the sum is < 0, there's no point in carrying that
        // element as it'd only reduce the sum. Restart sum from 0 again
        // Eg: [-2,-3,4,-1,-2,1,5,-3]
        int sum = 0;
        for (int i = 0; i < array.length; i++) {

            if (sum == 0) {
                start = i;
            }
            sum += array[i];
            // We were always starting with a sum = 0 when we're starting to
            // form a new subarray with sum > 0.
            // Track the ansStart index & ansEnd index
            if (sum > max) {
                max = sum;
                ansStart = start; // ??
                ansEnd = i; // ??
            }
            // If sum < 0, discard the sum calculated
            if (sum < 0) {
                sum = 0;
            }
        }

        //  In some cases, the question might say to consider the sum of the
        //  empty subarray while solving this problem. So, in these cases,
        //  before returning the answer we will compare the maximum subarray
        //  sum calculated with 0

        /* if (max < 0) {
            return 0;
        } */

        // TC - O(n). SC - O(1)


    }

    // ??
    public static void stock_buy_and_sell(int[] array) {
        // Eg: [7,1,5,3,6,4] - These represent the stock prices for n days
        // If you sell a stock on i th day, you buy on the min price
        // day (1st - (i-1)th day) - ???

        int min = array[0];
        int profit = 0;
        int cost = 0;
        for (int i = 1; i < array.length; i++) {
            // Cost = SP - Min Price
            cost = array[i] - min;
            profit = Math.max(profit, cost);
            min = Math.min(array[i], min);
        }
    }

    public static int[] rearrange_array_elements_by_sign_equal(int[] array) {
        // Eg: [3,1,-2,-5,2,-4]

        // Brute-Force Solution
        /*
        int[] positive = new int[array.length / 2];
        int[] negative = new int[array.length / 2];
        int positive_index = 0;
        int negative_index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                positive[positive_index] = array[i];
                positive_index++;
            } else {
                negative[negative_index] = array[i];
                negative_index++;
            }
        }
        // Now, all the positive elements are at even index - 0,2,4,6,...
        // and all the negative elements are at odd index - 1,3,5,7,...

        // TC - O(n/2)
        for (int i = 0; i < array.length / 2; i++) {
            // Even index - Positive elements
            array[2 * i] = positive[i];
            array[(2 * i) + 1] = negative[i];
        }
        */

        // Overall TC - O(3/2 * n). SC - O(n/2) + O(n/2) = O(n): We took 2 extra arrays
        // of size n/2 each

        // Optimal Solution
        int[] response_array = new int[array.length];
        int posIndex = 0;
        int negIndex = 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                response_array[negIndex] = array[i];
                negIndex += 2;
            } else {
                response_array[posIndex] = array[i];
                posIndex += 2;
            }
        }
        return response_array;
    }

    public static int[] rearrange_array_elements_by_sign_unequal(int[] array) {
        // Eg: [1,2,-4,-5,3,6]. If any of the positive & negative numbers
        // are left, add them at the end without altering the order
        // Here, pos != neg. So, 2 cases: n(pos) > n(neg) OR n(pos) < n(neg)

        // Brute-Force Solution

        // ArrayList maintains insertion order
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int[] response_array = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                positive.add(array[i]);
            } else {
                negative.add(array[i]);
            }
        }
        // Now, check whether positive list > OR  < negative list

        if (positive.size() > negative.size()) {
            for (int i = 0; i < negative.size(); i++) {
                response_array[2 * i] = positive.get(i);
                response_array[(2 * i) + 1] = negative.get(i);
            }
            int index = 2 * negative.size();
            for (int i = negative.size(); i < positive.size(); i++) {
                response_array[index] = positive.get(i);
                index++;
            }
        }
        else {
            for (int i = 0; i < positive.size(); i++) {
                response_array[2 * i] = positive.get(i);
                response_array[(2 * i) + 1] = negative.get(i);
            }
            int index = 2 * positive.size();
            for (int i = positive.size(); i < negative.size(); i++) {
                response_array[index] = negative.get(i);
                index++;
            }
        }

        // TC - O(n) + O(min[pos, neg]) + O(leftovers) - Worst Case - O(2n)
        // SC - O(n)
        return response_array;


    }

    public static List<Integer> next_permutation(List<Integer> array) {
        // Given an array Arr[] of integers, rearrange the numbers of the given array into
        // the lexicographically next greater permutation of numbers.
        // Eg: [3,1,2] - [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]

        // Brute-Force Solution
        // 1. Generate All possible Permutations in sorted order - Recursion
        // 2. Linear Search
        // 3. Next index permutations
        // TC - O(n! * n)

        // Optimal Solution
        // Eg: [2,1,5,4,3,0,0]
        // Next elements have longer prefix match - If we fix 2,1,5; then 4,3,0,0 if arranged
        // in any order would not yield a number  > 4300. So, NOT Possible.

        // Step 1: Find the break point:
        int break_point_index = -1; // break point
        for (int i = array.size() - 2; i >= 0; i--) {
            if (array.get(i) < array.get(i + 1)) {
                // index i is the break point
                break_point_index = i;
                break;
            }
        }

        // If break point does not exist - Edge Case
        if (break_point_index == -1) {
            // reverse the whole array:
            Collections.reverse(array);
            return array;
        }

        // Step 2: Find the next greater element and swap it with arr[index]:
        for (int i = array.size() - 1; i > break_point_index; i--) {
            if (array.get(i) > array.get(break_point_index)) {
                swap(array, i, break_point_index);
                // The moment you find the 1st element > array.get(i), swap them & break out
                break;
            }
        }

        // Step 3: reverse the right half
        List<Integer> sublist = array.subList(break_point_index + 1, array.size());
        Collections.reverse(sublist);

        // TC - O(3n). SC - O(1), not considering that array is being modified
        return array;
    }

    public static List<Integer> leaders_in_an_array(int[] array) {
        // Given an array, print all the elements which are leaders. A Leader is an element
        // that is greater than all the elements on its right side in the array.

        // Brute-Force Solution
        /*
        List<Integer> leader_list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            boolean leader = true;
            // Start iterating/comparing from the next right element of ith element
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] > array[i]) {
                    leader = false;
                    break;
                }
            }
            if (leader) {
                leader_list.add(array[i]);
            }
        }
        // TC - O(n^2). SC - O(1)
        return leader_list;
        */

        // Optimal Solution - If the ith element is > the max element out of all the elements
        // right of (i+1)th element, then ith element is a leader
        // BACKTRACKING
        List<Integer> leaders = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] == max) {
                leaders.add(max);
            }
        }
        // TC - O(n * log(N))
        leaders.sort(Comparator.reverseOrder());
        return leaders;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void swap(List<Integer> array, int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }



}

