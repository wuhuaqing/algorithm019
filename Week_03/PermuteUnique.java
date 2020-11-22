package leetcode.week03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II https://leetcode-cn.com/problems/permutations-ii/
 */
public class PermuteUnique {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3};
        List<List<Integer>> lists = new PermuteUnique().permuteUnique(nums);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, path, lists);
        return lists;
    }

    public void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> lists) {
        if (path.size() == nums.length) {
            // lists.add( path ); 这种写法不行，将数组引用放入list，后面的操作，会修改path,lists中的结果会受影响，使用下面这种方式，拷贝值到list中
            lists.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //数组元素前一个和后一个相等
           boolean isRepeat =  (i>0 ) && (nums[i] == nums[i-1])  &&  !used[i-1];
           if(isRepeat){
               continue;
           }

            if (!used[i]){
                used[i] = true;
                path.add(nums[i]);
                backtrack(nums,used,path,lists);
                path.remove(path.size()-1);
                used[i] = false;

            }
        }

    }
}
