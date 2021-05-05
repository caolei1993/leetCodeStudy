package main.java.com.exercise.week_009;

/**
 * @Author cl
 * @Date 2021/3/25 7:29
 * @Version 1.0
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class LeetCode_82_1_删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        // 创建虚拟头节点(方便所有节点情况统一处理)
        ListNode virtualHead = new ListNode(Integer.MIN_VALUE, head);
        // 初始化pre为头节点的值
        int pre = head.val;
        // 初始化遍历节点为头节点，前置节点为虚拟头节点
        ListNode node = head, preNode = virtualHead;
        // 当有且仅有一个节点时，不需要处理
        while (node.next != null) {
            // 当前节点的下一个节点值与pre的值做比较（pre可能是前一个节点的值，也可能是判断有相等的已删除节点的值）
            if (node.next.val == pre) {
                // 当判断有相同值的节点
                // preNode的next,等于当前节点next.next(过滤掉当前节点和当前节点next两个值)
                preNode.next = node.next.next;
                node = preNode;
            } else {
                // 如果判断node.next与pre不相同，则表明node值是唯一的
                // 前置初始化为node, 遍历节点轮询到下一个，pre的值置为轮询节点的值
                preNode = node;
                node = node.next;
                pre = node.val;
            }
        }
        return virtualHead.next;
    }
}
