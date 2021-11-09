package leetcode.editor.en.second;

public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode test = new ListNode(1, new ListNode(2, new ListNode(3)));
        new PalindromeLinkedList().traverse(test);
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private ListNode left;
    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverseList(head);
    }

    public boolean traverseList(ListNode head) {
        if (head == null) return true;
        boolean res = traverseList(head.next);
        res = res && left.val == head.val;
        left = left.next;
        return res;
    }

    // 链表的递归后序遍历
    public void traverse(ListNode head) {
        if (head.next == null) {
            System.out.println(head.val);
            return;
        }
        traverse(head.next);
        System.out.println(head.val);
    }
}
