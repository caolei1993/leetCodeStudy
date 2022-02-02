[toc]

# [LeetCode_1763_1_最长的美好子字符串](https://leetcode-cn.com/problems/longest-nice-substring/)
## 理解
常规朴素解法，我们遍历所有子串，时间复杂度为O(n^2)，再对子串进行合规检查，时间
复杂度为O(n)

时间复杂度整体为O(n^3)  
空间复杂度为O(n)

## 解法一
### 代码
```java
public class LeetCode_1763_1_最长的美好子字符串 {
    public String longestNiceSubstring(String s) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i + 1 > ans.length() && check(s.substring(i, j + 1))) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    private boolean check(String str) {
        Set<Character> set = new HashSet<>();
        char[] charStr = str.toCharArray();
        for (char c : charStr) {
            set.add(c);
        }
        for (char c : charStr) {
            char a = Character.toLowerCase(c);
            char b = Character.toUpperCase(c);
            if (!set.contains(a) || !set.contains(b)) {
                return false;
            }
        }
        return true;
    }
}
```

## 理解
我们只需要确认大小写字符是否同时出现，而不关心出现次数，所以我们可以借助二进制
值来存储某个字符的大小写是否出现，利用int值的低26位来存储字符是否出现，利用a
存储小写出现情况，利用b来存储大写出现的情况，最后比较a和b来确认是否大小写同时
出现。

时间复杂度为O(n^2)  
空间复杂度为O(1)

## 解法二
### 代码
```java
public class LeetCode_1763_2_最长的美好子字符串 {
    public String longestNiceSubstring(String s) {
        int n = s.length();
        int idx = -1, len = 0;
        for (int i = 0; i < n; i++) {
            int a = 0, b = 0;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (c >= 'a' && c <= 'z') {
                    a |= 1 << (c - 'a');
                } else {
                    b |= 1 << (c - 'A');
                }
                if (j - i + 1 > len && a == b) {
                    idx = i;
                    len = j - i + 1;
                }
            }
        }
        return idx == -1 ? "" : s.substring(idx , idx + len);
    }
}
```

# [LeetCode_2000_1_反转单词前缀](https://leetcode-cn.com/problems/reverse-prefix-of-word/)
## 理解
利用双指针进行的字符串模拟，遍历字符串中的字符，找到需要翻转的点，利用双指针进行
翻转，最终返回翻转后的字符数组转化的字符串即可。

时间复杂度为O(n)  
空间复杂度为O(n)

### 代码
```java
public class LeetCode_2000_1_反转单词前缀 {
    public String reversePrefix(String word, char ch) {
        char[] chs = word.toCharArray();
        int n = chs.length, idx = -1;
        for (int i = 0; i < n && idx == -1; i++) {
            if (chs[i] == ch) {
                idx = i;
            }
        }
        int l = 0, r = idx;
        while (l < r) {
            char c = chs[l];
            chs[l++] = chs[r];
            chs[r--] = c;
        }
        return String.valueOf(chs);
    }
}
```