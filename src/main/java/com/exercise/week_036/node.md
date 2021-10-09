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

# [LeetCode_434_1_字符串中的单词数](https://leetcode-cn.com/problems/number-of-segments-in-a-string/)
## 题目
统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。

请注意，你可以假定字符串里不包括任何不可打印的字符。

示例:
```
输入: "Hello, my name is John"
输出: 5
解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
```

## 理解
解法一：使用字符串自带的split函数，但是注意需要过滤掉空元素

时间复杂度为O(n)，空间复杂度为O(M)，M为分割后数组长度

解法二：通过遍历，过滤掉空的元素，不为空时通过while循环找到单词结尾处并统计

时间复杂度O(n)，空间复杂度为O(1)

## 解法一
### 代码
```java
public class LeetCode_434_1_字符串中的单词数 {
    public int countSegments(String s) {
        int ans = 0;
        String[] ss = s.split(" ");
        for (int i = 0; i < ss.length; i++) {
            if (!ss[i].equals("")) {
                ans++;
            }
        }
        return ans;
    }

}
```

## 解法二
### 代码
```java
public class LeetCode_434_2_字符串中的单词数 {
    public int countSegments(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n;) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }
            while (i < n && s.charAt(i) != ' ') {
                i++;
            }
            ans++;
        }
        return ans;
    }
}
```

# [LeetCode_187_1_重复的DNA序列](https://leetcode-cn.com/problems/repeated-dna-sequences/)
## 题目
所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。

 
示例 1：
```
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC","CCCCCAAAAA"]
```

示例 2：
```
输入：s = "AAAAAAAAAAAAA"
输出：["AAAAAAAAAA"]
```

提示：

* 0 <= s.length <= 10^5
* s[i] 为 'A'、'C'、'G' 或 'T'

## 理解
解法一：滑动窗口+哈希表  
使用滑动窗口，依次获取以i为结尾的长度为10的子串，利用哈希表记录子串及出现次数，防止答案有重复，我们
在判断首次确认有重复时才添加为答案  

时间复杂度为O(n*C)，C为常数，是子串的固定长度10  
空间复杂度为O(n)

解法二：滑动窗口+哈希表+位运算  
由于s中只包含4种字符，我们可以将每个字符用一个二进制数字表示：
* 'A'表示为二进制00
* 'C'表示为二进制01
* 'G'表示为二进制02
* 'T'表示为二进制03

如此以来，一个长为10的字符串就可以用一个20byte表示，一个int值有32byte，我们只用低20位即可。

有了上述的映射，我们就可以将每个子串映射为一个int值，再通过哈希表存入值与出现的次数，进而判断
子串是否重复
* 滑动窗口右移一位， x = x << 2，因为每个字符用两个byte表示，所以需要左移两位留出空位等待新字符填补
* 一个新字符进入窗口，x |= map.get(ch)，这里的map.get(ch)是为了取ch对应的二进制数
* 窗口最左边的字符离开窗口，x &= ((1 << 20) - 1)，因为我们只考虑低20位，所以将值与低20位为20个1的
值相与，获取当前值的低20位

将这三步合并，我们就可以在O(1)时间复杂度下计算出一个表示子字符串的整数，即:   
x = ((x << 2) | map.get(ch)) & ((1 << 20) - 1)

优化后时间复杂度为O(n)，空间复杂度为O(n)


## 解法一
### 代码
```
public class LeetCode_187_1_重复的DNA序列 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 10; i <= n; i++) {
            String subStr = s.substring(i - 10, i);
            int cnt = map.getOrDefault(subStr, 0);
            if (cnt == 1) {
                ans.add(subStr);
            }
            map.put(subStr, cnt + 1);
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_187_2_重复的DNA序列 {
    static final int L = 10;
    Map<Character, Integer> map = new HashMap<Character, Integer>(){{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        if (n < L) {
            return ans;
        }
        int val = 0;
        // 求取前9个字符的值
        for (int i = 0; i < L - 1; i++) {
            val = (val << 2) | map.get(s.charAt(i));
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i + L <= n; i++) {
            val = ((val << 2) | map.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
            int c = cnt.getOrDefault(val, 0);
            if (c == 1) {
                ans.add(s.substring(i, i + L));
            }
            cnt.put(val, c + 1);
        }

        return ans;
    }
}
```

# [LeetCode_352_1_将数据流变为多个不相交区间](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/)
## 题目
 给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。

实现 SummaryRanges 类：

* SummaryRanges() 使用一个空数据流初始化对象。
* void addNum(int val) 向数据流中加入整数 val 。
* int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 

示例：
```
输入：
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
输出：
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

解释：
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // 返回 [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
```

提示：

* 0 <= val <= 10^4
* 最多调用 addNum 和 getIntervals 方法 3 * 10^4 次
 

进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?

## 理解
解法一：二分 + List存储区间  

我们使用List来存储不相交的区间，存入的过程我们需要维护其单调性，方便我们在新的值加入时，使用二分法
快速找到其相邻的区间，并判断值与区间的关系。  
* 值在左区间中
* 值连接了左区间和右区间，需要做区间合并
* 值是左区间上边界的临界值+1，需要扩展左区间的上界
* 值是右区间下边界的临界值-1，需要扩展右区间的下界
* 值不与左右区间相交，创建新的不相交区间

m为不相交区间的数量  

时间复杂度：addNum方法，二分查询值的相邻区间的复杂度为O(logm)，但是我们需要维护集合的单调性，插入的时候需要指定
下标插入数据，插入的复杂度为O(m)，所以整体的复杂度为O(m)，getIntervals需要遍历集合，整体复杂度为O(m)  
空间复杂度为O(m)

解法二：TreeSet存储区间  
TreeSet的底层数据结构是红黑树，他的插入和删除操作都能在O(logn)级别完成，所以我们可以利用TreeSet来
优化我们的时间复杂度  

而且我们可以使用TreeSet自带的floor和ceiling方法来找传入值的相邻的区间，因为值不可能与上区间有交集
所以找右相邻区间时也可以使用higher方法，效果都是一样的。同时为了简化复杂的分情况讨论，
起始时我们可以先往 TreeSet 添加两个哨兵分别代表正无穷和负无穷，以确保调用 floor/ceiling 
时不会返回空。

获取区间可以使用for循环，也可以使用迭代器

时间复杂度：addNum方法复杂度优化到O(logm)，getIntervals为O(m)  
空间复杂度：O(m)

## 解法一
### 代码
```java
public class LeetCode_352_1_将数据流变为多个不相交区间 {
    /**
     * 用来保存不相交区间的集合
     */
    List<int[]> list = new ArrayList<>();
    /**
     * 定义哨兵头区间和尾区间
     */
    int[] head = new int[]{-10, -10}, tail = new int[]{10010, 10010};

    public LeetCode_352_1_将数据流变为多个不相交区间() {
        list.add(head);
        list.add(tail);
    }

    public void addNum(int val) {
        int n = list.size();
//        if (n == 2) {
//            list.add(1, new int[]{val, val});
//            return;
//        }
        // 通过二分法，查找相邻的区间
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (list.get(mid)[0] <= val) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int[] cur = new int[]{val, val};
        int[] pre = list.get(r);
        int[] next = list.get(r + 1);

        if ((pre[0] <= val && pre[1] >= val)) {

        } else if ((pre[1] + 1 == val) && (next[0] - 1 == val)) {
            pre[1] = next[1];
            list.remove(next);
        } else if (pre[1] + 1 == val) {
            pre[1] = val;
        } else if (next[0] - 1 == val) {
            next[0] = val;
        } else {
            list.add(r + 1, cur);
        }
    }

    public int[][] getIntervals() {
        int n = list.size();
        int[][] ans = new int[n - 2][2];
        int ids = 0;
        for (int i = 1; i < n - 1; i++) {
            ans[ids++] = list.get(i);
        }
        return ans;
    }
}

```

## 解法二
### 代码
```java
public class LeetCode_352_2_将数据流变为多个不相交区间 {
    /**
     * 用来保存不相交区间的集合
     */
    TreeSet<int[]> set = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
    /**
     * 定义哨兵头区间和尾区间
     */
    int[] head = new int[]{-10, -10}, tail = new int[]{10010, 10010};

    public LeetCode_352_2_将数据流变为多个不相交区间() {
        set.add(head);
        set.add(tail);
    }

    public void addNum(int val) {
        int[] cur = new int[]{val, val};
        int[] pre = set.floor(cur);
        int[] next = set.higher(cur);
        if ((pre[0] <= val) && (pre[1] >= val)) {

        } else if ((pre[1] + 1 == val) && (next[0] - 1 == val)) {
            pre[1] = next[1];
            set.remove(next);
        } else if (pre[1] + 1 == val) {
            pre[1] = val;
        } else if (next[0] - 1 == val) {
            next[0] = val;
        } else {
            set.add(cur);
        }
    }

    public int[][] getIntervals() {
        int n = set.size();
        int[][] ans = new int[n - 2][2];
        Iterator<int[]> it = set.iterator();
        it.next();
        for (int i = 0; i < n - 2 ; i++) {
            ans[i] = it.next();
        }
        return ans;
    }
}
```