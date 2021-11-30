package main.java.com.exercise.week_026;

/**
 * @Author cl
 * @Date 2021/7/22 16:57
 * @Version 1.0
 */
public class LeetCode_剑指Offer52_2_两个链表的第一个公共节点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
