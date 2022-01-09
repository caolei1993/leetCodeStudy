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

# [LeetCode_71_1_简化路径](https://leetcode-cn.com/problems/simplify-path/)
## 理解
利用双端队列辅助解决，不断将完整的item放入队列尾部，如果值为'..'，则队列
尾部出队，如果为'.'，则不操作，其余都从队尾入队，最后从队头组合返回路径返回。

时间复杂度为O(n)  
空间复杂度为O(n)

### 代码
```java
public class LeetCode_71_1_简化路径 {
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        int n = path.length();
        for (int i = 0; i < n;) {
            if (path.charAt(i) == '/') {
                i++;
                continue;
            }
            int j = i + 1;
            while (j < n && path.charAt(j) != '/') {
                j++;
            }
            String item = path.substring(i, j);
            if (item.equals("..")) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else if (!item.equals(".")) {
                deque.addLast(item);
            }
            i = j;
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/").append(deque.pollFirst());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
```

# [LeetCode_1614_1_括号的最大嵌套深度](https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses/)
## 理解
简单字符串模拟题，将字符串转换为字符数组，遍历数组，遇到'('统计值加1，
遇到')'统计值减1，返回统计过程中的最大值，即为最大嵌套深度。

时间复杂度为O(n)  
空间复杂度为O(n)，使用charAt函数遍历可以将空间复杂度降为O(1)

### 代码
```java
public class LeetCode_1614_1_括号的最大嵌套深度 {
    public int maxDepth(String s) {
        char[] cs = s.toCharArray();
        int ans = 0, val = 0;
        for (char c : cs) {
            if (c == '(') {
                val++;
            }
            if (c == ')') {
                val--;
            }
            ans = Math.max(ans, val);
        }
        return ans;
    }
}
```

# [LeetCode_89_1_格雷编码](https://leetcode-cn.com/problems/gray-code/)
## 理解
对称求解法，将n位格雷编码序列翻转拼接在原队列之后，前后两部分都满足格雷
编码的规则，只需要处理链接处的关系，我们将后续的翻转队列都加1，保证链接
处二进制只有一位不同，首位也只有一位不同。

时间复杂度为O(n^2)  
空间复杂度为O(1)

## 解法一
### 代码
```java
public class LeetCode_89_1_格雷编码 {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        while (n-- > 0) {
            int m = ans.size();
            for (int i = m - 1; i >= 0; i--) {
                ans.set(i, ans.get(i) << 1);
                ans.add(ans.get(i) + 1);
            }
        }
        return ans;
    }
}
```
## 理解
公式法

时间复杂度为O(2^n)  
空间复杂度为O(1)

## 解法二
### 代码
```java
public class LeetCode_89_2_格雷编码 {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ans.add((i >> 1) ^ i);
        }
        return ans;
    }
}
```

# [LeetCode_1629_1_按键持续时间最长的键](https://leetcode-cn.com/problems/slowest-key/)
## 理解
简单字符串模拟题，依次遍历按键时间，计算每个键的按的时间，遍历过程中，
计算按键时间最长的键，如果按键时间一样，则判断字符大小，取字符较大者。

时间复杂度为O(n)  
空间复杂度为O(1)

### 代码
```java
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
```