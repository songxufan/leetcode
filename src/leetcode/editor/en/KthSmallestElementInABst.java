//Given the root of a binary search tree, and an integer k, return the kth (1-in
//dexed) smallest element in the tree. 
//
// 
// Example 1: 
//
// 
//Input: root = [3,1,4,null,2], k = 1
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: root = [5,3,6,2,4,null,null,1], k = 3
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is n. 
// 1 <= k <= n <= 104 
// 0 <= Node.val <= 104 
// 
//
// 
//Follow up: If the BST is modified often (i.e., we can do insert and delete ope
//rations) and you need to find the kth smallest frequently, how would you optimiz
//e? Related Topics Binary Search Tree 
// 👍 3739 👎 85

// 2021-04-14 15:44:25

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.List;

public class KthSmallestElementInABst {
    public static void main(String[] args) {
        Solution solution = new KthSmallestElementInABst().new Solution();
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
    public int kthSmallest(TreeNode root, int k) {
        // the inorder traversal is sequential for binary search tree
        // 改进的做法不用每次都遍历全部，找到结果只会就可以退出了
        // 如果要提高效率，维持一个size字段，就可以对于每一个节点都知道自己的rank了
        traverse(root, k);
        return res;
    }

    private int rank = 0;
    private int res = 0;

    private void traverse(TreeNode root, int k) {
        if (root == null) return;
        traverse(root.left, k);
        // 对于最左侧的第一个节点，root.left是null，rank是1，1-indexed
        rank += 1;
        if (rank == k) {
            res = root.val;
            // 找到结果之后就不用遍历了
            return;
        }
        traverse(root.right, k);
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

    class Solution1 {
        public int kthSmallest(TreeNode root, int k) {
            // the inorder traversal is sequential for binary search tree
            traverse(root);
            return inorder.get(k - 1);
        }

        private void traverse(TreeNode root) {
            if (root == null) return;
            traverse(root.left);
            // 现在的方法是在inorder traversal时把对应的val存到list中，通过list来记录是第几个
            // 其实不需要，直接维护一个变量来记录是第几个就可以了
            inorder.add(root.val);
            traverse(root.right);
        }

        private List<Integer> inorder = new LinkedList<>();
    }
}



