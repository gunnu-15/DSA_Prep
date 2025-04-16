package LinkedList;

public class LinkedList_1D {
    public static void main(String[] args) {
        int[] arr = {12};
        // When you print Node x, the system returns the memory address/pointer where data of
        // x is stored
        Node head = convert_array_to_LL(arr);
        System.out.println("Traverse in LL: ");
        traverse_in_LL(head);

        head = insert_kth_node(head, 1, 23);
        traverse_in_LL(head);


    }

    // Array to LL: We have stored links to each element using next reference variable
    public static Node convert_array_to_LL(int[] array) {
        if (array.length == 0) {
            Node head = null;
            return head;
        }
        Node head = new Node(array[0]);
        Node mover = head;
        // Since the head always refers to arr[0], we can start iterating from arr[1]
        for (int i = 1; i < array.length; i++) {
            Node temp = new Node(array[i]);
            // mover refers to previous Node, & we're trying to store the 'temp' pointer/
            // reference in reference variable next(mover.next) of 'mover'
            mover.next = temp;
            // Move the mover Node reference forward
            mover = temp;
        }
        return head;
    }

    // Traversal in LL - NEVER ever tamper/change the head
    public static void traverse_in_LL(Node head) {
        // We're using temp as we don't want to tamper with head
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Length of LL
    public static int length_of_LL() {
        int[] array = {12, 5, 6, 8};
        Node head = convert_array_to_LL(array);

        Node temp = head;
        int count = 0;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
            count++;
        }
        return count;
    }

    public static boolean search_an_element_in_LL(Node head, int element) {

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            if (temp.data == element) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Deletion - 4 variations
    public static Node delete_head(Node head) {

        if (head == null) {
            return head;
        }
        head = head.next;
        return head;
        // In Java, Garbage Collector makes sure to free up space when there's no reference
        // to data, it'll delete it.
    }

    public static Node delete_tail(Node head) {

        Node temp = head;
        // If either the head is null or there's only 1 element in the LL
        if (head == null || head.next == null) {
            return head;
        }
        // Iterate over the entire LL until you reach the end of the LL - i.e. the tail
        while (temp.next.next != null) {
            // Move temp to the next node
            temp = temp.next;
        }
        // After the while loop executes, temp points to second-last element & temp.next
        // points to last element, i.e. the tail. So, make temp.next as null
        temp.next = null;
        return head;
        // In Java, Garbage Collector makes sure to free up space when there's no reference
        // to data, it'll delete it.
    }

    public static Node delete_kth_node(Node head, int kth_element) {
        // There is a singly-linked list head, and we want to delete a node 'node' in it.
        // You are given the node to be deleted 'node'. You will not be given access to
        // the first node of head.
        // All the values of the linked list are unique, and it is guaranteed that the
        // given node 'node' is not the last node in the linked list.

        // Always start by thinking of various edge cases

        if (head == null) {
            return null;
        }

        if (kth_element == 1) {
            head = head.next;
            return head;
        }

        Node temp = head;
        int count = 0;
        // Initialize prev Node
        Node prev = null;

        while (temp != null) {
            count++;
            if (count == kth_element) {
                // You're essentially trying to reassign prev Node's pointer to subsequent
                // node, basically bypassing the kth_element
                prev.next = prev.next.next;
                // Break out of the while loop
                break;
            }
            // How to keep track of previous Node?
            prev = temp;
            // If count != k, move the temp to the next Node, we've already kept track of
            // the prev Node in the above line
            temp = temp.next;
        }
        return head;
    }

    public static Node delete_node_by_value(Node head, int value) {
        // There is a singly-linked list head, and we want to delete a node 'node' in it.
        // You are given the node to be deleted 'node'. You will not be given access to
        // the first node of head.
        // All the values of the linked list are unique, and it is guaranteed that the
        // given node 'node' is not the last node in the linked list.

        // Always start by thinking of various edge cases

        if (head == null) {
            return null;
        }

        if (head.data == value) {
            head = head.next;
            return head;
        }

        Node temp = head;
        // Initialize prev Node
        Node prev = null;

        while (temp != null) {
            if (temp.data == value) {
                // You're essentially trying to reassign prev Node's pointer to subsequent
                // node, basically bypassing the kth_element/value
                prev.next = prev.next.next;
                // Break out of the while loop
                break;
            }
            // How to keep track of previous Node?
            prev = temp;
            // If count != k, move the temp to the next Node, we've already kept track of
            // the prev Node in the above line
            temp = temp.next;
        }
        return head;
    }

    // Insertion - 4 variations
    public static Node insert_head(Node prev_head, int value) {

        Node temp = new Node(value);
        if (prev_head == null) {
            return temp;
        }
        temp.next = prev_head;
        return temp;
        // In Java, Garbage Collector makes sure to free up space when there's no reference
        // to data, it'll delete it.
    }

    public static Node insert_tail(Node head, int value) {

        Node temp = new Node(value);
        Node iterator = head;
        // If either the head is null or there's only 1 element in the LL
        if (head == null) {
            return temp;
        }
        // Iterate over the entire LL until you reach the end of the LL - i.e. the tail
        while (iterator.next != null) {
            // Move temp to the next node
            iterator = iterator.next;
        }
        // After the while loop executes, temp points to last element, i.e. the tail
        // & temp.next will now point to the last element.
        iterator.next = temp;
        return head;
    }

    // Medium
    public static Node insert_kth_node(Node head, int kth_element, int value) {
        // There is a singly-linked list head, and we want to insert a node 'node' in it.
        // You are given the node to be inserted - 'node'.

        // Initialize a new Node to be inserted
        Node subsequent_node = new Node(value);
        // Initialize prev Node
        Node prev = null;

        Node temp = head;
        int count = 0;

        // Edge Case - If the head is null - Two cases: 1) k = 1. 2) k = something else
        if (head == null) {
            if (kth_element == 1) {
                return subsequent_node;
            } else {
                return null;
            }
        }

        // Edge Case - If kth_element position = 1
        if (kth_element == 1) {
            return new Node(value, head);
        }


        while (temp != null) {
            count++;
            // If count is 1 behind k, then only the below code block will execute
            if (count == kth_element - 1) {

                subsequent_node.next = temp.next;
                temp.next = subsequent_node;
                break;
            }
            // If count != k, move the temp to the next Node, we've already kept track of
            // the prev Node in the above line
            temp = temp.next;
        }
        return head;
    }

    public static Node insert_node_before_value(Node head, int element, int value) {
        // Here, you have to insert a Node with data = element & insert this
        // node before a particular value in the LL. Assume that the value is present
        // in the LL

        // Initialize a new Node to be inserted
        Node subsequent_node = new Node(element);
        // Initialize prev Node
        Node prev = null;

        Node temp = head;

        // Edge Case - If the head is null
        if (head == null) {
            return null;
        }

        // Edge Case - If there's only 1 element in LL & it is the value itself, place
        // the Node(element) before the head(value)
        if (head.data == value) {
            return new Node(element, head);
        }

        while (temp != null) {
            // Keep iterating with temp pointing to an element just preceding the value
            // Thie below logic tracks if temp.next's data = value
            if (temp.next.data == value) {
                // Create the element
                subsequent_node.next = temp.next;
                temp.next = subsequent_node;
                break;
            }
            // If count != k, move the temp to the next Node, we've already kept track of
            // the prev Node in the above line
            temp = temp.next;
        }
        return head;
    }


}


class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    Node(int data) {
        this.data = data;
        this.next = null;
    }

}
