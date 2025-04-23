package LinkedList.Medium_Problems_LL;


import java.util.HashMap;
import java.util.Map;

public class Medium_Problems_LL {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 1, 2, 3};
        int[] arr2 = {4, 5, 7, 8, 2, 3};
        Node head1 = convert_array_to_LL(arr1);
        Node head2 = convert_array_to_LL(arr2);
//        traverse_in_LL(head1);
        Node result_head = intersection_of_2_LL(head1, head2);
        traverse_in_LL(result_head);


    }

    // Medium - dummy Node approach
    public static Node add_2_numbers_LL(Node head1, Node head2) {
        // Brute-Force Solution
        /*
        int number1 = get_number_using_reverse_digits_LL(head1);
        int number2 = get_number_using_reverse_digits_LL(head2);
        int sum = number1 + number2;
        Node result_head = get_node_elements_in_reverse_using_number(sum);
        return result_head;
         */

        // Optimal Solution
        // Approach - Keep track of the carry using a variable and simulate
        // digits-by-digits sum starting from the head of the list, which contains the
        // least significant digit.

        // Take 2 temp variables to iterate over the 2 different heads - head1, head2
        Node temp1 = head1;
        Node temp2 = head2;

        // dummy Node Approach - Whenever we need to create a new LL, where we store the
        // result, always prefer the concept of dummy Node
        Node dummyNode = new Node(-1);
        // Keep a variable Node to iterate/move forward in the resultant Node
        Node current = dummyNode;

        // Keep track of carry over when adding 2 single-digit numbers
        int carryOver = 0;

        while (temp1 != null || temp2 != null) {
            int num1 = temp1 != null ? temp1.data : 0;
            int num2 = temp2 != null ? temp2.data : 0;

            int sum = num1 + num2 + carryOver;
            int digit = sum % 10;
            carryOver = sum / 10;
            Node new_node = new Node(digit);
            current.next = new_node;
            // Move the resultant node pointer forward
            current = current.next;

            // Move the temp1 & temp2 node pointers forward for the 2 LLs
            // Check if they've reached the end of the LL
            if (temp1 != null) {
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                temp2 = temp2.next;
            }
        }
        // If there's still carry over left after both the LLs iteration are finished,
        // create a new last node containing carryOver value.
        if (carryOver != 0) {
            current.next = new Node(carryOver);
        }
        // TC - O(max(n1, n2)). SC - O(1) No extra space used(space used is to store result)
        return dummyNode.next;

    }
    /*
    private static int get_number_using_reverse_digits_LL(Node head) {
        // Eg: {2, 4, 5}. Number = 542
        Node temp = head;
        int count = 0;
        int number = 0;
        while (temp != null) {
            number = (int) (number + (temp.data * Math.pow(10, count)));
            count++;
            temp = temp.next;
        }
        return number;
    }

    private static Node get_node_elements_in_reverse_using_number(int number) {
        // Eg: number = 542. Node = {2, 4, 5}
        Node head = new Node(number % 10);
        Node temp = head;

        while (number / 10 != 0) {
            number = number / 10;
            int digit = number % 10;
            Node node = new Node(digit);
            temp.next = node;
            temp = temp.next;
        }
        return head;
    }
     */

    public static Node odd_even_LL(Node head) {
        // Given the head of a singly linked list, group all the nodes with odd indices
        // together followed by the nodes with even indices, and return the reordered
        // list.
        // The first node is considered odd, and the second node is even, and so on.
        // Note that the relative order inside both the even and odd groups should
        // remain as it was in the input.

        // Brute-Force Solution - TC - O(2n). SC - O(n): First iterate over all the even
        // indices of the LL, capture them in an array.O(n/2). Next, iterate over all
        // the odd indices of the LL & capture them in the same array. O(n/2). Now,
        // again start iterating from the head of the LL, this time start replacing
        // each node with the value from the array. O(n). TC - O(n/2) + O(n/2) + O(n).

        // Better Solution(my own approach)
        /*
        Node dummy_odd = new Node(-1);
        Node current_odd = dummy_odd;
        Node dummy_even = new Node(-2);
        Node current_even = dummy_even;

        Node temp = head;
        int index = 0;
        while (temp != null) {
            if (index % 2 == 0) {
                current_even.next = new Node(temp.data);
                current_even = current_even.next;
;            }
            else {
                current_odd.next = new Node(temp.data);
                current_odd = current_odd.next;
            }
            temp = temp.next;
            index++;
        }
        // How to join dummy_odd & dummy_even Nodes ?
        // Remove dummy_odd head(so you get rid of dummy Node & only have elements)
        Node head_dummy_odd = delete_head(dummy_odd);
        // Now, try to link the head of the new dummy odd LL with the tail of the
        // dummy_even LL
        current_even.next = head_dummy_odd;
        return dummy_even.next;
         */

        // Optimal Approach

        // If the LL is empty or if there's only 1 element
        if (head == null || head.next == null) {
            return head;
        }

        // Start iterating over all the Odd elements(at even indices) & Even elements(at
        // odd indices) simultaneously & start making connections among these 2 sets.
        Node odd = head;
        Node even = head.next;

        // Needed as we need to connect the last Odd element to the head of the Even
        // sub-LL, so we'll point here
        Node even_head = head.next;

        // If the even hasn't reached the end, odd wil also not reach the end as the
        // order is (Odd -> Even -> ....) ??
        while (even != null && even.next != null) {
            // Connect odd with the next subsequent Odd element
            odd.next = odd.next.next;
            odd = odd.next;

            // Connect even with the next subsequent Even element
            even.next = even.next.next;
            even = even.next;
        }

        // After both iterations are over, the last element of Odd should point to
        // the head of the Even sub-LL
        odd.next = even_head;
        return head;
    }

    /*
    private static Node delete_head(Node head) {

        if (head == null) {
            return head;
        }
        head = head.next;
        return head;
        // In Java, Garbage Collector makes sure to free up space when there's no reference
        // to data, it'll delete it.
    }
     */

    // Medium - do more dry runs of this func
    public static Node sort_LL_of_0s_1s_2s(Node head) {
        // Given the head of a singly linked list consisting of only 0, 1 or 2.
        // Sort the given linked list and return the head of the modified list.
        // Do it in-place by changing the links between the nodes without creating new nodes.

        // Brute-Force Solution - Data replacement. Count the number of 0s, 1s & 2s
        // by linearly iterating. Once the traversal is complete, do another traversal
        // and replace each node with first 0s, then 1s & finally 2s. TC - O(2n)


        // Optimal Solution

        // Edge Cases: If the head is null OR if it has only 1 element
        if (head == null || head.next == null) {
            return head;
        }

        Node dummy_node_0 = new Node(-1);
        // Used for traversal
        Node zero = dummy_node_0;
        Node dummy_node_1 = new Node(-1);
        // Used for traversal
        Node one = dummy_node_1;
        Node dummy_node_2 = new Node(-1);
        // Used for traversal
        Node two = dummy_node_2;

        Node temp = head;
        while (temp != null) {
            // Remember: We should NOT create new nodes.
            if (temp.data == 0) {
                zero.next = temp;
                zero = temp;
            } else if (temp.data == 1) {
                one.next = temp;
                one = temp;
            } else {
                two.next = temp;
                two = temp;
            }
            temp = temp.next;
        }

        // Once all the respective sub-LL of 0s, 1s & 2s is populated, their respective
        // pointers(dummy_node_x) will point to their respective last elements.
        // So, now link the last node of 0 with head of 1 & last node of 1 with head of 2.

        // If there are no 1's, then we want the dummy_0s pointer to point to
        // dummy_2s's head
        zero.next = (dummy_node_1.next != null) ? dummy_node_1.next :
                dummy_node_2.next;
        one.next = dummy_node_2.next;
        two.next = null;

        // TC - O(n). SC - O(1)
        return dummy_node_0.next;
    }

    // Medium - L + R = size + 1; Fast & Slow Pointers
    public static Node remove_Nth_node_from_end_of_list(Node head, int n) {

        // Brute-Force Solution

        /*
        // First, get the count of the LL
        int count = 0;
        Node temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // Edge Case: If LL is empty
        if (head == null) {
            return head;
        }

        // If there's only 1 element in LL
        if (head.next == null) {
            if (count == n) {
                return null;
            }
            else {
                return null;
            }
        }
        // If n = size of LL
        if (head != null && head.next != null && count == n) {
            head = head.next;
            return head;
        }

        // Now L + R = size + 1, so in order to delete the Nth node from the end, we
        // need to reroute (N+1)th node's (from end, R = N + 1) pointer.
        // So, we need to get to the L => size + 1 - R => (size - N)th node from beginning

        // L = (size - N)th node
        int result = count - n;
        Node itr = head;
        // Now, fetch the (count - n)th node
        while (itr != null) {
            result-=1;
            if (result == 0) {
                break;
            }
            itr = itr.next;

        }
        // Store the to_be_deleted node
        assert itr != null;
        Node deleted_node = itr.next;

        // Reroute the connection by skipping/deleting the nth node from end
        itr.next = itr.next.next;
        // TC - O(size) + O(size - n). Worst Case => ~ O(2n). SC - O(1)
        return head;
         */

        // Optimal Solution - Fast Pointer, Slow Pointer Approach

        Node fast = head;
        Node slow = head;
        // Move the fast pointer to n steps forward - a head start basically
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Edge Case: If n = size of LL, then just remove the head & return head.next
        if (fast == null) {
            head = head.next;
            return head;
        }

        // Since fast will have to be the last element
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        Node delete_node = slow.next;
        // Reroute the connection, skipping the node to be deleted
        slow.next = slow.next.next;

        // TC - O(length). SC - O(1)
        return head;
    }

    // Easy - LL In-Place Reversal(next, move current pointer to previous,
    // update previous, update current to next)
    public static Node reverse_a_LL_iterative(Node head) {


        // Brute-Force Solution - Iterate over the LL first. Store all the elements in
        // a Stack DS. Now, again iterate over the LL, this time replace the nodes with
        // stack's popped elements. LIFO principle.

        Node previous = null;
        Node current = head;
        Node next = head;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        // TC - O(n). SC - O(1)
        return previous;
    }

    // Easy - Have NO clue how this was solved - Understand it thoroughly
    public static Node reverse_a_LL_recursive(Node head) {
        // Edge Case: If LL is empty OR LL has only 1 element
        if (head == null || head.next == null) {
            return head;
        }

        Node new_head = reverse_a_LL_recursive(head.next);
        Node front = head.next;
        front.next = head;
        head.next = null;

        // TC - O(n). SC - O(n) as recursive stack space is used
        return new_head;
    }

    // Easy (NOT at all easy) - Try this ques 3-4 times
    public static boolean check_if_LL_is_palindrome(Node head) {
        // Brute-Force Solution - Take a Stack DS(LIFO). Iterate over the LL, & add
        // them into the stack. Now, again iterate over the LL, this time compare the
        // topmost element of the stack with the temp of LL & keep on iterating. If
        // everything matches(when stack is empty || temp == null), it's a palindrome,
        // else not. TC - O(2n). SC - O(n)

        // Optimal Approach - We'll divide the LL into 2 halves, we'll reverse the 2nd
        // half, & then we'll iterate both the 1st half & reversed 2nd half
        // simultaneously. If every node matches, then it's a palindrome.

        // Edge Case
        if (head == null || head.next == null) {
            return true;
        }

        // Find out the middle element of LL using TortoiseHare method/Hast-Slow Pointers
        Node middle_1 = middle_of_LL_1st_middle(head);

        // Now, from the next node of middle_1 onwards, find the reverse of 2nd half.
        Node new_head = reverse_a_LL_recursive(middle_1.next);

        // Point a start pointer to head
        Node first = head;
        Node second = new_head;

        while (second != null) {
            if (first.data != second.data) {
                reverse_a_LL_recursive(new_head);
                return false;
            }
            // Move 1 step forward each of the pointers
            first = first.next;
            second = second.next;
        }

        // Re-reverse the second half of the LL
        reverse_a_LL_recursive(new_head);
        return true;

        // TC - O(2n). SC - O(1).
    }

    // Easy - Tortoise-Hare method. Fast & Slow Pointers
    public static Node middle_of_LL_2nd_middle(Node head) {

        // Middle Node => [(N/2) + 1]
        Node fast = head;
        Node slow = head;

        // Optimal Solution - Tortoise-Hare Method. Fast & Slow Pointers.
        // Intuition - If fast pointer moves at twice the speed of the slow pointer, by
        // the time the fast pointer covers a distance 'd', the slow pointer would
        // cover a distance 'd/2' -> essentially the middle of the LL.

        // The below logic should run as long as fast is != null & as long as it hasn't
        // reached the end of LL
        while (fast != null && fast.next != null) {
            // Move slow pointer 1 step ahead
            slow = slow.next;
            // Move fast pointer 2 steps ahead
            fast = fast.next.next;
        }
        return slow;

    }

    public static Node middle_of_LL_1st_middle(Node head) {

        // Middle Node => [(N/2) + 1]
        Node fast = head;
        Node slow = head;

        // Optimal Solution - Tortoise-Hare Method. Fast & Slow Pointers.
        // Intuition - If fast pointer moves at twice the speed of the slow pointer, by
        // the time the fast pointer covers a distance 'd', the slow pointer would
        // cover a distance 'd/2' -> essentially the middle of the LL.

        // The below logic should run as long as fast is != null & as long as it hasn't
        // reached the end of LL
        while (fast.next != null && fast.next.next != null) {
            // Move slow pointer 1 step ahead
            slow = slow.next;
            // Move fast pointer 2 steps ahead
            fast = fast.next.next;
        }
        return slow;

    }

    // Medium - Optimal - NO clue how it was written ??
    public static Node add_1_to_LL(Node head) {

        // Brute-Force Solution (could write on my own)
        /*
        // TC for this recursive function - O(n)
        Node new_head = reverse_a_LL_recursive(head);
        Node temp = new_head;
        int carry_over = 1;
        // TC - O(n) for this while loop
        while (temp != null) {
            int sum = temp.data + carry_over;

            int digit = sum % 10;
            carry_over = sum / 10;

            temp.data = digit;
            // Check if you've reached the end of LL
            if (temp.next != null) {
                temp = temp.next;
            } else {
                break;
            }
        }

        if (carry_over != 0) {
            Node new_node = new Node(carry_over);
            temp.next = new_node;
        }

        // Now, re-reverse the newly formed LL after adding 1
        // TC - O(n) as now we're re-reversing
        Node new_added_head = reverse_a_LL_recursive(new_head);
        // TC - O(3n)
        return new_added_head;
        */

        // Optimal Solution - Use recursion(recursion has something called - Backtracking)
        // Again. NO clue how to write this recursive func
        int carry = helper_func(head);
        // If there's carry remaining, create a new Node
        if (carry == 1) {
            Node new_node = new Node(carry);
            new_node.next = head;
            return new_node;
        }
        // Else, return head
        // TC - O(n). SC - O(n)
        return head;
    }

    private static int helper_func(Node temp) {
        // Base Case: When we reach null, the carry over will be 1
        if (temp == null) {
            return 1;
        }
        int carry = helper_func(temp.next);
        temp.data = temp.data + carry;
        // If there's no carry
        if (temp.data < 10) {
            return 0;
        }
        // If temp.data > 10, take 0 as digit & return 1 as carry
        temp.data = 0;
        return 1;
    }

    // Easy - Optimal: NO clue how this was solved ??
    public static Node intersection_of_2_LL(Node head1, Node head2) {
        // Given the heads of two singly linked-lists headA and headB, return the node
        // at which the two lists intersect. If the two linked lists have no
        // intersection at all, return null.

        // Brute-Force Solution - Memorize all the nodes of LL1, then traverse the LL2,
        // if you find any of the nodes of LL1 while traversing LL2, return the 1st
        // common node.
        // HASHING.
        /*
        HashMap<Node, Integer> hashing = new HashMap<Node, Integer>();
        Node temp1 = head1;
        // TC - O(n1)
        while (temp1 != null) {
            hashing.put(temp1, 1);
            temp1 = temp1.next;
        }
        Node temp2 = head2;
        // TC of this loop - O(n2 * log(n2))
        while (temp2 != null) {
            // TC - of Map.containsKey() method - O(1): Best Case. O(log(n)): Worst Case
            if (hashing.containsKey(temp2)) {
                return temp2;
            }
            temp2 = temp2.next;
        }
        // If there's no common/intersection Node
        // Total TC - O(n1) + O(n2 * log(n2))
        return null;
        */

        // Difference in Length method - To compare bot the LL simultaneously, first
        // start the pointer of the longer LL n steps(LL_long - LL_short) forward, now
        // both the LLs are at the same position. Now, start comparing each of them
        // simultaneously.

        /*
        // Calculate length of LL1 - O(n1)
        Node temp1 = head1;
        int count_1 = 0;
        while (temp1 != null) {
            count_1++;
            temp1 = temp1.next;
        }

        // Calculate length of LL2 - O(n2)
        Node temp2 = head1;
        int count_2 = 0;
        while (temp2 != null) {
            count_2++;
            temp2 = temp2.next;
        }

        // TC - O(smaller) + O(2 * larger)
        if (count_1 > count_2) {
            return collision_point(head2, head1, count_1 - count_2);
        }
        else {
            return collision_point(head1, head2, count_2 - count_1);
        }
        */

        // Optimal Solution

        if (head1 == null || head2 == null) {
            return null;
        }
        Node temp1 = head1;
        Node temp2 = head2;

        // If, both the LLs are same, then the head will be the collision point
        while (temp1 != temp2) {
            // If they reach end of their respective LLs, redirect them to the head of
            // alternate LL
            temp1 = (temp1 == null) ? temp2 : temp1.next;
            temp2 = (temp2 == null) ? temp1 : temp2.next;

            // Collision point
            return temp1.next;
        }
        return null;
    }

    private static Node collision_point(Node smaller_head, Node larger_head, int difference) {
        Node temp_larger = larger_head;
        // Align the larger pointer in line with the smaller pointer
        while (difference > 0) {
            difference--;
            temp_larger = temp_larger.next;
        }

        Node temp_smaller = smaller_head;
        // Check if both temp_smaller & temp_larger match
        if (temp_larger != temp_smaller) {
            temp_smaller = temp_smaller.next;
            temp_larger = temp_larger.next;
        }
        // TC - O(difference: larger - smaller) + O(smaller: till null) = O(larger)
        return temp_smaller;


    }

    // Easy - HASHING, Tortoise-Hare Algorithm & Circular Races
    public static Boolean detect_loop_or_cycle_in_LL(Node head) {
        // Given head, the head of a linked list, determine if the linked list has a
        // cycle in it. There is a cycle in a linked list if there is some node in the
        // list that can be reached again by continuously following the next pointer.
        // Internally, pos is used to denote the index of the node that tail's next
        // pointer is connected to. Note that pos is not passed as a parameter.
        // Return true if there is a cycle in the linked list. Otherwise, return false.

        // Brute-Force Solution - HASHING
        /*
        Node temp = head;
        // Take a HashMap DS
        HashMap<Node, Integer> hashing = new HashMap<>();
        // Check important as it might be possible that there's NO loop/cycle
        while (temp != null) {
            if (hashing.containsKey(temp)) {
                return temp;
            } else {
                hashing.put(temp, 1);
            }
            temp = temp.next;
        }
        // If there's no loop
        return null;

        // But under typical conditions, where the hash function distributes keys
        // well and the load factor is properly managed, both .put() and
        // .containsKey() are amortized O(1).

        // TC - O(n * 2 * O(1)) -> n: iterating over LL, 2 - 2 map operations, both
        // having O(1)
        //  SC - O(n) as map might store n nodes in Worst Case
        */

        // Optimal Solution - Tortoise-Hare Algorithm

        Node slow = head;
        Node fast = head;

        // Make sure fast pointer doesn't reach null(even elements) & fast.next doesn't
        // reach null(odd elements) - for a linear LL
        while (fast != null && fast.next != null) {
            // slow hops 1 step forward
            slow = slow.next;
            // fast hops 2 steps forward
            fast = fast.next.next;

            // Intuition - In a circular loop, when 2 pointers with different speeds
            // (slow -> 1 step, fast -> 2 steps) traverse a circular path, the fast
            // pointer is bound to catch up on the slow pointer at some point &
            // overtake it as well. So, we're sure that the fast pointer would eventually
            // catch up with the slow pointer.
            if (fast == slow) {
                return true;
            }
        }

        // TC - O(n). SC - O(1)
        return false;

    }

    // Easy - Hashing(keep track of count of nodes when itr in HashMap),
    // Tortoise-Hare Algo make another circular round to find length
    public static int length_of_loop_in_LL(Node head) {

        // Brute-Force Solution - HASHING
        /*
        Node temp = head;
        // Initialize a HashMap with key as Node & value as count/index starting from 1.
        HashMap<Node, Integer> hashing = new HashMap<>();

        int count = 1;
        while (temp != null) {
            // If we encounter a particular node once again, the difference b/w count
            // now & the count stored in hashing map of the node 1st time -> length of
            // loop
            if (hashing.containsKey(temp)) {
                int length_of_loop = count - hashing.get(temp);
                return length_of_loop;
            }
            else {
                hashing.put(temp, count);
            }
            temp = temp.next;
            count++;
        }
        // return 0 if there's no loop
        // TC - O(n * 2 * O(1)). SC - O(n)
        return 0;
        */

        // Optimal Solution - Tortoise-Hare Algorithm

        Node slow = head;
        Node fast = head;

        // Make sure fast pointer doesn't reach null(even elements) & fast.next doesn't
        // reach null(odd elements) - for a linear LL
        while (fast != null && fast.next != null) {
            // slow hops 1 step forward
            slow = slow.next;
            // fast hops 2 steps forward
            fast = fast.next.next;

            // Intuition - In a circular loop, when 2 pointers with different speeds
            // (slow -> 1 step, fast -> 2 steps) traverse a circular path, the fast
            // pointer is bound to catch up on the slow pointer at some point &
            // overtake it as well. So, we're sure that the fast pointer would eventually
            // catch up with the slow pointer.
            if (fast == slow) {
                return findLengthOfLoopFromCommonNode(slow, fast);
            }
        }

        // If there's no loop in LL
        // TC - O(n). SC - O(1)
        return 0;
    }

    private static int findLengthOfLoopFromCommonNode(Node slow, Node fast) {
        int count = 0;
        while (slow != fast) {
            fast = fast.next;
            count++;
        }
        return count;
    }

    //  - I should've been able to solve this, but couldn't
    public static Node delete_middle_of_LL(Node head) {
        // Aim: Reaching the middle is of NO USE for us. We need to somehow reach the
        // node just before the middle node of the LL, in that way we can reroute the
        // connections to delete the middle node.

        // Brute-Force Solution -  Try reaching to the [N/2]th node. First iterate the
        // entire LL to find count, then calc [N/2], then iterate till [N/2], reduce
        // count & reach the [N/2]th node. Now, reroute the links. TC - O(3/2 * n)

        // Tortoise-Hare Modified Algorithm

        // Edge Case: If LL is empty OR LL has only 1 element
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        // Move the fast pointer 2 steps ahead, BUT SKIP the slow pointer movement for
        // 1 iteration
        fast = fast.next.next;

        // Now, continue with iterations of both pointers as usual
        // Iterate until the fast pointer is NOT null AND it is at the last element
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // After the while loop, slow pointer will point to an element just preceding
        // the middle node. So, reroute the connections now
        slow.next = slow.next.next;
        // TC - O(n) as we're doing it in one pass
        return head;
    }

    public static Node starting_pt_of_loop_in_LL(Node head) {
        // Brute-Force Solution - HASHING
        /*
        Node temp = head;
        // Take a HashMap
        HashMap<Node, Integer> hashing = new HashMap<>();

        while (temp != null) {
            // If you encounter the same Node again(the first repeating node will be
            // the starting node itself)
            if (hashing.containsKey(temp)) {
                return temp;
            } else {
                hashing.put(temp, 1);
            }
            temp = temp.next;
        }
        // If there's no loop in LL. TC - O(n * 2 * O(1)). SC - O(n)
        return null;
        */

        // Optimal Solution - 1) Detecting the loop (Tortoise-Hare Algorithm)
        // 2) Finding the starting pt - Start 1 pointer from outside the loop, & other
        // pointer from inside the loop. They're bound to meet each other eventually at
        // the starting node.

        // Edge Case
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        // Make sure fast pointer doesn't reach null(even elements) & fast.next doesn't
        // reach null(odd elements) - for a linear LL
        while (fast != null && fast.next != null) {
            // slow hops 1 step forward
            slow = slow.next;
            // fast hops 2 steps forward
            fast = fast.next.next;

            // Intuition - Keep iterating both fast (from inside the loop) & slow (from
            // outside the loop) pointers simultaneously. They're bound to meet
            // each other if there's a loop in LL.
            if (fast == slow) {
                // Reassign the slow pointer to head, & again start iterating
                slow = head;
                while (fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                // return the starting pt - either slow/fast
                return slow;
            }
        }
        // If there's no loop
        return null;
    }

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

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    Node(int data) {
        this.data = data;
        this.next = null;
    }

}

