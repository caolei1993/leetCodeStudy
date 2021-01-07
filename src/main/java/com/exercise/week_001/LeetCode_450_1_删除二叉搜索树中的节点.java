package main.java.com.exercise.week_001;

/**
 * @Author cl
 * @Date 2021/1/7 18:48
 * @Version 1.0
 */
public class LeetCode_450_1_删除二叉搜索树中的节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            // 叶子节点（无规定，返回）
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null) {
                root.val = predecessor(root).val;
                root.left = deleteNode(root.left, root.val);
            } else {
                root.val = successor(root).val;
                root.right = deleteNode(root.right, root.val);
            }
        }

        return root;
    }

    /**
     * 查询node的前驱
     * @param node
     * @return
     */
    private TreeNode predecessor(TreeNode node) {
        // 1.有左子节点，查询左子节点中最大的
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node;


        // 2.无左子节点，有父节点，查询父节点，一直查询到节点是父节点的右节点，否则没有前驱

        // 3.无左节点，无父节点，没有前驱
    }

    /**
     * 查询node的后继
     * @param node
     * @return
     */
    private TreeNode successor(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

}
