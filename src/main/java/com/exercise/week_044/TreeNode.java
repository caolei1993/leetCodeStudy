package main.java.com.exercise.week_044;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/25 10:31 下午
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
