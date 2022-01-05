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

# [LeetCode_1576_1_替换所有的问号](https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/)
## 理解
字符串模拟题，每个位置最多有两边相邻，所以我们只需要每个位置最多遍历3次，
即可找到一个与相邻都不同的字符，依次类推，处理完字符数组，最终转化为字符
串返回即可。

时间复杂度：O(n*C)，C为常数3  
空间复杂度：O(n)

### 代码
```java
public class LeetCode_1576_1_替换所有的问号 {
    public String modifyString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3 && cs[i] == '?'; j++) {
                boolean flag = true;
                if (i - 1 >= 0 && cs[i - 1] == 'a' + j) {
                    flag = false;
                }
                if (i + 1 < n && cs[i + 1] == 'a' + j) {
                    flag = false;
                }
                if (flag) {
                    cs[i] = (char)('a' + j);
                }
            }
        }
        return String.valueOf(cs);
    }
}
```