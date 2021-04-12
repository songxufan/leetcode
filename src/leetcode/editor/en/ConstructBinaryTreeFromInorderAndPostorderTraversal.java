//Given two integer arrays inorder and postorder where inorder is the inorder tr
//aversal of a binary tree and postorder is the postorder traversal of the same tr
//ee, construct and return the binary tree. 
//
// 
// Example 1: 
//
// 
//Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//Output: [3,9,20,null,null,15,7]
// 
//
// Example 2: 
//
// 
//Input: inorder = [-1], postorder = [-1]
//Output: [-1]
// 
//
// 
// Constraints: 
//
// 
// 1 <= inorder.length <= 3000 
// postorder.length == inorder.length 
// -3000 <= inorder[i], postorder[i] <= 3000 
// inorder and postorder consist of unique values. 
// Each value of postorder also appears in inorder. 
// inorder is guaranteed to be the inorder traversal of the tree. 
// postorder is guaranteed to be the postorder traversal of the tree. 
// 
// Related Topics Array Tree Depth-first Search 
// 👍 2570 👎 49

// 2021-04-12 21:03:17

package leetcode.editor.en;
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeWithBound(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTreeWithBound(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR) {
        // postL和postR是遍历postorder数组的边界，两端均包括，inL和inR含义类似
        if (inL > inR || postL > postR) return null;
        // postorder的最后一个是root
        int rootVal = postorder[postR];
        TreeNode result = new TreeNode(rootVal);
        // 在inorder中找到root，左边是left，右边是right
        // postorder中的left和right和inorder中的长度相同
        int rootIndex = -1;
        for (int i = inL; i <= inR; i += 1) {
            if (rootVal == inorder[i]) {
                rootIndex = i;
                break;
            }
        }
        int leftLength = rootIndex - inL;
        result.left = buildTreeWithBound(inorder, inL, rootIndex - 1, postorder, postL, postL + leftLength - 1);
        result.right = buildTreeWithBound(inorder, rootIndex + 1, inR, postorder, postL + leftLength, postR - 1);
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


