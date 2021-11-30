package main.java.com.exercise.week_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author cl
 * @Date 2021/7/13 16:32
 * @Version 1.0
 */
public class LeetCode_218_1_天际线问题 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> ps = new ArrayList<>();
        // 将所有的左右端点都存入集合，方便做排序
        for (int[] p : buildings) {
            int l = p[0], r = p[1], h = p[2];
            ps.add(new int[]{l, -h});
            ps.add(new int[]{r, h});
        }
        // 优先使用x坐标排序，如果x坐标相等，再用高度排序
        Collections.sort(ps, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        // 定义由大到小排列的优先队列
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        // 记录前一个处理的矩形高度，初始化为0（便于最后一个端点的获取）
        int prev = 0;
        queue.offer(prev);
        // 遍历所有的点
        for (int[] point : ps) {
            int index = point[0], height = point[1];
            // 如果是左端点，高度入队，代表右边有一条延长边
            if (height < 0) {
                queue.offer(-height);
            } else {
                // 如果是右端点，高度出队，代表这个高度的边的结束
                queue.remove(height);
            }
            // 取出最高高度
            int cur = queue.peek();
            if (cur != prev) {
                List<Integer> list = new ArrayList<>();
                list.add(index);
                list.add(cur);
                ans.add(list);
                prev = cur;
            }
        }
        return ans;
    }
}
