//Given an integer n, return all the structurally unique BST's (binary search tr
//ees), which has exactly n nodes of unique values from 1 to n. Return the answer 
//in any order. 
//
// 
// Example 1: 
//
// 
//Input: n = 3
//Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]
//]
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: [[1]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 8 
// 
// Related Topics Dynamic Programming Tree 
// 👍 3106 👎 213

// 2021-05-31 17:47:51

package leetcode.editor.en;


import java.util.LinkedList;
import java.util.List;

public class UniqueBinarySearchTreesIi {
    public static void main(String[] args) {
        Solution solution = new UniqueBinarySearchTreesIi().new Solution();
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
    public List<TreeNode> generateTrees(int n) {
        // 这道题目不需要记录结果
        // 前面题目123和456的BST个数相同，用一维数组记录，坐标是节点个数
        // 这里123和456实际得到的BST不同，不会有重叠子问题
        return getTrees(1, n);
    }

    private LinkedList<TreeNode> getTrees(int low, int high) {
        LinkedList<TreeNode> result = new LinkedList<>();
        if (low > high) {
            result.add(null);
            return result;
        }
        for (int i = low; i <= high; i += 1) {
            LinkedList<TreeNode> leftList = getTrees(low, i - 1);
            LinkedList<TreeNode> rightList = getTrees(i + 1, high);
            for (TreeNode left: leftList) {
                for (TreeNode right: rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }



}
//leetcode submit region end(Prohibit modification and deletion)

class TreeNode {
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


