package main.java.com.exercise.week_005;

/**
 * @Author cl
 * @Date 2021/2/4 18:05
 * @Version 1.0
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class LeetCode_141_1_环形链表 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode second = head;
        while(true) {
            if (head == null || second == null || second.next == null) {
                return false;
            }
            head = head.next;
            second = second.next.next;
            if (head == second) {
                return true;
            }
        }
    }
}
