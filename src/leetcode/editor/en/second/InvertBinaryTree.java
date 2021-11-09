package leetcode.editor.en.second;

public class InvertBinaryTree {

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

    public TreeNode invertBinaryTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertBinaryTree(root.left);
        TreeNode right = invertBinaryTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }
}
