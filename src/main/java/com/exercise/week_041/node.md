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