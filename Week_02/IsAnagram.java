package leetcode.week02;

import java.util.Arrays;
import java.util.HashMap;

//
// 242 有效的字母异位词
// https://leetcode-cn.com/problems/valid-anagram/
// 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1:
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
//
//
// 示例 2:
//
// 输入: s = "rat", t = "car"
//输出: false
//
// 说明:
//你可以假设字符串只包含小写字母。
//
// 进阶:
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
// Related Topics 排序 哈希表
//
public class IsAnagram {

    public static void main(String[] args) {
        //   String  s = "anagram",   t = "nagaram";
        //  String  s = "rat", t = "car" ;
          String s = "whq", t = "qhw";
        // String s = "aacc", t = "ccac";
        System.out.println(new IsAnagram().isAnagram(s, t));
        ;
    }

    //—————————————— 第三遍 2020/11/11 -----------------

    /**
     * 判断两个char[]数组是否相同
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        char [] sCodeArr =  new char[26];
        char [] tCodeArr =  new char[26];
        for (int index= 0;index<sChars.length;index++){
            char sChar = sChars[index];
            char tChar = tChars[index];
            sCodeArr [sChar -'a'] ++;
            tCodeArr [tChar -'a'] ++;
        }
        String sCoStr = String.valueOf(sCodeArr);
        String tCoStr = String.valueOf(tCodeArr);
         System.out.println(sCoStr);
         System.out.println(tCoStr);
        return sCoStr.equals(tCoStr)  ;

      // 一种是相互抵消
       /* int[] store = new int[26];
        for (int i = 0; i < s.length(); i++) {
            //s.charAt(i)-'a' 以 'a'的编码值为起点
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }
        for (int j = 0; j < store.length; j++) {
            if (store[j] != 0) {
                return false;
            }
        }*/

    }

    /**
     * 排序后比较
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram02(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        for (int index = 0; index < s.length(); index++) {
            if(sChars[index] != tChars[index]){
                return false;
            }
        }
        return true;
    }


    //—————————————— 第一/二遍 2020/11/10 -----------------

    /**
     * 异位词 中 只是字符的顺序不同，出现的字符以及字符出现的个数是相同
     * 可以将字符串 转成字符数组，然后进行排序
     * 遍历比较每一个位置上的字符，如果不同则代表两个字符串不是相互异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram01(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);
        for (int i = 0; i < sChars.length; i++) {
            if (sChars[i] != tChars[i]) {
                return false;
            }
        }
        return true;

    }

    /**
     * 异位词，每一个相同的字符通过hash算法得到的哈希值作为hash表的下标，
     * 如果s中有一个字符，通过hash得到它的位置，此位置加1，t中有一个字符，通过hash得到它的位置，此位置减1
     * 如果此位置最终的值为0，代表两个字符串中此字符的个数是相同的。
     * 如果hash表中有位置的值不为0，代表两个字符串有字符的个数不一样，则代表两个字符串不是异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram00(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        //26 为字母的个数为26，hash表的长度也就是26
        int[] store = new int[26];
        for (int i = 0; i < s.length(); i++) {
            //s.charAt(i)-'a' 以 'a'的编码值为起点
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }
        for (int j = 0; j < store.length; j++) {
            if (store[j] != 0) {
                return false;
            }
        }

        return true;

    }

//    public boolean isAnagram(String s, String t) {
//        if (s.length() != t.length()) {
//            return false;
//        }
//        int[] alpha = new int[26];
//        for (int i = 0; i < s.length(); i++) {
//            int sHash = s.charAt(i) - 'a';
//            int tHash = t.charAt(i) - 'a';
//            alpha[sHash]++;
//            alpha[tHash]--;
//        }
//        for (int i = 0; i < 26; i++) {
//            if (alpha[i] != 0) {
//                return false;
//            }
//        }
//        return true;
//    }


    /**
     * 使用hashmap 进行值匹配
     * 无法排除 aacc ccac这类情况
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramError(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        HashMap<Integer, Character> sCharacterHashMap = new HashMap<>();
        HashMap<Integer, Character> tCharacterHashMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            sCharacterHashMap.put(i, sChars[i]);
            tCharacterHashMap.put(i, tChars[i]);
        }
        for (int j = 0; j < sCharacterHashMap.size(); j++) {
            if (!tCharacterHashMap.containsValue(sCharacterHashMap.get(j))) {
                return false;
            }

        }
        return true;

    }
}
