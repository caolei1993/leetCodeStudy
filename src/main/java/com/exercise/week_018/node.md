[toc]
# [LeetCode_1787_1_使所有区间的异或结果为零](https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero/)
## 题目
给你一个整数数组 nums​​​ 和一个整数 k​​​​​ 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 right（包括 left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。

返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。

 

示例 1：
```
输入：nums = [1,2,0,3,0], k = 1
输出：3
解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
```

示例 2：
```
输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
输出：3
解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
```

示例 3：
```
输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
输出：3
解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
```

提示：

* 1 <= k <= nums.length <= 2000
* 0 <= nums[i] < 2^10

## 理解
从示例可以看出，其实就是每k个元素
一组进行重复，我们证明一下：  
假设[i,j]区间的长度为k，nums[i] ^ nums[i+1] ^ …… ^ nums[j] = 0，  
区间整体右移一个长度，nums[i+1] ^ nums[i+2] ^ …… ^ nums[j+1] = 0，  
两个区间的等式整体异或，中间元素重复抵消，最终得到nums[i] ^ nums[j+1] = 0，  
即nums[i] = nums[j+1]，上面的猜想得到了证明。  
将整个数组，分解为k列，n行的矩阵，最后一行可能不会满（因为题意也没
说，nums的长度一定是k的整数倍），问题转化为求取，最终使得每列相等，
同时首行的异或值为0的最小修改次数。  
采用动态规划，f[i][xor]代表考虑前i+1列，且首行前i+1列异或值为
xor的最小修改次数，最终答案为f[k-1][0]。  
第一维的取值范围为[0,k)，根据题意0 <= nums[i] < 2^10，异或
为不进位的加法，所以第二维度的范围为[0,1024)  
我们可以使用map统计每列每个数字出现的次数，用cnt统计每列数字的个数（
因为最后一行可能不满k个）  
不是一般性的考虑转移方程：  
* 如果当前处于第0列，那么f[0][xor]只需要考虑需要把多少个不为xor的
数变为xor，即f[0][xor] = cnt - map.get(xor)  
* 如果不是首列，需要考虑与前一列的关系：  
f[i][xor]由两部分组成，一个是前i-1列的修改次数，以及本列的修改次数。
  1. 这个时候需要分开考虑，只修改本列的部分数据，这个时候需要遍历本列
    的所有数字，找到修改的最小个数。  
    f[i][xor] = f[i-1][xor^num] + (cnt - map.get(num))
  2. 修改本列的所有数据，这个我们就得知道前i-1列的最小的修改次数，
    我们利用g数组提前存储起来
    f[i][xor] = g[i-1] + cnt
     
f[i][xor]的最终值，取以上两种情况中的较小值。

### 代码
```java
public class LeetCode_1787_1_使所有区间的异或结果为零 {
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int max = 1024;
        // f[i][j]代表前i+1列，首行异或结果为j的最小修改次数
        int[][] f = new int[k][max];
        // 记录每列最小修改次数的值
        int[] g = new int[k];
        Arrays.fill(g, Integer.MAX_VALUE);

        // 遍历每列, i为列数，cnt记录每列总共有多少个数字
        for (int i = 0, cnt = 0; i < k; i++, cnt = 0) {
            // 记录每列中每个值的个数
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            // 首列，只需要考虑修改几个得到xor值即可
            if (i == 0) {
                for (int xor = 0; xor < max; xor++) {
                    f[0][xor] = cnt - map.getOrDefault(xor, 0);
                    g[0] = Math.min(g[0], f[0][xor]);
                }
            } else {
                // 非首列，需要考虑前一列
                for (int xor = 0; xor < max; xor++) {
                    // 整列修改
                    f[i][xor] = g[i - 1] + cnt;
                    // 非整列修改
                    for (int num: map.keySet()) {
                        f[i][xor] = Math.min(f[i][xor], f[i - 1][xor ^ num] + cnt - map.get(num));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }
        }
        return f[k - 1][0];
    }
}
```
# [LeetCode_1190_1_反转每对括号间的子串](https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/)
## 题目
给出一个字符串 s（仅含有小写英文字母和括号）。

请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。

注意，您的结果中 不应 包含任何括号。

 

示例 1：
```
输入：s = "(abcd)"
输出："dcba"
```

示例 2：
```
输入：s = "(u(love)i)"
输出："iloveu"
```

示例 3：
```
输入：s = "(ed(et(oc))el)"
输出："leetcode"
```

示例 4：
```
输入：s = "a(bcdefghijkl(mno)p)q"
输出："apmnolkjihgfedcbq"
```

提示：

* 0 <= s.length <= 2000
* s 中只有小写英文字母和括号
* 我们确保所有括号都是成对出现的

## 理解
解法一：使用双端队列，从前往后遍历每个字符，把不是')'的所有字符
都从队尾入队，当遍历到')'时，从队尾出队字符，直到队尾为'('，也就
是找到一组匹配的（），将中间元素反转再从队尾入队（注意剔除括号），
依次直到元素遍历完，最后从队首出队元素组成string返回。
解法二：利用数组代替双端队列，提高处理效率，利用数组和双指针来
替代双端队列。

## 解法一
### 代码
```java
public class LeetCode_1190_1_反转每对括号间的子串 {
    public String reverseParentheses(String s) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        List<Character>  subList;
        for (char c : s.toCharArray()) {
            if (c != ')') {
                deque.offerLast(c);
            } else {
                subList = new ArrayList<>();
                while (deque.peekLast() != '(') {
                    subList.add(deque.pollLast());
                }
                // 去除‘（’
                deque.pollLast();
                // 将反转的字符串放回deque（从后往前取值放入集合，再从前往后放入队列，相当于已经做了反转）
                for (char cc : subList) {
                    deque.offerLast(cc);
                }
            }
        }
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_1190_2_反转每对括号间的子串 {
    char[] deque = new char[2000];
    int head = 0, tail = -1;
    char[] sub = new char[2000];
    public String reverseParentheses(String s) {
        for (char c : s.toCharArray()) {
            if (c == ')') {
                int ids = 0;
                while (tail >= head) {
                    if (deque[tail] == '(') {
                        tail--;
                        for (int i = 0; i < ids; i++) {
                            deque[++tail] = sub[i];
                        }
                        break;
                    } else {
                        sub[ids++] = deque[tail--];
                    }
                }
            } else {
                deque[++tail] = c;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (tail >= head) {
            sb.append(deque[head++]);
        }
        return sb.toString();
    }
}
```

# [LeetCode_231_1_2的幂](https://leetcode-cn.com/problems/power-of-two/)
## 题目
给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。

如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。

示例 1：
```
输入：n = 1
输出：true
解释：20 = 1
```

示例 2：
```
输入：n = 16
输出：true
解释：24 = 16
```

示例 3：
```
输入：n = 3
输出：false
```

示例 4：
```
输入：n = 4
输出：true
```

示例 5：
```
输入：n = 5
输出：false
```

提示：

* -231 <= n <= 231 - 1
 
进阶：你能够不使用循环/递归解决此问题吗？

## 理解
解法一：通过循环处理，如果值是2的幂，肯定能整除2，通过不断的除以2，最终结果肯定等于1，
最终为2/2 = 1.
解法二：利用lowbit，求取二进制中最低位为1的值，因为求取的为2的幂，那么转化为2进制肯定
只包换一个1，所以通过lowbit求取的值应该与它本身相等。  
也可变相转化为 n & n - 1 == 0，舍弃最高位1后，值为0。

## 解法一
### 代码
```java
public class LeetCode_231_1_2的幂 {
    public boolean isPowerOfTwo(int n) {
        if (n < 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_231_2_2的幂 {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }
}
```