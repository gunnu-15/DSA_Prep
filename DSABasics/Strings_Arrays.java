package DSABasics;


public class Strings_Arrays {
    public static void main(String[] args) {
        // String
        String name = "Akash";
        System.out.println(name.charAt(name.length() - 1));
        // Every character of a string is stored as char in an Array.
        char[] nameArray = name.toCharArray();
        nameArray[nameArray.length - 1] = 'z';
        System.out.println(nameArray[nameArray.length - 1]);
    }
}
