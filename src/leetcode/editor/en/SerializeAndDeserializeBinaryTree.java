//Serialization is the process of converting a data structure or object into a s
//equence of bits so that it can be stored in a file or memory buffer, or transmit
//ted across a network connection link to be reconstructed later in the same or an
//other computer environment. 
//
// Design an algorithm to serialize and deserialize a binary tree. There is no r
//estriction on how your serialization/deserialization algorithm should work. You 
//just need to ensure that a binary tree can be serialized to a string and this st
//ring can be deserialized to the original tree structure. 
//
// Clarification: The input/output format is the same as how LeetCode serializes
// a binary tree. You do not necessarily need to follow this format, so please be 
//creative and come up with different approaches yourself. 
//
// 
// Example 1: 
//
// 
//Input: root = [1,2,3,null,null,4,5]
//Output: [1,2,3,null,null,4,5]
// 
//
// Example 2: 
//
// 
//Input: root = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: root = [1]
//Output: [1]
// 
//
// Example 4: 
//
// 
//Input: root = [1,2]
//Output: [1,2]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 104]. 
// -1000 <= Node.val <= 1000 
// 
// Related Topics Tree Design 
// 👍 4172 👎 191

// 2021-04-15 15:55:50

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        // 通过前序，后序，层级(level-order)遍历来实现
        // 普通情况下，单一一个遍历是无法将二叉树还原的，所以遍历中必须包括null节点
        // 中序遍历不成立，无法找到root节点位置
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // level-order(BFS)
    // 迭代 Queue 遍历中需要包括null节点
    private final String SEP = ",";
    private final String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(curr.val).append(SEP);
            q.offer(curr.left);
            q.offer(curr.right);
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] datas = data.split(SEP);
        String first = datas[0];
        if (first.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(first));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < datas.length) {
            TreeNode curr = q.poll();
            if (curr == null) continue;
            String left = datas[i];
            if (left.equals(NULL)) {
                curr.left = null;
            } else {
                curr.left = new TreeNode(Integer.parseInt(left));
                q.offer(curr.left);
            }
            i += 1;
            String right = datas[i];
            if (right.equals(NULL)) {
                curr.right = null;
            } else {
                curr.right = new TreeNode(Integer.parseInt(right));
                q.offer(curr.right);
            }
            i += 1;
        }
        return root;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public class CodecPreorder {
        // preorder
        private final String SEP = ",";
        private final String NULL = "#";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            traverse(root, sb);
            return sb.toString();
        }

        private void traverse(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            sb.append(root.val).append(SEP);
            traverse(root.left, sb);
            traverse(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] datas = data.split(SEP);
            LinkedList<String> nodes = new LinkedList<>();
            for (String s: datas) {
                nodes.addLast(s);
            }
            return deTraverse(nodes);
        }

        private TreeNode deTraverse(LinkedList<String> nodes) {
            if (nodes.isEmpty()) return null;
            // 每次构造一个，就从nodes中取出一个
            // preorder 第一个是root
            String first = nodes.removeFirst();
            if (first.equals(NULL)) return null;
            TreeNode root = new TreeNode(Integer.parseInt(first));
            root.left = deTraverse(nodes);
            // 一个一个从前往后的去掉 等到调用root.right的时候，nodes里面第一个就是root.right的root
            root.right = deTraverse(nodes);
            return root;
        }
    }

    public class CodecPostorder {
        // postorder
        // 后序和前序遍历很像，前序列表第一个是root，后序列表最后一个是root
        private final String SEP = ",";
        private final String NULL = "#";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            traverse(root, sb);
            return sb.toString();
        }

        private void traverse(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            traverse(root.left, sb);
            traverse(root.right, sb);
            sb.append(root.val).append(SEP);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] datas = data.split(SEP);
            LinkedList<String> nodes = new LinkedList<>();
            for (String s: datas) {
                nodes.addLast(s);
            }
            return deTraverse(nodes);
        }

        private TreeNode deTraverse(LinkedList<String> nodes) {
            if (nodes.isEmpty()) return null;
            // 每次构造一个，就从nodes中取出一个
            // postorder 最后一个是root
            String last = nodes.removeLast();
            if (last.equals(NULL)) return null;
            TreeNode root = new TreeNode(Integer.parseInt(last));
            root.right = deTraverse(nodes);
            // 一个一个从后往前的去掉 等到调用root.left的时候，nodes里面最后一个就是root.left的root
            root.left= deTraverse(nodes);
            return root;
        }
    }
}


