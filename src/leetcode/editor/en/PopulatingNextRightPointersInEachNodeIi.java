//Given a binary tree 
//
// 
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}
// 
//
// Populate each next pointer to point to its next right node. If there is no ne
//xt right node, the next pointer should be set to NULL. 
//
// Initially, all next pointers are set to NULL. 
//
// 
// Example 1: 
//
// 
//Input: root = [1,2,3,4,5,null,7]
//Output: [1,#,2,3,#,4,5,7,#]
//Explanation: Given the above binary tree (Figure A), your function should popu
//late each next pointer to point to its next right node, just like in Figure B. T
//he serialized output is in level order as connected by the next pointers, with '
//#' signifying the end of each level.
// 
//
// Example 2: 
//
// 
//Input: root = []
//Output: []
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 6000]. 
// -100 <= Node.val <= 100 
// 
//
// 
// Follow-up: 
//
// 
// You may only use constant extra space. 
// The recursive approach is fine. You may assume implicit stack space does not 
//count as extra space for this problem. 
// 
// Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 
// 👍 3033 👎 218

// 2021-11-09 21:44:26

package leetcode.editor.en;


import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNodeIi {
    public static void main(String[] args) {
        Solution solution = new PopulatingNextRightPointersInEachNodeIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        // 反而是level的最好写，不用考虑中间会有null的情况但是要求constant extra space
        if (root == null) return null;
        Queue<Node> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
            int levelLength = q.size();
            for (int i = 0; i < levelLength; i++) {
                Node curr = q.poll();
                if (i < levelLength - 1) {
                    curr.next = q.peek();
                }
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
}


