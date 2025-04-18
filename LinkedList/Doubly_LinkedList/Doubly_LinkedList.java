package LinkedList.Doubly_LinkedList;

import java.util.Stack;

public class Doubly_LinkedList {
    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 1, 6, 5};
        Node head = convert_array_to_doubly_LL(arr);
        traverse_in_LL(head);
        Node new_node = reverse_DLL(head);
        traverse_in_LL(new_node);
    }

    public static Node convert_array_to_doubly_LL(int[] array) {
        if (array.length == 0) {
            Node head = null;
            return head;
        }
        Node head = new Node(array[0]);
        Node prev = head;
        // Since we've already defined head, we can start iterating from the 2nd element
        for (int i = 1; i < array.length; i++) {
            // Iterate over a particular Node in array
            // the previous Node of array[i] would point to prev(back pointing)
            Node temp = new Node(array[i], null, prev);
            // We also need to point the next of prev Node to temp(next pointing)
            prev.next = temp;
            // Now, move prev Node to temp(ietare 1 step forward)
            prev = temp;
        }
        return head;
    }

    public static void traverse_in_LL(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Deletion - 4 variations
    public static Node delete_head(Node head) {
        // Edge Case: If the array is empty OR If the Doubly_LL has only 1 element
        if (head == null || head.next == null) {
            return null;
        }

        // If the Doubly_LL has more than 1 element
        Node prev = head;
        // Move head to the next element
        head = head.next;
        // Remove the back connection of 1st element(now head as per above line as we've
        // moved head to the next element)
        head.back = null;
        // Remove the next connection of 0th element(prev)
        prev.next = null;
        return head;
    }

    public static Node delete_tail(Node head) {
        // Edge Case: If the array is empty OR If the Doubly_LL has only 1 element
        if (head == null || head.next == null) {
            return null;
        }

        // If the Doubly_LL has more than 1 element
        // Approach - Remove the next & back connections b/w last element & second-last
        // element
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // Now, temp will point to the last element
        Node second_last = temp.back;
        // Disconnect the second-last element's next connection with last element
        second_last.next = null;
        // Now, disconnect the last element's back connection with second-last element
        temp.back = null;

        return head;
    }

    // Medium
    public static Node delete_kth_element_of_doubly_LL(Node head, int k) {

        Node temp = head;
        int count = 0;
        while (temp != null) {
            // Iterate through the entire doubly LL
            count++;
            if (count == k) {
                // The moment you break out of the loop, temp will point to the kth element
                break;
            }
            temp = temp.next;
        }
        Node prev = temp.back;
        Node front = temp.next;

        // Edge Case: If the doubly LL has 1 single element
        if (prev == null && front == null) {
            return null;
        }

        // Edge Case: If k = 1, delete the starting element, i.e head
        else if (prev == null) {
            head = delete_head(head);
        }
        // k = last element(n), delete the last element, i.e tail
        else if (front == null) {
            head = delete_tail(head);
        }
        // Reassign pointers to change connections
        else {
            prev.next = front;
            front.back = prev;
            temp.next = null;
            temp.back = null;
            return head;
        }
        return head;
    }

    public static void delete_node_of_doubly_LL(Node temp) {
        // get the prev Node
        Node prev = temp.back;
        // get the front Node
        Node front = temp.next;

        // If the front Node is null(i.e the tail element)
        if (front == null) {
            prev.next = null;
            temp.back = null;
            // The return statement is necessary as we don't want further code lines to
            // execute when temp points to tail of LL, so exit this function after this
            return;
        }

        // Severe the connections now
        prev.next = front;
        front.back = prev;
        // Severe the connections of the particular temp node
        temp.next = null;
        temp.back = null;
    }


    // Insertion - 4 variations
    public static Node insert_before_head(Node head, int element) {
        Node new_node = new Node(element, head, null);
        head.back = new_node;
        return new_node;
    }

    public static Node insert_before_tail(Node head, int element) {

        Node new_head;

        // If the LL is empty
        if (head == null) {
            return new Node(element, null, null);
        }

        // If there's only 1 single element in the doubly LL, the single element acts as
        // both head and tail, so call insert_before_head() func
        if (head.next == null) {
            new_head = insert_before_head(head, element);
            return new_head;
        }
        // If there's more than 1 element
        else {

            Node temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            // temp now points to the second-last element, so temp.next points to the tail
            Node new_node = new Node(element, temp.next, temp);
            // the second-last element's next should now point to new_node
            temp.next = new_node;
            // the last element/tail's back should point to new_node
            temp.next.back = new_node;
            return head;
        }
    }

    public static Node insert_before_kth_element(Node head, int k, int element) {
        if (k == 1) {
            return insert_before_head(head, element);
        } else {

            Node temp = head;
            int count = 0;
            while (temp != null) {
                count++;
                if (count == k) {
                    break;
                }
                temp = temp.next;
            }

            Node prev = temp.back;
            Node new_node = new Node(element, temp, prev);
            temp.back = new_node;
            prev.next = new_node;
            return head;
        }
    }

    public static Node insert_before_given_node(Node head, Node given_node, int element) {
        Node prev = given_node.back;
        Node new_node = new Node(element, given_node, prev);
        prev.next = new_node;
        given_node.back = new_node;
        return head;
    }

    // Medium
    public static Node reverse_DLL(Node head) {

        // Brute-Force Solution - Take a Stack DS.
        /*
        Stack<Integer> stack = new Stack<>();
        Node temp = head;
        while (temp != null) {
            // Iterate over each node & push that element into the stack
            stack.push(temp.data);
            temp = temp.next;
        }
        // Now, we'll take out each element from the top of the stack & create a new LL
        // in reverse order
        temp = head;
        while (temp != null) {
            // peep() just fetches the top element of the stack, it DOESN'T delete it
            temp.data = stack.peek();
            // pop() DELETES the retrieved data
            stack.pop();
            temp = temp.next;
        }
         */
        // TC - O(n) + O(n) -> O(2n). SC - O(n) as we're using a Stack DS.

        // Optimal Solution - Swap next & back pointers
        Node temp = head;
        Node previous = null;
        while (temp != null) {
            // Swap out next & back pointers of a node
            previous = temp.back;
            temp.back = temp.next;
            temp.next = previous;
            temp = temp.back;
        }
        // After all the iterations, previous will point to the 2nd element, & since
        // we've swapped the next, back pointers, previous.back will fetch the head.
        assert previous != null;
        return previous.back;
        // TC - O(n). SC - O(1)
    }


}

class Node {
    int data;
    Node next;
    Node back;

    Node(int data, Node next, Node back) {
        this.data = data;
        this.next = next;
        this.back = back;
    }

    Node(int data) {
        this.data = data;
        this.next = null;
        this.back = null;
    }
}
