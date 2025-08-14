/**
 * @Author:
 * @CreateTime: 2025-07-23
 * @Description: 反转链表
 */
public class LCR206 {
    public ListNode reverseList(ListNode head) {
        // 将当前节点的 next 指针改为指向前一个节点
        // 迭代法
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseList1(ListNode head) {
        // 递归法
        return recur(head, null);
    }

    public ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) return pre; // 终止条件
        ListNode res = recur(cur.next, cur);  // 递归后继节点
        cur.next = pre;  // 修改节点引用指向
        return res; // 返回反转链表的头节点
    }

    public ListNode mySolution(ListNode head) {
        // 迭代
        ListNode reverse = new ListNode();
        while (head != null) {
            reverse.val = head.val;
            reverse = new ListNode(head.val, reverse);
            head = head.next;
        }
        return reverse;
    }
}
