package Binary_Trees.Traversals;

import java.util.*;

public class Traversals {


    public static void main(String[] args) {
        TreeNode root_l0 = new TreeNode(1);
        root_l0.left = new TreeNode(2);
        root_l0.right = new TreeNode(3);
        root_l0.left.left = new TreeNode(4);
        root_l0.left.right = new TreeNode(5);
        root_l0.left.right.left = new TreeNode(6);
        root_l0.left.right.right = new TreeNode(7);
        root_l0.right.right = new TreeNode(8);
        root_l0.right.right.left = new TreeNode(9);

        List<Integer> wrapList = iterative_preorderTraversal(root_l0);
        for (Integer i : wrapList) {
            System.out.print(i + " ");
        }
    }


    // L R order is common b/w all 3, only the Root position varies

    // Preorder Traversal DFS - Root L R - Root is Pre, at the start
    public static void preorderTraversal(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }

        list.add(root.data);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
        // TC - O(N), N -> number of nodes. Auxiliary SC - O(N)
    }

    public static List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }

    // Inorder Traversal DFS - L Root R - Root is In between
    public static void inorderTraversal(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root.data);
        inorderTraversal(root.right, list);
        // TC - O(N), N -> number of nodes. Auxiliary SC - O(N)
    }

    public static List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    // Postorder Traversal DFS - L R Root - Root is Post, at the end
    public static void postorderTraversal(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.data);
        // TC - O(N), N -> number of nodes. Auxiliary SC - O(N)
    }

    public static List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }

    // Level order Traversal
    // Medium - Queue DS + List<List<>> to store level-wise traversal
    public static List<List<Integer>> levelorderTraversal(TreeNode root) {
        // Here, we'll take 2 DS -  Queue & List<List<>>
        // We'll first take the root tree, store it. Then, we store its left & right tree
        // in the queue. Once it's done, we'll store the root tree level in List<<List<>>
        // & remove the root node from the queue. We'll continue this process

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // Store traversal level-wise
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if (root == null) {
            return wrapList;
        }
        // offer() - Inserts the specified element into this queue if it is possible
        // to do so
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();

            for (int i = 0; i < levelNum; i++) {
                // peek() - Retrieves, but does not remove, the head of this queue, or
                // returns null if this queue is empty

                // Add the left node of the root node
                if (queue.peek() != null && queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }
                // Add the right node of the root node
                if (queue.peek() != null && queue.peek().right != null) {
                    queue.offer(queue.peek().right);
                }
                // poll() - Retrieves and removes the head of this queue, or returns null if
                // this queue is empty

                // Remove the root node after adding it into subList
                subList.add(Objects.requireNonNull(queue.poll()).data);
            }
            wrapList.add(subList);
        }
        return wrapList;

        // TC - O(N), N -> number of nodes. Auxiliary SC - O(N)
    }

    // Easy - Iterative using Stack
    public static List<Integer> iterative_preorderTraversal(TreeNode root) {
        // In Iterative method -> we'll use Stack
        // Preorder -> Root L R.
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> preorder_list = new ArrayList<>();

        // Edge Case:
        if (root == null) {
            return preorder_list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            preorder_list.add(stack.peek().data);
            stack.pop();
            // Reason: We need to 1st access the L node & then only the R node. By
            // pushing root.right 1st & then root.left, the top of stack will contain
            // root.left(LIFO)
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
            if (!stack.isEmpty()) {
                root = stack.peek();
            }
        }
        // TC - O(N). SC - O(N)
        return preorder_list;

    }

    // NO CLUE WHATSOEVER
    public static List<Integer> iterative_inorderTraversal(TreeNode root) {
        // In Iterative method -> we'll use Stack
        // Inorder -> L Root R.
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> inorder_list = new ArrayList<>();

        TreeNode node = root;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                node = stack.pop();
                inorder_list.add(node.data);
                node = node.right;
            }
        }
        return inorder_list;
    }

    // Easy - Iterative using 2 Stacks. NO CLUE WHATSOEVER
    public static List<Integer> iterative_postorderTraversal(TreeNode root) {
        // In Iterative method of Post order -> we'll use 2 Stacks
        // Postorder -> L R Root

        /*
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<Integer> postorder_list = new ArrayList<>();

        // Edge Case
        if (root == null) {
            return postorder_list;
        }

        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.peek();
            stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            postorder_list.add(stack2.peek().data);
            stack2.pop();
        }
        // TC - O(N). SC - O(2N)
        return postorder_list;
        */

        // Better Solution - NO CLUE WHATSOEVER
        TreeNode curl = root;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> postorder_list = new ArrayList<>();
        // Keep going to the LEFT in the tree until you encounter NULL
        if (curl != null) {
            stack.push(curl);
            curl = curl.left;
        } // Once you encounter Null, go RIGHT
        else {
            TreeNode temp = stack.peek().right;
            // If the RIGHT reaches an end, i.e. NULL
            if (temp == null) {
                temp = stack.pop();
                stack.pop();
                postorder_list.add(temp.data);
                // Go back & check if temp is stack.peek()'s RIGHT element. If so,
                // reassign temp as stack's top element, & add it to List
                while (!stack.isEmpty() && temp == stack.peek().right) {
                    temp = stack.peek();
                    stack.pop();
                    postorder_list.add(temp.data);
                }
            }// Else, if RIGHT doesn't reach end,
            else {
                curl = temp;
            }
        }
        // TC - O(2N). SC - O(N)
        return postorder_list;
    }

    // Perform more DRY RUNS of this logic
    public static List<Integer> pre_in_post_order_traversals_in_1_traversal(TreeNode root) {

        // We'll keep ONLY 1 Stack - we'll store <TreeNode, number>
        // Rules:
        // If num == 1, preOrder list. Push that number by doing ++. If there exists a
        // left, enter the left
        // If num == 2, inOrder list. Push that number by ++. If there exists a right,
        // enter the right.
        // If num == 3, postOrder list.


        // Map.Entry is IMMUTABLE, cannot modify it. So, we'll create a simple
        // mutable Pair class
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));
        List<Integer> preOrder_list = new ArrayList<>();
        List<Integer> inOrder_list = new ArrayList<>();
        List<Integer> postOrder_list = new ArrayList<>();

        // Edge Case
        if (root == null) {
            return null;
        }

        while (!stack.isEmpty()) {
            Pair pair = stack.pop();

            // This is part of Preorder.
            if (pair.getValue() == 1) {
                preOrder_list.add(pair.node.data);
                // Increment value by 1.
                pair.value++;
                stack.push(pair);
                // Push the LEFT side of tree
                if (pair.node.left != null) {
                    stack.push(new Pair(pair.node.left, 1));
                }

            }

            // This is part of Inorder
            else if (pair.getValue() == 2) {
                inOrder_list.add(pair.node.data);
                // Increment value by 1.
                pair.value++;
                stack.push(pair);
            }
            // Push the RIGHT side of tree
            if (pair.node.right != null) {
                stack.push(new Pair(pair.node.right, 1));
            }

            // This is part of Postorder. Don't push it back again
            else {
                postOrder_list.add(pair.node.data);
            }
            // TC - O(3N). SC - O(N)
        }
        return preOrder_list; // OR inOrder_list OR postOrder_list
    }
}

class TreeNode {

    // Data Component
    int data;

    // Pointers to children - left & right using reference variables
    TreeNode left;
    TreeNode right;

    // Empty constructor
    public TreeNode() {
    }

    // Define a constructor which takes in a key(value) & assigns it to its data
    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Define constructor that takes in all the params
    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class Pair {
    public TreeNode node;
    public Integer value;

    public Pair(TreeNode key, Integer value) {
        this.node = key;
        this.value = value;
    }

    public TreeNode getKey() {
        return this.node;
    }

    public int getValue() {
        return (int) this.value;
    }
}
