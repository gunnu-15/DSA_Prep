package DSABasics;

import java.util.Scanner;

public class IfElse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int marks = sc.nextInt();
        if (marks < 25) {
            System.out.println("F");
        }
        else if (marks <= 44) {
            System.out.println("E");
        }
        else if (marks <= 49) {
            System.out.println("D");
        }
        else if (marks <= 59) {
            System.out.println("C");
        }
        else if (marks <= 79) {
            System.out.println("B");
        }
        else if (marks <= 100) {
            System.out.println("A");
        }
    }
}
