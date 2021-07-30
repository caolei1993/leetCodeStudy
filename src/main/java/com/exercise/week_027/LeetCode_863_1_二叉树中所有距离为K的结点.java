package main.java.com.exercise.week_027;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/7/28 16:37
 * @Version 1.0
 */
public class LeetCode_863_1_二叉树中所有距离为K的结点 {
    /**
     * 题目限制最多有501个节点，且每个节点最多有两个边2*（无向边2）
     */
    final static int N = 501, M = N * 4;
    /**
     * head[a]代表a节点对应边的集合的头结点（类似链表）
     */
    int[] head = new int[N];
    /**
     * end[i]表示边i指向的节点
     */
    int[] end = new int[M];
    /**
     * next[i]表示以链表形式存储的边i对应的下一条边
     */
    int[] next = new int[M];
    /**
     * idx用来编号每一条边
     */
    int idx;

    private void add (int a, int b) {
        end[idx] = b;
        next[idx] = head[a];
        head[a] = idx++;
    }

    boolean[] vis = new boolean[N];
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        Arrays.fill(head, -1);
        // 构建图
        dfs(root);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(target.val);
        vis[target.val] = true;
        while (!queue.isEmpty() && k >= 0) {
            int size = queue.size();
            while (size-- > 0) {
                int value = queue.poll();
                if (k == 0) {
                    ans.add(value);
                    continue;
                }

                for (int i = head[value]; i != -1 ; i = next[i]) {
                    // 获取i指向的结点
                    int j = end[i];
                    if (!vis[j]) {
                        queue.offer(j);
                        vis[j] = true;
                    }
                }
            }
            k--;
        }
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            add(node.val, node.left.val);
            add(node.left.val, node.val);
            dfs(node.left);
        }
        if (node.right != null) {
            add(node.val, node.right.val);
            add(node.right.val, node.val);
            dfs(node.right);
        }
    }
}
