//Given a root node reference of a BST and a key, delete the node with the given
// key in the BST. Return the root node reference (possibly updated) of the BST. 
//
// Basically, the deletion can be divided into two stages: 
//
// 
// Search for a node to remove. 
// If the node is found, delete the node. 
// 
//
// Follow up: Can you solve it with time complexity O(height of tree)? 
//
// 
// Example 1: 
//
// 
//Input: root = [5,3,6,2,4,null,7], key = 3
//Output: [5,4,6,2,null,null,7]
//Explanation: Given key to delete is 3. So we find the node with value 3 and de
//lete it.
//One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
//Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also
// accepted.
//
// 
//
// Example 2: 
//
// 
//Input: root = [5,3,6,2,4,null,7], key = 0
//Output: [5,3,6,2,4,null,7]
//Explanation: The tree does not contain a node with value = 0.
// 
//
// Example 3: 
//
// 
//Input: root = [], key = 0
//Output: []
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 104]. 
// -105 <= Node.val <= 105 
// Each node has a unique value. 
// root is a valid binary search tree. 
// -105 <= key <= 105 
// 
// Related Topics Tree 
// 👍 2834 👎 107

// 2021-04-14 17:39:58

package leetcode.editor.en;
public class DeleteNodeInABst {
    public static void main(String[] args) {
        Solution solution = new DeleteNodeInABst().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        // base case root == null说明没有找到对应的key
        if (root == null) return null;
        if (root.val == key) {
            // 进行删除操作
            if (root.left == null && root.right == null) {
                // 末端节点，直接删除
                root = null;
            } else if (root.left == null || root.right == null) {
                // 只有一侧有子节点，让子节点代替自己即可
                // BST的子树都是BST
                root = (root.left != null) ? root.left : root.right;
            } else {
                // 找到左子树中最大的节点，将其作为新的root，然后在左子树中删除那个最大的节点
                TreeNode maxLeft = getMaxNode(root.left);
                root.val = maxLeft.val;
                root.left = deleteNode(root.left, maxLeft.val);
            }
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMaxNode(TreeNode root) {
        // 最右边的节点就是最大的节点
        // 此时传入的参数root不可能为null
        while (root.right != null) root = root.right;
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
}


