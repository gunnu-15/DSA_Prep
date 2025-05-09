package Binary_Trees.Medium_Problems;

import com.sun.source.tree.Tree;

import java.util.*;

public class Medium_Problems {

    public static void main(String[] args) {
        TreeNode root_l0 = new TreeNode(1);
        root_l0.left = new TreeNode(2);
        root_l0.right = new TreeNode(5);
        root_l0.left.left = new TreeNode(3);
        root_l0.left.right = new TreeNode(4);
        root_l0.right.left = new TreeNode(4);
        root_l0.right.right = new TreeNode(3);


//        List<Integer> wrapList = left_right_view_of_binary_tree_iterative(root_l0);
//        for (Integer integer : wrapList) {
//            System.out.print(integer + " ");
//        }

        boolean isSymmetric = symmetric_binary_tree(root_l0);
        System.out.println(isSymmetric);
    }

    // Easy - Perform DRY RUNS
    public static int find_height_maximum_depth(TreeNode root) {
        // Recursive OR Level Order

        // Recursion - 1 + Max(left, right)

        // Edge Case
        if (root == null) {
            return 0;
        }

        int left = find_height_maximum_depth(root.left);
        int right = find_height_maximum_depth(root.right);
        // TC - O(N). SC - O(N) - worst case
        return 1 + Math.max(left, right);
    }

    // Medium
    public static int check_for_balanced_binary_tree(TreeNode root) {
        // Balanced BT - for every node, ht(left) - ht(right) <= 1

        // Brute-Force Solution
        /*
        if (root == null) {
            return null;
        }
        int left_height = find_height_maximum_depth_of_binary_tree(root.left);
        int right_height = find_height_maximum_depth_of_binary_tree(root.right);

        // If |left_height - right_height| > 1, NOT balanced
        if (Math.abs(right_height - left_height) > 1) {
            return false;
        }
        // Keep going to the next Left & Right nodes

        Boolean left_check = check_for_balanced_binary_tree(root.left);
        Boolean right_check = check_for_balanced_binary_tree(root.right);

        // If any node, in the bottom, says that it's not a Balanced tree, return false
        if (!left_check || !right_check) {
            return false;
        }
        // else, return it's a Balanced Tree.
        // TC - O(N) * O(N) ~ O(N^2)
        return true;
        */

        // Better Solution
        // Edge Case
        if (root == null) {
            return 0;
        }

        int left = find_height_maximum_depth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = find_height_maximum_depth(root.right);
        if (right == -1) {
            return -1;
        }
        // If |left_height - right_height > 1|, NOT a balanced tree
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        // TC - O(N). SC - O(N) - worst case for skewed tree
        return 1 + Math.max(left, right);
    }

    // Medium - Some Questions ??
    public static int diameter_of_binary_tree(TreeNode root) {
        // The Diameter of a Binary Tree - Longest distance between any two nodes of
        // that tree. This path may or may not pass through the root.

        /*
        int max = 0;
        // Edge Case
        if (root == null) {
            // This return should prompt you to think about recursion
            return;
        }
        // If node is null, returns 0
        int left_height = find_height_maximum_depth(root.left);
        int right_height = find_height_maximum_depth(root.right);
        max = Math.max(max, left_height + right_height);
        diameter_of_binary_tree(root.left);
        diameter_of_binary_tree(root.right);
        // TC - O(n^2)
        */

        // Better Solution - Understand LOGIC of finding height of BT
        // Perform DRY RUNS. Why taking ARRAY works but not variable here ??
        int[] diameter = new int[1];
        max_depth_height_modified(root, diameter);
        return diameter[0];
    }

    private static int max_depth_height_modified(TreeNode root, int[] diameter) {
        // Edge Case
        if (root == null) {
            return 0;
        }

        int left_height = max_depth_height_modified(root.left, diameter);
        int right_height = max_depth_height_modified(root.right, diameter);
        // Extra logic here - Updating max value at every node
        diameter[0] = Math.max(diameter[0], left_height + right_height);

        // TC - O(N). SC - O(N) - worst case
        return 1 + Math.max(left_height, right_height);
    }

    // Easy
    public static Boolean check_if_2_trees_are_identical(TreeNode root1, TreeNode root2) {
        // To check if 2 trees are identical - Perform any of the traversals -
        // Pre/In/Post order traversals

        // Edge Case
        if (root1 == null || root2 == null) {
            return root1 == root2;
        }
        // Check the root node, its left node & right node. Do it recursively
        return (root1.data == root2.data) &&
                check_if_2_trees_are_identical(root1.left, root2.left) &&
                check_if_2_trees_are_identical(root1.right, root2.right);
    }

    // Easy - Toggle switch to add elements in subList based on toggle
    public static List<List<Integer>> zigzag_traversal_of_binary_tree(TreeNode root) {
        List<List<Integer>> wrapList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        boolean left_to_right = true;

        // Edge Case
        if (root == null) {
            return wrapList;
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.peek();

                // IMPORTANT - One CANNOT add an element if index > subList.size().
                // This restriction applies to both ArrayLists & LinkedLists. Java
                // throws IndexOutOfBoundsException
                int index = (left_to_right) ? i : (size - 1 - i);
                if (node != null) {
                    if (left_to_right) {
                        subList.addLast(node.data);
                    } else {
                        subList.addFirst(node.data);
                    }
                }
                if (queue.peek() != null && queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }
                if (queue.peek() != null && queue.peek().right != null) {
                    queue.offer(queue.peek().right);
                }
                queue.poll();
            }
            // Toggle the switch to alternate b/w left & right movements
            left_to_right = !left_to_right;
            wrapList.add(subList);
        }
        return wrapList;
    }

    // Medium - Found Difficult to code it
    public static List<Integer> boundary_traversal_of_binary_tree_ACW(TreeNode root) {

        // Approach: Left boundary excluding Leaf nodes
        // Now Leaf nodes - nodes with no child nodes
        // Right boundary in Reverse

        // Start by going left, if there's no left, go Right, until you encounter a Leaf
        // Node.
        // For Leaf Nodes - do In Order Traversal
        // For Right boundary in Reverse -  Start by going Right, if there's no Right,
        // go Left. Then add all the elements in the reverse order in a Stack

        ArrayList<Integer> answer = new ArrayList<>();
        // If the node is NOT a leaf node
        if (!isLeaf(root)) {
            answer.add(root.data);
        }
        // Call the Left boundary func
        addLeftBoundary(root, answer);
        // Call the Leaf Nodes func
        addLeaves(root, answer);
        // Call the Right boundary func
        addRightBoundary(root, answer);

        return answer;
    }

    private static void addLeftBoundary(TreeNode root, List<Integer> result) {
        // Start with root's Left.
        TreeNode left = root.left;
        // Keep going Left until there's no left node to go to
        while (left != null) {
            // Don't include Leaf Nodes
            if (!isLeaf(left)) {
                result.add(left.data);
            }
            // Go to subsequent Left nodes, if present
            if (left.left != null) {
                left = left.left;
            }
            // Else, go to node's Right
            else {
                left = left.right;
            }
        }
    }

    private static void addRightBoundary(TreeNode root, List<Integer> result) {

        List<Integer> temp = new ArrayList<>();
        // Start with root's Right.
        TreeNode right = root.right;
        // Keep going Right until there's no Right node to go to
        while (right != null) {
            // Don't include Leaf Nodes
            if (!isLeaf(right)) {
                temp.add(right.data);
            }
            // Go to subsequent Right nodes, if present
            if (right.right != null) {
                right = right.right;
            }
            // Else, go to node's Left
            else {
                right = right.left;
            }
        }

        // Now, we need to add reverse the temp List as we want to traverse Right nodes
        // in reverse
        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
        }
    }

    private static void addLeaves(TreeNode root, List<Integer> result) {
        // If you encounter Leaf nodes
        if (isLeaf(root)) {
            result.add(root.data);
            return;
        }
        // Else, keep going to the left OR to the right of the node, until you
        // encounter the Leaf node
        if (root.left != null) {
            addLeaves(root.left, result);
        }
        if (root.right != null) {
            addLeaves(root.right, result);
        }

    }

    private static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    // Hard - Verticals(X-axis), Levels(Y-axis). Queue<Node, <Vertical, Level>>
    // Map<Vertical, Map<Level, Node>> to handle multiple nodes at same level
    public static List<List<Integer>> vertical_order_traversal_of_binary_tree(TreeNode root) {
        // Verticals: X-axis. Levels: Y-axis

        // Use any Traversal to assign (Vertical, Level) to each node
        // For every level, there might be multiple nodes on a vertical - to sort them,
        // use a MultiSet/PriorityQueue/List which is sorted(to tackle repeated values case)

        // Map to store nodes based on
        // vertical and level information
        Map<Integer, Map<Integer, TreeSet<Integer>>> nodes = new TreeMap<>();

        // Queue for BFS/Level order traversal, each
        // element is a pair containing node
        // and its vertical and level information
        Queue<Pair<TreeNode, Pair<Integer, Integer>>> queue = new LinkedList<>();

        // Push the root node with initial vertical
        // and level values (0, 0)
        queue.add(new Pair<>(root, new Pair<>(0, 0)));

        // BFS/Level Order traversal
        while (!queue.isEmpty()) {
            // Retrieve the node and its vertical
            // and level information from
            // the front of the queue
            Pair<TreeNode, Pair<Integer, Integer>> p = queue.poll();
            TreeNode node = p.getKey();

            // Extract the vertical and level information
            // x -> vertical
            int x = p.getValue().getKey();
            // y -> level
            int y = p.getValue().getValue();

            // Insert the node value into the
            // corresponding vertical and level
            // in the map
            nodes.computeIfAbsent(x, k -> new TreeMap<>())
                    .computeIfAbsent(y, k -> new TreeSet<>())
                    .add(node.data);

            // Process left child
            if (node.left != null) {
                queue.add(new Pair(node.left, new Pair(x - 1, y + 1)));
            }

            // Process right child
            if (node.right != null) {
                queue.add(new Pair(node.right, new Pair(x + 1, y + 1)));
            }
        }

        // Prepare the final result list
        // by combining values from the map
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Map<Integer, TreeSet<Integer>>> entry : nodes.entrySet()) {
            List<Integer> col = new ArrayList<>();
            for (TreeSet<Integer> set : entry.getValue().values()) {
                // Insert node values
                // into the column list
                col.addAll(set);
            }
            // Add the column list
            // to the final result
            ans.add(col);
        }
        return ans;
    }

    // Hard - Top View would be 1st node of all the Verticals
    public static List<Integer> top_view_of_binary_tree(TreeNode root) {
        // Vertical Lines Concept - the 1st node of each vertical line(X-axis) will
        // comprise the Top View.

        // Queue - store initial node & vertical number
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        // Map DS - stores line(vertical) & 1st node of that vertical(sorted map)
        // TreeMap: Stores <K,V> pairs sorted by keys in natural order
        Map<Integer, Integer> map = new TreeMap<>();

        List<Integer> result = new ArrayList<>();

        // Edge Case
        if (root == null) {
            return result;
        }

        // To traverse the whole BT - use Level Order Traversal
        // Add root node
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            int vertical = (int) pair.getValue();
            TreeNode node = (TreeNode) pair.getKey();
            // Ensure that if there's no entry in the map corresponding to a
            // particular level, then only add entry into the map
            if (map.get(vertical) == null) {
                map.put(vertical, node.data);
            }
            // Go to the LEFT of the node => vertical = vertical - 1
            if (node.left != null) {
                queue.add(new Pair<>(node.left, vertical - 1));
            }
            // Go to the RIGHT of the node => vertical = vertical + 1
            if (node.right != null) {
                queue.add(new Pair<>(node.right, vertical + 1));
            }
        }

        // Since the entries of <K, V> in TreeMap are sorted by keys in natural order
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        // TC - O(N). SC - O(N)
        return result;
    }

    // Hard - Bottom View would be last node of all the Verticals
    public static List<Integer> bottom_view_of_binary_tree(TreeNode root) {
        // Vertical Lines Concept - the last node of each vertical line(X-axis) will
        // comprise the Bottom View.

        // Queue - store initial node & vertical number
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        // Map DS - stores line(vertical) & last node of that vertical(sorted map)
        // TreeMap: Stores <K,V> pairs sorted by keys in natural order
        Map<Integer, Integer> map = new TreeMap<>();

        List<Integer> result = new ArrayList<>();

        // Edge Case
        if (root == null) {
            return result;
        }

        // To traverse the whole BT - use Level Order Traversal
        // Add root node
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            int vertical = (int) pair.getValue();
            TreeNode node = (TreeNode) pair.getKey();

            // Add every node to the map. This way, the last node of every vertical will
            // eventually be in the map as all the other above nodes will be overridden
            // when adding
            map.put(vertical, node.data);
            // Go to the LEFT of the node => vertical = vertical - 1
            if (node.left != null) {
                queue.add(new Pair<>(node.left, vertical - 1));
            }
            // Go to the RIGHT of the node => vertical = vertical + 1
            if (node.right != null) {
                queue.add(new Pair<>(node.right, vertical + 1));
            }
        }

        // Since the entries of <K, V> in TreeMap are sorted by keys in natural order
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        // TC - O(N). SC - O(N)
        return result;
    }

    // Medium - Last node of every level - Right View
    // First node of every level - Left View
    public static List<Integer> left_right_view_of_binary_tree_iterative(TreeNode root) {
        // Recursive Solution: TC - O(N). SC - O(N) - height of tree.

        List<Integer> finalList = new ArrayList<>();
        // Edge Case
        if (root == null) {
            return finalList;
        }

        // Iterative Solution: Level Order Traversal

        List<List<Integer>> resultList = new ArrayList<>();
        // Queue to perform Level Order Traversal
        Queue<TreeNode> queue = new LinkedList<>();
        // Add root node
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // Taking LinkedList instead of ArrayList as we need to add the subList
            // elements in the reverse order(traversing each level from RIGHT)
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.peek();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                // Adding the node element from the last as we need REVERSE order of elements
                // addFirst() - adds each element at the front of the LinkedList
                subList.addFirst(node.data);
                queue.poll();
            }
            // Add subList to final resultList
            resultList.add(subList);
        }


        // Now fetch the 1st element of each subList from the resultList
        for (List<Integer> subList : resultList) {
            finalList.add(subList.get(0));
        }
        return finalList;
    }

    // Medium - Reverse PreOrder Recursion with Level
    public static void right_view_of_binary_tree_recursive(TreeNode root,
                                                           Integer level,
                                                           List<Integer> result) {
        // Recursion - Reverse PreOrder Recursion - Root R L(REVERSE of L R)

        // DS to store nodes/elements

        // Base Condition
        if (root == null) {
            return;
        }
        // Approach/Logic - Since we're going RIGHT, RIGHT, RIGHT 1st, we'll add the
        // rightmost nodes at each level. Once, we'll start going LEFT, LEFT, LEFT,
        // the size of DS != level as there would already be nodes in DS. So, nothing
        // gets added from LEFT side into the DS.
        if (level == result.size()) {
            result.add(root.data);
        }
        // First we go RIGHT, RIGHT, RIGHT, ...
        right_view_of_binary_tree_recursive(root.right, level + 1, result);
        // Then we go LEFT, LEFT, LEFT, ...
        right_view_of_binary_tree_recursive(root.left, level + 1, result);
        // In both cases, level would increase by 1
    }

    public static List<Integer> right_view_of_binary_tree_recursive(TreeNode root,
                                                                    Integer level) {
        List<Integer> result = new ArrayList<>();
        right_view_of_binary_tree_recursive(root, 0, result);
        return result;
    }

    // Easy - Mirror Property => Left -> Right. Right -> Left
    public static boolean symmetric_binary_tree(TreeNode root) {
        // Draw a Mirror at the center
        // Mirror Property => Left looks -> Right and Right looks -> Left

        // For LEFT half of BT, perform PreOrder Traversal => Root L R
        // For RIGHT half of BT, perform REVERSE PreOrder Traversal => Root R L

        // Edge Case
        if (root == null) {
            return true;
        }
        // Start by passing Root's Left & Right node to Helper function
        return symmetric_binary_tree_helper(root.left, root.right);
        // TC - O(N). SC - O(N)


    }

    private static boolean symmetric_binary_tree_helper(TreeNode left, TreeNode right) {
        // If either of the left or right node is Null, check if both are equal OR not
        if (left == null || right == null) {
            return left == right;
        }
        // If corresponding nodes don't match, straightaway return false
        if (left.data != right.data) {
            return false;
        }
        // If both nodes match, continue comparison by going Left's Left -> Right's Right
        return symmetric_binary_tree_helper(left.left, right.right) &&
        // Continue comparison by going Left's Right -> Right's Left
        symmetric_binary_tree_helper(left.right, right.left);

        // && -> BOTH sets of comparisons should match, then only it's symmetric, else NOT
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
