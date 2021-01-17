package main.java.com.exercise.week_002;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/15 17:39
 * @Version 1.0
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 */
public class LeetCode_230_1_二叉搜索树中第K小的元素 {
    private List<Integer> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        inorder(root);
        if (list.isEmpty()) {
            return 0;
        }
        return list.get(k-1);
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }
}
