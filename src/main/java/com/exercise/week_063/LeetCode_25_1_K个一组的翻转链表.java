package main.java.com.exercise.week_063;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/3/26 17:04
 */
public class LeetCode_25_1_K个一组的翻转链表 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode;

        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummyNode.next;
                }
            }

            ListNode next = tail.next;
            ListNode[] reverts = reverseListNode(head, tail);
            head = reverts[0];
            tail = reverts[1];

            pre.next = head;
            tail.next = next;
            pre = tail;
            head = tail.next;
        }

        return dummyNode.next;
    }

    private ListNode[] reverseListNode(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (tail != prev) {
            ListNode next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }
        return new ListNode[]{tail, head};
    }
}
