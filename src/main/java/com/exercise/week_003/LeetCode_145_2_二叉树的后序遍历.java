package main.java.com.exercise.week_003;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/1/19 20:45
 * @Version 1.0
 */
public class LeetCode_145_2_二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        if (root == null) {
            return list;
        }
        TreeNode node = root;
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            stack2.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null)  {
                stack.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }
        return list;
    }
}
