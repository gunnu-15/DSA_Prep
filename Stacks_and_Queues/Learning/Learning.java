package Stacks_and_Queues.Learning;


import java.util.*;

public class Learning {

    public static void main(String[] args) {

        Min_Stack stack = new Min_Stack();
        stack.push(2147483646);
        stack.push(2147483646);
        stack.push(2147483647);
        stack.top();
        stack.pop();
        stack.getMin();
        stack.pop();
        stack.getMin();
        //
        stack.pop();
        stack.push(2147483646);
        stack.top();
        stack.getMin();
        stack.push(-2147483648);
        stack.top();
        stack.getMin();
        stack.pop();
        stack.getMin();
    }

    // Easy - struggled to get the logic to crack it
    public static boolean check_for_balanced_parenthesis(String s) {
        char[] charArray = s.toCharArray();
        java.util.Stack<Character> characterStack = new java.util.Stack<>();
        for (char c : charArray) {
            // Collect all the opening parenthesis: (, {, [ in a Stack
            if (c == '(' || c == '{' || c == '[') {
                characterStack.add(c);
            }
            // Now, this else block will execute for all closing parenthesis: ), }, ]
            // encountered sequentially in the string
            else {

                // Edge Case
                if (characterStack.isEmpty()) {
                    return false;
                }

                // Start from last element of opening parenthesis stack
                char opening_parenthesis = characterStack.peek();
                characterStack.pop();
                if (opening_parenthesis == '(' && c == ')' || opening_parenthesis ==
                        '{' && c == '}' || opening_parenthesis == '[' && c == ']') {
                    continue;
                }

                // If the encountered closing_parenthesis doesn't have a corresponding
                // opening_parenthesis, return false
                else {
                    return false;
                }
            }
        }
        // After each character is traversed in for loop, if every item in stack finds
        // its corresponding other half, the stack would become empty
        return characterStack.isEmpty();
    }

    // Medium - Store <K, V> pair in Stack. K -> element, V -> min_value so far
    public static void min_stack() {
        // Design a stack that supports push, pop, top, and retrieving the
        // minimum element in constant time.

        // Implement the MinStack class:

        // MinStack() - initializes the stack object.
        // void push(int val) - pushes the element val onto the stack.
        // void pop() - removes the element on the top of the stack.
        // int top() - gets the top element of the stack.
        // int getMin() - retrieves the minimum element in the stack.
    }
}

class Stack {
    int size = 10000;
    int[] arr = new int[size];
    int top = -1;

    // TC - O(1)
    void push(int x) {
        top++;
        arr[top] = x;
    }

    // TC - O(1)
    int pop() {
        int x = arr[top];
        top--;
        return x;
    }

    // TC - O(1)
    int peek() {
        return arr[top];
    }

    int size() {
        return top + 1;
    }
}

class Queue {
    private int arr[];
    private int start, end, currSize, maxSize;

    public Queue() {
        arr = new int[16];
        start = -1;
        end = -1;
        currSize = 0;
    }

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        start = -1;
        end = -1;
        currSize = 0;
    }

    public void push(int x) {
        // If we reach the max limit size of the array, then exit the program
        if (currSize == maxSize) {
            System.out.println("Queue is full\nExiting...");
            System.exit(1);
        }
        // If queue is empty, i.e. then move both the pointers to 0th index
        if (end == -1) {
            start = 0;
            end = 0;
        }
        // Else, only the end will move
        else
            end = (end + 1) % maxSize;
        arr[end] = x;
        System.out.println("The element pushed is " + x);
        currSize++;
    }

    public int pop() {
        if (start == -1) {
            System.out.println("Queue Empty\nExiting...");
            System.exit(1);
        }
        int popped = arr[start];
        if (currSize == 1) {
            start = -1;
            end = -1;
        } else
            start = (start + 1) % maxSize;
        currSize--;
        return popped;
    }

    public int peek() {
        if (start == -1) {
            System.out.println("Queue is Empty");
            System.exit(1);
        }
        return arr[start];
    }

    public int size() {
        return currSize;
    }
}

class Stack_LL {

    private class Stack_Node {
        int val;
        Stack_Node next;

        public Stack_Node(int val) {
            this.val = val;
            this.next = null;
        }

        public Stack_Node(int val, Stack_Node next) {
            this.val = val;
            this.next = next;
        }
    }

    // We're initialising this Node to reference to the head/top of the LL, we'll
    // keep updating the head as new elements are pushed into Stack
    Stack_Node top;
    int size;

    public Stack_LL() {
        this.top = null;
        this.size = 0;
    }


    // For each element pushed, we'd get a LL connection in the reverse order.
    // Eg: If push(3), push(4), push(2); then 2 -> 4 -> 3 -> null would be the LL here
    public void push(int x) {
        Stack_Node node = new Stack_Node(x);
        node.next = top;
        // As the element is pushed, increase the size
        size++;

    }

    public int peek() {
        // push(3), push(4), push(2); then 2 -> 4 -> 3 -> null would be the LL here
        // pop() would remove node 2 & reroute the connections(head would change here)

        // So, after pushing all the elements, top would point to head(i.e. last pushed
        // element)
        return top.val;
    }

    public int pop() {
        // push(3), push(4), push(2); then 2 -> 4 -> 3 -> null would be the LL here
        // peek () would return node val 2 without changing connections

        // Now store the head(i.e. last pushed value) in temp reference, then remove the
        // head of LL(i.e. last pushed element)

        // Check if the Queue is empty(top -> null in THAT case)
        if (top == null) {
            return -1;
        }

        Stack_Node temp = top;
        // Move top/head to the next node(i.e. to the previously pushed element)
        top = top.next;
        return temp.val;


    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

class Queue_LL {

    private class Queue_Node {
        int val;
        Queue_Node next;

        public Queue_Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    Queue_Node start;
    Queue_Node end;
    int size;

    public void push(int x) {

        Queue_Node node = new Queue_Node(x);

        // Check if Queue is empty
        if (start == null) { // && end == null is NOT reqd
            // Move start & end both to this newly created node
            start = end = node;
            // Keep the start node where it is, move the end node to the next pointer
            end = end.next;
        }
        // Else, means there are some elements. In this case, only move the end
        // pointer after creating a new node
        else {
            end = end.next;
        }
        size++;
    }

    public int pop() {
        // Check if Queue is empty
        if (start == null) {
            System.exit(1);
        }
        // Move the start pointer to the next node, & return the 1st element(FIFO)
        Queue_Node temp = start;
        start = start.next;
        return start.val;
    }

    public int peek() {
        if (start == null) {
            System.exit(1);
        }

        return start.val;
    }

}

class Stack_using_Queue {

    public java.util.Queue<Integer> queue;

    public void push(int x) {
        // We are given a Queue(Java in-built). We need to utilise this to convert
        // into a Stack

        // Add the element into the Queue
        queue.add(x);
        // Now, we need to reverse the Queue. Take each previous element of queue(),
        // adding at the end & removing it from previous position

        // We need to reposition (n-1) elements, so run loop (n-1) times
        for (int i = 1; i < queue.size(); i++) {
            // peek the head of the Queue, add it to end of Queue
            queue.add(queue.peek());
            // Now, poll the head of the Queue
            queue.poll();
        }
    }

    // Once the queue is arranged as to represent a stack - simply call the pop &
    // peek methods
}

class Queue_using_Stack {

    // We'll take 2 Stacks, similar to previous methods of operating with Queues

    java.util.Stack<Integer> stack1, stack2;

    public void push(int x) {


        /* TC - O(2N) - Lot of time
        // s1 -> s2
        while (stack1.size() >= 0) {
           stack2.add(stack1.peek());
           stack1.pop();
        }

        // x -> s1
        stack1.add(x);

        // s2 -> s1
        while (stack2.size() >= 0) {
            stack1.add(stack2.peek());
            stack2.pop();
        }
        */

        // Approach - 2
        java.util.Stack<Integer> stack1 = new java.util.Stack<>();
        java.util.Stack<Integer> stack2 = new java.util.Stack<>();

        // Push into stack1
        stack1.push(x);
    }

    public int pop() {
        // If stack2 is empty
        if (stack2.isEmpty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.peek());
                stack1.pop();
            }
        }

        int x = stack2.peek();
        stack2.pop();
        return x;
    }

    public int peep() {
        // If stack2 is empty
        if (stack2.isEmpty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.peek());
                stack1.pop();
            }
        }
        return stack2.peek();

    }
}

class Min_Stack {
    public Min_Stack() {
    }

    // We need to keep a track of the min value every time an element is pushed into
    // a stack. So, we'll store a <K, V> pair in our stack.
    // K -> element, V -> min_value.
    // SC - O(2N) as we're storing <K,V> pair
    java.util.Stack<Map.Entry<Integer, Integer>> stack = new java.util.Stack<>();

    public void push(int x) {
        // If stack is empty, the 1st element to be added would have Map<x, x> entry
        if (stack.isEmpty()) {
            stack.push(new AbstractMap.SimpleEntry<>(x, x));
        }
        else {
            int updated_min = (x <= stack.peek().getValue()) ? x : stack.peek().getValue();
            stack.push(new AbstractMap.SimpleEntry<>(x, updated_min));
        }
    }

    public int pop() {
        // Edge Case: If stack is empty
        if (stack.isEmpty()) {
            System.exit(1);
        }
        int deleted_element = stack.peek().getKey();
        stack.pop();
        return deleted_element;
    }

    public int top() {
        // Edge Case: If stack is empty
        if (stack.isEmpty()) {
            System.exit(1);
        }
        return stack.peek().getKey();
    }

    public int getMin() {
        // Edge Case: If stack is empty
        if (stack.isEmpty()) {
            System.exit(1);
        }
        return stack.peek().getValue();
    }
}

class Min_Stack_Mathematical_Formula {
    // If val(10) < min(12), 2 * val - prev_min = new_val(8) will be stored in
    // the stack, INSTEAD of the val. [min -> val]
    // Proof: [Val - min < 0. So, val + val - min < val. New_val < val]

    // prev_min = 2 * min - new_val(top of stack). Use this to get back the prev_min

    // TC - O(1). SC - O(n)

    java.util.Stack<Integer> stack = new java.util.Stack<>();
    int min;

    public void push(int val) {
        if (stack.isEmpty()) {
            min = val;
            stack.push(val);
        }
        else {
            // If incoming val is > min, just push it
            if (val > min) {
                stack.push(val);
            }
            // Else, push the Mathematical Formula derived new_val
            else {
                // [12, 15, 8]
                stack.push(2 * val - min); // 2 * 10 - 12 = 8
                min = val; // min = 10 (which is NOT pushed into stack directly)
            }
        }
    }
    public void pop() {
        if (stack.isEmpty()) {
            System.exit(1);
        }
        int x = stack.peek();
        stack.pop();
        // If the top of the stack is < min, top contains the MODIFIED value.
        // So, get the prev_min = 2 * min - new_val(top of stack)

        if (x < min) {
            int prev_min = 2 * min - x;
        }
        // Else, top contains the correct value.

    }
    public int peek() {
        if (stack.isEmpty()) {
            System.exit(1);
        }
        // peek() -> 8
        int x = stack.peek();
        // Eg: [12, 15, 8]. Here, min = 10(which IDEALLY WOULD have been the top element,
        // we/re just NOT STORING it in the stack)
        // Eg: [12, 15, 14]. Here, min = 12 & peek() -> 14
        // 12 < 14, so just return 14 as peek element
        if (min < x) {
            return x;
        }
        // 10 is > 8, so just the min(10). This would be the peek element
        else {
            return min;
        }
    }
    public int getMin() {
        return min;
    }
}


