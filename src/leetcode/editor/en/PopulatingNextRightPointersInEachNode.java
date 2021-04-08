//You are given a perfect binary tree where all leaves are on the same level, an
//d every parent has two children. The binary tree has the following definition: 
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
//
// Follow up: 
//
// 
// You may only use constant extra space. 
// Recursive approach is fine, you may assume implicit stack space does not coun
//t as extra space for this problem. 
// 
//
// 
// Example 1: 
//
// 
//
// 
//Input: root = [1,2,3,4,5,6,7]
//Output: [1,#,2,3,#,4,5,6,7,#]
//Explanation: Given the above perfect binary tree (Figure A), your function sho
//uld populate each next pointer to point to its next right node, just like in Fig
//ure B. The serialized output is in level order as connected by the next pointers
//, with '#' signifying the end of each level.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the given tree is less than 4096. 
// -1000 <= node.val <= 1000 
// Related Topics Tree Depth-first Search Breadth-first Search 
// 👍 3235 👎 164

// 2021-04-08 15:51:41

package leetcode.editor.en;
public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        Solution solution = new PopulatingNextRightPointersInEachNode().new Solution();
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
        // 对于1234567这样的树，5和6属于不同的父节点，所以传参数的时候一定要传两个参数
        if (root == null) return null;
        connectNode(root.left, root.right);
        return root;
    }

    private void connectNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) return;
        node1.next = node2;
        connectNode(node1.left, node1.right);
        connectNode(node1.right, node2.left);
        connectNode(node2.left, node2.right);
    }

    private Node connectWrong(Node root) {
        if (root == null || root.left == null) return root;
        root.left.next = root.right;
        connectWrong(root.left);
        connectWrong(root.right);
        return root;
        // 这是一种常见的错误答案，主要存在的问题是，1234567这样一个完全二叉树中，5和6属于不同的父节点
        // 如果参数只传一个节点，做操作的所有节点都属于共同的父节点，5和6是不可能连起来
        // 所以需要引入一个参数是两个节点的辅助函数，能够把5和6连起来
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
};

}


