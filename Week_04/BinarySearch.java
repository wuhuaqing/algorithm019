package leetcode.week04;

/**
 * 二分查找模版代码
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        //奇数整除向下取整
        System.out.println(nums.length/2);
    }
    /**
     * 二分查找三个必要条件：
     * 1。数据单调性（单调递增或者单调递减）
     * 2。上下有界 数组有上下界
     * 3。可以通过索引查找
     * @param array
     * @param target
     */
    public int binarySearch(int[] array,int target){
        int left = 0 ;
        int right = array.length-1;
        int mid =  0;
         while (left<=right){
             //中间索引
             mid = (right - left)/2 +left ;
             //中间元素为要查找的目标值，返回目标索引
             if(array[mid] == target){
                 return  mid ;
             }else if(array[mid]>target){
                //目标值在左侧
                 right = mid -1;
             }else if(array[mid]<target){
                 //目标值在右侧
                 left = mid+1;
             }
         }
         return -1;
    }
}
