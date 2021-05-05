package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/25 9:23
 * @Version 1.0
 */

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
