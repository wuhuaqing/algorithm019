package leetcode;

public class MoveZeros {

    public static void main(String[] args) {
      // int[] nums =  new MoveZeros().moveZeros(new int[]{0,1,3,0,12});
      int[] nums =  new MoveZeros().moveZeros(new int[]{3,0,3,5,0,12});
        //   int[] nums =  new MoveZeros().moveZeros01(new int[]{3,0,3,0 ,12});
        for (int el : nums ){
            System.out.print(el + " , ");
        }
    }

    /**
     * [0,1,3,0,12]
     * @param nums
     * @return
     */
    public  int[] moveZeros(int[] nums){
        int change = 0 ;
        //单个循环中
        for(int index =0 ;index<nums.length;index++){
            //将数组中的非零元素复制到数组前部，原先位置上的数据置0
            if(nums[index]!=0){
              nums[change] =  nums[index];
              //不一致代表此处数据需要置0
              if(change!=index){
                 nums[index] = 0;
              }
              //只有数组中元素不为0，change才能往后挪
              change++;
            }
        }
        return nums;
    }


    /**
     * [0,1,3,0,12]
     * @param nums
     * @return
     */
    public  int[] moveZeros01(int[] nums){
        int change = 0 ;
        //第一个循环将非零元素都复制到数组前部
        for(int index =0 ;index<nums.length;index++){
            if(nums[index]!=0){
               nums[change] = nums[index];
               change ++ ;
            }
        }
       //第二个循环将数组后部的元素全部置为0
        for (;change<nums.length;){
            nums[change] = 0;
            change++;
        }
        return nums;
    }

}
