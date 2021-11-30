package main.java.com.exercise.week_041;

/**
 * @Author cl
 * @Date 2021/11/10 13:59
 * @Version 1.0
 */
public class LeetCode_495_2_提莫攻击 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        int e = 0;
        for (int time : timeSeries) {
            if (time >= e) {
                ans += duration;
            } else {
                ans += time - e + duration;
            }
            e = time + duration;
        }
        return ans;
    }
}
