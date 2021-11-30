package main.java.com.exercise.week_026;

/**
 * @Author cl
 * @Date 2021/7/21 11:08
 * @Version 1.0
 */
public class LeetCode_剑指Offer52_1_两个链表的第一个公共节点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            int len1 = 0, len2 = 0;
            ListNode node = headA;
            while (node != null) {
                len1++;
                node = node.next;
            }
            node = headB;
            while (node != null) {
                len2++;
                node = node.next;
            }
            int v = Math.abs(len1 - len2);
            node = headA;
            ListNode node1 = headB;
            if (len1 >= len2) {
                while (v > 0) {
                    node = node.next;
                    v--;
                }
            } else {
                while (v > 0) {
                    node1 = node1.next;
                    v--;
                }
            }

            while (node != null) {
                if (node == node1) {
                    return node;
                } else {
                    node = node.next;
                    node1 = node1.next;
                }
            }
            return null;
    }
}
