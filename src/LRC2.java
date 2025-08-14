/**
 * @Author:
 * @CreateTime: 2025-07-24
 * @Description: 两数相加
 */
public class LRC2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return  recur(l1, l2, 0);
    }

    private ListNode recur(ListNode l1, ListNode l2, int k) {
        if (l1 == null) {
            if (k > 0) {
                return recur(new ListNode(k), l2, 0);
            }
            return l2;
        }
        if (l2 == null) {
            if (k > 0) {
                return recur(l1, new ListNode(k), 0);
            }
            return l1;
        }
        int sum = l1.val + l2.val + k;
        l1.val = sum % 10;
        l1.next = recur(l1.next, l2, sum/10);
        return l1;
    }
}
