package main.java.com.exercise.week_041;

/**
 * @Author cl
 * @Date 2021/11/8 14:09
 * @Version 1.0
 */
public class LeetCode_299_1_猜数字游戏 {
    public String getHint(String secret, String guess) {
        int n = secret.length();
        int[] cn1 = new int[10];
        int[] cn2 = new int[10];
        // 公牛数量,奶牛数量
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            int c1 = secret.charAt(i) - '0', c2 = guess.charAt(i) - '0';
            if (c1 == c2) {
                a++;
            } else {
                cn1[c1]++;
                cn2[c2]++;
            }
        }
        for (int i = 0; i < 10; i++) {
            b += Math.min(cn1[i], cn2[i]);
        }
        return a + "A" + b + "B";
    }
}
