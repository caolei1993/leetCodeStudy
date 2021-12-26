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

# [LeetCode_686_1_重复叠加字符串匹配](https://leetcode-cn.com/problems/repeated-string-match/)
## 理解
解法一：确认复制的下界与上界，下界为叠加字符串长度大于等于b的最小复制次数，
上界为下界的复制次数+1（匹配开始坐标必定会从第一个a中开始，否则在前面必然
存在也匹配的字符串子集）。  
在确认上下界后，拼接好字符串，直接判断是否包含子串，并判断结尾坐标，来确认
复制次数，并返回。 


## 解法一
### 代码
```java
public class LeetCode_686_1_重复叠加字符串匹配 {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length()) {
            sb.append(a);
            ans++;
        }
        sb.append(a);
        int idx = sb.indexOf(b);
        if (idx == -1) {
            return -1;
        }
        return idx + b.length() > a.length() * ans ? ans + 1 : ans;
    }
}
```

# [LeetCode_1705_1_吃苹果的最大数目](https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/)
## 理解
最小堆+贪心算法，贪心每天吃的都是最快腐烂的苹果，使得我们吃的苹果的数量
达到最大化，使用优先队列实现最小堆，优先队列中存入二维数组，分别保存
苹果最后可食用的期限和苹果数量。

时间复杂度：令n为数组长度，最多有n组苹果入堆、出堆。复杂度为O(nlogn),
空间复杂度为O(n)

### 代码
```java
public class LeetCode_1705_1_吃苹果的最大数目 {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = apples.length, time = 0, ans = 0;

        while (time < n || !queue.isEmpty()) {
            // 如果没到最大天数，将产出的苹果添加到队列
            if (time < n && apples[time] > 0) {
                queue.offer(new int[]{time + days[time] - 1, apples[time]});
            }
            // 将过期腐烂的苹果去除
            while (!queue.isEmpty() && queue.peek()[0] < time) {
                queue.poll();
            }
            // 吃苹果
            if (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int count = --cur[1];
                if (cur[0] > time && count > 0) {
                    queue.offer(cur);
                }
                ans++;
            }
            time++;
        }
        return ans;
    }
}
```

# [LeetCode_1609_1_奇偶树](https://leetcode-cn.com/problems/even-odd-tree/)
## 理解
层序遍历，遍历的过程中根据层数奇偶性，判断递增或递减以及奇数偶数。

时间复杂度均为O(n)，空间复杂度为O(n)

### 代码
```java
public class LeetCode_1609_2_奇偶数 {
    public boolean isEvenOddTree(TreeNode root) {
        boolean flag = true;
        Queue<TreeNode> queue = new LinkedList<>();
        int size, pre;
        queue.offer(root);
        while (!queue.isEmpty()) {
            size = queue.size();
            pre = flag ? 0 : 0x3f3f3f3f;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (flag) {
                    if (node.val <= pre || node.val % 2 == 0) {
                        return false;
                    }
                } else {
                    if (node.val >= pre || node.val % 2 == 1) {
                        return false;
                    }
                }
                pre = node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            flag = !flag;
        }
        return true;
    }
}
```

# [LeetCode_1078_1_Bigram分词](https://leetcode-cn.com/problems/occurrences-after-bigram/)
## 理解
简单的字符串模拟，遍历字符串，当作首单词和依次匹配，匹配成功则添加到集合，
最后集合转化为数组返回。

时间复杂度为O(n)，空间复杂度为O(n)

### 代码
```java
public class LeetCode_1078_1_Bigram分词 {
    public String[] findOcurrences(String text, String first, String second) {
        String[] strs = text.split(" ");
        int len = strs.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < len - 2; i++) {
            if (strs[i].equals(first) && strs[i + 1].equals(second)) {
                ans.add(strs[i + 2]);
            }
        }
        return ans.toArray(new String[0]);
    }
}
```
