Time and Space Complexity

# Time Complexity

Time Complexity is NOT the same as time taken by code to run.
Let's say if the code is run on 2 different machines: 
- Old Windows(might take 2 sec)
- New Macbook(might take 1 sec)

Depending on the configurations of the system, time taken would be different on different machines.

Time Complexity - Rate at which the time taken increases wrt the input size.
Eg: For input size = 10, time taken -> 2 sec
For input size = 20, time taken -> 4 sec
For input size = 30, time taken -> 6 sec

If you plot a graph b/w input size(x-axis) & time taken(y-axis), you get a STRAIGHT LINE.
The ANGLE made by the line wrt x-axis OR the SLOPE of the line = Rate of increase 

Now, this rate of increase might be different on different machines.

Q. How do we measure Time Complexity?

Big-O Notation -> O(hypothetical time taken)
Worst Case Complexity/Upper-Bound Complexity

Eg: for(int i = 1; i <= 5; i++) {sout("Hello")}
Big-O(n) -> No of steps taken by this piece of code is mentioned in the brackets.

Rules to calculate time complexity:
- Always calculate Time Complexity in terms of Worst Case scenario(Best Case, Average Case, Worst Case)
- Avoid Constants
- Avoid lower values

So, in the above for-loop example, the for loop runs for 5 times: 
- Increment of i
- Check if condition is satisfied
- Print statement
- These 3 steps are run 5 times, so 5 * 3 = 15 times.

So, for for(int i = 1; i<= n; i++) -> O(n * 3)



Eg: if (marks < 25) sout("D")
else if (marks < 45) sout("C")
else if (marks < 65) sout("B")
else sout("A")

Best Case(Omega): Least amount of time taken by program. If marks input = 10, only the first 2 operations: 
- 1st condition check
- Print
So, O(2) operations -> Best Case

If marks input = 70
- Check, false
- Check, false
- Check, false
- Print("A")
4 Operations here. So, Worst Case here -> O(4)

Average Case(Theta): Mean of Best Case & Worst Case



Avoid Constants: 
Eg: O(4N^3 + 3N^2 + 8). If input size(n) = 10^5
O() = 10^15 + 10^10 + 8(insignificant when compared to higher values beside it, so ignored)



Avoid Lower Values:
Eg: O(4N^3 + 3N^2 + 8)
O() = 10^15 + 10^10 + 8; Adding 10^10 will be insignificant when compared to 10^15


Q. for(int i = 0; i<n; i++) {
        for(int j = 0; j<n; j++){
            sout("")
    }
}. TC = ?

i = [0,n) -> n times
j = [0,n) -> n times
So, n * n iterations -> n^2. So, O(n^2).
We've ignored comparative check logics & print statements as they are insignificant when compared to n^2

Q. for(int i = 0; i<n; i++) {
        for(int j = 0; j<=i; j++){
            sout("")
    }
}. TC = ?

1 + 2 + 3 + .... + n = n(n + 1)/2 = (n^2 + n)/2 = n^2/2 + n/2 ~ O(n^2/2) ~ O(n^2) (ignoring insignificant values)

# Space Complexity

Memory space a program takes(naively speaking)
Again, can vary with different machines. So, Big-O notation is used for space also.

Space Complexity - Auxiliary space(space taken to solve problem) + Input space(space taken to store input)

Eg: Let's say 2 variables of input: a & b. c = a + b
c -> Auxiliary space(Extra space to solve the problem)
Eg: int[n] -> O(n) is the Space Complexity


A crucial rule: Never change/manipulate the input variable. Data should NOT be manipulated. The data might be used somewhere else, be very careful about this. Don't tamper with data.

Time limit of 1 sec generally means O(10^8)
So, 2 sec -> 0(2 * 10^8)