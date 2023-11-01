package main.java.com.exercise.week_001;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/5/6 9:49 上午
 */
public class 环形链表长度 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                int tmp = 0;
                do {
                    p2 = p2.next;
                    tmp++;
                } while (p1 != p2);
                System.out.println(tmp);
                return head;
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        ListNode n1 = new ListNode(3);
//        ListNode n2 = new ListNode(2);
//        ListNode n3 = new ListNode(0);
//        ListNode n4 = new ListNode(-4);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n2;
//        detectCycle(n1);
        System.out.println(Math.floor(2));
    }
}
