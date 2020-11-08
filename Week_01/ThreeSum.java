package leetcode.week01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15 三数之和
 * https://leetcode-cn.com/problems/3sum/
 * 给定数组 nums = []，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p> 题解
 * https://leetcode-cn.com/problems/3sum/solution/pai-xu-shuang-zhi-zhen-zhu-xing-jie-shi-python3-by/
 * https://leetcode-cn.com/problems/3sum/solution/15-san-shu-zhi-he-ha-xi-fa-shuang-zhi-zhen-fa-xi-2/
 * https://leetcode-cn.com/problems/3sum/solution/san-shu-zhi-he-javajian-ji-ti-jie-by-wang-zi-hao-z/
 */
public class ThreeSum {


    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        //int [] nums = new int[]{-1, 0, 1, 2, -1, -4};
        // int [] nums = new int[]{-2,0,0,2,2};
        //int[] nums = new int[]{-2, 0, 0, 0, 2, 2, 2};
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        // List<List<Integer>> lists = threeSum.threeSum(nums);
        List<List<Integer>> lists = threeSum.threeSum00(nums);
        System.out.println(lists);

    }

    //---------- 第一遍 2020/11/7 ------------

    /**
     * 整体思路： a + b + c = 0
     * -a =  b+c
     * b,c使用双指针进行选择
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同  //第一层循环判断前后两个元素不相同是为了排除两个相同的 《三数之和》 的数组
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * 整体思路： a + b + c = 0
     *      * -a =  b+c
     *      * b,c使用双指针进行选择
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum00(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();

        for (int index = 0; index < length; index++) {
            //临近两值相等，则容易出现重复的数组
            if (index > 0 && nums[index] == nums[index - 1]) {
                continue;
            }
            int a = nums[index];

            int rightMove = length - 1;

            //得到 需要的  b+c 的和
            int target = -(a);

            for (int leftMove = index + 1; leftMove < length; leftMove++) {

                //临近两值相等，则容易出现重复的数组
                if (leftMove > index + 1 && nums[leftMove] == nums[leftMove - 1]) {
                    continue;
                }

                // 需要保证 b 的指针在 c 的指针的左侧
                while (leftMove < rightMove && nums[leftMove] + nums[rightMove] > target) {
                    rightMove--;
                }
                 // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (leftMove == rightMove) {
                    break;
                }
                int b = nums[leftMove];
                int c = nums[rightMove];

                if (b + c == target) {

                    List subList = new ArrayList();
                    subList.add(a);
                    subList.add(b);
                    subList.add(c);
                    lists.add(subList);

                }
            }
        }
        return lists;
    }


}

