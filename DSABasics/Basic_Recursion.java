package DSABasics;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.swap;

public class Basic_Recursion {

    public static void main(String[] args) {

        // Reverse an array
        /* Integer[] intArray = {1,3,2,5,4,6,7,9};
        List<Integer> intArrayList = Arrays.asList(intArray);
        int length = intArrayList.size();
        List<Integer> reverseList = reverse_an_array(0, length - 1, intArrayList);
        reverseList.forEach(System.out::print); */

        // Check if string is palindrome or not
       /*
        String string = scanner.nextLine();

        // Convert a string into a List of Characters
        List<Character> characterList = new ArrayList<>();
        characterList = string.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> reverseStringList = check_if_string_is_palindrome(0, characterList.size(), characterList);

        // Character List back to String
        String reverseString = reverseStringList.stream().map(String::valueOf).collect(Collectors.joining());

        if (string.equals(reverseString)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("NOT a palindrome");
        }
        */

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the nth number of fibonacci series: ");
        int n = scanner.nextInt();
        System.out.println(fibonacci_series(n));

    }

    // Recursion - When a function calls itself until a specified condition is met
    public static void print_1_using_recursion(int count) {
        // This will throw - StackOverflow Error
        // The function call in main() method is not yet completed, so it will be waiting
        // in memory. Each subsequent function call will be waiting in memory as they're
        // not yet completed. So, the Stack space has a specific memory & it'd keep on
        // compiling until its full.
        // Stack Overflow - Numerous function calls waiting in recursion

        // Base Condition - Condition used to stop infinite recursion
        if (count == 4) {
            return;
        }
        System.out.println(count);
        count++;
        print_1_using_recursion(count);

        // Recursion Tree
    }

    // Important
    public static void print_name_N_times_using_recursion(int i, int n) {
        if (i > n) {
            return;
        }
        System.out.println("Akash");
        // TC - O(n). Space Complexity - Stack Space here; O(n)
        print_name_N_times_using_recursion(i + 1, n);
    }

    public static void print_1_to_N_using_recursion(int i, int n) {
        if (i > n) {
            return;
        }
        System.out.println(i);
        // TC - O(n). Space Complexity - Stack Space here; O(n)
        print_1_to_N_using_recursion(i + 1, n);
    }

    public static void print_N_to_1_using_recursion(int i, int n) {
        if (i < 1) {
            return;
        }
        System.out.println(i);
        // TC - O(n). Space Complexity - Stack Space here; O(n)
        print_N_to_1_using_recursion(i - 1, n);
    }

    // Important
    public static void print_1_to_N_backtracking(int i, int n) {
        if (i < 1) {
            return;
        }
        // Recursion Tree Importance - backtracking from innermost branch of tree
        // to print numbers in asc order. f(3,3) -> f(2,3) -> f(1,3) -> f(0,3) -> returns empty.
        // Now, f(0,3) call is completed in stack, next line will print i = 1; Now f(1,3)
        // is completed, next i = 2 is printed. Now f(2,3) is completed, so i = 3 is printed, ...
        // Backtracking
        print_1_to_N_backtracking(i - 1, n);
        System.out.println(i);
    }

    public static void print_N_to_1_backtracking(int i, int n) {
        if (i > n) {
            return;
        }
        // Recursion Tree Importance - backtracking from innermost branch of tree
        // to print numbers in asc order. f(1,3) -> f(2,3) -> f(3,3) -> f(4,3) -> returns empty.
        // Now, f(4,3) call is completed in stack, next line will print i = 3; Now f(3,3)
        // is completed, next i = 2 is printed. Now f(2,3) is completed, so i = 1 is printed, ...
        // Backtracking
        print_N_to_1_backtracking(i + 1, n);
        System.out.println(i);

    }

    // Important - Parameterised Recursion
    public static void sum_of_first_N_numbers_parameterised_recursion(int i, int sum) {
        if (i < 1) {
            System.out.println(sum);
            return;
        }
        // Important Step
        sum_of_first_N_numbers_parameterised_recursion(i - 1, sum + i);
    }

    // Important - Functional Recursion
    public static int sum_of_first_N_numbers_functional_recursion(int i) {
        // f(n) -> Sum of first N numbers. f(3) = 3 + f(2), f(2) = 2 + f(1), f(1) = 1 + f(0).
        // f(0) will return 0, so f(1) -> 1, f(2) -> 3, f(3) -> 6, ...
        if (i < 1) {
            return 0;
        }
        // Important Step
        return i + sum_of_first_N_numbers_functional_recursion(i - 1);
    }

    public static int factorial_of_N_numbers_functional_recursion(int i) {
        // f(n) -> Product of first N numbers. f(3) = 3 * f(2), f(2) = 2 * f(1), f(1) = 1 * f(0).
        // f(0) will return 1, so f(1) -> 1, f(2) -> 3, f(3) -> 6, ...
        if (i < 1) {
            return 1;
        }
        // Important Step. TC - O(n). SC - O(n)
        return i * factorial_of_N_numbers_functional_recursion(i - 1);
    }

    // Important
    public static List<Integer> reverse_an_array(int leftIndex, int rightIndex,
                                                 List<Integer> array) {
        // pointer at leftIndex, another pointer at rightIndex. Swap the values of both
        // pointers. Keep on ding this until the leftIndex crosses the rightIndex
        if (leftIndex >= rightIndex) {
            return array;
        }
        // Collections - swap(array, leftIndex, rightIndex) function
        swap(array, leftIndex, rightIndex);
        return reverse_an_array(leftIndex + 1, rightIndex - 1, array);

        // Can we do this with only one index variable, instead of 2(i.e. - l,r)??
        // Yes, we can. L + R = (n - 1) in an array
        // So, if we take i as leftIndex; rightIndex = (n - 1 - i)
        // Do this until i >= (n/2) [i.e. reaching midpoint of array]
    }

    public static List<Character> check_if_string_is_palindrome(int leftIndex, int size,
                                                                List<Character> characterList) {

        if (leftIndex >= size / 2) {
            return characterList;
        }
        swap(characterList, leftIndex, (size - 1 - leftIndex));
        return check_if_string_is_palindrome(leftIndex + 1, size, characterList);

    }

    // Important - Multiple Recursion Calls
    public static int fibonacci_series(int n) {
        // Fibonacci Series - 0,1,1,2,3,5,8,13,21,34,55,....
        if (n <= 1) {
            return n;
        }
        // One recursion ends, then only subsequent recursions would start execution
        // TC - O(2^n) - Exponential
        return fibonacci_series(n-1) + fibonacci_series(n-2);

    }


}
