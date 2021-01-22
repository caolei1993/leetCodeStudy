package main.java.com.exercise.week_003;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/1/21 10:53
 * @Version 1.0
 */
public class LeetCode_145_3_二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode last = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.peek();
            if (curr.right == null || curr.right == last) {
                list.add(curr.val);
                stack.pop();
                last = curr;
                curr = null;
            } else {
                curr = curr.right;
            }
        }
        return list;
    }
}
