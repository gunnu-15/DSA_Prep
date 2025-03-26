package DSABasics;

import java.util.Scanner;

public class Patterns {
    public static void main(String[] args) {
        System.out.print("Enter the number of rows you want to print * pattern: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        printPattern21(n);
    }

    public static void printPattern1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    // Important
    public static void printPattern2(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void printPattern3(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void printPattern4(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    // Important
    public static void printPattern5(int n) {
        for (int i = n; i > 0; --i) {
            for (int j = 0; j < i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void printPattern6(int n) {
        for (int i = n; i > 0; --i) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    // Important Pattern
    public static void printPattern7_my_method(int n) {

        System.out.println("Number of rows for which to print pattern: " + n);
        int no_of_stars_to_print_in_nth_row = (2 * n) - 1;
        System.out.println("Number of * to print in nth row: " + no_of_stars_to_print_in_nth_row);
        int spaces_in_first_row = (n - 1);

        do {
            // Print First set of spaces
            for (int i = 1; i <= spaces_in_first_row; i++) {
                System.out.print(" ");
            }
            // Print 2nd set of *'s
            for (int j = 1; j <= (no_of_stars_to_print_in_nth_row - (2 * spaces_in_first_row)); j++) {
                System.out.print("*");
            }
            // Print 3rd set of spaces
            for (int k = 1; k <= spaces_in_first_row; k++) {
                System.out.print(" ");
            }
            spaces_in_first_row -= 1;
            // Next line
            System.out.println();

        } while (spaces_in_first_row >= 0);
    }

    // Important pattern
    public static void printPattern7_Optimal_method(int n) {

        // Total rows - 5; i = 0; i < 5 => i = 0,1,2,3,4
        // Total columns - 9
        // Pattern - print Spaces, Starts, Spaces

        // [4 sp, 1 *, 4 sp]. [3 sp, 3 *, 3 sp]. [2 sp, 5 *, 2 sp]. [1 sp, 7 *, 1 sp]. [0 sp, 9 *, 0 sp].
        // Spaces in a row(assuming n = 5) = (n - i - 1)
        // * in a row = (2 * i) + 1

        for (int i = 0; i < n; i++) {
            // Step I - Print spaces
            for (int j = 1; j <= (n - i - 1); j++) {
                System.out.print(" ");
            }
            // Step II - Print *
            for (int k = 1; k <= ((2 * i) + 1); k++) {
                System.out.print("*");
            }
            // Step III - Print spaces again
            for (int l = 1; l <= (n - i - 1); l++) {
                System.out.print(" ");
            }
            // Next line
            System.out.println();
        }
    }

    public static void printPattern8(int n) {

        // Total rows - 5; i = 0; i < 5 => i = 0,1,2,3,4
        // Total columns - 9
        // Pattern - print Spaces, Starts, Spaces

        // [4 sp, 1 *, 4 sp]. [3 sp, 3 *, 3 sp]. [2 sp, 5 *, 2 sp]. [1 sp, 7 *, 1 sp]. [0 sp, 9 *, 0 sp].
        // Spaces in a row(assuming n = 5) = (n - i - 1)
        // * in a row = (2 * i) + 1

        for (int i = (n - 1); i >= 0; i--) {
            // Step I - Print spaces
            for (int j = 1; j <= (n - i - 1); j++) {
                System.out.print(" ");
            }
            // Step II - Print *
            for (int k = 1; k <= ((2 * i) + 1); k++) {
                System.out.print("*");
            }
            // Step III - Print spaces again
            for (int l = 1; l <= (n - i - 1); l++) {
                System.out.print(" ");
            }
            // Next line
            System.out.println();
        }
    }

    public static void printPattern9(int n) {

        // Upper Pyramid
        printPattern7_Optimal_method(n);

        // Lower Pyramid
        printPattern8(n);
    }

    public static void printPattern10(int n) {

        printPattern2(n);
        printPattern5(n - 1);

        // Alternate method

        for (int i = 1; i <= (2 * n - 1); i++) {
            int stars = i;
            if (i > n) {
                stars = ((2 * n) - i);
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }

            // Next line
            System.out.println();

        }

    }

    // Important
    public static void printPattern11(int n) {

        for (int i = 0; i < n; i++) {
            // Initialize a variable with 0 & assign it 1 if the row number is even
            int start = 0;
            // Even condition check
            if (i % 2 == 0) {
                start = 1;
            }

            for (int j = 0; j <= i; j++) {
                System.out.print(start);
                // Switch b/w 1 & 0 alternatively
                start = Math.abs(start - 1);
            }

            // Next line
            System.out.println();

        }

    }

    // Important
    public static void printPattern12(int n) {
        // [Numbers - asc, (2*n - 2*i) Spaces, Numbers - desc]

        // Row Iterations with i = 1,2,3,4...
        for (int i = 1; i <= n; i++) {

            // Step I: Print first i natural numbers in ascending order
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            // Step II: Print spaces in between
            for (int k = 1; k <= (2 * (n - i)); k++) {
                System.out.print(" ");
            }
            // Step III: Print the first i natural numbers in descending order
            for (int l = i; l > 0; l--) {
                System.out.print(l);
            }

            // Next line
            System.out.println();
        }
    }

    // Important
    public static void printPattern13(int n) {
        // Initialize a variable with value 1, this value will be incremented with each iteration of nested for loops
        int num = 1;
        // Outer loop for n times
        for (int i = 1; i <= n; i++) {
            // Inner loop for 1 element in row 1, 2 elements in row 2, 3 elements in row 3, 4 elements in row 4, etc
            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                // Keep on incrementing the num by 1 for each iteration
                num += 1;
            }
            // Next line
            System.out.println();
        }
    }

    // Important - ASCII char - A,B,C,D,..  for (char j = 'A', j < 'A' + i; j++)
    public static void printPattern14(int n) {
        for (int i = 1; i <= n; i++) {
            // One can loop through char - A,B,C,D,E,... as they're stored in ASCII form by computers
            // So, when adding a number(e.g. 2) to a char(e.g. A) & iterating from 'A' to 'A' + 2, result -> 'A', 'B', 'C'
            for (char j = 'A'; j < 'A' + i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void printPattern15(int n) {
        for (int i = n; i >= 0; i--) {
            for (char j = 'A'; j < 'A' + i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    // Important - To convert an ASCII number to its equivalent character, type cast the number with 'char' data type
    public static void printPattern16(int n) {
        char letter = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                // To convert an ASCII number to its equivalent character, type cast the number with 'char' data type
                System.out.print((char) (letter + i));
            }
            System.out.println();
        }
    }

    // Important
    public static void printPattern17(int n) {
        for (int i = 0; i < n; i++) {
            // Step I - Print spaces
            for (int j = 1; j <= (n - i - 1); j++) {
                System.out.print(" ");
            }

            // Step II - Print chars - ascending order, breakpoint, descending order
            char letter = 'A';
            // Define breakpoint - the point up to which chars would increment, post which chars would decrement
            int breakpoint = ((2 * i) + 1) / 2;
            for (int k = 0; k < (2 * i) + 1; k++) {
                System.out.print(letter);
                // If else logic to differentiate b/w chars increment/decrement
                if (k < breakpoint) {
                    letter++;
                } else {
                    letter--;
                }
            }

            // Step III - Print spaces again
            for (int l = 1; l <= (n - i - 1); l++) {
                System.out.print(" ");
            }
            // Next line
            System.out.println();
        }
    }

    public static void printPattern18(int n) {
        for (int i = 0; i < n; i++) {
            // Important Step - Keep this in mind whenever you have to decrement chars
            for (char j = (char) ('E' - i); j <= 'E'; j++) {
                // To convert an ASCII number to its equivalent character, type cast the number with 'char' data type
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void printPattern19(int n) {
        // Part I: Descending pattern
        for (int i = 0; i < n; i++) {
            // Step I: Print *
            for (int j = (n - i); j > 0; j--) {
                System.out.print("*");
            }

            // Step II: Print Spaces
            for (int k = 1; k <= (2 * i); k++) {
                System.out.print(" ");
            }

            // Step III: Print *
            for (int l = (n - i); l > 0; l--) {
                System.out.print("*");
            }

            // Next line
            System.out.println();
        }

        // Part II: Ascending pattern
        for (int i = 1; i <= n; i++) {
            // Step I: Print *
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            // Step II: Print Spaces
            for (int k = 1; k <= 2 * (n - i); k++) {
                System.out.print(" ");
            }

            // Step III: Print *
            for (int l = 1; l <= i; l++) {
                System.out.print("*");
            }

            // Next line
            System.out.println();
        }
    }

    // Important - Approach
    public static void printPattern21(int n) {
        // Rows - i
        for (int i = 0; i < n; i++) {
            // Columns - j
            for (int j = 0; j < n; j++) {
                // Only boundary points are taken here to print *
                if (i == 0 || j == 0 || i == (n - 1) || j == (n - 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();

        }
    }
}
