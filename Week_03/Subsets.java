package leetcode.week03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 78. 子集 https://leetcode-cn.com/problems/subsets/
 */
public class Subsets {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        List<List<Integer>> lists = new Subsets().subsets(nums);
        for (List item : lists) {
            System.out.print(item);
        }
    }

 //第一二遍 2020/11/22
    /**
     * 结合 77 组合问题 使用递归回溯的方法进行处理，同时注意剪枝减少不必要的递归
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 遍历 获取 nums 数组不同元素个数的集合
        for (int i = 0; i <= nums.length; i++) {
            backtrack(nums, 0, i, path, lists);
        }
        return lists;
    }

    /**
     * 回溯处理
     * @param nums 数组元素
     * @param start 开始角标
     * @param k 组合元素个数为k  【1，2】[1,3],[1,4] 代表这类组合的元素是两个
     * @param path k个元素的组合数集合
     * @param lists 大集合
     */
    public void backtrack(int[] nums, int start, int k, List<Integer> path, List<List<Integer>> lists) {
        if (path.size() == k) {
            lists.add(new ArrayList<>(path));
            return;
        }
        //剪枝处理
        int end = nums.length - (k - path.size()) + 1;
        for (int i = start; i < end; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1, k, path, lists);
            path.remove(path.size() - 1);
        }

    }


}
