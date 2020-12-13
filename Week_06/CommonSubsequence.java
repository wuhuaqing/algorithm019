package leetcode.week06;

import javax.print.attribute.standard.Finishings;

/**
 * 1143. 最长公共子序列
 */
public class CommonSubsequence {

    public static void main(String[] args) {
           String  text1 = "abcde", text2 = "ace";
        //   String  text1 = "abcde", text2 = "aced";
        //   String  text1 = "", text2 = "";
        // String text1 = " ", text2 = " ";
        int i = new CommonSubsequence().longestCommonSubsequence(text1, text2);
        System.out.println(i);

    }

    //第三遍

    public int longestCommonSubsequence(String text1, String text2){
        if (text1 == null || text2 == null) {
            return 0;
        }
        int m =  text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];

        char[] charT1 = text1.toCharArray();
        char[] charT2 = text2.toCharArray();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //取i位字符
                char cT1 = charT1[i];
                //取j位字符
                char cT2 = charT2[j];
                //当前两字符，则取左上角的值加1 ：代表目前位置的字串个数加1（又找到一个相同的字符）
                if(cT1 == cT2 ){
                   // sb.append(cT1);

                    dp[i+1][j+1] = dp[i][j] +1;
                }else{
                    //两字符不相等，则要保持当前字串之前遍历得到的相同字符数的记录 （中间状态得保持）
                    dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]) ;
                }
            }
        }
       // System.out.println(sb.toString());

        return dp[m][n];
    }

    /**
     * 动态规划题解 https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-tu-wen-jie-xi-by-yijiaoqian/
     * 将两字符串构建成二位表格进行 状态的递推
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence00(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        char[] charsT1 = text1.toCharArray();
        char[] charsT2 = text2.toCharArray();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char t1 = charsT1[i];
                char t2 = charsT2[j];
                //当前两字符，则取左上角的值加1 ：代表目前位置的字串个数加1（又找到一个相同的字符）
                if (t1 == t2) {
                  //  sb.append(t1);
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    //两字符不相等，则要保持当前字串之前遍历得到的相同字符数的记录 （中间状态得保持）
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        // System.out.println(sb.toString());
        return dp[m][n];
    }
}
