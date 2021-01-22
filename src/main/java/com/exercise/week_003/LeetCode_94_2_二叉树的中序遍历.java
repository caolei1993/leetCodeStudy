package main.java.com.exercise.week_003;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/1/19 20:13
 * @Version 1.0
 */
public class LeetCode_94_2_二叉树的中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }
}
