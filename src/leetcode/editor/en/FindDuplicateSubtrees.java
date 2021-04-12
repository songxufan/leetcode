//Given the root of a binary tree, return all duplicate subtrees. 
//
// For each kind of duplicate subtrees, you only need to return the root node of
// any one of them. 
//
// Two trees are duplicate if they have the same structure with the same node va
//lues. 
//
// 
// Example 1: 
//
// 
//Input: root = [1,2,3,4,null,2,4,null,null,4]
//Output: [[2,4],[4]]
// 
//
// Example 2: 
//
// 
//Input: root = [2,1,1]
//Output: [[1]]
// 
//
// Example 3: 
//
// 
//Input: root = [2,2,2,3,null,3,null]
//Output: [[2,3],[3]]
// 
//
// 
// Constraints: 
//
// 
// The number of the nodes in the tree will be in the range [1, 10^4] 
// -200 <= Node.val <= 200 
// 
// Related Topics Tree 
// 👍 1915 👎 238

// 2021-04-12 21:12:54

package leetcode.editor.en;

import java.util.*;

public class FindDuplicateSubtrees {
    public static void main(String[] args) {
        Solution solution = new FindDuplicateSubtrees().new Solution();
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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return new LinkedList<>(resultTree) ;
    }

    private String traverse(TreeNode root) {
        if (root == null) return "#";
        String left = traverse(root.left);
        String right = traverse(root.right);
        String res = left + ","+ right + "," + root.val;
        int freq = childTree.getOrDefault(res, 0);
        if (freq == 1) resultTree.add(root);
        childTree.put(res, freq + 1);
        return res;
    }

    private Map<String, Integer> childTree = new HashMap<>();

    private LinkedList<TreeNode> resultTree = new LinkedList<>();

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


