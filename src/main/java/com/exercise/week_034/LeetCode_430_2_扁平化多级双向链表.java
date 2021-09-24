package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/24 15:58
 * @Version 1.0
 */
public class LeetCode_430_2_扁平化多级双向链表 {
    public Node flatten(Node head) {
        flat(head);
        return head;
    }

    private Node flat(Node node) {
        Node last = node;
        while (node != null) {
            if (node.child == null) {
                last = node;
                node = node.next;
            } else {
                Node tmp = node.next;
                Node childLast = flat(node.child);
                node.next = node.child;
                node.child.prev = node;
                node.child = null;
                childLast.next = tmp;
                if (tmp != null) {
                    tmp.prev = childLast;
                }
                node = childLast;
            }
        }
        return last;
    }
}
