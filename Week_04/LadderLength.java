package leetcode.week04;

import java.util.*;

/**
 * 127. 单词接龙 https://leetcode-cn.com/problems/word-ladder/
 */
public class LadderLength {

    public static void main(String[] args) {

//        String beginWord = "hit";
//        String endWord = "cog";
//        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};

//        String beginWord = "hot";
//        String endWord = "dog";
//        String[] wordList = {"hot", "dog"};

//        String beginWord = "a";
//        String endWord = "c";
//        String[] wordList = {"a","b","c"};

        String beginWord = "hot";
        String endWord = "dog";
        String[] wordList = {"hot", "dog", "dot"};

        List<String> wordLists = Arrays.asList(wordList);
        int n = new LadderLength().ladderLength(beginWord, endWord, wordLists);
        // int n = new LadderLength().ladderLength(beginWord, endWord, wordLists);
        System.out.println(n);

    }


    //第三遍 2020/11/25

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        }
        //单词列表节点
        HashSet<String> wordSets = new HashSet<>(wordList);
        //wordSets.remove(beginWord);
        //访问过的单词节点
        HashSet<String> visited = new HashSet<>();
        // visited.add(beginWord);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (buildGrph(poll, queue, wordSets, visited, endWord)) {
                    return step + 1;
                }
            }
            step++;
        }

        return 0;
    }

    public boolean buildGrph(String poll, Queue<String> queue, HashSet<String> wordSets, HashSet<String> visited, String endWord) {

        char[] chars = poll.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            char oldPosChar = chars[j];
            for (char k = 'a'; k <= 'z'; k++) {
                chars[j] = k;
                String tempStr = String.valueOf(chars);
                if (wordSets.contains(tempStr)) {
                    if (tempStr.equals(endWord)) {
                        return true;
                    }
                    //防止图中的环一直循环
                    if (!visited.contains(tempStr)) {
                        queue.offer(tempStr);
                        visited.add(tempStr);
                    }
                }
            }
            //当前某个位置上的字符变换处理完成后，还原为之前的字符
            chars[j] = oldPosChar;

        }
        return false;
    }

    /**
     * 无向无权图中的广度优先搜索 思路  （广度优先模版代码 + 建图处理）
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength01(String beginWord, String endWord, List<String> wordList) {

        //字典数组存入set中
        HashSet<String> wordSets = new HashSet<>(wordList);
        //字符数组长度为0，字典中没有目标字符串
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }
        wordSets.remove(beginWord);

        //已经访问过的字符串集合
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);
        //广度优先搜索 必要的辅助容器： 队列
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        //可达路径
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //取出队头字符串
                String poll = queue.poll();
                if (buildG(poll, wordSets, endWord, queue, visited)) {
                    return step + 1;
                }
            }
            //一次字符串匹配，则代表路径加1
            step++;
        }
        //上面循环操作没有返回路径长度，则代表start - > end 无法在给定的字符数组中转变成功
        return 0;

    }

    public boolean buildG(String poll, HashSet<String> wordSets, String endWord, Queue<String> queue, HashSet<String> visited) {
        char[] chars = poll.toCharArray();
        //当前字符串的长度， 将当前字符串每个位置 依次替换 形成字符串与 字符串数组中的字符串比较，
        // 如果有，则代表当前字符串 通过一次字符变幻 后 与字符串数组中某个字符串相同，路径可达
        for (int j = 0; j < chars.length; j++) {
            //某个位置上的字符要匹配字符时，将原来的的字符保留，遍历匹配后，还原这个位置上的字符，因为字符串每次只能更改一个字符
            char oldPositionChar = chars[j];

            for (char k = 'a'; k <= 'z'; k++) {

                chars[j] = k;
                String nextword = String.valueOf(chars);
                if (wordSets.contains(nextword)) {
                    //最终组装的字符等于目标字符串，返回路径长度
                    if (nextword.equals(endWord)) {
                        return true;
                    }
                    //当前字符串没有被访问过，加入队列，用来进行下一层级搜索
                    if (!visited.contains(nextword)) {
                        queue.offer(nextword);
                        visited.add(nextword);

                    }
                }
            }
            //还原该位置上的字符
            chars[j] = oldPositionChar;
        }
        return false;
    }

    /**
     * 无向无权图中的广度优先搜索 思路  （广度优先模版代码 + 建图处理）
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength00(String beginWord, String endWord, List<String> wordList) {

        //字典数组存入set中
        HashSet<String> wordSets = new HashSet<>(wordList);
        //字符数组长度为0，字典中没有目标字符串
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }
        wordSets.remove(beginWord);

        //已经访问过的字符串集合
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);
        //广度优先搜索 必要的辅助容器： 队列
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        //可达路径
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //取出队头字符串
                String poll = queue.poll();
                char[] chars = poll.toCharArray();
                //当前字符串的长度， 将当前字符串每个位置 依次替换 形成字符串与 字符串数组中的字符串比较，
                // 如果有，则代表当前字符串 通过一次字符变幻 后 与字符串数组中某个字符串相同，路径可达
                for (int j = 0; j < chars.length; j++) {
                    //某个位置上的字符要匹配字符时，将原来的的字符保留，遍历匹配后，还原这个位置上的字符，因为字符串每次只能更改一个字符
                    char oldPositionChar = chars[j];

                    for (char k = 'a'; k <= 'z'; k++) {

                        chars[j] = k;
                        String nextword = String.valueOf(chars);
                        if (wordSets.contains(nextword)) {
                            //最终组装的字符等于目标字符串，返回路径长度
                            if (nextword.equals(endWord)) {
                                //长度加1再返回
                                return step + 1;
                            }
                            //当前字符串没有被访问过，加入队列，用来进行下一层级搜索
                            if (!visited.contains(nextword)) {
                                queue.offer(nextword);
                                visited.add(nextword);
                                // step++;
                            }
                        }
                    }
                    //还原该位置上的字符
                    chars[j] = oldPositionChar;

                }
            }
            //一次字符串匹配，则代表路径加1
            step++;
        }
        //上面循环操作没有返回路径长度，则代表start - > end 无法在给定的字符数组中转变成功
        return 0;

    }
}
