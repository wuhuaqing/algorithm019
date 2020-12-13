package leetcode.week06;

import java.util.Arrays;

/**
 * 62. 不同路径
 */
public class UniquePaths {

    public static void main(String[] args) {
        int i = new UniquePaths().uniquePaths(3, 7);
        System.out.println(i);
    }

    // 第四遍 2020/12/10

    public int uniquePaths(int m, int n){
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


    // 第三遍 2020/12/9

    public int uniquePaths03(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // i j 这个点的走法是前一个 和 上一个 点 走法之和
                dp[i][j] = dp[i-1][j]+ dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    // 第一二遍 2020/12/8

    public int uniquePaths02(int m, int n) {
        int[]  dp = new int [n];
        Arrays.fill(dp,1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // i j 这个点的走法是前一个 和 上一个 点 走法之和
                dp[j] = dp [j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public int uniquePaths01(int m, int n) {
        int[][] dp = new int[m][n];
        //将第一行第一列的值单独赋值
        for (int i = 0; i < m; i++) {
            //第一列 都只有一种走法
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            //第一行都只有一种走法
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // i j 这个点的走法是前一个 和 上一个 点 走法之和
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    public int uniquePaths00(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            //第一列 都只有一种走法
            dp[i][0] = 1;
            // System.out.println("[" + i + ",0] = " + " " + dp[i][0]);
            for (int j = 0; j < n; j++) {
                //第一行都只有一种走法
                if (i == 0 || j == 0) {
                    dp[0][j] = 1;
                    // System.out.println("[0," + j + "] = " + " " + dp[i][0]);
                    continue;
                }
                // i j 这个点的走法是前一个 和 上一个 点 走法之和
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                // System.out.println("[" + i + "," + j + "] = " + " " + dp[i][j]);
            }
        }
        return dp[m - 1][n - 1];
    }
}
