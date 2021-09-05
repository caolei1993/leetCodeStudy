[toc]

# [LeetCode_165_1_比较版本号](https://leetcode-cn.com/problems/compare-version-numbers/)
## 题目
给你两个版本号 version1 和 version2 ，请你比较它们。

版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。

比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。

返回规则如下：

* 如果 version1 > version2 返回 1，
* 如果 version1 < version2 返回 -1，
* 除此之外返回 0。
 

示例 1：
```
输入：version1 = "1.01", version2 = "1.001"
输出：0
解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
```

示例 2：
```
输入：version1 = "1.0", version2 = "1.0.0"
输出：0
解释：version1 没有指定下标为 2 的修订号，即视为 "0"
```

示例 3：
```
输入：version1 = "0.1", version2 = "1.1"
输出：-1
解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
```

示例 4：
```
输入：version1 = "1.0.1", version2 = "1"
输出：1
```

示例 5：
```
输入：version1 = "7.5.2.4", version2 = "7.5.3"
输出：-1
```


提示：

* 1 <= version1.length, version2.length <= 500
* version1 和 version2 仅包含数字和 '.'
* version1 和 version2 都是 有效版本号
* version1 和 version2 的所有修订号都可以存储在 32 位整数 中


## 理解
简单模拟题，对字符串进行分割，注意需要对.进行转义，逐位比较大小即可。

## 解法一
```java
public class LeetCode_165_1_比较版本号 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int l1 = v1.length;
        int l2 = v2.length;
        if (l1 >= l2) {
            int count = l1 - l2;
            int ans = 0;
            for (int i = 0; i < l2; i++) {
                int i1 = Integer.parseInt(v1[i]);
                int i2 = Integer.parseInt(v2[i]);
                if (i1 > i2) {
                    return 1;
                } else if (i1 < i2) {
                    return -1;
                } else {
                    continue;
                }
            }
            for (int i = l2; i < l1; i++) {
                int i1 = Integer.parseInt(v1[i]);
                if (i1 == 0) {
                    continue;
                } else {
                    return 1;
                }
            }
            return ans;
        } else {
            int count = l2 - l1;
            int ans = 0;
            for (int i = 0; i < l1; i++) {
                int i1 = Integer.parseInt(v1[i]);
                int i2 = Integer.parseInt(v2[i]);
                if (i1 > i2) {
                    return 1;
                } else if (i1 < i2) {
                    return -1;
                } else {
                    continue;
                }
            }
            for (int i = l1; i < l2; i++) {
                int i2 = Integer.parseInt(v2[i]);
                if (i2 != 0) {
                    return -1;
                }
            }
            return ans;
        }
    }
}
```

## 解法二
```java
public class LeetCode_165_2_比较版本号 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int l1 = v1.length;
        int l2 = v2.length;
        int i = 0, j = 0;
        while (i < l1 || j < l2) {
            int a = 0, b = 0;
            if (i < l1) {
                a = Integer.parseInt(v1[i++]);
            }
            if (j < l2) {
                b = Integer.parseInt(v2[j++]);
            }
            if (a != b) {
                return a > b ? 1 : -1;
            }
        }
        return 0;
    }
}
```

# [LeetCode_剑指Offer22_1_链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)
## 题目
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

 

示例：
```
给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
```

## 理解
解法一：遍历链表求取链表长度，因为我们要求取倒数第k个节点，那么我们只需要求取需要从头节点走多少步
即可，用求取的链表长度减去k就是我们要走的步数。  
时间复杂度O(n)，空间复杂度O(1)

解法二：使用快慢指针，使快指针先走k步，到达第k+1个节点处，此时剩余的节点为len - k个，我们再同时走
快指针和慢指针，当快指针走到尾时，慢指针正好走了len - k步，指向倒数第k个节点。  
时间复杂度O(n)，空间复杂度O(1)

## 解法一
### 代码
```java
public class LeetCode_剑指Offer22_1_链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        int count = len - k;
        while (count > 0) {
            head = head.next;
            count--;
        }
        return head;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_剑指Offer22_2_链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
```

# [LeetCode_剑指Offer10_1_斐波那契数列](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)
## 题目
写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 
示例 1：
```
输入：n = 2
输出：1
```

示例 2：
```
输入：n = 5
输出：5
```

提示：

* 0 <= n <= 100

## 理解
注意处理n<=1的情况，其余情况通过不断循环重置a,b的值进行求解。

### 代码
```java
public class LeetCode_剑指Offer10_1_斐波那契数列 {
    int mod = (int) 1e9 + 7;
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            c = c % mod;
            a = b;
            b = c;
        }
        return b;
    }
}

``` 

# [LeetCode_470_1_用Rand7实现Rand10](https://leetcode-cn.com/problems/implement-rand10-using-rand7/)
## 题目
已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。

不要使用系统的 Math.random() 方法。


示例 1:
```
输入: 1
输出: [7]
```

示例 2:
```
输入: 2
输出: [8,4]
```

示例 3:
```
输入: 3
输出: [8,1,10]
```
 

提示:

* rand7 已定义。
* 传入参数: n 表示 rand10 的调用次数。
 

进阶:

* rand7()调用次数的 期望值 是多少 ?
* 你能否尽量少调用 rand7() ?

## 理解
rand7的范围是等概率的[1,7]，值域的整体偏移不会改变概率，即我们对rand7 - 1得到值域为[0,6]的范围，
也是等概率的。  
但是如果rand7 + rand7 得到值域为[2,14]的范围，每个值就不是等概率的，比如出现2的概率只有（1,1）为1/49，但是
出现4的概率有（2,2），（1,3），（3,1）为3/49，概率是不相同的。  所以得出值域的拼接和叠加不是等概率的。  
我们可以把rand7看成独立事件，把两次rand7的结果看成7进制数的2位，rand7-1值域整体为[0,6]，2为7进制
取值范围为[0,48]，解法一返回[1,10]的数，解法二为了提高有效率，[1,40]的数都有效，返回值除10取余+1。

## 解法一
### 代码
```java
public class LeetCode_470_1_用Rand7实现Rand10 {
    public int rand10() {
        while (true) {
            int ans = (rand7() - 1) * 7 + (rand7() - 1);
            if (1 <= ans && ans <= 10) {
                return ans;
            }
        }
    }
    public int rand7() {
        return 1;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_470_2_用Rand7实现Rand10 {
    public int rand10() {
        while (true) {
            int ans = (rand7() - 1) * 7 + (rand7() - 1);
            if (1 <= ans && ans <= 40) {
                return ans % 10 + 1;
            }
        }
    }
    public int rand7() {
        return 1;
    }
}
```
