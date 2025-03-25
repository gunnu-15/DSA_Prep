package DSABasics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputBufferedReader {

    public static void main(String[] args) throws IOException {
        // Use Scanner to take input from console & read it
        // Data Types: Int, Long, Float, Double, String, Char
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(in);
        String input = bf.readLine();
        int num = Integer.parseInt(input);
        System.out.println("Hey " + num);
    }
}
