package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/24 15:06
 * @Version 1.0
 */
public class LeetCode_430_1_扁平化多级双向链表 {
    public Node flatten(Node head) {
        Node node = head;
        while (node != null) {
            Node child = node.child;
            if (child != null) {
                Node tmp = node.next;
                Node chead = flatten(node.child);
                node.next = chead;
                chead.prev = node;
                node.child = null;
                while (node.next != null) {
                    node = node.next;
                }
                node.next = tmp;
                if (tmp != null) {
                    tmp.prev = node;
                }
            }
            node = node.next;
        }
        return head;
    }
}
