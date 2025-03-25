package DSABasics;

public class For_While_DoWhile_Loops {
    public static void main(String[] args) {
        // For loop
        for (int i = 0; i <=25; i = i + 5) {
            System.out.println(i);
        }
        System.out.println("-----------------------");
        // While loop
        int i = 2;
        while (i <=5) {
            System.out.println(i);
            i+=1;
        }
        System.out.println("-----------------------");
        // Do While Loop
        do {
            System.out.println("Akash" + i);
            i+=1;
        } while (i <=1);
    }
}
