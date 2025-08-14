/**
 * @Author:
 * @CreateTime: 2025-07-24
 * @Description: 两两交换链表中的节点
 */
public class LCR22 {
    public ListNode swapPairs(ListNode head) {
        // 递归真好用
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public ListNode mySolution(ListNode head) {
        // 递归真好用
        return recur(head);
    }

    public ListNode recur(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        dummy.next = head.next;
        ListNode ptr= head.next.next;
        head.next.next = head;
        head.next = recur(ptr);
        return dummy.next;
    }
}
