package main.java.com.exercise.week_027;

/**
 * @Author cl
 * @Date 2021/7/27 14:50
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