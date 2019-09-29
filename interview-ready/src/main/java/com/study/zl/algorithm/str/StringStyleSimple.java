package com.study.zl.algorithm.str;

import java.util.*;

/**
 * @Author long
 * @Date 2019/9/15 9:33
 */
public class StringStyleSimple {

    public static void main(String[] args) throws InterruptedException {
        String a1 = "a"; String a2 = "b";
        String b1 = "aa"; String b2 = "ab";
        String c1 = "aa"; String c2 = "aab";
        boolean bb1 = canConstruct(a1, a2);
        boolean bb2 = canConstruct(b1, b2);
        boolean bb3 = canConstruct(c1, c2);
        System.out.println(bb1 + " " + bb2 + " " + bb3);
    }

    // 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，返回 -1
    // 该字符串中只包含小写字母
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 0);
            } else {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 0) {
                return i;
            }
        }
        return -1;
    }

    // 优化方法：从字符串的前后两个位置同时开始查找，相遇时连个
    public int firstUniqCharA(String s) {
        char[] original = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int index = s.length();
        for (char c : original) {
            int first = s.indexOf(c);
            if (first != -1 && first == s.lastIndexOf(c)) {
                index = Math.min(first, index);
            }
        }
        if (index != s.length()) {
            return index;
        } else {
            return -1;
        }
    }





    // 救赎金问题
    // 第一个字符串里面的字符能不能由第二个字符串里面的字符构成，若可以，返回true。否则返回false。
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] bucket = new int[26];
        for (int i = 0; i < magazine.length() ; i++) {
            // magazine.charAt(i) - 'a' 计算范围只会在0-25范围内
            // 故0-25位置上存储的就是z-a各个字符的个数
            bucket[magazine.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            // 没次遍历，将该位置的字符个数减一
            if (-- bucket[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }




    // 验证回文串，只考虑数字和字母，并且字母忽略大小写
    public static boolean isPalindrome(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        String dealStr = s.toLowerCase();
        int left = 0;
        int right = dealStr.length() - 1;
        while (left < right) {
            while (left < right &&!isSuitable(dealStr.charAt(left))) {
                left ++;
            }
            while (left < right && !isSuitable(dealStr.charAt(right))) {
                right --;
            }
            if (left >= right) {
                return true;
            }
            if (dealStr.charAt(left ++) != dealStr.charAt(right--)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isSuitable (char a) {
        return Character.isLetter(a) || Character.isDigit(a);
    }


    public static String addBinaryA(String a, String b) {
        // 定义一个StringBuilder拼接计算结果
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        // 从两个字符串的尾部开始遍历，直到遍历到最长字符串的首位
        for(int i = a.length() - 1, j = b.length() - 1;i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            // a.charAt(i) - '0' 将ASCII码转换为字符所代表的数值
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            // 将2的余数拼接到字符串上
            ans.append(sum % 2);
            // 将2的除数进位
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }


    private static String fillString(String original, int maxLength) {
        String newStr = original;
        int length = original.length();
        if (length < maxLength) {
            int i = maxLength - length;
            StringBuffer buf = new StringBuffer();
            for (int j = 0; j < i; j++) {
                buf.append("0");
            }
            newStr = buf.toString() + newStr;
        }
        return newStr;
    }



    // 报数问题
    public static String countAndSay(int n) {
        String res = "1";
        for (int i = 2; i <= n ; i++) {
            StringBuffer buf = new StringBuffer();
            // 对res 进行统计
            char ch = res.charAt(0);
            int num = 0;
            for (int j = 0; j < res.length() ; j++) {
                if (ch == res.charAt(j)) {
                    num ++;
                } else {
                    buf.append(num).append(ch);
                    ch = res.charAt(j);
                    num = 1;
                }
            }
            // 更新res
            res = buf.toString();
        }
        return res;
    }



    public static boolean isValid1(String s) {
        // 边缘情况判断
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        int length = s.length();
        if (length % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Set<Character> keys = map.keySet();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (keys.contains(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || map.get(stack.pop()) != c) {
                    return false;
                }
            }
            /*
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.empty() || stack.pop() != '(') {
                        return false;
                    }
                    continue;
                case '}':
                    if (stack.empty() || stack.pop() != '{') {
                        return false;
                    }
                    continue;
                case ']':
                    if (stack.empty() || stack.pop() != '[') {
                        return false;
                    }
                    continue;
            }
            */
        }
        return stack.isEmpty();
    }



    // 验证括号的有效性
    public static boolean isValid(String s) {
        // 字符串为空，返回 true
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        int length = s.length();
        // 字符串长度为奇数，返回 false
        if (length % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put(')', '(');
        map.put('[', ']');
        map.put(']', '[');
        map.put('{', '}');
        map.put('}', '{');
        // 执行判断
        int left = 0;
        int right = length - 1;
        while (left < right) {
            char c = s.charAt(left);
            boolean flag = true;
            if (map.get(c) == s.charAt(left + 1)) {
                left += 2;
            } else if (map.get(c) == s.charAt(right)) {
                left += 1;
                right -= 1;
            } else {
                // 还有一种情况， 递归调用一下哈
                // (([]){})
                int searchIndex = left + 1;
                while (searchIndex <= right && map.get(c) != s.charAt(searchIndex)) {
                    searchIndex += 1;
                }
                if (searchIndex == right) {
                    flag = false;
                } else {
                    isValid(s.substring(left, searchIndex));
                    left = searchIndex + 1;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }




    /**
     * 查询字符串数组 单词数组
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        // 单词数组长度
        int lenw = words.length;
        // 查询数组长度
        int lenq = queries.length;
        // 返回值数组,长度等于查询数组长度
        int res[] = new int[lenq];

        // 定义两个数组，存储每个单词最小字符个数
        int countw[] = new int[lenw];
        int countq[] = new int[lenq];
        // 统计查询字符串数组最小字符个数
        for(int i=0; i < lenq;i++){
            countq[i] = getMinLetterCount(queries[i]);
        }
        for(int i=0 ; i < lenq;i++){
            countw[i] = getMinLetterCount(words[i]);
            res[i] = 0;
            for(int j = 0 ; j < lenw; j ++) {
                if (countq[i] < countw[j]) {
                    res[i]++;
                }
            }

        }
        return res;
    }



    public static int getMinLetterCount(String s) {
        Map<Character, Integer> map = new HashMap<>();
        List<Character> characters = new ArrayList<>(
                Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',  's',  't',  'u', 'v',  'w',  'x', 'y',  'z'));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            if (map.containsKey(character)) {
                return map.get(character);
            }
        }
        return 0;
    }



    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     * 示例 1:
     * 输入: "III"
     * 输出: 3
     *
     * 示例 2:
     * 输入: "IV"
     * 输出: 4
     *
     * 示例 3:
     * 输入: "IX"
     * 输出: 9
     *
     * 示例 4:
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     *
     * 示例 5:
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    public static int romanToInt(String s) {
        // 算法一：判断后面数值是否大于前面
        Map<Character, Integer> romaNumber = new HashMap<>();
        romaNumber.put('I', 1);
        romaNumber.put('V', 5);
        romaNumber.put('X', 10);
        romaNumber.put('L', 50);
        romaNumber.put('C', 100);
        romaNumber.put('D', 500);
        romaNumber.put('M', 1000);
        int firstVal = 0;
        int nextVal = 0;
        int sum = 0;
        for (int i = 0; i < s.length() ; i++) {
            firstVal = romaNumber.get(s.charAt(i));
            if (i == s.length() - 1) {
                sum = sum + firstVal;
            } else {
                if ((nextVal = romaNumber.get(s.charAt(i+1))) > firstVal) {
                    sum = sum - firstVal;
                } else {
                    sum = sum + firstVal;
                }
            }
        }
        return sum;
    }
}
