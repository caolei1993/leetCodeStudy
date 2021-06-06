package main.java.com.exercise.week_019;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/6/4 20:43
 * @Version 1.0
 */
public class LeetCode_160_2_相交链表 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        int m = 0, n = 0;
        while (a != null) {
            m++;
            a = a.next;
        }
        while (b != null) {
            n++;
            b = b.next;
        }
        int t = Math.abs(m - n);
        a = headA;
        b = headB;
        while (t-- != 0) {
            if (m > n) {
                a = a.next;
            } else {
                b = b.next;
            }
        }
        while (a != null) {
            if (a.equals(b)) {
                return a;
            }
            a = a.next;
            b = b.next;
        }
        return null;
    }
}
