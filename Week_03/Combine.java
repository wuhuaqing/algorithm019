package leetcode.week03;

import javax.print.attribute.EnumSyntax;
import java.util.*;

/***
 * 77. 组合  https://leetcode-cn.com/problems/combinations/
 */
public class Combine {

    public static void main(String[] args) {
        Combine combine = new Combine();
        List<List<Integer>> lists = combine.combine(4, 2);
        for (List ins :  lists) {
            System.out.println(ins);
        }
    }


    // 第一二遍 2020/11/22

    public List<List<Integer>> combine(int n,int k){
        if(n<k){
            return new ArrayList<>();
        }
        List<List<Integer>> lists = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        dfsCutsub(n,k,1,stack,lists);
        return lists;

    }

    /**
     * 深度遍历 回溯 + 剪枝法
     * https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
     * @param n
     * @param k
     * @param start
     * @param path
     * @param lists
     */
    public void dfsCutsub(int n,int k,int start,Deque<Integer> path,List<List<Integer>> lists){
        if(path.size() == k){
            lists.add(new ArrayList<>(path));
            return;
        }
        //剪枝后的遍历终点
        int end = n-(k-path.size()) +1;
        for (int i = start; i <= end; i++) {
            path.addLast(i);
            System.out.println("递归前path:" + path);
            dfsCutsub(n,k,i+1,path,lists);
            path.removeLast();
            System.out.println("递归后path:" + path);
        }
    }

    /**
     *
     * @param n
     * @param k
     * @param start
     * @param path
     * @param lists
     */
    public void dfs (int n,int k,int start,Deque<Integer> path,List<List<Integer>> lists){
        //第一步 递归终止条件
        if(path.size() == k ){
              lists.add(new ArrayList<>(path));
              return;
        }

        for (int i = start; i <= n; i++) {
            path.addLast(i);
            System.out.println("递归前path:" + path);
            dfs(n,k,i+1,path,lists);
            path.removeLast();
            System.out.println("递归后path:" + path);
        }

    }




    public List<List<Integer>> combine00(int n, int k) {
        if (n < k) {
            return new ArrayList<>();
        }

        List<List<Integer>> lists = new ArrayList<>();
        Deque<Integer> queque = new ArrayDeque<>();
        dfs00(n, k, 1, queque, lists);
        return lists;
    }

    public void dfs00(int n, int k, int start, Deque<Integer> path, List<List<Integer>> lists) {
        if (path.size() == k) {
            lists.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= n; i++) {
            path.addLast(i);
           // System.out.println("递归之前 => " + path);
            dfs00(n, k, i + 1, path, lists);
            path.removeLast();
          //  System.out.println("递归之后 => " + path);
        }

    }


    /**
     *  递归 + 回溯 模版代码
     */
    public void backtrackStencil (int n,int k,int start,Deque<Integer> path,List<List<Integer>> lists){
        //第一步 递归终止条件
        if( path.size() == k ){
            //收集搜索结构
            lists.add(new ArrayList<>(path));
            return;
        }
        // 集合元素的遍历
        for (int i = start;i<=n ;i++ ) {
            //第二步 本层操作
            path.addLast(i);
            // 第三步 递归往下一层传递参数
            dfs(n,k,i+1,path,lists);
            //第四步 重置状态   // 递归后紧接着就是回溯重置状态
            path.removeLast();
        }
    }

}
