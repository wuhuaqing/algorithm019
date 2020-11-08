package leetcode.week01;

/**
 * 26. 删除排序数组中的重复项
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicates {

    public static void main(String[] args) {

        RemoveDuplicates rmDuplicates = new RemoveDuplicates();
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(rmDuplicates.removeDuplicates(nums));

    }

    //--------------------- 第一遍 2020/11/5 ----------------
    /**
     *  前提：有序数组
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        int storeIndex = 0;

        if (nums.length == 0) {
            return 0;
        }
        for (int index = 0; index < nums.length; index++) {
            //前后不相等时，代表
            if (nums[storeIndex] != nums[index]) {
                //排重指针往后挪一步位置 （1，1，1，3）以及（1，1，3）都是如此
                storeIndex++;
                //将不相等的元素复制到排重指针位
                nums[storeIndex] = nums[index];
            }
        }
       //角标从零开始，表示个数得加一
        return storeIndex+=1;

    }
}

// for (int i = 0; i < nums.length; i++) {
//            System.out.print(nums[i]);
//        }
//  System.out.println("----");