package main.java.com.exercise.week_004;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/2/2 7:47
 * @Version 1.0
 */
public class LeetCode_114_3_二叉树展开为链表 {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode pre = next;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }
}
