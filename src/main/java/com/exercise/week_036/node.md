[toc]

# [LeetCode_482_1_密钥格式化](https://leetcode-cn.com/problems/license-key-formatting/)
## 题目
有一个密钥字符串 S ，只包含字母，数字以及 '-'（破折号）。其中， N 个 '-' 将字符串分成了 N+1 组。

给你一个数字 K，请你重新格式化字符串，使每个分组恰好包含 K 个字符。特别地，第一个分组包含的字符个数必须小于等于 K，但至少要包含 1 个字符。两个分组之间需要用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。

给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。

示例 1：
```
输入：S = "5F3Z-2e-9-w", K = 4
输出："5F3Z-2E9W"
解释：字符串 S 被分成了两个部分，每部分 4 个字符；
     注意，两个额外的破折号需要删掉。
```

示例 2：
```
输入：S = "2-5g-3-J", K = 2
输出："2-5G-3J"
解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
```

提示:

1. S 的长度可能很长，请按需分配大小。K 为正整数。
2. S 只包含字母数字（a-z，A-Z，0-9）以及破折号'-'
3. S 非空

## 理解
因为题目要求第一个分组字符个数要小于等于K，所以我们从后往前遍历，依次拼接K个字符后，添加一个破折号，
这样保证如果不能平均分配时，第一个分组的字符个数小于K，最终将拼接的字符反转再将字符转为大写即可。

时间复杂度为O(n)，空间复杂度为O(n)

### 代码
```java
public class LeetCode_482_1_密钥格式化 {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') {
                continue;
            }
            if (cnt == k) {
                sb.append("-");
                cnt = 0;
            }
            sb.append(c);
            cnt++;
        }
        return sb.reverse().toString().toUpperCase();
    }
}
```

# [LeetCode_284_1_顶端迭代器](https://leetcode-cn.com/problems/peeking-iterator/)
## 题目
请你设计一个迭代器，除了支持 hasNext 和 next 操作外，还支持 peek 操作。

实现 PeekingIterator 类：

* PeekingIterator(int[] nums) 使用指定整数数组 nums 初始化迭代器。
* int next() 返回数组中的下一个元素，并将指针移动到下个元素处。
* bool hasNext() 如果数组中存在下一个元素，返回 true ；否则，返回 false 。
* int peek() 返回数组中的下一个元素，但 不 移动指针。
 

示例：
```

```
输入：
["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
[[[1, 2, 3]], [], [], [], [], []]
输出：
[null, 1, 2, 2, 3, false]

解释：
PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
peekingIterator.next();    // 返回 1 ，指针移动到下一个元素 [1,2,3]
peekingIterator.peek();    // 返回 2 ，指针未发生移动 [1,2,3]
peekingIterator.next();    // 返回 2 ，指针移动到下一个元素 [1,2,3]
peekingIterator.next();    // 返回 3 ，指针移动到下一个元素 [1,2,3]
peekingIterator.hasNext(); // 返回 False
 

提示：

* 1 <= nums.length <= 1000
* 1 <= nums[i] <= 1000
* 对 next 和 peek 的调用均有效
* next、hasNext 和 peek 最多调用  1000 次
 

进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？

## 理解
预先先调用一次next函数，保存在next的临时变量中，用来作为peek的返回值，相应的hasNext和next方法
也通过更新next变量和判断next变量来实现。  

时间复杂度和空间复杂度均为O(1)

### 代码
```java
public class LeetCode_284_1_顶端迭代器 implements Iterator<Integer> {
    Iterator<Integer> iter;
    Integer next;

    public LeetCode_284_1_顶端迭代器(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        if (iter.hasNext()) {
            next = iter.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int ans = next;
        next = iter.hasNext() ? iter.next() : null;
        return ans;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}
```

# [LeetCode_414_1_第三大的数](https://leetcode-cn.com/problems/third-maximum-number/)
## 题目
给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。

示例 1：
```
输入：[3, 2, 1]
输出：1
解释：第三大的数是 1 。
```

示例 2：
```
输入：[1, 2]
输出：2
解释：第三大的数不存在, 所以返回最大的数 2 。
```

示例 3：
```
输入：[2, 2, 3, 1]
输出：1
解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
```

提示：

* 1 <= nums.length <= 10^4
* -2^31 <= nums[i] <= 2^31 - 1
 

进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？

## 理解
解法一：使用set去重，并存入list排序  

去重时间复杂度为O(n)，排序的时间复杂度为O(nlogn)，整体时间复杂度为O(nlogn)，空间复杂度为O(n)  

解法二：有限变量加遍历  
经典的数组寻找次大值的方法为使用两个变量a，b来存储最大值和次大值，再遍历的过程中不断更新最大值和次大值，
整体的时间复杂度为O(n)  
* 当遍历值x > a时，更新最大值和次大值
* 当遍历值不满足条件1，x > b时，此时根据是否有严格次大值的要求，而决定是否需要添加x < a的条件：如果没有
严格次大值的要求，可直接更新b，如果有需要判断x > b 并且 x < a，满足更新b  

时间复杂度为O(n)，空间复杂度为O(1) 

## 解法一
### 代码
```java
public class LeetCode_414_1_第三大的数 {
    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        if (list.size() <= 2) {
            return list.get(list.size() - 1);
        } else {
            return list.get(list.size() - 3);
        }
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_414_2_第三大的数 {
    static final long INF = Long.MIN_VALUE;
    public int thirdMax(int[] nums) {
        long a = INF, b = INF, c = INF;
        for (int num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (num < a && num > b) {
                c = b;
                b = num;
            } else if (num < b && num > c) {
                c = num;
            }
        }
        return (int)(c == INF ? a : c);
    }
}

```