package main.java.com.exercise.week_063;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/3/14 17:32
 */
public class LeetCode_92_1_翻转链表II {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode virtualHead = new ListNode(0, head);
        ListNode pre = virtualHead;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return virtualHead.next;
    }
}
