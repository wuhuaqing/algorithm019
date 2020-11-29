package leetcode.week04;

import leetcode.week02.GetLeastNumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/submissions/
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        List<String> strings = new GenerateParenthesis().generateParenthesis(3);
        for (String str :
                strings) {
            System.out.println(str);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> list =new ArrayList<>();
        if(n ==0){
           return list;
        }
        generate(list,"",0,0,n);
        return list;
    }

    public void generate(List<String> list,String str,int left,int right,int n){
        if(left == n && right == n ){
            list.add(str);
            return ;
        }

        if(left<n){
            generate(list,str+"(",left+1,right,n);
        }
        if(right<left){
            generate(list,str+")",left,right+1,n);
        }
    }
}
