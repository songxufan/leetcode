package leetcode.editor.en.second;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateNextRightPointer {
    public class Node {
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

    // 用queue的level traversal
    public Node connect(Node root) {
        // 如果用迭代的话，就是层级遍历level traversal
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

    public Node connectRecursively(Node root) {
        if (root == null) return null;
        connectTwoNode(root.left, root.right);
        return root;
    }

    public void connectTwoNode(Node left, Node right) {
        if (left == null || right == null) return;
        left.next = right;
        connectTwoNode(left.left, left.right);
        connectTwoNode(left.right, right.left);
        connectTwoNode(right.left, right.right);
        // 对于5和6，分属于两个不同的父节点2和3，那么我们的递归函数起码要有两个参数
    }

    public Node connectBFS(Node root) {
        if (root == null) return null;
        Node curr = root;
        while (curr.left != null) {
            Node temp = curr;
            while (temp != null) { // 比如说temp是2
                temp.left.next = temp.right; // 45
                if (temp.next != null) { // temp.next是3
                    temp.right.next = temp.next.left; // 56
                }
                temp = temp.next;
            }
            curr = curr.left;
        }
        return root;
    }
    // 1 23 4567
}
