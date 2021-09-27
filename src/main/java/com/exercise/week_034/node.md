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

# [LeetCode_326_1_3的幂](https://leetcode-cn.com/problems/power-of-three/)
## 题目
给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。

整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x

 
示例 1：
```
输入：n = 27
输出：true
```

示例 2：
```
输入：n = 0
输出：false
```

示例 3：
```
输入：n = 9
输出：true
```

示例 4：
```
输入：n = 45
输出：false
```
 

提示：

* -2^31 <= n <= 2^31 - 1
 
进阶：

* 你能不使用循环或者递归来完成本题吗？

## 理解
解法一：数学常规方法，使用了while循环  
时间复杂度为O(log以3为底的n)，空间复杂度为O(1)  

解法二：题目要求不使用循环和递归，题目传参n的取值范围为int的范围，我们首先确认出int类型中最大的3次幂
的值，约为3^19=1162261467  
如果n为3的幂的话，必然满足n*3^k = 1162261467，即n必然是1162261467的一个约数，所以我们只需要判断
n是否是1162261467的约数即可  
时间复杂度为O(1)，空间复杂度为O(1)

解法三：使用静态代码块提前求出int范围内为整数的3的幂，存入set，后面直接判断n是否被set包含即可  
时间复杂度O(log以3为底的C)，C是常数，是int的最大值，所以时间复杂度可以看做O(1)，空间复杂度为
O(log以3为底的C)

## 解法一
### 代码
```java
public class LeetCode_326_1_3的幂 {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_326_2_3的幂 {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```

## 解法三
### 代码
```java
public class LeetCode_326_3_3的幂 {
    static Set<Integer> set = new HashSet<>();
    static {
        int v = 1;
        set.add(v);
        while (v <= Integer.MAX_VALUE / 3) {
            v *= 3;
            set.add(v);
        }
    }
    public boolean isPowerOfThree(int n) {
        return n > 0 && set.contains(n);
    }
}
```

# [LeetCode_430_1_扁平化多级双向链表](https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/)
## 题目
多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。

 
示例 1：
```
输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
输出：[1,2,3,7,8,11,12,9,10,4,5,6]
解释：

输入的多级列表如下图所示：

扁平化后的链表如下图：

```

示例 2：
```
输入：head = [1,2,null,3]
输出：[1,3,2]
解释：

输入的多级列表如下图所示：

  1---2---NULL
  |
  3---NULL
```

示例 3：
```
输入：head = []
输出：[]
```

 
如何表示测试用例中的多级链表？

以 示例 1 为例：
```
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
```

序列化其中的每一级之后：
```
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
```

为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
```
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
```

合并所有序列化结果，并去除末尾的 null 。
```
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
```
 

提示：

* 节点数目不超过 1000
* 1 <= Node.val <= 10^5

## 理解
解法一：使用常规的递归方法，遍历链表上的节点，判断节点的child是否有值：  
1. child为null，直接遍历下一个节点  
2. child不为null，需要递归调用扁平化函数，将child扁平化，在将扁平化的child接在原链表上，注意
原扁平化函数返回的是链表的头节点，我们在处理时需要将扁平化的child的尾节点接上原链表的下一个节点
（需要用临时变量提前保存当前节点的下一个节点），此时就需要通过循环找到child扁平化后的尾节点，再做
后续处理  

循环以上操作直到遍历完链表，时间复杂度：因为递归返回的是扁平化的头节点，最坏的情况每个节点都需要访问
h次（h是递归的深度，最坏情况下h=n），整体的时间复杂度为O(n^2)  
空间复杂度：最坏的情况，所有的节点都分布在child中，此时递归深度为n，空间复杂度为O(n)

解法二：重写递归函数，使扁平化递归函数返回的是扁平化链表的尾节点，此时我们就避免遍历寻找尾节点的相关复杂
度  
时间复杂度优化到了O(n)，空间复杂度最坏也为O(n)

解法三：使用迭代的方法解决此问题：  
与递归不同的是，迭代是以段为单位进行扁平化的，而递归是以深度方向进行扁平化的，这就导致了两种方式对扁平
节点的处理顺序不同。  
时间复杂度为，遍历每个节点，每个节点被访问的次数为常数次，整体复杂度为O(n)，空间复杂度为O(1)

## 解法一
### 代码
```java
public class LeetCode_430_1_扁平化多级双向链表 {
    public Node flatten(Node head) {
        Node node = head;
        while (node != null) {
            Node child = node.child;
            if (child != null) {
                Node tmp = node.next;
                Node chead = flatten(node.child);
                node.next = chead;
                chead.prev = node;
                node.child = null;
                while (node.next != null) {
                    node = node.next;
                }
                node.next = tmp;
                if (tmp != null) {
                    tmp.prev = node;
                }
            }
            node = node.next;
        }
        return head;
    }
}
```

# [LeetCode_583_1_两个字符串的删除操作](https://leetcode-cn.com/problems/delete-operation-for-two-strings/)
## 题目
给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。

 
示例：
```
输入: "sea", "eat"
输出: 2
解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
```

提示：

* 给定单词的长度不超过500。
* 给定单词中的字符只含有小写字母。

## 理解
解法一：将问题转化为LCS问题（最长公共子序列），求取到最长公共子序列长度后，在用两个字符串的长度分别减
去最长公共子序列长度，再相加即为答案  

状态定义f[i][j]代表word1前i个字符与word2前j个字符的最大公共子序列长度（为了避免边界判断，假想添加
了空格前缀）

初始化初始值，所有的f[i][0]与f[0][j]都为1，因为假想了空格前缀  

不失一般性的考虑一下转移方程：  
* 如果s1[i] == s2[j]，那么f[i][j] = f[i - 1][j - 1] + 1
* 如果不相等，f[i][j] = Math.max(f[i - 1][j], f[i][j - 1])

最终结果为f[n][m] - 1，减去假想的空格的长度1

时间复杂度为O(n*m)，空间复杂度为O(n*m)

解法二：可以在上述思想的基础上，契合题意做相应的变通  

状态定义f[i][j]代表word1的前i个字符与word2前j个字符，在改变为相等字符串时，最小的操作次数  

初始值初始化，f[i][0] = i，f[0][j] = j

转移方程：
* 如果s1[i] == s2[j]，那么不需要删除，f[i][j] = f[i - 1][j - 1]
* 如果不相等，则f[i][j] = Math.min(f[i - 1][j] + 1， f[i][j - 1] + 1)

最终返回f[n][m]

时间复杂度为O(n*m)，空间复杂度为O(n*m)

## 解法一
### 代码
```java
public class LeetCode_583_1_两个字符串的删除操作 {
    public int minDistance(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int n = s1.length, m = s2.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            f[i][0] = 1;
        }

        for (int j = 0; j <= m; j++) {
            f[0][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                }
            }
        }
        int max = f[n][m] - 1;
        return n - max + m - max;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_583_2_两个字符串的删除操作 {
    public int minDistance(String word1, String word2) {
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        int n = c1.length, m = c2.length;
        // 定义状态，f[i][j]表示字符串1的前i个字符，字符串2的前j个字符，修改为相同字符串最少的操作次数
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            f[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            f[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 两个字符不同，则取操作一次后取值较小者
                f[i][j] = Math.min(f[i - 1][j] + 1, f[i][j - 1] + 1);
                // 如果两个字符相等，则不需要删除
                if (c1[i - 1] == c2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1];
                }
            }
        }
        // 减掉空格哨兵的一个长度
        return f[n][m];
    }
}
```

# [LeetCode_371_1_两整数之和](https://leetcode-cn.com/problems/sum-of-two-integers/)
## 题目
给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。


示例 1：
```
输入：a = 1, b = 2
输出：3
```

示例 2：
```
输入：a = 2, b = 3
输出：5
```

提示：

* -1000 <= a, b <= 1000

## 理解
解法一：我们逐位操作从低到高，再将结果合并在一起，u1，u2分别是a，b按位取的值，t用来记录低位是否有进位  
1. u1，u2都为1时，当前位置的值取决于低位进位信息，即ans |= (t << i)，同时需要将进位信息置为1
2. u1，u2有一个为1时，当前位置的值是 t ^ 1，即ans |= (t ^ 1) << i，此时无需处理进位信息，原先进位，
则当前进位，原先不进位，则当前不进位
3. u1，u2都为0时，当前位置的值也取决于低位进位信息，即ans |= (t << i)，此时进位信息需要置为0

32位轮询完毕后，最终返回叠加的结果ans即可  
时间复杂度为O(C)，C为常数32，空间复杂度为O(1)

解法二：使用递归法求解  
再不考虑进位的情况下，a和b的和的计算结果为a ^ b，然后在此基础上，我们可以考虑将进位累加进来，累加
操作可以通过递归调用getSum方法实现  
当a和b的当前位均为1时，该位才会产生进位，同时进位会影响当前位的下一位高位，因此进位的最终结果为
a & b << 1  
因此我们可以通过不断调用getSum(a ^ b，(a & b) << 1)来求取结果  
我们需要考虑递归的截止条件，由于进位操作会不断操作右移操作，当右移次数最多32次之后，（a&b） << 1的结果
为0，0与任何数相加都为0，此时递归截止  
时间复杂度为O(C)，C为32，空间复杂度为O(C)

## 解法一
### 代码
```java
public class LeetCode_371_1_两整数之和 {
    public int getSum(int a, int b) {
        int ans = 0, t = 0;
        for (int i = 0; i < 32; i++) {
            int u1 = (a >> i) & 1, u2 = (b >> i) & 1;
            if (u1 == 1 && u2 == 1) {
                ans |= (t << i);
                t = 1;
            } else if (u1 == 1 || u2 == 1) {
                ans |= (t ^ 1) << i;
            } else {
                ans |= (t << i);
                t = 0;
            }
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_371_2_两整数之和 {
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }
}
```