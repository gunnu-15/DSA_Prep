package InputOutput;

import java.util.Scanner;

public class InputScanner {
    public static void main(String[] args) {
        // Use Scanner to take input from console & read it
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println("Hey " + x);
        System.out.println("Hey " + y);
        scanner.close();
    }
}
