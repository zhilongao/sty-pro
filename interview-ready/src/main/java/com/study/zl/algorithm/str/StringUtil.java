package com.study.zl.algorithm.str;

import java.util.*;

/**
 * @Author long
 * @Date 2019/9/7 9:34
 */
public class StringUtil {
    final static Set<Character> sets = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public static void main(String[] args) {
        String s1 = "hello";
        String result1 = "holle";

        String s2 = "leetcode";
        String result2 = "leotcede";

        String s = reverseVowels(s2);
        System.out.println(s);
    }

    // 交换元音字母
    public static String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left <= right) {
            while (!judgeVowel(chars[left])) {
                left ++;
                if (left > s.length() -1) {
                    break;
                }
            }
            while (!judgeVowel(chars[right])) {
                right --;
                if (right < 0) {
                    break;
                }
            }
            if (left > right) {
                break;
            }
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
        return new String(chars);
    }

    public static boolean judgeVowel(char c) {

        return sets.contains(c);
    }


    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] split = s.split("\\s+");
        for (int i = 0; i < split.length ; i++) {
            char[] chars = split[i].toCharArray();
            int low = 0;
            int hig = chars.length - 1;
            while (low < hig) {
                char temp = chars[low];
                chars[low ++] = chars[hig];
                chars[hig --] = temp;
            }
            sb.append(chars);
            if (i != split.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }




    public static String reverseStr5(String s, int k) {
        // 计算操作次数和剩余次数
        int length = s.length();
        int lastNum = length % (2 * k);
        int num = length / (2 * k);
        char[] chars = s.toCharArray();
        for (int i = 1; i < 2 * num; i += 2) {
            int low = (i - 1) * k;
            int high = i * k - 1;
            reverse5(chars, low, high);
        }
        // 剩余的元素大于k个，获取到最后开始的下标
        if (lastNum > k) {
            reverse5(chars, 2 * k * num, 2 * k * num + k - 1);
        } else {
            reverseChar(chars, 2 * k * num, chars.length - 1);
        }
        return new String(chars);
    }

    public static void reverse5(char[] chars, int low, int high) {
        while (low < high) {
            char temp = chars[low];
            chars[low ++] = chars[high];
            chars[high --] = temp;
        }
    }

    // 有效的操作
    public static String reverseStr4(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        int cur = 0;
        while (length >= 2 * k) {
            StringBuilder reverse = new StringBuilder(s.substring(cur, cur + k)).reverse();
            String substring = s.substring(cur + k, cur + k * 2);
            sb.append(reverse).append(substring);
            cur = cur + k * 2;
            length = length - k * 2;
        }
        if (length <= k) {
            StringBuilder reverse = new StringBuilder(s.substring(cur)).reverse();
            sb.append(reverse);
        } else {
            StringBuilder reverse = new StringBuilder(s.substring(cur, cur + k)).reverse();
            String substring = s.substring(cur + k);
            sb.append(reverse).append(substring);
        }
        return sb.toString();
    }


    public static String reverseStr1(String s, int k) {
        // 最后剩余的元素数量
        int lastCnt = s.length() % (2 * k);
        // 总共需要操作的次数
        int num = s.length() / (2 * k);
        // 转换为数组
        char[] chs = s.toCharArray();

        /*int start = 1;
        while (num > 0) {
            int low = (start - 1) * k;
            int high = start * k - 1;
            start = start + 2;
            reverseChar(chs, low, high);
            num --;
        }*/

        for (int i = 1; i <= 2 * num; i += 2) {
            int low = (i - 1) * k;
            int high = i * k - 1;
            reverseChar(chs, low, high);
        }
        if (lastCnt > k) {
            reverseChar(chs, 2 * k * num, 2 * k * num + k - 1);
        } else {
            reverseChar(chs, 2 * k * num, chs.length - 1);
        }
        return new String(chs);
    }

    // 将给定数组的位置
    public static void reverseChar(char[] chs, int low, int high) {
        while (low < high) {
            char temp = chs[low];
            chs[low++] = chs[high];
            chs[high--] = temp;
        }
    }







    public static String reverseStr(String s, int k) {
        char[] split = s.toCharArray();
        int length = split.length;
        int start = 0;
        if (k >= length) {
            return new StringBuilder(s).reverse().toString();
        }
        while (start < length) {
            reverse(split, start, k);
            start = start + 2 * k;
        }
        if (start < length - 1) {
            reverse(split, start, k);
        }
        return new String(split);
    }

    // 交换给定数组前k个元素
    public static void reverse(char[] chars, int start, int k) {
        int length = chars.length;
        int left = start;
        int right = start + k - 1;
        while (right < length && left < length && left < right) {
            // 翻转i 到 k + start的数组中的字符
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left ++;
            right --;
        }
    }




    /**
     * 给定两个字符串，你需要从这两个字符串中找出最长的特殊序列。
     * 最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
     *
     * 子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。
     * 空序列为所有字符串的子序列，任何字符串为其自身的子序列。
     *
     * 输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-uncommon-subsequence-i
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        return 0;
    }






    /**
     * 判断输入单词是否满足下列条件
     * 1. 所有字母都是大写
     * 2. 所有字母都是小写
     * 3. 只有第一个字母是大写
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        int length = word.length();
        boolean firstIsBig = false;
        int bigNum = 0;
        for (int i = 0; i < length ; i++) {
            char c = word.charAt(i);
            boolean upperCase = Character.isUpperCase(c);
            if (upperCase) {
                bigNum ++;
                if (i == 0) {
                    firstIsBig = true;
                }
            }
        }
        if (firstIsBig) {
            return bigNum == 1 || bigNum == length;
        } else {
            return bigNum == 0;
        }
    }


    public boolean canJumpFromPosition(int position, int[] nums) {
        // 可达时返回true
        if (position == nums.length - 1) {
            return true;
        }
        // 当前的index+可跳的步数 所有的步数
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }

    public boolean canJump(int[] nums) {
        return canJumpFromPosition(0, nums);
    }



    public static boolean validPalindrome1(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            if (chars[i] != chars[j]) {
                return verify(chars, i, j - 1) || verify(chars, i + 1, j);
            }
            i ++;
            j --;
        }
        return true;
    }

    public static boolean verify(char[] chars, int start, int end) {
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start ++;
            end --;
        }
        return true;
    }


    // 最多截取一个字符，判断是否为回文
    public boolean validPalindrome(String s) {
        // 直接就是回文串
        if (verify(s)) {
            return true;
        }
        // 截取一个字符判断是否为回文串
        for (int i = 0; i < s.length(); i++) {
            StringBuffer buffer = new StringBuffer(s);
            if (verify(buffer.deleteCharAt(i).toString())) {
                return true;
            }
        }
        return false;
    }

    public boolean verify(String str) {
        int length = str.length();
        int left = 0;
        int right = length;
        for ( ; left < left; left++) {
            if (str.charAt(left) == str.charAt(right)) {
                right--;
                continue;
            }
            return false;
        }
        return true;
    }





    // 编写一个函数来查找字符串数组中的最长公共前缀, 若是不存在公共前缀，则返回""
    // ["flower","flow","flight"] fl
    // ["dog","racecar","car"] ""
    // 所有输入只包含小写字母a-z
    // 水平扫描
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length ; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    // 垂直扫描
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }



    public static String reverseOnlyLetters1(String S) {
        StringBuffer buffer = new StringBuffer();
        int j = S.length() - 1;
        for (int i = 0; i < S.length(); i++) {
            // 若是当前字符为字母时,表示可以和后面的做交换
            if (Character.isLetter(S.charAt(i))) {
                // 找到一个可以交换的交换
                while (!Character.isLetter(S.charAt(j))) {
                    j --;
                }
                buffer.append(S.charAt(j --));
            } else {
                buffer.append(S.charAt(i));
            }
        }
        return buffer.toString();
    }


    // 反转所有的字母
    public static String reverseOnlyLetters(String S) {
        // 使用栈来实现
        Stack<Character> stack = new Stack<>();
        StringBuffer buffer = new StringBuffer();
        char[] chars = S.toCharArray();
        for (char c : chars) {
            if (Character.isLetter(c)) {
                stack.push(c);
            }
        }

        for (char c : chars) {
            if (Character.isLetter(c)) {
                buffer.append(stack.pop());
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }


    public static boolean judgeIsLetter(char c) {
        if ((c >= 97 && c <= 122) || (c >= 65 && c<= 90)) {
            return true;
        }
        return false;


    }




    // 返回最后一个单词的长度
    public static int lengthOfLastWord(String s) {
        String dealStr = s;
        if ((s == null) || ((dealStr = s.trim()).length() == 0)) {
            return 0;
        }
        int result = 0;
        for (int i = dealStr.length() - 1; i >= 0 ; i--) {
            if (dealStr.charAt(i) != ' ') {
                result ++;
            } else {
                break;
            }
        }
        return result;
    }


    // 压缩算法->优化
    public static int compress1(char[] chars) {
        // 定义锚点
        int anchor = 0;
        // 定义写下标
        int write = 0;
        // 定义都下标
        int read = 0;
        // 从第一个读取到字符数组的最后一个
        for (read = 0; read < chars.length; read++) {
            // 若是已经读取到了字符数组的最后一个或者是read位置处和read+1位置处的字符不相等
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                // 从0号位置开始存放元素，write做计数用
                chars[write++] = chars[anchor];
                //
                if (read > anchor) {
                    // 将字符个数拆分成为字符，挨个写入到chars数组里面
                    String numStr = (read - anchor + 1) + "";
                    char[] numCharArray = numStr.toCharArray();
                    for (char c: numCharArray) {
                        chars[write++] = c;
                    }
                }
                // 将read + 1下标赋值给anchor
                anchor = read + 1;
            }
        }
        return write;
    }


    /**
     *  统计字符串中出现的单例个数->原生方法split实现
     * @param s
     * @return
     */
    public static int countSegments1(String s) {
        String cleanParam = s.trim();
        if (cleanParam.equals("")) {
            return 0;
        }
        String[] split = cleanParam.split("\\s+");
        return split.length;
    }

    /**
     * 统计字符串中出现的单词个数->其它方式实现
     * @param s
     * @return
     */
    public static int countSegments2(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                result ++;
            }
        }
        return result;
    }
}
