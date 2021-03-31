//Given the head of a singly linked list, reverse the list, and return the rever
//sed list. 
//
// 
// Example 1: 
//
// 
//Input: head = [1,2,3,4,5]
//Output: [5,4,3,2,1]
// 
//
// Example 2: 
//
// 
//Input: head = [1,2]
//Output: [2,1]
// 
//
// Example 3: 
//
// 
//Input: head = []
//Output: []
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is the range [0, 5000]. 
// -5000 <= Node.val <= 5000 
// 
//
// 
// Follow up: A linked list can be reversed either iteratively or recursively. C
//ould you implement both? 
// Related Topics Linked List 
// 👍 6634 👎 127

// 2021-03-31 14:41:32

package leetcode.editor.en;
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode test = new ListNode(1);
        test.next = new ListNode(2);
        test.next.next = new ListNode(3);
        test.next.next.next = new ListNode(4);
        test.next.next.next.next = new ListNode(5);
        Solution2 solution2 = new ReverseLinkedList().new Solution2();
        printListNode(solution2.reverseBetween(test, 3, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // recursively
        // 首先处理特殊case
        if (head == null) return null;
        if (head.next == null) return head;
        /**
         * 1(head)->2(head.next)->3->4->5
         * 对于head.next做reverse操作
         * 1(head)->2(head.next)<-3<-4<-5(result)
         * 注意：原来的head和head.next还是原来的对应的节点，head.next就是result的尾巴
         */
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public static void printListNode(ListNode head) {
    StringBuilder sb = new StringBuilder();
    while (head != null) {
        sb.append(head.val);
        head = head.next;
    }
    System.out.println(sb.toString());
}
class Solution1 {
    public ListNode reverseList(ListNode head) {
        // iteratively
        ListNode result = new ListNode();
        while (head != null) {
            // 往前插
            ListNode curr = new ListNode(head.val);
            curr.next = result.next;
            result.next = curr;
            head = head.next;
        }
        return result.next;
    }
}

class Solution2 {
    public ListNode successor = null;

    // 反转链表前n个节点
    public ListNode reverseN(ListNode head, int n) {
        if (head == null) return null;
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode result = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return result;
    }

    // 反转链表区间 index从1开始
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
}
}


