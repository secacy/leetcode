/**
 * @Author:
 * @CreateTime: 2025-07-24
 * @Description: 合并两个有序链表
 */
public class LCR21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 递归法
        // 我们判断 l1 和 l2 头结点哪个更小，然后较小结点的 next 指针指向其余结点的合并结果。（调用递归）

//        if (list1 == null && list2 == null) {
//            return null;
//        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
            // return new ListNode(list1.val, mergeTwoLists(list1.next, list2));
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
            // return new ListNode(list2.val, mergeTwoLists(list1, list2.next));
        }
    }
}
