package main.java.com.exercise.week_047;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/16 10:08 下午
 */
public class LeetCode_382_1_链表的随机节点 {
    List<ListNode> list = new ArrayList<>();
    public LeetCode_382_1_链表的随机节点(ListNode head) {
        while(head != null) {
            list.add(head);
            head = head.next;
        }
    }

    public int getRandom() {
        int size = list.size();
        Random random = new Random();
        return list.get(random.nextInt(size)).val;
    }
}
