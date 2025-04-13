package Binary_Search;

import java.util.Scanner;

public class BS_on_Answers {

    // BS on Answers - Applicable only when looking for Min/Max & when we know that
    // some portion is NOT possible, other portion is possible.

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
        System.out.print("Enter the value of threshold: ");
        int threshold = sc.nextInt();

        long smallest_divisor = find_smallest_divisor_given_a_threshold(array, threshold);
        System.out.println("The smallest divisor of the array given a threshold is: "
                + smallest_divisor);

    }

    public static long find_sqrt_of_number(int number) {
        // Given a positive integer n, find and return its square root. If NOT a
        // perfect square, return the floor value of 'sqrt(n)'

        // Approach: low = 1, high = n. mid = (low+high)/2. If mid * mid > n, reject
        // RIGHT half & continue BS. Else, reject LEFT half & continue BS

        long low = 1, high = number;
        long sqrt = Integer.MAX_VALUE;

        while (low <= high) {
            long mid = (low + high) / 2;

            // Check condition
            if (mid * mid <= number) {
                sqrt = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // TC - O(log base 2 (N))
        return sqrt;

    }

    public static long find_nth_root_of_number(int number, int n) {
        // find the Nth root of number - number X when raised to the power N equals
        // number. If the nth root is not an integer, return -1

        long low = 1, high = number;
        long nth_root = -1;
        while (low <= high) {
            long mid = (low + high) / 2;

            if (Math.pow((double) mid, (double) n) == number) {
                nth_root = mid;
                return nth_root;
            }
            // Search to the RIGHT
            else if (Math.pow((double) mid, (double) n) < number) {
                low = mid + 1;
            }
            // Search to the LEFT
            else {
                high = mid - 1;
            }
        }
        // TC - O(log 2 N) * [O(1) - for Math.pow() Java in-built function]
        // Think of Overflow Edge Cases - What if base/mid is ~ 10^9, exponential
        // power is ~ 10. In that case, such a large value CANNOT be stored by long/int
        // variables. So, just check if the overall result is > OR < number & then
        // make slight adjustments in the BS code
        return nth_root;
    }

    // Easy
    public static long find_smallest_divisor_given_a_threshold(int[] array, int threshold) {
        // You are given an array of integers 'arr' and an integer i.e. a threshold
        // value 'limit'. Find the smallest positive integer divisor, such that upon
        // dividing all the elements of the given array by it, the sum of the
        // division's result(take ceil) is less than or equal to the given threshold value.

        // Eg: [1,2,5,9]. threshold = 7. (1/9) + (2/9) + (5/9) + (9/9)  => 4(min) <= 7

        // Step - I: Find out the Max element using LS
        int n = array.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(array[i], max);
        }

        // Constraint: What if there's no divisor. The min sum <= threshold possible
        // is 1+1+1+1+.... up to array.length = array.length. If this sum(array.length)
        // exceeds the threshold value, this means there's NO such divisor
        if (array.length > threshold) {
            return -1;
        }

        //Step - II: Apply BS
        int low = 0, high = max;
        int smallest_divisor = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            // search to the LEFT to find next least divisor
            // If mid becomes 0, ignore it as div by 0 will throw Arithmetic Exception
            if (sumByD(array, mid) <= threshold && mid != 0) {
                smallest_divisor = Math.min(smallest_divisor, mid);
                high = mid - 1;
            }
            // search to the RIGHT
            else {
                low = mid + 1;
            }
        }
        // TC - O(N * log(Max)). N -> length of array, Max -> Max element in the array
        return smallest_divisor;
    }

    private static int sumByD(int[] array, int divisor) {
        int sum = 0;
        if (divisor != 0) {
            for (int i = 0; i < array.length; i++) {
                sum += Math.ceilDiv(array[i], divisor);
            }
        }
        return sum;
    }

    // Easy
    public static long find_Kth_missing_positive_number(int[] array, int k) {
        // Given an array arr of positive integers sorted in a strictly increasing
        // order, and an integer k. Return the kth positive integer that is missing
        // from this array.

        int n = array.length;

        // Step - I: Find the max element in the given array using LS
        int max_element = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            max_element = Math.max(max_element, array[i]);
        }

        // Eg: [2,3,4,7,11]. K = 5th element missing ?
        // if array[i] < K, k++ => 2<5 -> K = 6, 3<6 -> K = 7, 4<7 -> K = 8,
        // 7<8 -> K = 9, 11 is NOT < 9 -> So, K = 9 will be the 5th element missing

        // Approach: For every element less than k, it'll always come before k, for
        // every element > K, we increment K by 1 as that particular element comes in b/w
        // Also, the ans(9) is b/w 7 & 11 indexes

        // Now [2,3,4,7,11] - Ideally the array with no numbers missing should have been
        // [1,2,3,4,5]: So, instead of 1, we have 2 => so 2 - 1 => 1 number is missing
        // 3 instead of 2 => 1 number missing
        // 4 instead of 3 => 1 number missing
        // 7 instead of 4 => 3 numbers missing
        // 11 instead of 5 => 6 numbers missing

        // Therefore, if K = 5, the 5th number missing would be b/w 7 & 11.
        // Apply BS from here

        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            // Missing number => Eg: 7(at index 3) => 7 - (mid + 1)
            int missing = array[mid] - (mid + 1);

            if (missing < k) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        // Eg: [4,7,9]
        // kth missing element formula = array[high] + more, (why array[high] ?)
        // more = k - missing
        // kth missing element = array[high] + (k - (array[high] - high - 1))
        // kth missing element = high + k + 1 (since low = high + 1)
        // kth missing element = low + k

        // TC - O(N). SC - O(1)
        return high + k + 1;
    }

    // Hard - No clue of this ques, need to go through it AGAIN THOROUGHLY
    public static double median_of_2_sorted_arrays(int[] array1, int[] array2) {
        // Given two sorted arrays arr1 and arr2 of size m and n respectively, return
        // the median of the two sorted arrays. The median is defined as the middle
        // value of a sorted list of numbers.

        // Eg: arr1 - [1,3,4,7,10,12]. arr2 - [2,3,6,15]
        // After sorting all 10 elements: 1 2 3 3 4 | 6 7 10 12 15

        int n1 = array1.length, n2 = array2.length;
        //if n1 is bigger swap the arrays:
        if (n1 > n2) return median_of_2_sorted_arrays(array2, array1);

        int n = n1 + n2; //total length
        int left = (n1 + n2 + 1) / 2; //length of left half
        //apply binary search:
        int low = 0, high = n1;
        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = left - mid1;
            //calculate l1, l2, r1 and r2;
            int l1 = (mid1 > 0) ? array1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? array2[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? array1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? array2[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 1) return Math.max(l1, l2);
                else return ((double) (Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
            } else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        // TC - O(log (min(n1, n2)))
        return 0; //dummy statement


    }

    // Medium - extension of previous problem - go thoroughly through it AGAIN
    public static double kth_element_of_2_sorted_arrays(int[] array1, int[] array2, int k) {
        int m = array1.length; int n = array2.length;
        if (m > n) return kth_element_of_2_sorted_arrays(array2, array1, k);

        int left = k; // length of left half

        // apply binary search:
        int low = Math.max(0, k - n), high = Math.min(k, m);
        while (low <= high) {
            int mid1 = (low + high) >> 1;
            int mid2 = left - mid1;
            // calculate l1, l2, r1, and r2
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;
            if (mid1 < m) r1 = array1[mid1];
            if (mid2 < n) r2 = array2[mid2];
            if (mid1 - 1 >= 0) l1 = array1[mid1 - 1];
            if (mid2 - 1 >= 0) l2 = array2[mid2 - 1];

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            }

            // eliminate the halves:
            else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        // TC - O(log(min(n1, n2)))
        return 0; // dummy statement
    }

}
