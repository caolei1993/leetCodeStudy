[toc]

# [LeetCode_476_1_数字的补数](https://leetcode-cn.com/problems/number-complement/)
## 题目
对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。

例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。
给你一个整数 num ，输出它的补数。

 
示例 1：
```
输入：num = 5
输出：2
解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
```

示例 2：
```
输入：num = 1
输出：0
解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
```


提示：

1 <= num < 2^31
 

注意：本题与 1009 https://leetcode-cn.com/problems/complement-of-base-10-integer/ 相同

## 理解
解法一：利用轮询的lowbit求解num的，求解num二进制的最高位1，假设num的二进制有x位，实际计算补数时，
只是对x - 1位求解即可，因为最高位1会变为0，所以我们可以对num取反，再与x - 1位1相与即为答案。

解法二：通过从高位到低位遍历，确定num的二进制位数x，再寻找x - 1位值中的0，用1做相应的移位或在一起
即为答案。

两种方案时间复杂度都为O(lognum)，空间复杂度为O(1)

## 解法一
### 代码
```java
public class LeetCode_476_1_数字的补数 {
    public int findComplement(int num) {
        int x = 0;
        for (int i = num; i != 0 ; i -= i & -i) {
            x = i;
        }
        return ~num & (x - 1);
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_476_2_数字的补数 {
    public static int findComplement(int num) {
        int index = 0;
        int ans = 0;
        for (int i = 31; i >= 0 ; i--) {
            if (((num >> i) & 1) == 1) {
                index = i;
                break;
            }
        }
        for (int i = index - 1; i >= 0; i--) {
            if (((num >> i) & 1) == 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

}
```

# [LeetCode_211_1_添加与搜索单词数据结构设计](https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/)
## 题目
请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。

实现词典类 WordDictionary ：

* WordDictionary() 初始化词典对象
* void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
* bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 

示例：
```
输入：
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
输出：
[null,null,null,null,false,true,true,true]

解释：
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
```

 
提示：

* 1 <= word.length <= 500
* addWord 中的 word 由小写英文字母组成
* search 中的 word 由 '.' 或小写英文字母组成
* 最多调用 50000 次 addWord 和 search

## 理解
使用字典树（Tire）实现，添加使用字典树的添加，查找使用dfs递归实现，注意单独判断字符为'.'的情况

时间复杂度：addWord的时间复杂度为O(L)，L是字符串长度，search操作的复杂度为O(C^L)，C为常数26

空间复杂度为O(n*L)，n为插入的字符串数量，L为字符串的最大长度

### 代码
```java
public class LeetCode_211_1_添加与搜索单词数据结构设计 {
    static class Node {
        Node[] tns = new Node[26];
        boolean isWord;
    }
    Node root = new Node();

    public LeetCode_211_1_添加与搜索单词数据结构设计() {

    }

    public void addWord(String word) {
        Node node = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int idx = word.charAt(i) - 'a';
            if (node.tns[idx] == null) {
                node.tns[idx] = new Node();
            }
            node = node.tns[idx];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, root, 0);
    }

    private boolean dfs(String word, Node node, int idx) {
        int n = word.length();
        if (n == idx) {
            return node.isWord;
        }
        char c = word.charAt(idx);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.tns[i] != null && dfs(word, node.tns[i], idx + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            int value = c - 'a';
            if (node.tns[value] != null) {
                return dfs(word, node.tns[value], idx + 1);
            }
            return false;
        }
    }
}

```

# [LeetCode_453_1_最小操作次数使数组元素相等](https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/)
## 题目
给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。

示例 1：
```
输入：nums = [1,2,3]
输出：3
解释：
只需要3次操作（注意每次操作会增加两个元素的值）：
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
```

示例 2：
```
输入：nums = [1,1,1]
输出：0
```
 

提示：

* n == nums.length
* 1 <= nums.length <= 10^5
* -10^9 <= nums[i] <= 10^9
* 答案保证符合 32-bit 整数

## 理解
使用数学解法，由题目可知每次只能操作n - 1个数，我们可利用反证法证明，每次递增的数肯定包含最小值，
且最小值会一直为相对最小值直到所有值相等为t，t>=max（最大值）

所以有 ans = ((min + ans) * t - sum) / (n - 1)

通过等式换算可得 ans = sum - n * min

因为要求和，所以整体时间复杂度为O(n)，空间复杂度为O(1)

### 代码
```java
public class LeetCode_453_1_最小操作次数使数组元素相等 {
    public int minMoves(int[] nums) {
        int n = nums.length;
        long sum = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }

        return (int)(sum - min * n);
    }
}
```