[toc]

# [LeetCode_475_1_供暖器](https://leetcode-cn.com/problems/heaters/)
## 理解
二分双指针运用题，整体半径数据满足两段性，大于等于ans的都能覆盖所有房屋，小于
ans的不能覆盖所有房屋。  
check方法的判断，利用双指针，每次找到能覆盖某个房屋的最小供暖器，然后判断屋子
在供暖器的半径范围内即可，如果遍历过程中有不满足的，返回false，否则遍历
结束都能覆盖，返回true。

### 代码
```java
public class LeetCode_475_1_供暖器 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int l = 0, r = (int)1e9;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(houses, heaters, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean check(int[] houses, int[] heaters, int x) {
        int n = houses.length, m = heaters.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && heaters[j] + x < houses[i]) {
                j++;
            }
            if (j < m && heaters[j] - x <= houses[i] && heaters[j] + x >= houses[i]) {
                continue;
            }
            return false;
        }
        return true;
    }
}
```

# [LeetCode_1154_1_一年中的第几天](https://leetcode-cn.com/problems/day-of-the-year/)
## 理解
简单字符串模拟题，因为每年每月的天数是固定的，除了闰年2月29天，所以可以
提前打表，使用前缀和，算出累加天数，再判断年份是否是闰年，再特殊处理

时间复杂度和空间复杂度均为O(1)

### 代码
```java
public class LeetCode_1154_1_一年中的第几天 {
    static int[] nums = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int[] f = new int[13];
    static {
        // 初始化前缀和
        for (int i = 1; i <= 12; i++) {
            f[i] = nums[i - 1] + f[i - 1];
        }
    }
    public int dayOfYear(String date) {
        String[] s = date.split("-");
        int y = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]), d = Integer.parseInt(s[2]);
        boolean isLeaf = (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
        int ans = m > 2 && isLeaf ? f[m - 1] + 1 : f[m - 1];
        return ans + d;
    }
}
```