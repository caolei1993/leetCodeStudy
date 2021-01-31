package main.java.com.exercise.week_004;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/1/31 13:46
 * @Version 1.0
 */
public class LeetCode_590_2_N叉树的后序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack1 = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            stack1.push(node);
            for (Node child : node.children) {
                stack.push(child);
            }
        }
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            list.add(node.val);
        }
        return list;
    }
}
