[toc]

# [LeetCode_673_1_最长递增子序列的个数](https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/)
## 题目
给定一个未排序的整数数组，找到最长递增子序列的个数。

示例 1:
```
输入: [1,3,5,4,7]
输出: 2
解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
```

示例 2:
```
输入: [2,2,2,2,2]
输出: 5
解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
```

* 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。

## 理解
动态规划问题，相比常规的LIS问题（求取最长上升子序列的长度），本题求解的是最长上升子序列的个数。
我们需要在常规的LIS的问题求解上，添加额外的变量来记录个数。  
f[i]代表以nums[i]为结尾的最长上升子序列长度。  
我们不失一般性的考虑f[i]的转移方程：  
1. 因为每个数都能独立为一个子序列，所以有f[i] = 1
2. 枚举[0,i)之间的所有数nums[j]，如果满足nums[j] < nums[i]，那么说明nums[i]可以接在nums[j]后面
形成上升子序列，此时可以使用f[j]来更新f[i]，f[i] = f[j] + 1  

由于本题求解的是最长上升自序列的个数，所以我们额外定义的g[i]，代表以nums[i]为结尾的最长子序列的个数，
结合f[i]，我们不失一般性的考虑一下g[i]的转移方程：  
1. 同理因为每个数都能看做一个子序列，因此必然有g[i] = 1
2. 枚举[0,i)之间的所有数nums[j]，如果满足nums[j] < nums[i]，那么说明nums[i]可以接在nums[j]后面
   形成上升子序列，此时我们需要分情况讨论f[i]和f[j] + 1的关系  
   * 如果f[i] < f[j] + 1，那么说明f[i]直接会被f[j] + 1更新，那么g[i] = g[j]
   * 如果f[i] = f[j] + 1，那么说明找到了一个新的符合条件的前驱，此时需要将值累加，g[i] += g[j]  
   
在转移过程中，我们可以用变量记录子序列的最大长度max，最终答案为f[i]=max的所有的g[i]的累加值   
时间复杂度为O(n^2)，空间复杂度为O(n)

### 代码
```java
public class LeetCode_673_1_最长递增子序列的个数 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] f = new int[n], g = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            f[i] = g[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (f[i] < f[j] + 1) {
                        f[i] = f[j] + 1;
                        g[i] = g[j];
                    } else if (f[i] == f[j] + 1) {
                        g[i] += g[j];
                    }
                }
            }
            max = Math.max(max, f[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] == max) {
                ans += g[i];
            }
        }
        return ans;
    }
}
```


# [LeetCode_58_1_最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/)
## 题目
给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。

单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。

 
示例 1：
```
输入：s = "Hello World"
输出：5
```

示例 2：
```
输入：s = "   fly me   to   the moon  "
输出：4
```

示例 3：
```
输入：s = "luffy is still joyboy"
输出：6
```

提示：

* 1 <= s.length <= 10^4
* s 仅有英文字母和空格 ' ' 组成
* s 中至少存在一个单词

## 理解
解法一：使用字符串分割函数将目标字符串分割为字符串数组，再将数组中最后一个元素的长度返回。

解法二：使用双指针从字符串末尾遍历，锁定最后一个单词的起始和截止坐标，再通过减法运算计算出最后一个单词长度


## 解法一
### 代码
```java
public class LeetCode_58_1_最后一个单词的长度 {
    public int lengthOfLastWord(String s) {
        String[] arr = s.split(" ");
        int n = arr.length;
        return arr[n - 1].length();
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_58_2_最后一个单词的长度 {
    public int lengthOfLastWord(String s) {
        int n = s.length();
        int j = n - 1;
        while (j >= 0 && s.charAt(j) == ' ') {
            j--;
        }
        int i = j;
        while (i >= 0 && s.charAt(i) != ' ') {
            i--;
        }
        return j - i;
    }
}
```

# [LeetCode_725_1_分隔链表](https://leetcode-cn.com/problems/split-linked-list-in-parts/)
## 题目
给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。

每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。

这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。

返回一个由上述 k 部分组成的数组。

 
示例 1：
```
输入：head = [1,2,3], k = 5
输出：[[1],[2],[3],[],[]]
解释：
第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
```

示例 2：
```
输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
输出：[[1,2,3,4],[5,6,7],[8,9,10]]
解释：
输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
```


提示：

* 链表中节点的数目在范围 [0, 1000]
* 0 <= Node.val <= 1000
* 1 <= k <= 50

## 理解
首先我们计算链表长度，再使用向下取整除法计算最小的分隔的每个单位的链表长度per，因为存在不能整除的情况，
不能整除的取余部分平均分布，按从前往后的顺序，按分隔指定值k分隔链表，注意每个子链表结尾节点的next需要
置为null，这里可能需要临时节点记录当前链表的最后一个节点。  
整体的时间复杂度为O(n)，空间复杂度O(1)

### 代码
```java
public class LeetCode_725_1_分隔链表 {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }
        int per = n / k;
        int count = n % k;

        ListNode[] ans = new ListNode[k];
        ListNode pre;
        for (int i = 0; i < k; i++) {
            int v = per;
            if (count > 0) {
                v = v + 1;
                count--;
            }
            ans[i] = head;
            while (v > 0) {
                pre = head;
                head = head.next;
                v--;
                if (v == 0) {
                    pre.next = null;
                }
            }
        }
        return ans;
    }
}
```