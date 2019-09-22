package com.study.zl.algorithm.array;

import java.nio.charset.Charset;
import java.util.*;

/**
 * @Author long
 * @Date 2019/8/14 9:47
 */
public class SortArray {

    public static void main(String[] args) {
        String params1 = "ab";
        System.out.println(params1.substring(0, 1));

    }


    public static String getHuiWen(String str) {
        int len = str.length();
        if (len == 0) {
            return  "";
        }
        int resultLen = 1;
        String resultStr = str.substring(0, 1);
        for (int i = 0; i < len; i++) {
            String oddStr = searchMaxLen(str, len, i, i);
            String evenStr = searchMaxLen(str, len, i, i + 1);
            String myStr = oddStr.length() >= evenStr.length() ? oddStr : evenStr;
            if (myStr.length() > resultLen) {
                resultStr = myStr;
                resultLen = resultStr.length();
            }
        }
        return resultStr;
    }

    public static String searchMaxLen(String str, int len, int left, int right) {
        int l = left;
        int r = right;
        while (l >= 0 && right < len && (str.charAt(l) == str.charAt(r))) {
            l --;
            r ++;
        }
        return str.substring(l + 1, r);
    }



    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        // 兜底的字符串和字符串长度
        int longestPalindrome = 1;
        String longestPalindromeStr = s.substring(0, 1);
        for (int i = 0; i < len; i++) {
            // 奇数
            String palindromeOdd = centerSpread(s, len, i, i);
            // 偶数
            String palindromeEven = centerSpread(s, len, i, i + 1);
            // 获取两个里面最大子串
            String maxLen = palindromeOdd.length() > palindromeEven.length() ? palindromeOdd : palindromeEven;
            // 更新longestPalindrome 和 longestPalindromeStr
            if (maxLen.length() > longestPalindrome) {
                longestPalindrome = maxLen.length();
                longestPalindromeStr = maxLen;
            }
        }
        // 返回最终的结果
        return longestPalindromeStr;
    }

    // 从中心向两边扩展
    private String centerSpread(String s, int len, int left, int right) {
        int l = left;
        int r = right;
        // 从中心节点往左右两边查找
        // l >= 0 && r < len && 并且左右两边的两个字符是否相等
        while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        // 这里要特别小心，跳出 while 循环的时候，是第 1 个满足 s.charAt(l) != s.charAt(r) 的时候
        // 所以，不能取 l，不能取 r--> (字符串截取的时候，属于左闭右开区间)
        // 返回给定区间内的最大回文子串
        return s.substring(l + 1, r);
    }






    public static int lookEasyTo(String s) {
        int length = s.length();
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < length; j ++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static int lookEasy(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int i = 0;
        int j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j ++));
                ans = Math.max(j - i, ans);
            } else {
                set.remove(s.charAt(i ++));
            }
        }
        return ans;
    }



    // 给定一个字符串，请你找出其中不含重复字符的最长子串的长度
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if(lookUp(i, j, s)) {
                    ans = Math.max(j - i, ans);
                }

            }
        }
        return ans;
    }

    public static boolean lookUp(int start, int end, String s) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }
}
