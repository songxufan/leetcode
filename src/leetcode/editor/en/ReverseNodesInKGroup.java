//Given a linked list, reverse the nodes of a linked list k at a time and return
// its modified list. 
//
// k is a positive integer and is less than or equal to the length of the linked
// list. If the number of nodes is not a multiple of k then left-out nodes, in the
// end, should remain as it is. 
//
// Follow up: 
//
// 
// Could you solve the problem in O(1) extra memory space? 
// You may not alter the values in the list's nodes, only nodes itself may be ch
//anged. 
// 
//
// 
// Example 1: 
//
// 
//Input: head = [1,2,3,4,5], k = 2
//Output: [2,1,4,3,5]
// 
//
// Example 2: 
//
// 
//Input: head = [1,2,3,4,5], k = 3
//Output: [3,2,1,4,5]
// 
//
// Example 3: 
//
// 
//Input: head = [1,2,3,4,5], k = 1
//Output: [1,2,3,4,5]
// 
//
// Example 4: 
//
// 
//Input: head = [1], k = 1
//Output: [1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range sz. 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics Linked List 
// 👍 3455 👎 400

// 2021-03-31 17:15:47

package leetcode.editor.en;
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup().new Solution();
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        if (k == 1) return head;
        // 首先反转前k个，找到开头和结尾(左开右闭)
        ListNode a = head;
        ListNode b = head;
        for (int i = 1; i <= k; i += 1) {
            if (b == null) return head;
            b = b.next;
        }
        ListNode result = reverseList(a, b);
        a.next = reverseKGroup(b, k);
        return result;
    }

    // 反转head开头的链表（head开头，null结尾，不包括null）
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    // 反转head开头，end结尾的列表，不包括end
    private ListNode reverseList(ListNode head, ListNode end) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode next;
        while (curr != end) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
        // 注意: 这里返回的结果是end-1到head，end是没有被包括进去的
    }
}
//leetcode submit region end(Prohibit modification and deletion)

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
}


