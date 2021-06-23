package main.java.com.exercise.week_021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/6/22 17:55
 * @Version 1.0
 */
public class LeetCode_1600_2_皇位继承顺序 {
    static class Node {
        Node next;
        // 记录最后一个儿子
        Node last;
        String name;
        boolean isDeaded = false;

        public Node (String name) {
            this.name = name;
        }
    }

    Map<String, Node> map = new HashMap<>();
    Node head = new Node("");
    public LeetCode_1600_2_皇位继承顺序(String kingName) {
        Node node = new Node(kingName);
        map.put(kingName, node);
        head.next = node;
    }

    public void birth(String parentName, String childName) {
        Node child = new Node(childName);
        map.put(childName, child);
        Node p = map.get(parentName);
        Node tmp = p;
        while (tmp.last != null) {
            tmp = tmp.last;
        }
        child.next = tmp.next;
        tmp.next = child;
        p.last = child;
    }

    public void death(String name) {
        Node node = map.get(name);
        node.isDeaded = true;
    }

    public List<String> getInheritanceOrder() {
        List<String> ans = new ArrayList<>();
        Node node = head.next;
        while (node != null) {
            if (!node.isDeaded) {
                ans.add(node.name);
            }
            node = node.next;
        }
        return ans;
    }
}
