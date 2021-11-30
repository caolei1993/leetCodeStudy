package main.java.com.exercise.week_030;

/**
 * @Author cl
 * @Date 2021/8/17 10:24
 * @Version 1.0
 */
public class LeetCode_551_1_学生出勤记录I {
    public boolean checkRecord(String s) {
        int n = s.length();
        int aCount = 0;
        for (int i = 0; i < n; ) {
            char c = s.charAt(i);
            switch (c) {
                case 'A':
                    aCount++;
                    if (aCount >= 2) {
                        return false;
                    }
                    i++;
                    break;
                case 'L':
                    int lCount = 1;
                    while (++i < n && s.charAt(i) == 'L') {
                        lCount++;
                    }
                    if (lCount >= 3) {
                        return false;
                    }
                    break;
                default:
                    i++;
            }
        }
        return true;
    }
}
