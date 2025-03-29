# Hashing

Given an array of integers: [1, 2, 1, 3, 2] and we are given some
queries: [1, 3, 4, 2, 10]. For each query, we need to find out how many times
the number appears in the array. For example, if the query is 1 our answer would
be 2, and if the query is 4 the answer will be 0.

Brute-force Approach: Time Complexity will be O(n) for each query. If Q such queries,
O(Q * n). Roughly, O(10^8 operations) ~ 1 sec. If O(10^10 operations) ~ 100 sec -> unrealistic

Hashing - Pre-storing & fetching.

![img.png](img.png)

Number Hashing - Using Arrays
Character Hashing - > Map characters with their respective ASCII
(American Standard Code for Information Interchange) values.
E.g - 'a' -> 97, 'b' -> 98, 'c' -> 99, 'd' -> 100, 'z' -> 122, etc


![img.png](img.png)


Error Knowledge -
When you use sc.nextInt(), it only reads the integer value but leaves the newline character (\n) in the input buffer.
So, when sc.nextLine() is called, it immediately reads the leftover newline character (\n) instead of waiting for user input, resulting in an empty string ("").
Since "" has no characters, calling charAt(0) leads to "Index 0 out of bounds for length 0" error.

Time Complexity of a HashMap - O(logN) ?? (Verify this)

HashMap:
- Unordered
- Allows null keys & values, fast retrieval
- O(1) for put() & get()

LinkedHashMap
- Maintains insertion Order
- slightly slower performance than HashMap due to ordering maintenance

TreeMap
- Sorted by keys(Natural ordering OR custom Comparator)
- No null keys, null values are allowed
- Performance: O(logN) 

Prefer Unordered maps(HashMap) as it has TC - O(1) for best & average cases & O(n) for 
Worst-case scenario

# How does Hashing Work?

- Why does worst-case happen for Unordered maps(HashMap)?
- How is a Map data structure created?
- A Map data structure is implemented in different ways: Division Way, ...
- 2, 5, 16, 28, 139. Array Hashing can be done for these values. 
- But, if there's a restriction on the size of an array(say 10)
- Division Way - The number is modulated with 10(num % 10) & stored as index no in the array
- Problem: [2, 5, 16, 28, 139, 38, 48, 28, 18] - Linear Chaining. 18-28-28-38-48 : 
Chain-like structure at index 8 of array.
- Collision: Every element went to the same hash space, finding the elements would 
be tough in this case. That's why, in Worst-Case, TC - O(n); n -> no of elements in the map


![img.png](img.png)


