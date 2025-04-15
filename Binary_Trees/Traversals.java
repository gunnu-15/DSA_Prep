package Binary_Trees;

import com.sun.source.tree.Tree;

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

        List<List<Integer>> wrapList = levelorderTraversal(root_l0);
        for (List<Integer> list : wrapList) {
           list.stream().forEach(i -> System.out.println(i + " "));
        }
    }




    // L R order is common b/w all 3, only the Root position varies

    // Preorder Traversal DFS - Ro L R - Root is Pre, at the start
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

    // Postorder Traversal DFS - Ro L R - Root is Post, at the end
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
    // Medium
    public static List<List<Integer>> levelorderTraversal(TreeNode root) {
        // Here, we'll take 2 DS -  Queue & List<List<>>
        // We'll first take the root tree, store it. Then, we store its left & right tree
        // in the queue. Once it's done, we'll store the root tree level in List<<List<>>
        // & remove the root node from the queue. We'll continue this process

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
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



}

class TreeNode {

    // Data Component
    int data;

    // Pointers to children - left & right using reference variables
    TreeNode left;
    TreeNode right;

    // Empty constructor
    public TreeNode() {}

    // Define a constructor which takes in a key(value) & assigns it to its data
    public TreeNode(int key) {
        this.data = key;
        this.left = null;
        this.right = null;
    }

    // Define constructor that takes in all the params
    public TreeNode(int key, TreeNode left, TreeNode right) {
        this.data = key;
        this.left = left;
        this.right = right;
    }
}
