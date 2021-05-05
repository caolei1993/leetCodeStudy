package main.java.com.exercise.week_010;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/4/1 7:37
 * @Version 1.0
 * https://leetcode-cn.com/problems/clumsy-factorial/
 */
public class LeetCode_1006_1_笨阶乘 {
    public int clumsy(int N) {
        Queue<Integer> queue = new LinkedList<>();
        // a代表来判断*/+-的临时值，b为符号位代表+，-
        int a = 0, ans = 0, b = 1;
        int pre = N;
        for (int i = N-1; i > 0; i--) {
            a = a % 4;
            switch (a) {
                case 0:
                    pre = pre * i;
                    break;
                case 1:
                    pre = pre / i;
                    queue.offer(pre * b);
                    pre = 0;
                    break;
                case 2:
                    b = 1;
                    queue.offer(i * b);
                    break;
                case 3:
                    b = -1;
                    pre = i;
                    break;
                default:
            }
            a++;
        }
        if (pre != 0) {
            queue.offer(pre * b);
        }
        while (!queue.isEmpty()) {
            ans += queue.poll();
        }
        return ans;
    }
}
