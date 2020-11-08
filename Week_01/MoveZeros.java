package leetcode.week01;

/**
 * 移动零
 * leetcode  283  https://leetcode-cn.com/problems/move-zeroes/
 */
public class MoveZeros {

    public static void main(String[] args) {
        // int[] nums =  new MoveZeros().moveZeros(new int[]{0,1,3,0,12});
        //int[] nums =  new MoveZeros().moveZeros(new int[]{3,0,3,5,0,12});
        //   int[] nums =  new MoveZeros().moveZeros01(new int[]{3,0,3,0 ,12});
        int[] nums = new MoveZeros().moveZeros(new int[]{0, 4, 3, 0, 12});
        for (int el : nums) {
            System.out.print(el + " , ");
        }
    }

    // --------------- 第三遍 2020/11/8 ----------------

    public int[] moveZeros(int[] nums) {
        int moveJ = 0;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] != 0) {
                nums[moveJ] = nums[index];
                //只有慢指针和正常指针不同时，将需要置换的位置元素置为0，避免出现两指针同一位置导致的错误置0
                if (moveJ != index) {
                    nums[index] = 0;
                }
                moveJ++;
            }
        }

        return nums;
    }


    public int[] moveZerosa(int[] nums) {
        int change = 0;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] != 0) {
                nums[change] = nums[index];
                change++;
            }
        }
        for (int k = change; k < nums.length; k++) {
            nums[k] = 0;
        }
        return nums;
    }


    //----------------第二遍 2020/11/4------------------

    /**
     * [0,1,3,0,12]
     *
     * @param nums
     * @return
     */
    public int[] moveZerosSe(int[] nums) {

        int changeJ = 0;
        for (int index = 0; index < nums.length; index++) {
            // 不为0进行置换操作
            if (nums[index] != 0) {
                nums[changeJ] = nums[index];
                //位置不一致时，代表index处需要置0
                if (index != changeJ) {
                    nums[index] = 0;
                }
                //只有元素不为0时，changeJ才往后挪
                changeJ++;
            }

        }
        return nums;

    }


    //-----------------第一遍 ---------------------

    /**
     * [0,1,3,0,12]
     *
     * @param nums
     * @return
     */
    public int[] moveZeros00(int[] nums) {
        int change = 0;
        //单个循环中
        for (int index = 0; index < nums.length; index++) {
            //将数组中的非零元素复制到数组前部，原先位置上的数据置0
            if (nums[index] != 0) {
                nums[change] = nums[index];
                //不一致代表此处数据需要置0
                if (change != index) {
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
     *
     * @param nums
     * @return
     */
    public int[] moveZeros01(int[] nums) {
        int change = 0;
        //第一个循环将非零元素都复制到数组前部
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] != 0) {
                nums[change] = nums[index];
                change++;
            }
        }
        //第二个循环将数组后部的元素全部置为0
        for (; change < nums.length; ) {
            nums[change] = 0;
            change++;
        }
        return nums;
    }

}
