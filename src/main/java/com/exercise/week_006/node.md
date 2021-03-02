[toc]
# [LeetCood_20_1_有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)
## 题目
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。  
左括号必须以正确的顺序闭合。
 

示例 1：
```
输入：s = "()"
输出：true
```

示例 2：
```
输入：s = "()[]{}"
输出：true
```

示例 3：
```
输入：s = "(]"
输出：false
```

示例 4：
```
输入：s = "([)]"
输出：false
```

示例 5：
```
输入：s = "{[]}"
输出：true
```

提示：

* 1 <= s.length <= 104
* s 仅由括号 '()[]{}' 组成

## 理解
利用栈的结构辅助处理。  
* 遇到左括号压入栈中
* 遇到右括号，弹出栈顶元素与当前括号做匹配，匹配上则继续，否则为无效括号

### 代码
```java
public class LeetCood_20_1_有效的括号 {
    public boolean isValid(String s) {
        Map<String, String> map = new HashMap<>();
        map.put("(", ")");
        map.put("{", "}");
        map.put("[", "]");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String str = String.valueOf(s.charAt(i));
            if (map.containsKey(str)) {
                stack.push(str);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                String left = stack.pop();
                if (!map.get(left).equals(str)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
```

# [LeetCode_856_1_括号的分数](https://leetcode-cn.com/problems/score-of-parentheses/)
## 题目
给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：

() 得 1 分。  
AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。  
(A) 得 2 * A 分，其中 A 是平衡括号字符串。  
 

示例 1：
```
输入： "()"
输出： 1
```

示例 2：
```
输入： "(())"
输出： 2
```

示例 3：
```
输入： "()()"
输出： 2
```

示例 4：
```
输入： "(()(()))"
输出： 6
```
提示：

S 是平衡括号字符串，且只含有 ( 和 ) 。  
2 <= S.length <= 50

## 理解
* 解法一：利用栈的结构，栈中初始化得分为0，每遇到一个'('栈的深度加1，并初始化当前深度的得分为0。
当我们遇到')'时，我们将当前深度的得分乘以2（有一种特殊情况即'()'只得一分，所以需要与前面的计算值取大的），
并加到上一个深度的得分上。
* 解法二：对于一个字符串 S，我们将左括号 ( 记为 1，右括号记为 -1，如果 S 的某一个非空前缀对应的和为 0，
那么这个前缀就是一个平衡括号字符串。我们遍历字符串 S，得到若干个前缀和为 0 的位置，
就可以将字符串拆分为 S = P_1 + P_2 + ... + P_n，其中每一个 P_i 都是一个平衡括号字符串。
这样我们就可以分别计算每一个 P_i 的分数再相加，即 score(S) = score(P_1) + score(P_2) + ... + score(P_n)。
      
对于一个不可拆分的平衡括号字符串，如果它为 ()，那么就得 1 分，否则它的最外层一定有一对左右括号，
可以将这对括号去除后继续进行拆分，再将得到的分数乘 2。（使用递归）
* 解法三：事实上，我们可以发现，只有 () 会对字符串 S 贡献实质的分数，
其它的括号只会将分数乘二或者将分数累加。因此，我们可以找到每一个 () 对应的深度 x，
那么答案就是 2^x 的累加和。


### 解法一
```java
public class LeetCode_856_1_括号的分数 {
    public int scoreOfParentheses(String S) {
        String left = "(";
        Stack<Integer> stack = new Stack<>();
        // 当前分数
        stack.push(0);
        for (int i=0; i < S.length(); i++) {
            String str = String.valueOf(S.charAt(i));
            if (str.equals(left)) {
                stack.push(0);
            } else {
                int m = stack.pop();
                int n = stack.pop();
                stack.push(n + (Math.max(2*m, 1)));
            }
        }
        return stack.pop();
    }
}
```
### 解法二
```java
public class LeetCode_856_2_括号的分数 {
    public int scoreOfParentheses(String S) {
      return scoreOfSubStr(S, 0, S.length()-1);
    }

    public int scoreOfSubStr(String str, int start, int end) {
        int ans = 0;
        int sum = 0;
        int index = start;
        for (int i= start; i <= end; i++) {
            int value = str.charAt(i) == '(' ? 1 : -1;
            sum += value;
            if (sum == 0) {
                if (i - index == 1) {
                    ans++;
                } else {
                    ans = ans + 2 * scoreOfSubStr(str, index+1, i-1);
                }
                index = i + 1;
            }
        }
        return ans;
    }
}
```
### 解法三
```java
public class LeetCode_856_3_括号的分数 {
    public int scoreOfParentheses(String S) {
        int ans = 0, depth = 0;
        for(int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                if (S.charAt(i - 1) == '(') {
                    ans += 1 << depth;
                }
            }
        }
        return ans;
    }
}

```

# [LeetCode_150_1_逆波兰表达式求值](https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/)
## 题目
根据 逆波兰表示法，求表达式的值。

有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：

整数除法只保留整数部分。  
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 

示例 1：
```
输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
```

示例 2：
```
输入: ["4", "13", "5", "/", "+"]
输出: 6
解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
```

示例 3：
```
输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
输出: 22
解释: 
该算式转化为常见的中缀算术表达式为：
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```

逆波兰表达式：

逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。

平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。  
该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。  
逆波兰表达式主要有以下两个优点：  

* 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
* 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。

## 理解
遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。

### 代码
```java
public class LeetCode_150_1_逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String s : tokens) {
            boolean result = s.matches("\\d+");
            if (result) {
                stack.push(s);
            } else {
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                switch (s) {
                    case "+":
                        stack.push(String.valueOf(a + b));
                        break;
                    case "-":
                        stack.push(String.valueOf(a - b));
                        break;
                    case "*":
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stack.push(String.valueOf(a / b));
                        break;
                    default:
                }
            }

        }
        return Integer.parseInt(stack.pop());
    }
}
```
# [LeetCode_224_1_基本计算器](https://leetcode-cn.com/problems/basic-calculator/)
## 题目
224. 基本计算器
实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。

示例 1：
```
输入：s = "1 + 1"
输出：2
```

示例 2：
```
输入：s = " 2-1 + 2 "
输出：3
```

示例 3：
```
输入：s = "(1+(4+5+2)-3)+(6+8)"
输出：23
```

提示：

1 <= s.length <= 3 * 105  
s 由数字、'+'、'-'、'('、')'、和 ' ' 组成  
s 表示一个有效的表达式  

## 理解
* 解法一：倒序遍历表达式，遇到')'表示要暂存当前遇到的值，遇到'('表示可结算离它最近的')'之间的
值，在暂存起来。由于是逆序读取，整个过程符合后进先出的规则，表达式是从左向右计算。
    1. 如果遍历多个连续数字时，需要还原数值，即123 = 3 + 20 + 100（借助变量n来求值 3*1 + 2*10 + 3*100）
    2. 如果遇到字符不是数字类型，'+'入栈1，'-'入栈-1
    3. 如果遇到')'先入栈，遇到'('则计算左括号与离他最近的右括号之间的值，并入栈
    4. 括号中子表达式计算可使用递归的方法
                                                                                     
* 解法二：
    1. 正向遍历表达式，
    2. 操作数还原，123 = 100 + 20 + 3，遍历到1时是1，遍历到2时是1*10+2=12，遍历到3时是12*10+3=123 
    3. 遇到加减直接计算符号左边表达式的值，再记录符号（+为1，-为-1），边遍历边计算
    4. 遇到'('记录左括号前的计算结果及符号到栈中，然后重新计算，就像计算一个新表达式一样
    5. 遇到')'，首先计算左侧的表达式，产生的结果就是括号字表达的值，再与之前压栈的括号前符号相乘，在与
    括号前结果相加
## 解法一
### 代码
```java
public class LeetCode_224_1_基本计算器 {
    final static int RIGHT = ')';
    public int calculate(String s) {

        s = s.trim();
        // 兼容 -2 + 1，类似这种表达式
        if (s.charAt(0) == '-') {
            s = '0' + s;
        }
        Stack<Integer> stack = new Stack<>();
        // 数字的初始值
        int num = 0;
        // 用来求某个数字的真实值，123 = 3*1 + 2*10 + 1*100
        int n = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char a = s.charAt(i);
            if (Character.isDigit(a)) {
                num += (a - '0') * n;
                n = n * 10;
                continue;
            }
            if (n != 1) {
                stack.push(num);
                // 还原n以及num的值
                n = 1;
                num = 0;
            }
            if (a == '+') {
                stack.push(1);
                continue;
            }
            if (a == '-') {
                stack.push(-1);
                continue;
            }
            if (a == ')') {
                stack.push(RIGHT);
                continue;
            }
            if (a == '(') {
                int value = calculateExpr(stack);
                // 去除右括号
                stack.pop();
                stack.push(value);
            }
        }
        if (n != 1) {
            stack.push(num);
        }
        return calculateExpr(stack); 

    }

    /**
     * 计算（）表达式的值
     * @param stack
     * @return
     */
    private int calculateExpr(Stack<Integer> stack) {
        int value = 0;
        if (!stack.isEmpty()) {
            value = stack.pop();
        }
        while (stack.size() > 1 && stack.peek() != RIGHT) {
            value += stack.pop() * stack.pop();
        }
        return value;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_224_2_基本计算器 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        // 最终结果
        int ans = 0;
        // 辅助累计求某个数字的值
        int num = 0;
        // 符号位，初始为1代表+
        int  sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            boolean digit = Character.isDigit(a);
            if (digit) {
                num = num * 10 + (a - '0');
            } else if (a == '+') {
                ans += sign * num;
                sign = 1;
                num = 0;
            } else if (a == '-') {
                ans += sign * num;
                sign = -1;
                num = 0;
            } else if (a == '(') {
                stack.push(ans);
                stack.push(sign);
                sign = 1;
                ans = 0;
            } else if (a == ')') {
                ans += sign * num;
                ans = ans * stack.pop();
                ans += stack.pop();
                num = 0;
            }
        }
        if (num != 0) {
            ans += sign * num;
        }
        return ans;
    }

}
```
# [LeetCode_227_基本计算器II](https://leetcode-cn.com/problems/basic-calculator-ii/)
## 题目
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:
```
输入: "3+2*2"
输出: 7
```

示例 2:
```
输入: " 3/2 "
输出: 1
```

示例 3:
```
输入: " 3+5 / 2 "
输出: 5
```

说明：

你可以假设所给定的表达式都是有效的。  
请不要使用内置的库函数 eval。

## 理解
* 加减符号与数值统一入栈，比如1-2可看做1+（-2）
* 乘除符号，取栈顶值与当前值做乘除运算结果再入栈，这样保证了乘除法的优先级

### 代码
```java
public class LeetCode_227_基本计算器II {
    public int calculate(String s) {
        // 添加等号，方便处理字符串最后一个数字
        s = s + '=';
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        int num = 0;
        char sigh = '+';
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                num = num * 10 + (arr[i] - '0');
            } else if (arr[i] != ' '){
                // 不是数字，就是 + - * /
                switch (sigh) {
                    case '+':
                        stack.push(num);
                        num = 0;
                        sigh = arr[i];
                        break;
                    case '-':
                        stack.push(-num);
                        num = 0;
                        sigh = arr[i];
                        break;
                    case '*':
                        num = num * stack.pop();
                        stack.push(num);
                        num = 0;
                        sigh = arr[i];
                        break;
                    case '/':
                        num = stack.pop() / num;
                        stack.push(num);
                        num = 0;
                        sigh = arr[i];
                        break;
                    default:
                }
            }
        }
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
```

# [LeetCode_772_基本计算器III](https://leetcode-cn.com/problems/basic-calculator-iii/)
## 题目
实现一个基本的计算器来计算简单的表达式字符串。  
表达式字符串可以包含左括号 (和右括号)，加号 + 和减号 -，非负 整数和空格 。  
表达式字符串只包含非负整数， +, -, *, / 操作符，左括号 ( ，右括号 )和空格。整数除法需要向下截断。  
你可以假定给定的字符串总是有效的。所有的中间结果的范围为 [-2147483648, 2147483647]。  
一些例子：  
```
"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
```

注：不要 使用内置库函数 eval。

## 理解
* 加减符号与数值统一入栈，比如1-2可看做1+（-2）
* 乘除符号，取栈顶值与当前值做乘除运算结果再入栈，这样保证了乘除法的优先级
* 括号整体可看做一个数字，这样就与基础计算器II一致了，遇到'('左括号，
获取与之对应的右括号的坐标，去除括号递归调用计算函数求值。
* 需要注意需要统一的下标管理，来确认轮询位置（递归调用时，处理了部分数据）

### 代码
```java
public class LeetCode_772_基本计算器III {

    public int calculate(String s) {
        s = s.trim();
        char[] arr = s.toCharArray();
        Stack<Integer> stack =  new Stack<>();
        int ans = 0;
        // 辅助求多位数数字
        int num = 0;
        // 遍历下标
        int index = 0;
        char sigh = '+';
        int length = arr.length;
        while (index < length) {
            // 多位数数字求值
            if (Character.isDigit(arr[index])) {
                num += num * 10 + (arr[index] - '0');
            }

            // 如果是左括号，整体看做是一个值
            if (arr[index] == '(') {
                int range = findClosing(s.substring(index));
                // 递归求括号中的值
                num = calculate(s.substring(index+1, index+range));
                // 坐标值调整
                index += range;
            }

            if ((index == length - 1 || !Character.isDigit(arr[index])) && arr[index] != ' ') {
                // 不是数字和括号，就是 + - * /
                switch (sigh) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        num = num * stack.pop();
                        stack.push(num);
                        break;
                    case '/':
                        num = stack.pop() / num;
                        stack.push(num);
                        break;
                    default:
                }
                num = 0;
                sigh = arr[index];
            }
            index++;
        }

        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    private int findClosing(String s) {
        int value = 0;
        int index = 0;
        while (true) {
            if (s.charAt(index) == '(') {
                value++;
            } else if (s.charAt(index) == ')') {
                value --;
                if (value == 0) {
                    return index;
                }
            }
            index++;
        }
    }
}
```