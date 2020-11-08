package leetcode.week01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 01 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] nums = new int[]{2, 7, 11, 15};
        // int[] nums = new int[]{3,2, 3};
        int target = 9;
        // int[] ints = twoSum.twoSum(nums, target);
        int[] ints = twoSum.twoSum01(nums, target);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }
    //————————————————第一遍 2020/11/7 --------------------

    /**
     * 暴力遍历  leetcode中提交超时  时间复杂度 O(n^2)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        //  List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int[] positionNum = new int[2];
        int length = nums.length;

        int moveJ = 0;
        for (int index = 0; index < length; index++) {

            for (moveJ = index + 1; moveJ < length; moveJ++) {

                int a = nums[index];
                int b = nums[moveJ];
                if (a + b == target) {
                    positionNum[0] = index;
                    positionNum[1] = moveJ;
                }
            }

        }

        return positionNum;
    }

    /***
     * 使用hashmap存储 匹配值得出符合目标的数组下标 时间复杂度 O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum01(int[] nums, int target) {
        int[] positionNum = new int[2];

        int length = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int index = 0; index < length; index++) {

            if (map.containsKey(target - nums[index])) {
                return new int[]{map.get(target - nums[index]), index};
            }
            map.put(nums[index], index);
        }
        //map() => map(2,0)
        // map(2,0)  map.containkey(2) ==> map.get(2) = 0 => [map.get(2),index = 1] == > [0,1]

        return positionNum;
    }
}
