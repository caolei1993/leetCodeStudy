package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/24 16:56
 * @Version 1.0
 */
public class LeetCode_430_3_扁平化多级双向链表 {
    public Node flatten(Node head) {
        Node dummy = new Node();
        dummy.next = head;
        while (head != null) {
            if (head.child != null) {
                Node tmp = head.next;
                Node child = head.child;
                head.next = child;
                child.prev = head;
                head.child = null;
                Node last = head;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = tmp;
                if (tmp != null) {
                    tmp.prev = last;
                }
            }
            head = head.next;
        }
        return dummy.next;
    }
}
