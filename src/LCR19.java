/**
 * @Author:
 * @CreateTime: 2025-07-24
 * @Description: 删除链表的倒数第N个结点
 */
public class LCR19 {
    // 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的 next 指针指向链表的头节点。
    // 这样一来，我们就不需要对头节点进行特殊的判断了。
    // 例如，在本题中，如果我们要删除节点 y，我们需要知道节点 y 的前驱节点 x，并将 x 的指针指向 y 的后继节点。

    // dummy 好评

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);  //哑节点
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    private int getLength(ListNode head) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            length ++;
        }
        return length;
    }

    // 在某些语言中，由于需要自行对内存进行管理
    // 因此在实际的面试中，对于「是否需要释放被删除节点对应的空间」这一问题，我们需要和面试官进行积极的沟通以达成一致

    public ListNode mySolution(ListNode head, int n) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            length ++;
        }

        int index = length - n;
        if (index == 0) {
            return head.next;
        }
        int i = 0;
        ListNode prev = head;
        curr = head.next;
        // 3个点，去掉倒数第二个，index = 1
        while (i+1 < index) {
            curr = curr.next;
            prev = prev.next;
            i++;
        }
        prev.next = curr.next;
        return head;
    }
}
