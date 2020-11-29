package leetcode.week04;

/**
 * 153. 寻找旋转排序数组中的最小值 https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMin {

    public static void main(String[] args) {
        //int[] nums = {3, 4, 5, 1, 2};
        int[] nums = {2,1,0, 5,4,3};
        int min = new FindMin().findMin(nums);
        System.out.println(min);
    }

    /**
     * 旋转数组的常见形式  {3, 4, 5, 1, 2} {2,1,0, 5,4,3}
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int target = nums[mid];
            //对应注释中的第一种情形，如果左边界小于等于中间值，取左边界值，去数组右侧找小值
            if (nums[left] <= target) {
                min = Math.min(nums[left], min);
                left = mid + 1;
            } else {
                //对应注释中第二种情形
                // 如果中间小于右边界值，取中间值，去左侧数组中找小值
                if (target <= nums[right]) {
                    min = Math.min(target, min);
                    right = mid - 1;
                } else {
                    //如果中间大于右边界值，右边界值，移动左边界，接着找最小值
                    min = Math.min(nums[right], min);
                    left = mid + 1;
                }
            }

        }
        return min;
    }
}
