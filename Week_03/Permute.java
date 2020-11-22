package leetcode.week03;

import java.util.*;

/**
 * 46. 全排列  https://leetcode-cn.com/problems/permutations/
 */
public class Permute {
    //         int index = 0;
    //         System.arraycopy(nums,index+1 ,temp,0  ,nums.length-1);
    //         for (int i = 0; i < temp.length; i++) {
    //            System.out.print(temp[i]);
    //        }
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int[] temp = new int[nums.length - 1];

        List<List<Integer>> lists = new Permute().permute(nums);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//
//        List<Integer> output = new ArrayList<Integer>();
//        for (int num : nums) {
//            output.add(num);
//        }
//
//        int n = nums.length;
//        backtrack(n, output, res, 0);
//        return res;
//    }
//
//    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
//        // 所有数都填完了
//        if (first == n) {
//            res.add(new ArrayList<Integer>(output));
//        }
//        for (int i = first; i < n; i++) {
//            // 动态维护数组
//            Collections.swap(output, first, i);
//            // 继续递归填下一个数
//            backtrack(n, output, res, first + 1);
//            // 撤销操作
//            Collections.swap(output, first, i);
//        }
//    }

    //第一二遍 2020/11/22

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // dfs(nums, permuteSub,lists);
        boolean[] used = new boolean[nums.length];
        backTrack(nums, used, path, lists);
        return lists;
    }

    public void backTrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> lists) {
        if (path.size() == nums.length) {
            lists.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                // System.out.println("递归前 path => " + path );
                backTrack(nums, used, path, lists);
                path.remove(path.size() - 1);
                used[i] = false;
                // System.out.println("递归回溯 path  used=> " + path +  used[i]);
            }
        }

    }


    public void dfs(int[] nums, List<Integer> permuteSub, List<List<Integer>> lists) {
        if (permuteSub.size() == nums.length) {
            lists.add(new ArrayList<>(permuteSub));
            return;
        }


        for (int index = 0; index < nums.length; index++) {
            if (permuteSub.contains(nums[index])) {
                continue;
            }
            permuteSub.add(nums[index]);
            dfs(nums, permuteSub, lists);
            permuteSub.remove(permuteSub.size() - 1);

        }
    }
}
