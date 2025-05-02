# Stacks and Queues

# Stacks
- Stack: Follows LIFO(Last-In-First-Out) principle. Has 4 main functions.
- push(): Push a certain value to the top of the stack
- pop(): REMOVES & returns the item at the top of the stack
- peek(): Returns the item at the top of the stack WITHOUT REMOVING it. Throws EmptyStackException if the stack is empty 
- size():
- TC - O(1) for all these operations

- In Java, a stack can be implemented in multiple ways, but typically it's built on top of other DS like arrays or linked lists.
- Java provides a built-in class Stack(java.util), internally it extends Vector, a resizable array.

# Queues
- Queue: Follows FIFO(First-In-First-Out) principle.
- For Queue implementations, use 2 pointers: start, end. REASON: We add elements at the last, BUT whenever there's a call for peek()/pop(), we take out from the back.

# Implement Stack using Arrays(fixed size)
- Disadvantage: It's not dynamic in nature, we need fixed size arrays. So, there's possibility of extra space left behind.

# Implement Queue using Arrays(fixed size)
- Circular Array. Take 2 pointers - start, end. Keep moving them as we're pushing elements. Once, either the start/end overtake the array size, start them again from index 0.


- Implement Stack using LinkedList(dynamic size)
- Implement using Queue(for Stack)/ using Stack(for Queue)


# Priority Order of Operators

- Power operator(^) - Max priority
- Multiply, Division(*, /) - 
- Addition, Subtraction(+, -) - 
- Anything apart from above - Min priority

- Prefix: *+pq -mn. Used in a programming language LISP extensively. Also in Tree DS
- Infix:(p + q) * (m - n)
- Postfix: pq+ mn- *. Used in Stack-based calculators