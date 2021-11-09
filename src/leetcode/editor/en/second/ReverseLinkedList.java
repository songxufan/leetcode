package leetcode.editor.en.second;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode test = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode res = new ReverseLinkedList().reverseBetweenIteratively(test, 2, 4);
        int a = 0;
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // from 1->2->3->4 to 4->3->2->1
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode reversed = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reversed;
    }

    // from 1->2->3->4 to 3->2->1->4 when n = 3, 后面的要保证还是接上的
    // 题目要有意义，就要保证n小于等于链表长度
    private ListNode successor;
    public ListNode reverseFirstNNodes(ListNode head, int n) {
        if (head == null) return null;
        if (n == 0) return head;
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode reversed = reverseFirstNNodes(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return reversed;
    }

    // 1-indexed
    public ListNode reverseBetweenMAndN(ListNode head, int m, int n) {
        if (m == 1) return reverseFirstNNodes(head, n);
        head.next = reverseBetweenMAndN(head.next, m - 1, n - 1);
        return head;
    }

    public ListNode reverseListIteratively(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr;
            curr = curr.next;
            temp.next = prev;
            prev = temp;
        }
        return prev;
    }

    public ListNode reverseListFirstNIteratively(ListNode head, int n) {
        if (head == null) return null;
        if (n == 0) return head;
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; i < n; i++) {
            ListNode temp = curr;
            curr = curr.next;
            temp.next = prev;
            prev = temp;
        }
        head.next = curr;
        return prev;
    }

    public ListNode reverseBetweenIteratively(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode prev = sentinel;
        ListNode curr = head;
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
            curr = curr.next;
        }
        ListNode start = prev;
        for (int i = 0; i < n - m + 1; i++) {
            ListNode temp = curr;
            curr = curr.next;
            temp.next = prev;
            prev = temp;
        }
        // 注意这里的最后几步，精髓就在这里了
        // 对于m是否为1可以分情况讨论
        // 如果m是1 最后结果就是 0-><-1<-2<-3 4 start和sentinel都是0，prev是3，current是4
        // 第一步1指向4，第二步0指向3，变成0->3->2->1->4，最后从3返回
        // 如果m不是1，最后结果就是 0->1->2-><-3<-4<-5 6 sentinel是0，start是2 prev是5，curr是6
        // 第一步3指向6，第二步2指向5，变成0->1->2->5->4->3->6 最后从1返回
        // 引入虚拟头节点之后，对于m是否为1的情况统一了
        start.next.next = curr;
        start.next = prev;
        return sentinel.next;
    }

    // 每k个一组翻转list
    public ListNode reverseListByKNodes(ListNode head, int k) {
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            if (curr == null) return head;
            curr = curr.next;
        }
        ListNode reversed = reverseFirstNNodes(head, k);
        head.next = reverseListByKNodes(curr, k);
        return reversed;
    }
}

// 假设reverse 1->2->3->4得到的结果是1<-2<-3<-4 此时head为1
// 那么，递归对于head.next进行reverse的结果就是 1->2<-3<-4
// 要对于1->2变成1<-2
