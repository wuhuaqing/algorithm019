package leetcode.week01;

public class ClimbStairs {

    /**
     * 70 爬楼梯
     * 解题思路：https://leetcode-cn.com/problems/climbing-stairs/solution/javaqing-wa-tiao-tai-jie-he-fei-bo-na-qi-shu-lie-2/
     *
     * @param args
     */
    public static void main(String[] args) {

        ClimbStairs climbStairs = new ClimbStairs();
        //  System.out.println(climbStairs.climbStairs(4));
        //System.out.println(climbStairs.climbStairs02(4));
        // System.out.println(climbStairs.climbStairs03(4));
      //  System.out.println(climbStairs.climbStairs04(5));
        System.out.println(climbStairs.climbStairs(5));
    }

    //----------------- 第三遍 2020/11/8 ----------------------------

    /**
     * 数组储存值
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        for (int index = 2; index < n; index++) {
            nums[index] = nums[index-1]+nums[index-2];
        }
        return nums[n-1] ;
    }

    /**
     * 三变量处理值
     * @param n
     * @return
     */
    public int climbStairsSe00(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int fPreAndPre = 1;
        int fPre = 2;
        int fn =  fPre + fPreAndPre;
        for (int index = 3; index <= n; index++) {

              fn =  fPre + fPreAndPre;
              fPreAndPre =  fPre;
              fPre=fn;

        }
        return fn ;
    }

    //----------------- 第一/二 遍 2020/11/5 -------------------------

    /**
     * 斐波拉契数组，使用递归解决 时间复杂度 O(2^n) 指数级，效率最低的解法
     *
     * @param n
     * @return
     */
    public int climbStairs00(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return climbStairs00(n - 1) + climbStairs00(n - 2);
    }


    /**
     * 斐波拉契数组，化解为非递归（for循环代替）
     *
     * @param n
     * @return
     */
    public int climbStairs01(int n) {
        if (n <= 2) {
            return n;
        }
        int[] feb = new int[n];
        feb[0] = 1;
        feb[1] = 2;
        for (int i = 2; i < n; i++) {
            feb[i] = feb[i - 1] + feb[i - 2];
        }
        return feb[n - 1];
    }



    /**
     *  斐波拉契数组，化解为非递归（for循环代替）,记忆前两个值，去掉数组记录，降低内存空间（由数组变为两个int变量）
     * @param n
     * @return
     */
    public int climbStairs03(int n) {
        if (n <= 2) {
            return n;
        }
        //和          前一项       前第二项
        int nums = 0;int pre = 2;int preAndPre = 1;

        for (int i = 2; i < n; i++) {
             nums = pre + preAndPre;
             preAndPre = pre;
             pre = nums;
        }
        return nums ;
    }

    /**
     * 使用公式解题
     * @param n
     * @return
     */
    public   int climbStairs04(int n) {
        double sqrt = Math.sqrt(5);
        return (int) ((Math.pow((1 + sqrt) / 2, n + 1) - Math.pow((1 - sqrt) / 2, n + 1)) / sqrt);
    }


}
