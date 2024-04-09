package main.java.com.exercise.week_063;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/4/9 19:14
 */
public class LeetCode_86_1_分隔链表 {
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(0);
        ListNode small = smallHead;
        ListNode largeHead = new ListNode(0);
        ListNode large = largeHead;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
