package main.java.com.exercise.week_039;

/**
 * @Author cl
 * @Date 2021/10/29 17:05
 * @Version 1.0
 */
public class LeetCode_335_1_路径交叉 {
    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        if (n < 4) {
            return false;
        }
        for (int i = 3; i < n; i++) {
            // d[i]与d[i - 3]相交
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
                return true;
            }
            // d[i]与d[i - 4]相交
            if (i >= 4 && distance[i - 1] == distance[i - 3] && distance[i] + distance[i - 4] >= distance[i - 2]) {
                return true;
            }
            // d[i]与d[i - 5]相交
            if (i >= 5 && distance[i] + distance[i - 4] >= distance[i - 2] &&
                    distance[i - 1] + distance[i - 5] >= distance[i - 3] &&
                    distance[i - 1] < distance[i - 3] && distance[i - 2] > distance[i - 4]) {
                return true;
            }
        }
        return false;
    }
}
