//Given the head of a linked list, remove the nth node from the end of the list 
//and return its head. 
//
// 
// Example 1: 
//
// 
//Input: head = [1,2,3,4,5], n = 2
//Output: [1,2,3,5]
// 
//
// Example 2: 
//
// 
//Input: head = [1], n = 1
//Output: []
// 
//
// Example 3: 
//
// 
//Input: head = [1,2], n = 1
//Output: [1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is sz. 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
// Follow up: Could you do this in one pass? 
// Related Topics Linked List Two Pointers 
// 👍 7441 👎 375

// 2021-11-08 21:37:05

package leetcode.editor.en;
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode prev = sentinel;
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i ++) {
            fast = fast.next;
        }
        while (fast != null) {
            prev = prev.next;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = slow.next;
        return sentinel.next;
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
// 一般这种正序遍历不能找到结果的链表题，都是双指针
// 两个指针之间有一定的关系，利用两个指针相遇，或者是第二个指针到末尾时第一个指针的状态来提供信息
// 注意，如果删除倒数第n个节点，此时prev不存在，需要引入虚拟头节点
// 分析方法 sentinel 1 2 3 null


