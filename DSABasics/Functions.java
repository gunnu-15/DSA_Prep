package DSABasics;

import java.util.Scanner;

public class Functions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        printName(name);
        int num = 10;
        passByValue(num);
        System.out.println(num);
    }

    // void/Parameterised function
    public static void printName(String name) {
        System.out.println("Hey " + name + "!");
        System.out.println();
    }

    // pass by Value Functions
    public static void passByValue(int num) {
        System.out.println(num);
        num+=5;
        System.out.println(num);
        num+=5;
        System.out.println(num);
    }

    // C/C++ supports the call by reference because in the call by reference we pass the address of actual parameters in the place of formal parameters using Pointers.
    // Java does not support Pointers that’s why Java does not support Call by Reference but c/c++ support pointers hence these language support call by Reference.
}
