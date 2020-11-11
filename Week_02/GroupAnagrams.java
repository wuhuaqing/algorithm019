package leetcode.week02;

import java.util.*;

/***
 * 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 好的题解
 * https://leetcode-cn.com/problems/group-anagrams/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--16/
 *
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        // String[] strs = new String[]{""};
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        System.out.println(groupAnagrams.groupAnagrams(strs));
    }



    //--------------- 第一/二遍2020/11/11---------
    /**
     *  hashmap str--> list来储存
     *  hash函数的设计思路：使用ASCII 码来消除字符串的顺序，并以此来保证异位词的key是同一个
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null ||strs.length == 0) {
            return null;
        }

        List<String> subList = null;
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str: strs ) {
            char[] chars = new char[26];
            for(char ch: str.toCharArray()){
                chars[ch - 'a'] ++;
            }
            String numStr =String.valueOf(chars);
            if(map.containsKey(numStr)){
                map.get(numStr).add(str);
            }else{
                subList =   new ArrayList< >();
                subList.add(str);
                map.put(numStr,subList);
            }

        }
        return new ArrayList<>(map.values());
    }

    /**
     * hashmap str--> list来储存
     * key str使用字符数组排序
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams00(String[] strs) {


        if (strs == null ||strs.length == 0) {
            return null;
        }

        List<String> subList = null;
        HashMap<String, List<String>> map = new HashMap<>();


        for (int index = 0; index < strs.length; index++) {

            String str = strs[index];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortStr = String.valueOf(chars);
            if (map.containsKey(sortStr)) {
                map.get(String.valueOf(chars)).add(str);
            } else {
                subList = new ArrayList<>();
                subList.add(str);
                map.put(sortStr, subList);
            }

        }

        return new ArrayList<>(map.values());

    }

}
