[toc]

# [LeetCode_1185_1_一周中的第几天](https://leetcode-cn.com/problems/day-of-the-week/)
## 理解
已知1970年最后一天为星期四，我们通过计算当前年月日与1970年最后一天的差值，
再对7取余，来确认星期几，计算差值时，需要注意闰年的处理。

时间复杂度为O(C)，C为1970年最后一天到目标日期的常量  
空间复杂度为O(1)

### 代码
```java
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
```