# LinkedList

# What is a LinkedList

- Linear DS that can be visualized as a chain with different nodes connected, where each node represents a different element. 
- The difference between arrays and linked lists is that, unlike arrays, the elements are not stored at a contiguous location.
- Adding elements to an array is not possible after the initial assignment of size.
- It's easy to inc/dec the size of a LinkedList.
- A linked list(LL) is a DS containing two crucial pieces of information, the first being the data and the other being the pointer to the next element. 
- The ‘head’ is the first node, and the ‘tail’ is the last node in a linked list.

# Where is it used?

- Stack & Queue - primary examples where LL DS is used
- Browser

# Struct/Class in Java

- There are two information sets to store at every node, thus there is a need to create a self-defined data type to handle them. 
- Therefore, we will use the help of structs(no OOPS concepts applicable) and classes(OOPS concepts applicable).
- The struct has two data types: data which contains the value of the node and a pointer next, which points to the next node in the list.
- There is a constructor which assigns the values to a new node.
- A new keyword is used to dynamically allocate memory to a node with data as arr[0].
- A pointer is a variable that stores the memory address of another variable. In simpler terms, it "points" to the location in memory where data is stored. 
- This allows you to indirectly access and manipulate data by referring to its memory address.
- Reference variables hold references to objects in memory.
- Since the head always refers to arr[0], we can start iterating from arr[1] whenever traversing (Imp.)