package leetcode.week04;

/**
 * 33. 搜索旋转排序数组  https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class ArraySearch {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int search = new ArraySearch().search(nums, target);
        System.out.println(search);
    }

    /**
     * 数组不是全部有序，部分有序时，需要判断
     * left<=mid 正序时 , nums[left]<= target && target <num[mid] 则代表目标值在左边有序数组中，否则在右侧数组中
     * left > mid 反序时 ，nums[mid]< target && target<=nums[right] 则代表目标值在右边有序数组中，否则在左侧数组中
     * <p>
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return mid;
            }
            //左边数组正序时
            if (nums[left] <= nums[mid]) {
                //判断目标值在不在左边数组中，如果在，移动右边界，不在移动左边界
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    //不在左边数组，移动左边界
                    left = mid + 1;
                }
            } else {
                //左边数组倒序时
                //判断目标值是否在右边数组 ，在就移动做边界，不在移动右边界
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            }
        }
        return -1;

    }
}
