package Binary_Search_Trees.Concepts;

public class Concepts {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);

        int k_smallest = kth_smallest_largest_element_in_BST(root, 1);
        System.out.println(k_smallest);
    }

    // For every given node in a Binary Search Tree if a left or a right child exists then:
    // Left Child < Node < Right Child
    // Left subtree(.) should itself be a BST
    // Right subtree(.) should itself be a BST

    // For duplicates - (L <= N < R). Have the duplicate node to left of original node &
    // then rest of BT. OR Use HASHING <Node value, frequency> in a map

    // Why BST? - Heights of BT are log 2 (N) & not O(N) (in BT)
    // Search in BT - O(N) - Pre/In/Post order Traversals

    public static TreeNode search_in_a_BST(TreeNode root, int value) {
        // Almost every BST is of height O(log 2 (N))
        // L < Node < R

        while (root != null && root.val != value) {
            // If value is < node, go LEFT, else go RIGHT
            root = value < root.val ? root.left : root.right;
        }
        return root;
    }

    public static int ceil_in_a_BST(TreeNode root, int number) {
        // Floor(Max no <= the number) -> number -> Ceil(Min no >= the number)

        // Take a variable(ceil) which stores the min no >= the number. Keep updating
        // this ceil whenever you encounter a node.val < ceil & node.val > number
        int ceil = -1;
        while (root != null) {
            // If node = number, return the node val
            if (root.val == number) {
                ceil = root.val;
                return ceil;
            }
            // If node > number, search LEFT to find a smaller node.
            if (root.val > number) {
                // IMPORTANT - What if there's no node = number? In that case we need
                // to get the next least number greater than number itself. So, we'll
                // update ceil as the node itself in case there's no matching node with
                // the number
                ceil = root.val;
                root = root.left;
            }
            // If node < number, go RIGHT
            else {
                root = root.right;
            }
        }
        // TC - O(log 2 (N))
        return ceil;
    }

    public static int floor_in_a_BST(TreeNode root, int number) {
        // Floor(Max no <= the number) -> number -> Ceil(Min no >= the number)

        // Take a variable(floor) which stores the MAX no <= the number. Keep updating
        // this floor whenever you encounter a node.val > floor & node.val < number
        int floor = -1;
        while (root != null) {
            // If node = number, return the node val
            if (root.val == number) {
                floor = root.val;
                return floor;
            }
            // If node > number, search LEFT to find a smaller node.
            if (root.val > number) {
                root = root.left;
            }
            // If node < number, go RIGHT
            else {
                // IMPORTANT - What if there's no node = number? In that case we need
                // to get the next MAX number < number itself. So, we'll
                // update floor as the node itself in case there's no matching node with
                // the number
                floor = root.val;
                root = root.right;
            }
        }
        // TC - O(log 2 (N))
        return floor;
    }

    // Medium - Starting the BST iteration with while(true). Don’t modify the root node.
    public static TreeNode insert_node_in_BST(TreeNode root, int new_value) {
        // Try to figure out(by iterating through the BST) where the potential
        // new_value can be inserted as a node by traversing[O(log 2(N))] the BST.
        // This new_value will always be in a LEAF position

        // IMPORTANT - Starting the BST iteration with while(true) is necessary as
        // we need to BREAK out of the traversal once we're able to create the new node.

        // IMPORTANT - Don't modify the root node as we need to return the root node
        // intact. Take a reference to the root node & iterate with it.
        TreeNode node = root;
        // Edge Vase
        if (node == null) {
            TreeNode new_node = new TreeNode(new_value);
            return new_node;
        }

        while (true) {

            // If new_value < node, go LEFT
            if (new_value < node.val) {
                if (node.left == null) {
                    TreeNode new_node = new TreeNode(new_value);
                    node.left = new_node;
                    // Necessary
                    break;
                } else {
                    node = node.left;
                }
            }
            // Else, new_value is > node, so go RIGHT
            else {
                if (node.right == null) {
                    TreeNode new_node = new TreeNode(new_value);
                    node.right = new_node;
                    // Necessary
                    break;
                } else {
                    node = node.right;
                }
            }
        }
        return root;
    }

    // Medium - (definitely difficult to think & code)
    public static TreeNode delete_node_in_BST(TreeNode root, int key) {


        // Edge Case
        if (root == null) {
            return null;
        }
        // Edge Case: If the root node of BST itself is to be deleted
        if (root.val == key) {
            return delete_node_in_BST_helper(root);
        }
        TreeNode node = root;
        while (node != null) {
            // If key < node, search for node in LEFT side
            if (key < node.val) {
                // If we found the node to be deleted(root.left), we'll replace this
                // (to_be_deleted) node with the LEFT half node we get from helper func
                if (node.left != null && node.left.val == key) {
                    node.left = delete_node_in_BST_helper(node.left);
                    // ?
                    break;
                }
                // Else, keep going to the LEFT
                else {
                    node = node.left;
                }
            }
            // Else, key >= node.val, so keep searching in RIGHT side
            else {
                // If we found the node to be deleted(root.right), we'll replace this
                // (to_be_deleted) node with the RIGHT half node we get from helper func
                if (node.right != null && node.right.val == key) {
                    node.right = delete_node_in_BST_helper(node.right);
                    // ?
                    break;
                }
                // Else, keep going to the RIGHT
                else {
                    node = node.right;
                }
            }
        }
        return root;
    }

    private static TreeNode delete_node_in_BST_helper(TreeNode root) {
        // If the deleted node's LEFT half is null, return its RIGHT half to reconnect.
        if (root.left == null) {
            return root.right;
        }
        // If the deleted node's RIGHT half is null, return its LEFT half to reconnect.
        else if (root.right == null) {
            return root.left;
        }
        // If both halves are present - We'll have to take 1 half & attach it to the other
        // half's last left/right node. For this case, we'll detach the right half
        // (rightChild) & attach it to the lastRightNode of left half. We'll then
        // connect the left half(leftChild) to deleted node's parent.
        else {
            // rightChild is the RIGHT half we'll disconnect & reconnect
            TreeNode rightChild = root.right;
            // We'll find the last/leaf node of leftChild/left half
            TreeNode lastRightofLeft = find_right_last_node_of_left_half_helper(root.left);
            // Now, we'll reconnect the rightChild with lastRightofLeft
            lastRightofLeft.right = rightChild;
            // We'll now need to connect the LEFT half to deleted node's parent, so
            // return the left half
            return root.left;

        }
    }

    private static TreeNode find_right_last_node_of_left_half_helper(TreeNode root) {
        // If the root is the last/LEAF node, return it
        if (root.right == null) {
            return root;
        }
        // Keep on going to the right child nodes until you encounter the LAST right
        // leaf node
        return find_right_last_node_of_left_half_helper(root.right);
    }

    // Medium - InOrder traversal of any BST is sorted.
    public static int kth_smallest_largest_element_in_BST(TreeNode root, int k) {
        // IMPORTANT - InOrder traversal of any BST is always sorted. So, we'll use DFS -
        // InOrder traversal to sort the BST & get the required element. Now, storing the
        // sorted BST in a List would lead to SC - O(N). So, to avoid this, we'll use a
        // counter. The moment counter reaches k, we got our node.


        // IMPORTANT - In Java, everything is passed by value — but objects are passed
        // by the value of their reference. This means: If you pass a primitive (int,
        // boolean, etc.), you can’t modify it inside another method. If you pass an
        // object, you can modify the object’s fields, but you can’t reassign the
        // reference itself and expect it to reflect outside.
        int[] counter = new int[1];
        int[] k_smallest = new int[1];
        // Edge Case
        if (root == null) {
            return -1;
        }

        inOrder_traversal_of_BST_helper(root, k, counter, k_smallest);
        return k_smallest[0];
    }

    // Medium - Maintain a range for every node.
    private static void inOrder_traversal_of_BST_helper(TreeNode root, int k,
                                                        int[] counter, int[] k_smallest) {

        if (root == null || counter[0] >= k) return;

        // Traverse left subtree
        inOrder_traversal_of_BST_helper(root.left, k, counter, k_smallest);

        // Increment counter after visiting left subtree
        counter[0]++;

        // Check if current node is the Kth smallest
        if (counter[0] == k) {
            k_smallest[0] = root.val;
            return;
        }

        // Traverse right subtree if
        // Kth smallest is not found yet
        inOrder_traversal_of_BST_helper(root.right, k, counter, k_smallest);
    }

    // Medium - (but easy)
    public static boolean check_if_tree_is_BST_or_BT(TreeNode root) {
        // For every node, we'll give a range - [MIN_VALUE, MAX_VALUE]

        // For root node - [- Integer.MAX_VALUE, Integer.MAX_VALUE]
        // For every node to LEFT of root - [- Integer.MAX_VALUE, root.val)
        // For every node to RIGHT of root - (root, Integer.MAX_VALUE]

        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    private static boolean isValidBST(TreeNode root, long min_value, long max_value) {

        if (root == null) {
            return true;
        }
        if (root.val >= max_value || root.val <= min_value) {
            return false;
        }
        return isValidBST(root.left, min_value, root.val) &&
                isValidBST(root.right, root.val, max_value);
    }

    // Medium
    public static TreeNode lowest_common_ancestor_LCA(TreeNode root, TreeNode p, TreeNode q) {
        // IMPORTANT - If we're standing at a node: there are 4 possibilities wrt nodes p & q
        // 1) Both p, q are left of node
        // 2) Both p, q are right of node
        // 3) One of p, q is to left of node, while the other is to right of node
        // 4) One of p, q is the node itself.

        // Edge Case
        if (root == null) {
            return null;
        }
        // If both p, q lie to the LEFT of node
        if (p.val < root.val && q.val < root.val) {
            // Keep going Left
            return lowest_common_ancestor_LCA(root.left, p, q);
        }
        // If both p, q lie to the RIGHT of node
        if (p.val > root.val && q.val > root.val) {
            // Keep going Right
            return lowest_common_ancestor_LCA(root.right, p, q);
        }
        // For cases 3) & 4) - If both p, q lie either side of the node, this means that
        // the node is the LCA. Also, if one of p,q is the node itself, then the node
        // becomes LCA for p, q. So, we'll just return the node
        return root;
    }


}

class TreeNode {

    // Data Component
    int val;

    // Pointers to children - left & right using reference variables
    TreeNode left;
    TreeNode right;

    // Empty constructor
    public TreeNode() {
    }

    // Define a constructor which takes in a key(value) & assigns it to its data
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    // Define constructor that takes in all the params
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
