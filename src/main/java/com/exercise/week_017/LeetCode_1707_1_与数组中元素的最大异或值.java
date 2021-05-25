package main.java.com.exercise.week_017;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/23 19:33
 */
public class LeetCode_1707_1_与数组中元素的最大异或值 {
    class Node {
        Node[] nds = new Node[2];
    }

    Node root = new Node();
    private void add(int num) {
        Node p = root;
        for (int i = 31; i >= 0; i--) {
            int a = (num >> i) & 1;
            if (p.nds[a] == null) {
                p.nds[a] = new Node();
            }
            p = p.nds[a];
        }
    }

    private int getVal(int num) {
        Node p = root;
        int val = 0;
        for (int i = 31; i >= 0; i--) {
            int a = (num >> i) & 1, b = 1 - a;
            if (p.nds[b] != null) {
                val |= b << i;
                p = p.nds[b];
            } else {
                val |= a << i;
                p = p.nds[a];
            }
        }
        return val ^ num;
    }


    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int m = nums.length;
        int[] ans = new int[n];
        Map<int[], Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(queries[i], i);
        }
        Arrays.sort(nums);
        Arrays.sort(queries, (a, b) -> a[1] - b[1]);
        int loc = 0;
        for (int[] arr : queries) {
            int x = arr[0], limit = arr[1];
            while (loc < m && nums[loc] <= limit) {
                add(nums[loc++]);
            }
            if (loc == 0) {
                ans[map.get(arr)] = -1;
            } else {
                ans[map.get(arr)] = getVal(x);
            }
        }
        return ans;
    }
}
