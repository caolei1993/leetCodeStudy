[toc]

# [LeetCode_1716_1_计算力扣银行的钱](https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank/)
## 理解
遍历模拟

时间复杂度为O(n)  
空间复杂度为O(1)

## 解法一
### 代码
```java
public class LeetCode_1716_1_计算力扣银行的钱 {
    public int totalMoney(int n) {
        int ans = 0;
        int start = 1;
        for(int i = 1; i <= n;) {
            int val = start;
            for (int j = 1; j <= 7 && i <= n; j++) {
                ans += val++;
                i++;
                if (j == 7) {
                    start++;
                }
            }
        }
        return ans;
    }
}
```

## 理解
使用等差公式求和，每一周的所有天数是等差数列，每周和每周的总和也是等差数列

时间复杂度为O(1)  
空军复杂度为O(1)

## 解法二
### 代码
```java
public class LeetCode_1716_2_计算力扣银行的钱 {
    public int totalMoney(int n) {
        int a = n / 7, b = n % 7;
        int a1 = (1 + 1 + 6) * 7 / 2, an = (a + a + 6) * 7 / 2;
        int ans = (a1 + an) * a / 2;
        a++;
        ans += (a + a + b - 1) * b / 2;
        return ans;
    }
}
```

# [LeetCode_382_1_链表的随机节点](https://leetcode-cn.com/problems/linked-list-random-node/)
## 理解
使用集合来存储链表元素，再使用随机函数取集合中的元素

时间复杂度为O(n)  
空间复杂度为O(n)

## 解法一
### 代码
```java
public class LeetCode_382_1_链表的随机节点 {
    List<ListNode> list = new ArrayList<>();
    public LeetCode_382_1_链表的随机节点(ListNode head) {
        while(head != null) {
            list.add(head);
            head = head.next;
        }
    }

    public int getRandom() {
        int size = list.size();
        Random random = new Random();
        return list.get(random.nextInt(size)).val;
    }
}
```

## 理解
采用蓄水池抽样算法

每次都只取一个数，当遇到第i个数时，我们有1/i的概率保留它，有(i-1)/i的
概率保留原来的数。

假设我们从1-10中选数  
* 当我们遍历到第一个数1时，我们有1/1的概率选中它
* 当我们遍历到2时，我们有1/2的概率选择2，有1/2的概率选择1
* 当我们遍历到3时，我们有1/3的概率选择3，有2/3的概率选择1，此时1被
保留的总概率为1/2 * 2/3 = 1/3
* 当我们遍历到4时，我们有1/4的概率选择4，有3/4的概率选择1，此时1被
保留的总的概率为1/2 * 2/3 * 3/4 = 1/4 
  
 ........

以此类推每个数子被选取的概率都为1/n

时间复杂度为O(n)  
空间复杂度为O(1)


## 解法二
### 代码
```java
public class LeetCode_382_2_链表的随机节点 {
    ListNode node;
    Random random = new Random();
    public LeetCode_382_2_链表的随机节点(ListNode head) {
        node = head;
    }

    public int getRandom() {
        int i = 1;
        int ans = 0;
        ListNode n = node;
        while (n != null) {
            if (random.nextInt(i) == 0) {
                ans = n.val;
            }
            i++;
            n = n.next;
        }
        return ans;
    }
}
```

# [LeetCode_1220_1_统计元音字母序列的数目](https://leetcode-cn.com/problems/count-vowels-permutation/)
## 理解
线性DP  

定义f[i][j]代表长度为i+1，结尾为['a', 'e', 'i', 'o', 'u']的下标
[0,4]的所有方案数

我们考虑如何从f[i]得到f[i + 1], 根据题意我们可知道几种情况：  

比如：每个元音 'a' 后面都只能跟着 'e'，可得f[i + 1][1] += f[i][0],
依次类推各个下标的转移方程

最终累加f[n - 1]的所有元素取模返回，注意数组与答案都定义为long型，
防止溢出。

时间复杂度为O(n * C)，C为常数5  
空间复杂度为O(n * C)

### 代码
```java
public class LeetCode_1220_1_统计元音字母序列的数目 {
    int MOD = (int)1e9 + 7;
    public int countVowelPermutation(int n) {
        // f[i][j]代表长度为i+1的字符串，结尾为j(元音字符的下标)的总的方案数
        long[][] f = new long[n][5];
        Arrays.fill(f[0], 1);
        for (int i = 0; i < n - 1; i++) {
            // 每个元音 'a' 后面都只能跟着 'e'
            f[i + 1][1] += f[i][0];

            // 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
            f[i + 1][0] += f[i][1];
            f[i + 1][2] += f[i][1];

            // 每个元音 'i' 后面 不能 再跟着另一个 'i'
            f[i + 1][0] += f[i][2];
            f[i + 1][1] += f[i][2];
            f[i + 1][3] += f[i][2];
            f[i + 1][4] += f[i][2];

            // 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
            f[i + 1][2] += f[i][3];
            f[i + 1][4] += f[i][3];

            // 每个元音 'u' 后面只能跟着 'a'
            f[i + 1][0] += f[i][4];
            for (int j = 0; j < 5; j++) {
                f[i + 1][j] %= MOD;
            }
        }
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans += f[n - 1][i];
        }
        return (int)(ans % MOD);
    }
}
```
