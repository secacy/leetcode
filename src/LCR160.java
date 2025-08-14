import java.util.HashSet;
import java.util.Set;

/**
 * @Author:
 * @CreateTime: 2025-07-23
 * @Description: 相交链表
 */
public class LCR160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 注意到：相交节点及之后都是相同的
        // 也就是遍历时相同节点及之后的长度是相同的
        // 考虑构建两个节点指针 A, B 分别指向两链表头节点 headA , headB
        // A 先遍历完 headA 再去遍历 headB，B 先遍历完 headB 再去遍历 headA
        // 当走到 node 时，恰好相遇
        ListNode A = headA, B = headB;
        while (A != B) {
            A = (A != null) ? A.next : headB;
            B = (B != null) ? B.next : headA;
        }
        return A;
    }

    public ListNode mySol(ListNode headA, ListNode headB) {
        Set<ListNode> set1 = new HashSet<>();
        Set<ListNode> set2 = new HashSet<>();
        while (headA != null && headB != null) {
            set1.add(headA);
            set2.add(headB);
            if (set1.contains(headB)) {
                return headB;
            }
            if (set2.contains(headA)) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        while (headA != null) {
            if (set2.contains(headA)) {
                return headA;
            }
            headA = headA.next;
        }
        while (headB != null) {
            if (set1.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}
