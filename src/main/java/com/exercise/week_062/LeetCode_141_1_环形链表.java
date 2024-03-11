package main.java.com.exercise.week_062;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/3/6 18:21
 */
public class LeetCode_141_1_环形链表 {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            head = head.next;
            if (fast == head) {
                return true;
            }
        }
        return false;
    }
}


