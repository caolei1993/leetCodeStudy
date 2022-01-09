package main.java.com.exercise.week_046;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/9 9:09 下午
 */
public class LeetCode_1629_1_按键持续时间最长的键 {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char ans = keysPressed.charAt(0);
        int val = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            int cur = releaseTimes[i] - releaseTimes[i - 1];
            char c = keysPressed.charAt(i);
            if (cur > val) {
                ans = c;
                val = cur;
            } else if (cur == val) {
                ans = ans - c >= 0 ? ans : c;
            }
        }
        return ans;
    }
}
