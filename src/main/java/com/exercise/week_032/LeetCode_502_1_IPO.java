package main.java.com.exercise.week_032;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author cl
 * @Date 2021/9/13 17:42
 * @Version 1.0
 */
public class LeetCode_502_1_IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        // 预处理，将所有任务的启动资本和利润存入List并排序
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[]{capital[i], profits[i]});
        }
        list.sort((a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        // 定义添加任务的下标
        int i = 0;
        while (k-- > 0) {
            while (i < n && list.get(i)[0] <= w) {
                queue.add(list.get(i++)[1]);
            }
            if (queue.isEmpty()) {
                break;
            }
            w += queue.poll();
        }
        return w;
    }
}
