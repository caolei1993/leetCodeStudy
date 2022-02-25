[toc]

# [LeetCode_1405_1_最长快乐字符串](https://leetcode-cn.com/problems/longest-happy-string/)
## 理解
贪心思想问题，每次都将剩余最多的字符添加到最后，保证拼接的字符串最长，这里可以
借助优先队列来实现每次取剩余字符最多的字符

这里需要注意，如果字符串长度超过2，并且前面两个字符与当前取出的字符一样，就不
能再添加了，只能取队列中的下一个多的字符，如果队列为空，则直接返回。

### 代码
```java
public class LeetCode_1405_1_最长快乐字符串 {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y)-> y[1] - x[1]);
        if (a != 0) {
            queue.offer(new int[]{0, a});
        }
        if (b != 0) {
            queue.offer(new int[]{1, b});
        }
        if (c != 0) {
            queue.offer(new int[]{2, c});
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 1) - 'a' == cur[0] && sb.charAt(len - 2) - 'a' == cur[0]) {
                if (queue.isEmpty()) {
                    break;
                }
                int[] next = queue.poll();
                sb.append((char)(next[0] + 'a'));
                next[1]--;
                if (next[1] > 0) {
                    queue.offer(next);
                }
                queue.offer(cur);
            } else {
                sb.append((char)(cur[0] + 'a'));
                cur[1]--;
                if (cur[1] > 0) {
                    queue.offer(cur);
                }
            }
        }
        return sb.toString();
    }
}
```

# [LeetCode_917_1_仅仅反转字母](https://leetcode-cn.com/problems/reverse-only-letters/)
## 理解
双指针应用题，使用双指针分别从左边和右边寻找字母并交换

### 代码
```java
public class LeetCode_917_1_仅仅反转字母 {
    public String reverseOnlyLetters(String s) {
        char[] cs = s.toCharArray();
        int l = 0, r = cs.length - 1;
        while (l < r) {
            // 从左到右查询字母
            while (!Character.isLetter(cs[l]) && l < r) {
                l++;
            }
            // 从右到左查询字母
            while (!Character.isLetter(cs[r]) && r > l) {
                r--;
            }
            if (l < r) {
                char mid = cs[r];
                cs[r--] = cs[l];
                cs[l++] = mid;
            }
        }
        return String.valueOf(cs);
    }
}
```

# [LeetCode_537_1_复数乘法](https://leetcode-cn.com/problems/complex-number-multiplication/)
## 理解
简单字符串模拟题，使用正则切割，确认实部和虚部的值，再根据复数的乘法规则  
(a + bi) * (c + di) = (ac - bd) + (ad + bc)i  
组装结果并返回

### 代码
```java
public class LeetCode_537_1_复数乘法 {
    public String complexNumberMultiply(String num1, String num2) {
        String[] s1 = num1.split("\\+|i"), s2 = num2.split("\\+|i");
        int a = Integer.parseInt(s1[0]);
        int b = Integer.parseInt(s1[1]);
        int c = Integer.parseInt(s2[0]);
        int d = Integer.parseInt(s2[1]);
        int x = a * c - b * d;
        int y = a * d + b * c;
        return x + "+" + y +"i";
    }
}
```