package Sliding_Window_Two_Pointer_Combined_Problems;

import java.util.*;

public class Medium_Problems {

    public static void main(String[] args) {
        int[] array = new int[]{1, 0, 1, 0, 1};
//        String s = "ABAB";
        int count = binary_subarrays_with_sum(array, 2);
        System.out.println("number_of_substrings_containing_all_3_characters: " + count);
    }

    // Constant Window
    public static int find_max_sum_of_k_consecutive_nos_in_array(int[] array, int k) {
        // Constant Window - Two Pointers Approach.

        // We'll take 2 pointers - l(left),r(right). The window will be from l -> r,
        // compute the sum. Now, slide the window 1 step forward - remove the 1st
        // element(lth element) & add the next element in array. So, new pointers - l++,
        // r++. Before adding, make sure to remove the arr[l] element from the previous
        // sum. Now find the max sum by Math.max(sum, maxSum)

        // Initialize Two Pointers - l,r
        int l = 0;
        int r = k - 1;
        int sum = 0;
        // Let maxSum be initialized to 0
        int maxSum = 0;

        // This loop will fetch the sum of the 1st window
        for (int i = l; i <= r; i++) {
            sum += array[i];
        }

        // Run the loop until the end pointer(r) reaches end of array
        while (r < array.length - 1) {
            // Since we get updated sum of next window at last step of loop, find the
            // maxSum at 1st step of loop here
            maxSum = Math.max(sum, maxSum);
            // Now, slide the window 1 step forward - remove arr[l] from sum, move l,r
            // pointers 1 step forward
            sum -= array[l];
            l++;
            // The moment r reaches last element, end the while loop
            r++;
            sum += array[r];

        }
        return maxSum;
    }

    // Only Non-Negative Numbers
    public static int longest_subarray_with_sum_less_than_k(int[] array, int k) {
        // Brute Force - Generate all the subarrays

        /*
        // Let maxLength be initialized to 0
        int maxLength = 0;

        // This loop tracks all the different 1st elements of all the subarrays possible
        for (int i = 0; i < array.length; i++) {
            // Initialize sum for each particular 1st element subarrays as 0
            int sum = 0;
            // This loop tracks all the subarrays possible with 1 particular 1st element
            for (int j = i; j < array.length; j++) {
                sum += array[j];

                // Now, write the specific condition the ques asks
                if (sum <= k) {
                    maxLength = Math.max(maxLength, j - 1 + 1);
                }
                // Optimization - If sum > k, just break off
                else if (sum > k) {
                    break;
                }
            }
        }
        // TC - O(N^2)
        return maxLength;
        */

        // Better Solution - Sliding Window & Two Pointer - Only Non-Negative integers

        // Take 2 pointers - l, r(left, right).
        int l = 0;
        int r = 0;
        // Start with window size of 1.
        int sum = array[0];
        int maxLength = 0;

        // Edge Case
        if (array == null || array.length == 0) return 0;

        // Run the loop until the end pointer(r) reaches end of array
        while (r < array.length) {
            // We want max subarray with sum <= k. So, till the time sum <= k, keep on
            // expanding the window size(increment r pointer)
            if (sum <= k) {
                // The moment r reaches last element, end the while loop
                maxLength = Math.max(maxLength, r - l + 1);
                r++;
                if (r < array.length) {
                    sum += array[r];
                }
            }
            // When sum > k, we need to shrink the window(decrement l pointer)
            else {
                sum -= array[l];
                // When we're decrementing the window, the l pointer will move forward
                l++;
            }
        }
        // TC - O(N) + O(N). SC - O(1)
        return maxLength;

        // Optimal Solution - Similar approach as above. Modification - Whenever sum > k,
        // we're shrinking the window size by incrementing l. Now, if a certain maxLength
        // satisfies sum < k. Then, in further we're only looking for POSSIBLE LARGER
        // maxLength value. So, keep the window size = maxLength throughout the iterations.
        // Just slide the window next. If there's a better maxLength, update it, if not,
        // maxLength becomes the answer. TC - O(N). SC - O(1). We can only follow this if
        // ques asks for length of longest subarray. If ques asks to return the entire
        // sub array, then 'Better Solution' will be the approach.
    }

    // Medium - 2 Sliding Windows - leftSum, rightSum
    public static int max_points_you_can_obtain_from_k_cards(int[] cardPoints, int k) {
        // 2 Sliding Windows - leftSum, rightSum

        int leftSum = 0;
        int rightSum = 0;
        int rightPointer = cardPoints.length - 1;

        // Edge Case
        if (cardPoints.length == 0) {
            return 0;
        }

        // Find out the initial sum of left Window
        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }
        int maxSum = leftSum;

        // Now, we need to keep on reducing the left window(leftSum) by the last element &
        // simultaneously keep on increasing the right window(rightSum) from last.
        for (int i = k - 1; i >= 0; i--) {
            // Reduce leftSum by the last element
            leftSum -= cardPoints[i];
            rightSum += cardPoints[rightPointer];
            rightPointer--;
            maxSum = Math.max(maxSum, leftSum + rightSum);
        }
        // TC - O(K) + O(K). SC - O(1)
        return maxSum;
    }

    // Medium - HASHING array of 256 size, Two Pointers
    public static int longest_substring_without_repeating_characters(String s) {
        // Brute-Force Solution - Generate all the substrings
        /*
        int maxLen = 0;
        // HASHING - To keep track of repeating characters
        int[] hashing = new int[256];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                // Now, we're iterating over the characters of a particular substring
                if (hashing[(int) s.charAt(j)] == 1) {
                    break;
                }
                maxLen = Math.max(maxLen, j - i + 1);
                hashing[(int) s.charAt(j)] = 1;
            }
        }
        // TC - O(N^2)
        return maxLen;
        */

        // Optimal Solution - Sliding Window & Two Pointer
        int left_pointer = 0;
        int right_pointer = 0;
        int maxLen = 0;

        int[] hashing = new int[256];
        // We want to initialize the 'hashing' array with all values as -1 at start
        Arrays.fill(hashing, -1);
        // int[] hashing = new int[256]{-1} -> This is NOT valid in Java

        // IMPORTANT - HASHING array has 0 - 255 elements(to represent 256 different
        // characters) & we'll store the index (of the char in String s) as value in
        // the hashing array.

        while (right_pointer < s.length()) {
            // If the right_pointer is at a character ALREADY present in the 'hashing'
            // array, we
            if (hashing[(int) s.charAt(right_pointer)] != -1) {
                // IMPORTANT - We need to CHECK that the duplicate character
                // encountered while traversing is PART of the substring we're
                // considering(left_pointer -> right_pointer)
                if (hashing[(int) s.charAt(right_pointer)] >= left_pointer) {
                    // Move the left_pointer 1 step ahead of the first occurrence of the
                    // duplicate character
                    left_pointer = hashing[(int) s.charAt(right_pointer)] + 1;
                }
            }

            maxLen = Math.max(maxLen, right_pointer - left_pointer + 1);
            // If the character (ASCII) value in 'hashing' array is NOT updated, we will
            // update the 'hashing' array with index of character of String s
            hashing[(int) s.charAt(right_pointer)] = right_pointer;
            // Move the right_pointer forward
            right_pointer++;
        }
        // TC - O(N). SC - O(256)
        return maxLen;
    }

    // Medium - Sliding Window & Two Pointers: Longest sub array with at most k zeroes.
    public static int maximum_consecutive_ones_part_III(int[] nums, int k) {
        // Given a binary array 'nums' and an integer k, return the maximum number of
        // consecutive 1's in the array if you can flip at most k 0's.


        // Problem same as - Longest sub array with at most k zeroes.
        // Brute-Force Solution - Generate all the sub arrays
        /*
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            // Count the number of zeroes in a subarray
            int zeroes_in_subarray = 0;
            for (int j = i; j < nums.length; j++) {
               if (nums[j] == 0) {
                   zeroes_in_subarray++;
               }
               if (zeroes_in_subarray <= k) {
                   maxLen = Math.max(maxLen, j - i + 1);
               }
               else {
                    break;
               }
            }
        }
        // TC - O(N^2)
        return maxLen;
        */

        // Better Solution - Sliding Window & Two Pointer Approach
        /*
        int left_pointer = 0;
        int right_pointer = 0;
        int zeroes = 0;
        int maxLength = 0;

        // Edge Case
        if (nums.length == 0) {
            return 0;
        }

        while (right_pointer < nums.length) {
            // If the zeroes are in range(at most k) in the window, keep EXPANDING
            // the window

            // Expand the Window until we encounter at most k zeroes
            if (nums[right_pointer] == 0) {
                zeroes++;
            }
            // The moment zeroes exceed k, SHRINK the window - How?
            // Keep moving the left pointer(shrinking) until zeroes <= k.
            while (zeroes > k) {
                if (nums[left_pointer] == 0) {
                    zeroes--;
                    left_pointer++;
                }
            }
            if (zeroes <= k) {
                maxLength = Math.max(maxLength, right_pointer - left_pointer + 1);
            }
            right_pointer++;
        }
        // TC - O(N) + O(N) = O(2N)
        return maxLength;
        */

        // Optimal Solution - Sliding Window(smarter way).
        // Similar approach as above. MODIFICATION - Whenever zeroes > k,
        // DON'T move the left_pointer all the way until zeroes <= k. Instead, once
        // zeroes > k, just move the left_pointer by 1. This will make sure that the window
        // size will NOT increase once zeroes become > k, instead we stay at the maxLength
        // POSSIBLE. The window has shifted, BUT the window size remains at the maxLength
        // POSSIBLE.

        int left_pointer = 0;
        int right_pointer = 0;
        int zeroes = 0;
        int maxLength = 0;

        while (right_pointer < nums.length) {
            if (nums[right_pointer] == 0) {
                zeroes++;
            }
            // Once, zeroes > k
            if (zeroes > k) {
                if (nums[left_pointer] == 0) {
                    zeroes--;
                }
                // Move the left_pointer by JUST 1 step
                left_pointer++;
            }
            if (zeroes <= k) {
                maxLength = Math.max(maxLength, right_pointer - left_pointer + 1);
            }
            right_pointer++;
        }
        // TC - O(N). SC - O(1)
        return maxLength;
    }

    // Medium - Set/Map to track freq of all unique type elements
    public static int fruits_into_baskets(int[] fruits) {
        // LC Problem - 904.
        // You are visiting a farm that has a single row of fruit trees arranged from
        // left to right. The trees are represented by an integer array fruits where
        // fruits[i] is the type of fruit the ith tree produces. You want to collect
        // as much fruit as possible. However, the owner has some strict rules that
        // you must follow:
        // You only have two baskets, and each basket can only hold a single type of
        // fruit. There is no limit on the amount of fruit each basket can hold.
        // Starting from any tree of your choice, you must pick exactly one fruit
        // from every tree (including the start tree) while moving to the right. The
        // picked fruits must fit in one of your baskets.
        // Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
        // Given the integer array fruits, return the maximum number of fruits you can pick.

        // Eg: [3,3,3,1,2,1,1,2,3,3,4] => Longest subarray with at most 2 types of
        // numbers.

        // Brute-Force Solution - Generate all the subarrays. Take a Set(unique
        // elements are stored here) DS.
        /*
        int maxFruits = 0;
        for (int i = 0; i < fruits.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < fruits.length; j++) {
                while (set.size() <= 2) {
                    // TC - O(log(N); N - size of the set). Here: O(log 2)
                    set.add(fruits[j]);
                }
                if (set.size() <= 2) {
                    maxFruits = Math.max(maxFruits, j - i + 1);
                }
                else {
                    //  Break out of the loop
                    break;
                }
            }
        }
        // TC - O(N^2)
        return maxFruits;
        */

        // Better Solution - Sliding Window & Two Pointer
        /*
        int left_pointer = 0;
        int right_pointer = 0;
        int maxFruits = 0;
        int k = 2;
        Map<Integer, Integer> map = new HashMap<>();
        while (right_pointer < fruits.length) {
            map.put(fruits[right_pointer], map.getOrDefault(fruits[right_pointer], 0) + 1);

            if (map.keySet().size() > k) {

                while (map.size() > k) {
                    map.put(fruits[left_pointer], map.getOrDefault
                            (fruits[left_pointer], 0) - 1);
                    if (map.get(fruits[left_pointer]) == 0) {
                        // IMPORTANT - Remove a particular key from Map
                        map.remove(fruits[left_pointer]);
                    }
                    left_pointer++;
                }
            }

            if (map.keySet().size() <= k) {
                maxFruits = Math.max(maxFruits, right_pointer - left_pointer + 1);
            }
            // right_pointer always moves till the end
            right_pointer++;
        }
        // TC - O(N) + O(N) = O(2N)
        return maxFruits;
        */

        // Optimal Solution - Similar approach as above. Modification - Whenever sum > k,
        // we're shrinking the window size by incrementing l. Now, if a certain maxLength
        // satisfies sum < k. Then, in further we're only looking for POSSIBLE LARGER
        // maxLength value. So, keep the window size = maxLength throughout the iterations.
        // Just slide the window next. If there's a better maxLength, update it, if not,
        // maxLength becomes the answer. TC - O(N). SC - O(1). We can only follow this if
        // ques asks for length of longest subarray. If ques asks to return the entire
        // sub array, then 'Better Solution' will be the approach.

        int left_pointer = 0;
        int right_pointer = 0;
        int maxFruits = 0;
        int k = 2;
        Map<Integer, Integer> map = new HashMap<>();

        while (right_pointer < fruits.length) {
            map.put(fruits[right_pointer], map.getOrDefault(fruits[right_pointer], 0) + 1);
            if (map.keySet().size() > k) {
                map.put(fruits[left_pointer], map.getOrDefault
                        (fruits[left_pointer], 0) - 1);
                if (map.get(fruits[left_pointer]) == 0) {
                    // IMPORTANT - Remove a particular key from Map
                    map.remove(fruits[left_pointer]);
                }
                left_pointer++;
            }

            if (map.keySet().size() <= k) {
                maxFruits = Math.max(maxFruits, right_pointer - left_pointer + 1);
            }
            // right_pointer always moves till the end
            right_pointer++;
        }
        // TC - O(N)
        return maxFruits;
    }

    // Medium - Set/Map to track freq of all unique characters. Same as above ques.
    public static int longest_substring_with_at_most_k_distinct_characters
    (String s, int k) {
        // Brute-Force Solution - Generate all the subarrays. Take a Set(unique
        // elements are stored here) DS.

        /*
        char[] characters = s.toCharArray();
        int maxLength = 0;
        for (int i = 0; i < characters.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < characters.length; j++) {
                while (set.size() <= k) {
                    // TC - O(log(N); N - size of the set). Here: O(log 2)
                    set.add(characters[j]);
                }
                if (set.size() <= k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
                else {
                    //  Break out of the loop
                    break;
                }
            }
        }
        // TC - O(N^2) * O(log 256 - depending on the characters in String)
        return maxLength;
        */

        // Better Solution - Two Pointer & Sliding Window
        /*
        int left_pointer = 0;
        int right_pointer = 0;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] characters = s.toCharArray();

        while (right_pointer < characters.length) {
            map.put(characters[right_pointer], map.getOrDefault(characters[right_pointer], 0) + 1);
            if (map.keySet().size() > k) {
                map.put(characters[left_pointer], map.getOrDefault
                        (characters[left_pointer], 0) - 1);
                if (map.get(characters[left_pointer]) == 0) {
                    // IMPORTANT - Remove a particular key from Map
                    map.remove(characters[left_pointer]);
                }
                left_pointer++;
            }

            if (map.keySet().size() <= k) {
                maxLength = Math.max(maxLength, right_pointer - left_pointer + 1);
            }
            // right_pointer always moves till the end
            right_pointer++;
        }
        // TC - O(N) + O(N)
        return maxLength;
        }
        */

        // Optimal Solution - Similar approach as above. Modification - Whenever sum > k,
        // we're shrinking the window size by incrementing l. Now, if a certain maxLength
        // satisfies sum < k. Then, in further we're only looking for POSSIBLE LARGER
        // maxLength value. So, keep the window size = maxLength throughout the iterations.
        // Just slide the window next. If there's a better maxLength, update it, if not,
        // maxLength becomes the answer. TC - O(N). SC - O(1). We can only follow this if
        // ques asks for length of longest subarray. If ques asks to return the entire
        // sub array, then 'Better Solution' will be the approach.

        int left_pointer = 0;
        int right_pointer = 0;
        char[] characters = s.toCharArray();
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (right_pointer < characters.length) {
            map.put(characters[right_pointer], map.getOrDefault(characters[right_pointer], 0) + 1);
            if (map.keySet().size() > k) {
                map.put(characters[left_pointer], map.getOrDefault
                        (characters[left_pointer], 0) - 1);
                if (map.get(characters[left_pointer]) == 0) {
                    // IMPORTANT - Remove a particular key from Map
                    map.remove(characters[left_pointer]);
                }
                left_pointer++;
            }

            if (map.keySet().size() <= k) {
                maxLength = Math.max(maxLength, right_pointer - left_pointer + 1);
            }
            // right_pointer always moves till the end
            right_pointer++;
        }
        // TC - O(N)
        return maxLength;

    }

    // Medium - BACKTRACKING the generation of all the subarrays. We'll figure out
    // the MIN Window which satisfies the ques statement.
    public static int number_of_substrings_containing_all_3_characters(String s) {
        // Given a string s consisting only of characters a, b and c. Return the number
        // of substrings containing at least one occurrence of all these characters a,
        // b and c.

        // Brute-Force Solution - Generate all the subarrays
        /*
        char[] characters = s.toCharArray();
        int count = 0;
        for (int i = 0; i < characters.length; i++) {
            Map<Character, Integer> hashing = new HashMap<>();
            for (int j = i; j < characters.length; j++) {
                hashing.put(characters[j], hashing.getOrDefault
                        (characters[j], 0) + 1);
                if (hashing.size() == 3) {
                    count++;
                }
            }
        }
        // TC - O(N^2)
        return count;
        */

        // Better Solution - During traversal of all the substrings. The moment we
        // reach a substring which contains all 3 characters - a, b, c, we can
        // straightaway conclude that all subsequent substrings further in that range
        // will also be valid. Let's say at index 3 of the String, we encounter the 1st
        // subarray, so subarrays ending with index4 & 5(String of length 6) will ALSO be
        // valid. We'll simply add 2 to the count, instead of traversing them.
        // Eg: // s = "abcabc". At j = 2, we get the 1st valid substring. So, j = 2,3,4,5
        // all subsequent substrings will ALSO be valid. So, count += (6 - 2)

        /*
        char[] characters = s.toCharArray();
        int count = 0;
        for (int i = 0; i < characters.length; i++) {
            Map<Character, Integer> hashing = new HashMap<>();
            for (int j = i; j < characters.length; j++) {
                hashing.put(characters[j], hashing.getOrDefault
                        (characters[j], 0) + 1);
                if (hashing.size() == 3) {
                    count += (characters.length - j);
                    // Break out of the inner for loop, to start the next set of substrings
                    // count
                    break;
                }
            }
        }
        // TC - O(N^2)
        return count;
        */

        // Optimal Solution - With every character, there is a substring that ends.
        // Basically, we're BACKTRACKING the generation of all the subarrays. We'll
        // figure out the min Window which satisfies the ques statement.

        char[] characters = s.toCharArray();
        int count = 0;
        int[] lastSeen = new int[3];
        // Initialize the lastSeen array with -1 values
        Arrays.fill(lastSeen, -1);

        for (int i = 0; i < characters.length; i++) {
            // This line keeps track of the last seen index of the 3 characters - 'a',
            // 'b' & 'c' in the lastSeen array
            lastSeen[characters[i] - 'a'] = i;
            // If
            count += (1 + Math.min(lastSeen[0],
                    Math.min(lastSeen[1], lastSeen[2])));
        }
        return count;
    }

    // Medium
    public static int longest_repeating_character_replacement(String s, int k) {
        // You are given a string s and an integer k. You can choose any character of
        // the string and change it to any other uppercase English character. You can
        // perform this operation at most k times.

        // Return the length of the longest substring containing the same letter you
        // can get after performing the above operations.

        // Brute-Force Solution - Generate all the sub arrays
        // Eg: String s = 'AABABC'. length - maxFreq = number of conversions needed(k)

        /*
        char[] characters = s.toCharArray();
        int maxLength = 0;
        for (int i = 0; i < characters.length; i++) {
            // Since the string will contain only Uppercase characters, take a HASH
            // array of size 26.
            Integer[] hashing = new Integer[]{};
            // Initialize the maxFreq for every outer iteration
            int maxFreq = 0;
            for (int j = i; j < characters.length; j++) {
                hashing[s.charAt(j) - 'A']++;
                maxFreq = Math.max(maxFreq, hashing[s.charAt(j)]);
                // Number of changes = length of substring - maxFreq
                int number_of_changes = (j - i + 1) - maxFreq;
                // If number of changes are within k
                if (number_of_changes <= k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
                // Else, break out
                else {
                    break;
                }
            }
        }
        // TC - O(N^2). SC - 0(26)
        return maxLength;
        */

        // Better Solution - Two Pointer & Sliding Window
        char[] characters = s.toCharArray();
        int maxLength = 0;
        int maxFreq = 0;
        int[] hashing = new int[26];
        int left_pointer = 0;
        int right_pointer = 0;

        while (right_pointer < characters.length) {
            // Keep track of the frequency of each character using the 'hashing' array
            hashing[s.charAt(right_pointer) - 'A']++;
            // Update the maxFreq
            maxFreq = Math.max(maxFreq, hashing[s.charAt(right_pointer) - 'A']);
            // If it's NOT a valid one, trim down the window from left
            while ((right_pointer - left_pointer + 1) - maxFreq > k) {
                // Decrement the frequency from 'hashing' array as we're trimming the
                // window from left
                hashing[s.charAt(left_pointer) - 'A']--;
                // Now, find out the new maxFreq from the 'hashing' array bi iterating
                // over the entire array & finding the max frequency again
                maxFreq = 0;
                for (int i = 0; i < 26; i++) {
                    maxFreq = Math.max(maxFreq, hashing[i]);
                }
                // Move the left_pointer
                left_pointer++;
            }
            // Once while loop is finished, we're sure that
            // If the subarry is a valid one
            if ((right_pointer - left_pointer + 1) - maxFreq <= k) {
                maxLength = Math.max(maxLength, (right_pointer - left_pointer + 1));
            }
            // Move the right_pointer forward
            right_pointer++;
        }
        // TC - O(N) + O(N) * 26. SC - O(26)
        return maxLength;

        // Optimal Solution - Similar to previous questions of Sliding Window & Two
        // Pointer, don't try to decrement the window size if condition fails,
        // rather keep the window size to maxLength, & then try if you can further
        // increase maxLength
    }

    // Medium - Number of subarrays with sum ≤ goal) - (Number of subarrays with sum
    // < goal) = Required solution
    public static int binary_subarrays_with_sum(int[] nums, int goal) {
        // Brute-Force Solution

        // Better Solution - Two Pointer & Sliding Window
        // (Number of subarrays with sum ≤ goal) - (Number of subarrays with sum < goal)
        // = Required solution

        // SC - O(2N * 2). SC - O(1)
        return atMost(nums, goal) - atMost(nums, goal - 1);

    }

    private static int atMost(int[] nums, int goal) {
        int left_pointer = 0;
        int right_pointer = 0;
        int sum = 0;
        int number_of_subarrays_count = 0;

        // Edge Case
        if (goal < 0) {
            return 0;
        }

        while (right_pointer < nums.length) {
            sum += nums[right_pointer];

            while (sum > goal) {
                sum -= nums[left_pointer];
                left_pointer++;
            }

            // All subarrays ending at 'right_pointer' & starting from 'left_pointer'
            // are valid.
            number_of_subarrays_count += (right_pointer - left_pointer + 1);
            right_pointer++;
        }
        // TC - O(2N). SC - O(1)
        return number_of_subarrays_count;
    }

    public static int count_number_of_nice_subarrays(int[] nums, int k) {
        // Given an array of integers 'nums' and an integer k. A continuous subarray
        // is called nice if there are k odd numbers on it. Return the number of
        // nice sub-arrays.

        // (Number of subarrays with <= k odd numbers) - (Number of subarrays
        // with <= (k-1) odd numbers)

        return atMaxNiceNumbers(nums, k) - atMaxNiceNumbers(nums, (k-1));
    }

    private static int atMaxNiceNumbers(int[] nums, int k) {
        int left_pointer = 0;
        int right_pointer = 0;
        int number_of_odd_numbers = 0;
        int count_of_subarrays = 0;

        while (right_pointer < nums.length) {

            if (nums[right_pointer] % 2 == 1) {
                number_of_odd_numbers++;
            }
            // Shrink the window until we have at most k odd numbers
            while (number_of_odd_numbers > k) {
                if (nums[left_pointer] % 2 == 1) {
                    number_of_odd_numbers--;
                }
                left_pointer++;
            }
            // All subarrays between left and right are valid
            count_of_subarrays += right_pointer - left_pointer + 1;

            right_pointer++;
        }
        return count_of_subarrays;
    }


}
