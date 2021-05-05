package main.java.com.exercise.week_009;

/**
 * @Author cl
 * @Date 2021/3/27 10:30
 * @Version 1.0
 */
public class LeetCode_61_1_旋转链表 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0 || head.next == null) {
            return head;
        }
        // 链表长度
        int size = 1;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            size++;
        }
        // 计算最后一个元素的位置(原本是倒数第k个节点，现在就是求正数第a个节点)
        int a = size - k % size;
        // k是size的倍数
        if (a == size) {
            return head;
        }
        // 将链表链接为环形链表
        node.next = head;
        // 找到新链表的尾部节点
        while (a > 0) {
            node = node.next;
            a--;
        }
        head = node.next;
        node.next = null;
        return head;
    }
}
