package main.java.com.exercise.week_046;

/**
 * @author cl
 * @version 1.0
 * @date 2022/1/3 8:24 下午
 */
public class LeetCode_1185_1_一周中的第几天 {
    static String[] ss = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public String dayOfTheWeek(int day, int month, int year) {
        int ans = 4;
        for (int y = 1971; y < year; y++) {
            if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
                ans += 366;
            } else {
                ans += 365;
            }
        }
        for (int m = 1; m < month; m++) {
            ans += days[m - 1];
            if (m == 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
                ans++;
            }
        }
        ans += day;
        return ss[ans % 7];
    }
}
