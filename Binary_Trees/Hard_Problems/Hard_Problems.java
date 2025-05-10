package Binary_Trees.Hard_Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hard_Problems {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
    }

    public static List<Integer> root_to_node_path_in_binary_tree(TreeNode root,
                                                                 Integer element) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        root_to_node_path_in_binary_tree_helper(root, element, list);
        return list;
    }

    // Medium - Perform more DRY RUNS(a bit difficult)
    private static boolean root_to_node_path_in_binary_tree_helper(TreeNode root, Integer element,
                                                        List<Integer> list) {
        // If node is null, return false
        if (root == null) {
            return false;
        }
        list.add(root.data);
        if (root.data == element) {
            return true;
        }

        // IMPORTANT - This recursion will keep going to the left & right nodes. If any node
        // is the leaf node & its != element, this block will NOT execute, so the node
        // would be removed from the list(line 47) & return FALSE.
        if (root_to_node_path_in_binary_tree_helper(root.left, element, list) ||
                root_to_node_path_in_binary_tree_helper(root.right, element, list)) {
            return true;
        }
        list.remove(list.size() - 1);
        return false;

    }

    // Medium - Found difficult to code
    public static TreeNode lowest_common_ancestor_LCA(TreeNode root, TreeNode node1,
                                                      TreeNode node2) {
        // Brute-Force Solution: Apply node to path previous function to both nodes.
        // TC - O(N) + O(N). SC - O(N) + O(N).

        // Base Case
        if (root == null) {
            return null;
        }
        // Base Case
        if (root == node1 || root == node2) {
            return root;
        }

        TreeNode left = lowest_common_ancestor_LCA(root.left, node1, node2);
        TreeNode right = lowest_common_ancestor_LCA(root.right, node1, node2);

        //
        if (left == null) {
            return right;
        }
        else if (right == null) {
            return left;
        }
        // Both Left & Right are not null, we found out Lowest Common Ancestor
        else {
            return root;
        }
        // TC - O(N). SC - O(N) - for skewed trees

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

class Pair<K, V> {
    public K key;
    public V value;

    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public Pair(TreeNode root, Pair<Integer, Integer> pair) {

    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }
}
