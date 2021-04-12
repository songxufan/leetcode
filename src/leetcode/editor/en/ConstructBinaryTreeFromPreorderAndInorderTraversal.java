//Given two integer arrays preorder and inorder where preorder is the preorder t
//raversal of a binary tree and inorder is the inorder traversal of the same tree,
// construct and return the binary tree. 
//
// 
// Example 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// Example 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
// Constraints: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder and inorder consist of unique values. 
// Each value of inorder also appears in preorder. 
// preorder is guaranteed to be the preorder traversal of the tree. 
// inorder is guaranteed to be the inorder traversal of the tree. 
// 
// Related Topics Array Tree Depth-first Search 
// 👍 5009 👎 129

// 2021-04-12 20:43:46

package leetcode.editor.en;
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeWithBound(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeWithBound(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        // preL和preR是遍历preorder数组的边界，两端均包括，inL和inR含义类似
        if (preL > preR || inL > inR) return null;
        // preorder的第一个是root
        int rootVal = preorder[preL];
        TreeNode result = new TreeNode(rootVal);
        // 在inorder中找到root，左边是left，右边是right
        // preorder中的left和right和inorder中的长度相同
        int rootIndex = -1;
        for (int i = inL; i <= inR; i += 1) {
            if (rootVal == inorder[i]) {
                rootIndex = i;
                break;
            }
        }
        int leftLength = rootIndex - inL;
        result.left = buildTreeWithBound(preorder, preL + 1, preL + leftLength, inorder, inL, rootIndex - 1);
        result.right = buildTreeWithBound(preorder, preL + leftLength + 1, preR, inorder, rootIndex + 1, inR);
        return result;
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


