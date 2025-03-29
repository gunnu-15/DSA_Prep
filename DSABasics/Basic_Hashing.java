package DSABasics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Basic_Hashing {

    public static void main(String[] args) {
        number_hashing_for_higher_order_numbers();
    }

    public static void number_hashing() {
        Scanner scanner = new Scanner(System.in);

        // Ask user for the size of the array
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        // Declare an array of size n
        int[] numbers = new int[n];

        // Taking input for the array
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        // Pre-compute OR Pre-storing

        // Max size of an int array, when defined in main func, - 10^6
        // Max size of an int array, when defined globally, - 10^7
        int[] hash = new int[13];
        Arrays.fill(hash, 0); // Fill all 13 elements with 0
        for (int i = 0; i < n; i++) {
            hash[numbers[i]]++;
        }

        // Query from user
        System.out.print("Enter the number of queries: ");
        int q = scanner.nextInt();
        while (q > 0) {
            int number;
            number = scanner.nextInt();
            // Fetch
            System.out.println("Number of times " + number + " occurs is - " + hash[number]);
            q--;
        }
    }

    public static void character_hashing() {
        // a,b,c,d,e,f,..... characters are mapped with numbers -> 0,1,2,3,4,5,.....
        // How to programmatically map them? - ASCII values
        // 'a' ASCII value -> 97, 'z' ASCII value -> 122
        // int character_ascii = 'a'. If you print character_ascii variable, it'll return 97.
        // (char - 'a') -> corresponding index in hash array

        System.out.print("Enter the string: ");
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();

        // New character hash array - 256 characters in total
        int[] hash = new int[256];

        // Convert string to char array
        char[] character_array = string.toCharArray();
        for (int i = 0; i < character_array.length; i++) {
            hash[(int) character_array[i]]++;
        }

        System.out.print("Enter the number of queries: ");
        int q = sc.nextInt();
        sc.nextLine(); // Consumes the leftover line

        while (q > 0) {
            System.out.print("Enter the character: ");
            char c = sc.nextLine().charAt(0);
            System.out.println("Number of occurrences of character " + c + " are - " + hash[(int)c]);
            q--;
        }

    }

    public static void number_hashing_for_higher_order_numbers() {
        Scanner scanner = new Scanner(System.in);

        // Ask user for the size of the array
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        // Declare an array of size n
        int[] numbers = new int[n];

        // Taking input for the array
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        // Pre-compute OR Pre-sorting using HashMap - uses less memory vs an Array
        // Time Complexity - O(logN)
        HashMap<Integer, Integer> numberVsFrequencyMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // How to update HashMap with Key -> Number & Value -> Incrementing if number is found
            numberVsFrequencyMap.put(numbers[i], numberVsFrequencyMap.getOrDefault(numbers[i], 0) + 1);
        }

        // Query from user
        System.out.print("Enter the number of queries: ");
        int q = scanner.nextInt();
        while (q > 0) {
            int number;
            number = scanner.nextInt();
            // Fetch
            System.out.println("Number of times " + number + " occurs is - " + numberVsFrequencyMap.get(number));
            q--;
        }










    }



}
