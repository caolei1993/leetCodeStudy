package main.java.com.exercise.week_003;

/**
 * @Author cl
 * @Date 2021/1/22 13:54
 * @Version 1.0
 */
public class LeetCode_104_2_二叉树的最大深度 {
    public int maxDepth(TreeNode root) {
      if (root == null) {
          return 0;
      }
      return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
