package LinkedList.Medium_Problems_DLL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Medium_Problems_DLL {

    public static void main(String[] args) {
        int[] arr1 = {-1, 2, 2, 2, 3, 3, 3, 7};
        Node head = convert_array_to_doubly_LL(arr1);
        traverse_in_LL(head);
        Node result_head = remove_duplicates_from_sorted_DLL(head);
        traverse_in_LL(result_head);
    }

    public static Node delete_all_occurrences_of_key_in_DLL(Node head, int key) {
        // Delete all occurrences/nodes having the given key, construct the DLL with
        // the remaining elements & return the new head.

        // I wrote this code by my own, we could still optimize it further
        /*
        // Edge Case
        if (head.data == key) {
            head = head.next;
            head.back = null;
        }


        Node temp = head;
        Node prev = temp.back;
        Node next = temp.next;
        while (temp != null) {

            if (temp.data == key) {

                prev.next = next;
                if (next != null) {
                    next.back = prev;
                }
                temp.next = null;
                temp.back = null;
            }

            temp = next;
            if (temp != null) {
                next = temp.next;
                prev = temp.back;
            }
        }

        return head;
        */

        // Slightly Optimal Solution
        Node temp = head;
        while (temp != null) {

            // If the node matches with the key to be deleted
            if (temp.data == key) {
                // If the starting node itself is to be removed
                if (temp == head) {
                    head = head.next;
                }
                Node prev = temp.back;
                Node next = temp.next;

                // Now, reroute the pointers
                // Check if next node exists(i.e. not null). If it does, point the back
                // pointer of next to prev
                if (next != null) {
                    next.back = prev;
                }
                // Check if prev node exists(i.e. not null). If it does, point the next
                // pointer of prev to next
                if (prev != null) {
                    prev.next = next;
                }

                // After rerouting, move temp to the next pointer
                temp = next;

            }
            // Else, move the temp to the next iteration as it's not equal to key
            else {
                temp = temp.next;
            }
        }
        // TC - O(n). SC - O(1)
        return head;
    }

    // Medium - sorted DLL -> use BS to find pair with sum.
    public static List<List<Integer>> find_pairs_with_given_sum_in_sorted_DLL(Node head,
                                                                              int sum) {

        // Brute-Force Solution
        /*
        Node temp1 = head;
        List<List<Integer>> list_of_list = new ArrayList<>();
        while (temp1 != null) {
            Node temp2 = temp1.next;
            while (temp2 != null && (temp1.data + temp2.data <= sum)) {

                if (temp1.data + temp2.data == sum) {
                    List<Integer> result_list = new ArrayList<>();
                    result_list.add(temp1.data);
                    result_list.add(temp2.data);
                    list_of_list.add(result_list);
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        // TC ~ O(n^2)
        return list_of_list;
        */

        // Optimal Solution - This DLL is sorted - this should trigger a better
        // approach: Take 2 pointers, similar to Binary Search

        Node left = head;
        // Find the last element of the DLL - O(n)
        Node right = findTail(head);
        List<List<Integer>> list_of_list = new ArrayList<>();

        // Edge Case
        if (head == null) {
            return list_of_list;
        }

        // Make sure that the right pointer doesn't cross the left pointer
        // O(n)
        while (left.data <= right.data) {

            // If left + right = sum
            if (left.data + right.data == sum) {
                List<Integer> result_list = new ArrayList<>();
                result_list.add(left.data);
                result_list.add(right.data);
                // Once you get the required sum, move both the pointers
                left = left.next;
                right = right.back;
            }

            // If left + right < sum
            else if (left.data + right.data < sum) {
                // Move only the left pointer next
                left = left.next;
            }

            // If left + right > sum
            else {
                // Move only the right pointer back
                right = right.back;
            }
        }
        // TC - O(n) + O(n) = O(2n). SC - O(1)
        return list_of_list;
    }

    private static Node findTail(Node head) {
        // Given a DLL, find the tail of the DLL

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }

    public static Node remove_duplicates_from_sorted_DLL(Node head) {

        // Brute-Force Solution - HASHING
        /*
        // Edge Case
        if (head == null || head.next == null) {
            return head;
        }

        HashMap<Integer, Integer> hashing = new HashMap<>();
        Node temp = head;
        while (temp != null) {
            if (hashing.containsKey(temp.data)) {
                // Remove this node from the DLL, so reroute the connections
                Node prev = temp.back;
                Node next = temp.next;

                prev.next = next;
                // Check if temp is the last element
                if (temp.next != null) {
                    next.back = prev;
                }

                temp.next = null;
                temp.back = null;

                temp = next;
            }
            else {
                hashing.put(temp.data, 1);
                temp = temp.next;
            }
        }
        return head;
         */

        // Better Solution - Since DLL is sorted, iterate over each element. If the
        // next subsequent elements are equal to initial node, just move forward. If
        // you encounter a new node, then reroute the pointers.
        Node temp = head;
        Node next = temp.next;
        while (temp.next != null && temp != null) {
            // If the next consecutive elements are same as the initial element.
            if (next.data == temp.data) {
                next = next.next;
            }
            else {
                // Reroute the links
                temp.next = next;
                next.back = temp;

                // Move temp to this next updated pointer
                temp = next;
                // Move next to temp.next
                next = temp.next;
            }
        }
        // TC - O(n). SC - O(1).
        return head;
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
            // Now, move prev Node to temp(iterate 1 step forward)
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
