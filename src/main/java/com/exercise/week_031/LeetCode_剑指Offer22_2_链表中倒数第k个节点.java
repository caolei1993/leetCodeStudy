package main.java.com.exercise.week_031;

/**
 * @Author cl
 * @Date 2021/9/2 21:51
 * @Version 1.0
 */
public class LeetCode_剑指Offer22_2_链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
