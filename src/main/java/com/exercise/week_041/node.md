[toc]

# [LeetCode_384_1_打乱数组](LeetCode_384_1_打乱数组)
## 理解
Knuth洗牌算法

### 代码
```java
public class LeetCode_384_1_打乱数组 {
    int[] num;
    int n;
    Random random = new Random();

    public LeetCode_384_1_打乱数组(int[] nums) {
        num = nums;
        n = nums.length;
    }

    public int[] reset() {
        return num;
    }

    public int[] shuffle() {
        int[] ans = num.clone();
        for (int i = n - 1; i > 0; i--) {
            swap(ans, i, random.nextInt(i - 1));
        }
        return ans;
    }

    private void swap(int[] ans, int i, int j) {
        int tem = ans[i];
        ans[i] = ans[j];
        ans[j] = tem;
    }
}
```

# [LeetCode_895_1_亲密字符串](https://leetcode-cn.com/problems/buddy-strings/)
## 理解
字符串模拟题  
需要明确亲密字符串成立的条件，如果两个字符串长度不一致，直接返回false，
如果两个字符串词频不一致，也直接返回false。

词频相同的情况下：  
1.只有两个位置字符不一样  
2.所有位置都相同，但有单个字符的词频超过1个（可以实现相同字符的互换）

以上两种情况满足其一即为亲密字符串

时间复杂度为log(n)，空间复杂度为log(2C)，C为常数26

### 代码
```java
public class LeetCode_895_1_亲密字符串 {
    public boolean buddyStrings(String s, String goal) {
        int n = s.length();
        int m = goal.length();
        if (n != m) {
            return false;
        }
        // 统计词频
        int[] cnt1 = new int[26], cnt2 = new int[26];
        // 记录不同位置的个数
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int a = s.charAt(i) - 'a', b = goal.charAt(i) - 'a';
            cnt1[a]++;
            cnt2[b]++;
            if (a != b) {
                sum++;
            }
        }
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) {
                return false;
            }
            if (cnt1[i] > 1) {
                flag = true;
            }
        }
        return sum == 2 || (sum == 0 && flag);
    }
}
```

# [LeetCode_423_1_从英文中重建数字](https://leetcode-cn.com/problems/reconstruct-original-digits-from-english/)
## 理解
根据0-9英文单词的特殊值来判断字符串是否包含了该数子，只有0包含z，z的个数
就是0的个数，只有8包含h，h的个数就是8的个数，按此顺序确认出一个，可以
唯一确认数字的顺序，以此遍历每个数子，取它的英文字母中词频最低的，删除
对应的每个字符的数量，再拼接答案，等所有数子都遍历完，需要将答案重新排序
返回

### 代码
```java
public class LeetCode_423_1_从英文中重建数字 {
    static String[] ss = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static int[] priority = new int[]{0, 8, 6, 3, 2, 7, 5, 9, 4, 1};

    public String originalDigits(String s) {
        int n = s.length();
        int[] cnts = new int[26];
        // 统计字符串中各个字母的词频
        for (int i = 0; i < n; i++) {
            cnts[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int v : priority) {
            int k = Integer.MAX_VALUE;

            char[] cc = ss[v].toCharArray();
            for (char c : cc) {
                k = Math.min(k, cnts[c - 'a']);
            }
            for (char c : cc) {
                cnts[c - 'a'] -= k;
            }
            while (k-- > 0) {
                sb.append(v);
            }
        }
        char[] chArr = sb.toString().toCharArray();
        Arrays.sort(chArr);
        return String.valueOf(chArr);
    }
}
```

# [LeetCode_438_1_找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/)
## 理解
解法一：利用双指针实现滑动窗口，统计p字符串与窗口字符串的字母词频，如果
完全一致，则说明是一组异位词，添加窗口最左端的坐标到目标集合

时间复杂度：统计p的词频的复杂度为O(m)，check两个数组词频的复杂度为
O(n*C)，C为常数26，所以整体复杂度为O(n*C + m)  
空间复杂度为O(C)

解法二：解法一中每次都需要对比两个数组的词频，我们可以优化一下，
统计了p的词频后，在滑动窗口中统计词频相当于对p中词频的抵消，
统计p中字母的种类的个数，统计窗口词频抵消为0时，统计增加s的种类
个数，窗口左端的字母统计增加词频，同时判断词频如果为1，减少s的字母
种类个数。如果p与窗口的字母种类个数相同则将窗口左端添加到目标数组。

时间复杂度为O(m + n + C)  
空间复杂度为O(C)

## 解法一
### 代码
```java
public class LeetCode_438_1_找到字符串中所有字母异位词 {
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        // 统计p的词频
        int[] cnt = new int[26];
        for (char c : p.toCharArray()) {
            cnt[c - 'a']++;
        }
        int[] cnt1 = new int[26];
        List<Integer> ans = new ArrayList<>();
        for (int l = 0, r = 0; r < n; r++) {
            cnt1[s.charAt(r) - 'a']++;
            if (r - l + 1 > m) {
                cnt1[s.charAt(l) - 'a']--;
                l++;
            }
            if (check(cnt, cnt1)) {
                ans.add(l);
            }
        }

        return ans;

    }

    private boolean check(int[] cnt, int[] cnt1) {
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != cnt1[i]) {
                return false;
            }
        }
        return true;
    }

}
```

## 解法二
### 代码
```java
public class LeetCode_438_2_找到字符串中所有字母异位词 {
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        // 词频统计数组
        int[] cnt = new int[26];
        for (char c : p.toCharArray()) {
            cnt[c - 'a']++;
        }
        // 统计p中有多少种字母
        int a = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                a++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int l = 0, r = 0, b = 0; r < n; r++) {
            int i = s.charAt(r) - 'a';
            cnt[i]--;
            if (cnt[i] == 0) {
                b++;
            }
            if (r - l + 1 > m) {
                int j = s.charAt(l) - 'a';
                cnt[j]++;
                l++;
                if (cnt[j] == 1) {
                    b--;
                }
            }
            if (a == b) {
                ans.add(l);
            }
        }
        return ans;
    }
}
```

# [LeetCode_495_1_提莫攻击](https://leetcode-cn.com/problems/teemo-attacking/)
## 理解
解法一：从第二次攻击开始遍历，对比每次攻击的间隔和预定中毒时间的大小，谁小选择谁，需要特殊处理
最后一次攻击。  
时间复杂度为O(n)，空间复杂度为O(1)

解法二：对比当前攻击时间与前一次攻击中毒的清醒时间，如果攻击时间大于等于清醒时间则直接累加一次
中毒周期，否则需要计算本次攻击时间与前一次攻击时间的间隔，进行累加。  
复杂度同上

## 解法一
### 代码
```java
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

```

## 解法二
### 代码
```java
public class LeetCode_495_2_提莫攻击 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        int e = 0;
        for (int time : timeSeries) {
            if (time >= e) {
                ans += duration;
            } else {
                ans += time - e + duration;
            }
            e = time + duration;
        }
        return ans;
    }
}
```

# [LeetCode_375_1_猜数字大小II](https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/)
## 理解
使用区间DP来解决问题  
状态定义：f[i][j]表示在范围 [i, j][i,j] 内确保胜利的最少金额  

假设我们第一个猜的数字是x，猜错了，需要支付x金额，真实值比x大的情况下，我们
还需要再交f[1][x - 1]，真实值比x小的情况下我们还需要叫f[x + 1][n]，最终我们需要确保一定胜利，
所以f[1][n] = x + max(f[1][x - 1], f[x + 1][n])  
由于f[1][x - 1]和f[x + 1][n]都是比原始问题[1, n]更小的区间，所以我们可以使用动态规划来求解

数据边界：当i=j时，只包含一个数字，不会猜错，所以f[i][j] = 0，当i > j时，[i,j]区间不存在，
所以我们dp的范围为i < j

转移方程的确立：在i,j区间里，第一次猜测的数字k可能是任意数字，得到的金额花费为
max(f[i][k - 1], f[k + 1][j]) + k，k可能为i到j中的任意值，我们需要取所有方案中的最小值，
赋值给f[i][j]

由于状态转移方程为根据规模小的子问题计算规模大的子问题，因此计算子问题的顺序为先计算规模小的子问题，
后计算规模大的子问题，需要注意循环遍历的方向。

### 代码
```java
public class LeetCode_375_1_猜数字大小II {
    public int getMoneyAmount(int n) {
        // f[i][j]代表在区间[i,j]中猜数的最小花费
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1 ; i--) {
            for (int j = i + 1; j <= n; j++) {
                int val = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cur = Math.max(f[i][k - 1], f[k+1][j]) + k;
                    val = Math.min(val, cur);
                }
                f[i][j] = val;
            }
        }
        return f[1][n];
    }
}
```