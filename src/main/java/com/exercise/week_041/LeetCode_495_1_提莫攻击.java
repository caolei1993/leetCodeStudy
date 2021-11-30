package main.java.com.exercise.week_041;

/**
 * @Author cl
 * @Date 2021/11/10 11:10
 * @Version 1.0
 */
public class LeetCode_495_1_提莫攻击 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            // 两次攻击之间如果间隔超过最大中毒时间，取最大中毒事件，否则取两次攻击的间隔时间
            ans += Math.min(duration, timeSeries[i] - timeSeries[i - 1]);
        }
        // 最后一次攻击
        ans += duration;
        return ans;
    }
}
