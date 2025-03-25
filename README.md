# DSA_Prep
DSA Preparation

Input - Scanner

nextInt() method - To accept multiple inputs in Java, we can use the nextInt() method of the Scanner class for each variable we want to receive input for. 

To use commonly used utility classes in Java, you can import the java.util package.
import java.util.* - imports all classes and interfaces from the java.util package.
You can then use classes from the java.util package, such as Scanner, ArrayList, HashMap, and others.

When you import a broad range of classes or packages, it can lead to longer compilation times and potential conflicts if different classes have the same name in different packages.

BufferedReader is more efficient when dealing with large volumes of input data, such as reading from files or network streams. 
It reads input as a stream of characters and can be particularly useful for handling text-based data where you need to parse 
lines or process data incrementally. 
BufferedReader also provides greater control over the input process, allowing you to handle exceptions and errors more gracefully. 

InputStreamReader class specifies where the input originates from.
InputStreamReader object designates input from the keyboard. e InputStreamReader class is used to convert the raw byte-based input stream (System.in) into a character-based input stream. This is necessary to read text input conveniently. 
System.in represents the standard input, which is usually your keyboard. But it could be something else, like a file or network stream, depending on what you 
point it to.

Create a BufferedReader object and pass the InputStreamReader object into it. 
This BufferedReader will be responsible for reading and processing the input. the BufferedReader is used to buffer the input stream, making it more efficient to read lines of text. It provides methods like readLine() to read complete lines of text