package leetcode.week03;

import leetcode.week01.ClimbStairs;
import leetcode.week02.GetLeastNumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/submissions/
 * //数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * // 示例：
 * //
 * // 输入：n = 3
 * //输出：[
 * //       "((()))",
 * //       "(()())",
 * //       "(())()",
 * //       "()(())",
 * //       "()()()"
 * //     ]
 */
public class GenerateParenthesis {


    public static void main(String[] args) {
        List<String> list = new GenerateParenthesis().generateParenthesis(3);
        for (String str : list) {
            System.out.println(str);
        }
    }

    //第三遍 2020/11/20

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0) {
            return list;
        }
        int left = 0;
        int right = 0;
        String s = "";
        _generatePar(n, left, right, s, list);
        return list;
    }

    private void _generatePar(int n, int left, int right, String s, List<String> list) {
        //左右两边都是n个括号
        if (right==n && left==n) {
            list.add(s);
            return;
        }
        //左边括号小于n个时，生成左括号
        if (left < n) {
            _generatePar(n, left + 1, right, s + "(", list);
        }
        //右边括号小于n个时，生成右括号
        if (right < left) {
            _generatePar(n, left, right + 1, s + ")", list);
        }


    }


    public List<String> generateParenthesis00(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0) {
            return list;
        }

        _generatePar00(n, 0, 0, "", list);
        return list;
    }

    public void _generatePar00(int n, int left, int right, String s, List<String> list) {
        //先写终结条件 左右括号个数相等时
        if (left == n && right == n) {
            list.add(s);
            return;
        }

        //左括号可以加的条件是左括号的个数小于N
        if (left < n) {
            _generatePar00(n, left + 1, right, s + "(", list);
        }
        //右括号可以加的条件是，右括号的个数小于左括号的个数
        if (right < left) {
            _generatePar00(n, left, right + 1, s + ")", list);
        }

    }


}
