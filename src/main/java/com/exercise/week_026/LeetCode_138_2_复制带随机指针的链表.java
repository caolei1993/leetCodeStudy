package main.java.com.exercise.week_026;

/**
 * @Author cl
 * @Date 2021/7/30 15:42
 * @Version 1.0
 */
public class LeetCode_138_2_复制带随机指针的链表 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node(-1);
        dummy.next = head;
        // 创建新的节点跟在对应的老的节点后面
        while (head != null) {
            Node node = new Node(head.val);
            node.next = head.next;
            head.next = node;
            head = head.next.next;
        }
        head = dummy.next;
        // 维护新的节点的random指针，新的节点的random指针等于老的节点的random指针的下一个
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
        head = dummy.next;
        Node ans = head.next;
        // 分割新老链表
        while (head != null) {
            Node tmp = head.next;
            if (tmp != null) {
                head.next = head.next.next;
            }
            head = tmp;
        }
        return ans;
    }
}
