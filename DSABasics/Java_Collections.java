package DSABasics;

import java.util.*;

public class Java_Collections {
    public static void main(String[] args) {

    }

    // List Interface - ArrayList class
    public static void arrayList() {
        // Provides dynamic arrays, helpful where lots of manipulation in array is needed
        // ArrayList cannot be used for primitive types - int, char, etc, A wrapper class is needed for such cases
        // Duplicates Are Allowed
        // null insertion is possible
        // Slower than arrays, increased memory usage, not thread-safe
        // ArrayList is not Synchronized.

        // Use ArrayList when random access (via index) is needed and modifications are infrequent.
        ArrayList<String> al = new ArrayList<String>();

        // Add elements at the end
        al.add(null);
        al.add("Geeks");

        // Add elements at the specified index
        al.add(1, "For");

        // Remove elements using index
        al.remove(0);

        // Remove elements using value
        al.remove("Geeks");

        // Update value using index
        al.set(0, "GFG");

        System.out.println(al);
    }

    // List Interface - LinkedList class
    public static void linkedList() {

        // Elements are not stored in adjacent locations
        // Every element s a separate object with a data part and address part. Elements are linked using pointers & addresses. Each element is known as a node.
        // Use LinkedList when frequent insertions/deletions are required, especially at the beginning or middle of the list.
        LinkedList<String> ll = new LinkedList<String>();

        // Add elements
        ll.add("B");
        ll.add("C");
        ll.addLast("D");
        ll.addFirst("A");
        ll.add(4, "E");

        // Remove elements
        ll.remove("C");
        ll.remove(4);
        ll.removeFirst();
        ll.removeLast();

        // Update elements
        ll.set(1, "b");

        // Iterate over elements
        for (int i = 0; i < ll.size(); i++) {
            System.out.println(ll.get(i) + " ");
        }

        // Using enhanced for loop
        for (String str : ll) {
            System.out.println(str + " ");
        }

        // LinkedList to toArray
        Object[] stringArray = ll.toArray();

        System.out.println(ll);

    }

    // List Interface - Vectors class
    public static void vectors() {
        // All methods are Synchronized - multiple threads can access the same vector without causing problems.
        // However, this synchronization comes at the cost of performance, so if you don’t need to share a vector between multiple threads, it’s generally better to use an alternative class like ArrayList which is not synchronized.
        // If you don’t need the synchronization features of Vector, using it will add unnecessary overhead to your code.
        // Can store null elements
        // Enumeration support - a way of iterating over elements

        Vector<Object> v = new Vector<>(3,2);

        // Add elements
        v.addElement(1);
        v.addElement("Geeks");

        // Add elements at the specified index
        v.insertElementAt(2, 1);

        // Remove elements using index
        v.removeElementAt(1);

        // Remove elements using value
        v.remove("Geeks");
        v.remove(2);

        // Update value using index
        v.set(0, 4);

        for (Object o : v) {
            System.out.println(o + " ");
        }

        System.out.println(v);
    }

    // List Interface - Stack class
    public static void stack() {
        // LIFO Principle - Last-In-First-Out
        // Sub-class of Vector & a legacy class - Thread-safe, might be overhead in an environment where thread safety is not needed
        Stack<String> stack = new Stack<String>();

        stack.push("Geeks");
        stack.push("For");
        stack.push("Geeks");

        // Iterator for stack
        Iterator<String> itr = stack.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next() + " ");
        }

        // Last In element will  be popped out
        stack.pop();
    }

    // Queue Interface - PriorityQueue
    public static void priorityQueue() {
        // FIFO Principle - First-In-First-Out, similar to a real-world queue line
        // Order of elements matter - elements ordered according to natural ordering(ascending by default)/by a Comparator
        // Highest priority elements appear at the front of the queue
        // No Null elements - it'll throw NullPointerException
        // Element with max ASCII value will have highest priority
        // Objects in PriorityQueue should be Comparable
        // Not thread-safe
        // O(log(N)) time for add & poll methods
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        pq.add(10);
        pq.add(20);
        pq.add(30);

        // Print top element of PriorityQueue - head of the queue
        System.out.println(pq.peek());

        // Print top element & remove it from PriorityQueue container
        System.out.println(pq.poll());

        // We will not get sorted elements by printing PriorityQueue, BUT elements are stored based on the priority order which is ascending by default.

        // Remove element
        pq.remove(10);

        // Iterator - itr.hasNext()

    }

    // Deque Interface - ArrayDeque class
    public static void arrayDeque() {
        // Double-ended Queue - add & remove elements from both ends of the queue
        // Can be used either as FIFO or LIFO
        // Doesn't allow null elements
        // Not threadsafe - doesn't support concurrent access by multiple threads
        Deque<String> deque = new ArrayDeque<String>();

        deque.addFirst("hello");
        deque.addLast("world!");

        String first = deque.removeFirst();
        String last = deque.removeLast();

        deque.poll(); deque.pollFirst(); deque.pollLast(); deque.pop();
        System.out.println(first + last);
    }

    // Set Interface - HashSet class
    public static void hashSet() {
        // Unordered collection
        // Stores only Unique values
        // Objects not inserted in the same order, inserted based on their hashcode
        // Allows null elements
        HashSet<String> hs = new HashSet<String>();

        hs.add("Geeks");
        hs.add("For");
        hs.add("Geeks");
    }

    // Set Interface - LinkedHashSet class
    public static void linkedHashSet() {
        // Uses doubly linked list to store data
        // Retains ordering of the elements
        LinkedHashSet<String> lhs = new LinkedHashSet<String>();

        lhs.add("Geeks");
        lhs.add("For");
        lhs.add("Geeks");
    }

    // Sorted Set Interface - TreeSet class
    public static void treeSet() {
        // Maintains ordering of the elements - natural ordering
        // Uses a Tree for storage
        TreeSet<String> ts = new TreeSet<String>();

        ts.add("Geeks");
        ts.add("For");
        ts.add("Geeks");
    }

    // Map Interface - HashMap class
    public static void hashMap() {
        // Data in Key-Value pairs
        // HashMap uses Hashing - converts large String to a small String that represents the same string so that indexing & search operations are faster

        // Creating HashMap and adding elements
        HashMap<Integer, String> hm = new HashMap<Integer, String>();

        hm.put(1, "Geeks");
        hm.put(2, "For");
        hm.put(3, "Geeks");

        // Traversing through the HashMap
        for (Map.Entry<Integer, String> e : hm.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
    }

}
