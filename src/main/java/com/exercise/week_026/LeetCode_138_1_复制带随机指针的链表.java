package main.java.com.exercise.week_026;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/7/23 11:16
 * @Version 1.0
 */
public class LeetCode_138_1_复制带随机指针的链表 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(-1);
        Node tmp = head, tail = dummy;
        while (tmp != null) {
            Node node = new Node(tmp.val);
            map.put(tmp, node);
            tail.next = node;
            tail = tail.next;
            tmp = tmp.next;
        }
        tail = dummy.next;
        while (head != null) {
            if (head.random != null) {
                tail.random = map.get(head.random);
            }
            tail = tail.next;
            head = head.next;
        }
        return dummy.next;
    }
}
