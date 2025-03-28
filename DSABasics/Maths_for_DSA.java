package DSABasics;

import java.util.*;

public class Maths_for_DSA {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        int n1 = sc.nextInt();
        System.out.print("Enter number: ");
        int n2 = sc.nextInt();
        GCD_HCF_Optimal_method(n1, n2);

    }

    public static List<Integer> count_digits(int n) {

        if (n != 0) {
            int log_method_to_find_digits = (int) (Math.log10(n) + 1);
            System.out.println("the number of digits: " + log_method_to_find_digits);
        }

        List<Integer> digits_list = new ArrayList<Integer>();
        while (n > 0) {
            int last_digit = n % 10;
            digits_list.add(last_digit);
            // Time Complexity - O(log10N)
            n = n / 10;
        }

        System.out.println("No of digits in the number: " + digits_list.size());
        return digits_list;
    }

    // Important
    public static int reverse_digits_of_a_number(int n) {
        int reverse_number = 0;
        while (n > 0) {
            int last_digit = n % 10;
            // Important Step - Eg: 9462, digits from last - 2,6,4,9. How can we keep 6 beside 2? - (2 * 10) = 20 + 6 = 26. Similar logic followed subsequently
            reverse_number = (reverse_number * 10) + last_digit;
            n = n / 10;
        }
        System.out.println("Reverse a Number: " + reverse_number);
        return reverse_number;
    }

    public static void check_palindrome(int n) {
       // Compare the given number with reverse of that number.
       if (n == reverse_digits_of_a_number(n)) {
           System.out.println("Palindrome");
       } else {
           System.out.println("NOT a palindrome");
       }


    }

    public static void GCD_HCF_my_method(int n1, int n2) {

        List<Integer> factors_of_n1 = factors_of_number(n1);
        List<Integer> factors_of_n2 = factors_of_number(n2);
        // Modifies factors_of_n1, so use a copy if the original list needs to be preserved.
        factors_of_n1.retainAll(factors_of_n2);

        int gcd_hcf = Collections.max(factors_of_n1);
        System.out.println(gcd_hcf);
    }

    // Important
    public static List<Integer> factors_of_number(int n) {
        List<Integer> factors_list = new ArrayList<Integer>();
        // Time Complexity of this approach - O(n)
        /*for (int i = 1; i <=n; i++) {
            if (n % i == 0) {
                factors_list.add(i);
            }
        }
        return factors_list;*/

        // Can we reduce the Time Complexity?
        List<Integer> factors = new ArrayList<Integer>();
        // Math.sqrt(n) will take time, instead we can write:
        // Time Complexity now - O(sqrt(n))
        for (int i = 1; i*i <= n; i++) {
            factors.add(i);
            if (n/i != i) {
                factors.add(n/i);
            }
        }
        // Comparator.reverseOrder() - to sort in descending order
        // Time Complexity for this sorting - O(no of factors * log(no of factors))
        factors.sort(null);
        return factors;
    }


    // Armstrong Numbers - a number where the sum of its digits, each raised to the power of the number of digits in the number, equals the original number.

    public static void armstrong_numbers(int n) {
        // Eg: 153 = 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153.
        // Eg: 370 = 3^3 + 7^3 + 0^3 = 27 + 343 + 0 = 370
        // 371, 407. 4-digit nos - 1634, 8208, 9474

        // Step I - Find count of digits in the number, this will act as exponent to digits
        List<Integer> digits_list = count_digits(n);
        int total_digits = digits_list.size();

        int armstrong_number = 0;
        for (Integer digit : digits_list) {
            armstrong_number = (int) (armstrong_number + Math.pow(digit, total_digits));
        }

        if (n == armstrong_number) {
            System.out.println("Armstrong Number");
        } else {
            System.out.println("NOT an armstrong number");
        }



    }

    public static void check_for_prime(int n) {
        int factor_count = 0;
        // Eg: 36 factors - 1 * 36, 2 * 18, 3 * 12, 4 * 9, (sqrt(N))6 * 6(sqrt(N)), ...(factors will repeat after this)
        // Time Complexity - O(sqrt(n))
        for (int i = 1; i*i <=n; i++) {
            if (n % i == 0) {
                factor_count++;
                if (n/i != i) {
                    factor_count++;
                }
            }
        }

        if (factor_count == 2) {
            System.out.println("Prime Number");
        } else {
            System.out.println("NOT a prime number");
        }

    }

    // Important
    public static void GCD_HCF_Optimal_method(int n1, int n2) {
        // Initialize gcd as 1, keep on updating it after every iteration
        int gcd = 1;
        // For 2 numbers - n1 & n2; it's enough to check till min value to find out gcd
        // O(min(n1, n2))
        // Start from the min(n1,n2) - this can be the highest gcd possible, then iterate for subsequent lower values
        // This is best case - worst case will have TC - O(min(n1,n2))
        for (int i = Math.min(n1, n2); i >= 1; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
                // The for loop is exited after coming across the 1st highest factor
                break;
            }
        }
        System.out.println("HCF/GCD of " + n1 + "and " + n2 + " is: " + gcd);
    }

    // Important - Alternate Approach to find GCD/HCF ? Euclidean Algorithm
    public static void GCD_HCF_Euclidean_Algorithm(int n1, int n2) {
        // For two numbers - n1, n2. GCD(n1, n2) = GCD(n1 - n2, n2) where n1 > n2.
        // E.g. GCD(20,15) = GCD(5,15) = GCD(10,5) = GCD(5,5) = GCD(5,0). The moment one of the numer becomes 0, the other numbers becomes the GCF

        // For (52, 10), this will take a lot of steps -> (52,10),(42,10),(32,10),(22,10),(12,10),(10,2),(8,2),(6,2),(4,2),(2,2),(0,2)
        // Instead of subtracting 10 every time, a better way: GCD(52 % 10, 10) = GCD(10, 2)
        // So, GCD(a, b) = GCD (a % b, b) where a > b. If one of them is 0, the other is GCD


        // Time Complexity - Whenever there's division happening, no of iterations will be in terms
        // of log. O(log base [phi] min(n1, n2)) [phi] as n1, n2 would fluctuate
        while (n1 > 0 && n2 > 0) {
            if (n1 > n2) {
                n1 = n1 % n2;
            } else {
                n2 = n2 % n1;
            }
        }

        if (n1 == 0) {
            System.out.println("GCD is " + n2);
        } else {
            System.out.println("GCD is " + n1);
        }
    }



}
