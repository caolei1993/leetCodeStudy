package main.java.com.exercise.week_019;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/6/4 20:28
 * @Version 1.0
 */
public class LeetCode_160_1_相交链表 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node = headA;
        Set<ListNode> set = new HashSet<>();
        set.add(node);
        while (node.next != null) {
            node = node.next;
            set.add(node);
        }
        node = headB;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
}
