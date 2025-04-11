package Binary_Search;

import java.util.Scanner;

public class BS_on_Answers {

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

    }


}
