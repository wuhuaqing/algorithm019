package leetcode.week06;

import leetcode.week03.MinDepth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 */
public class MinimumTotal {

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> subLeO = new ArrayList<>();
        List<Integer> subLeT = new ArrayList<>();
        List<Integer> subLeTh = new ArrayList<>();
        List<Integer> subLeF = new ArrayList<>();
        subLeO.add(2);
        subLeT.add(3);
        subLeT.add(4);
        subLeTh.add(6);
        subLeTh.add(5);
        subLeTh.add(7);
        subLeF.add(4);
        subLeF.add(1);
        subLeF.add(8);
        subLeF.add(3);
        list.add(subLeO);
        list.add(subLeT);
        list.add(subLeTh);
        list.add(subLeF);

        int i = new MinimumTotal().minimumTotal(list);
        System.out.println(i);

    }

    //第四遍

    /**
     * DP动态规划（动态递推）题解
     * https://leetcode-cn.com/problems/triangle/solution/san-chong-jie-fa-duo-tu-xiang-jie-120-san-jiao-xin/
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle){
        int row = triangle.size();
        int col =  triangle.get(row-1).size();
        int[][] dp = new int[row+1][col+1];
        for (int i = row-1; i >=0 ; i--) {
            //当前行的列数
            for (int j = triangle.get(i).size() - 1; j >=0; j--) {
                //当前位置的路径值为 下一行的同列 和 下一行的下一列的小值 + 当前位置的元素值
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1] )+ triangle.get(i).get(j);
            }
        }
        //返回三角形顶角的路径值
        return dp[0][0];
    }

    //第三遍
    public int minimumTotal01(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = triangle.get(row - 1).size();
        int[][] dp = new int[row + 1][col + 1];

        for (int i = row - 1; i >= 0; i--) {
            //当前这行的列数
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        // 三角形顶部元素就是最小路径和
        return dp[0][0];
    }


    /**
     * DP动态规划（动态递推）题解
     * https://leetcode-cn.com/problems/triangle/solution/san-chong-jie-fa-duo-tu-xiang-jie-120-san-jiao-xin/
     *
     * @param triangle
     * @return
     */
    public int minimumTotal00(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = triangle.get(row - 1).size();
        int[][] dp = new int[row + 1][col + 1];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
