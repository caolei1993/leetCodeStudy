package main.java.com.exercise.week_004;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/1/27 21:27
 * @Version 1.0
 */
public class LeetCode_589_2_N叉树的前序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.add(node.val);
            Collections.reverse(node.children);
            for (Node node1: node.children) {
                stack.push(node);
            }
        }
        return list;
    }
}
