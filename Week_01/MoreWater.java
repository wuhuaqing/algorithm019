package leetcode.week01;

import leetcode.timeuseproxy.CommonInterface;
import leetcode.timeuseproxy.TimeUseProxy;

/**
 * 盛放最多水的容器
 * leetcode 11 https://leetcode-cn.com/problems/container-with-most-water/
 * https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode-solution/
 */
public class MoreWater implements CommonInterface {


    public static void main(String[] args) {
        TimeUseProxy timeUseProxy = new TimeUseProxy();
        MoreWater moreWater = new MoreWater();
        CommonInterface bind = (CommonInterface) timeUseProxy.bind(moreWater);
        bind.doMethod();

    }

    @Override
    public void doMethod() {

       // int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height = new int[]{1,  2, 1};
      //  System.out.println(maxArea(height));
       // System.out.println(maxAreaSe(height));
        System.out.println(maxArea01Se(height));
    }

    //---------------- 第三遍 2020/11/5 ------------------------

    /**
     * 左右夹逼的方式，逼近最大面积
     * 左右双指针
     * @param height
     * @return
     */
    public int maxAreaSe(int[] height) {
        int area = 0;
        //左右夹逼，比较出最大面积值
        int leftIndex = 0 ; int rightIndex =  height.length -1;
        for(int index = 0; index<height.length;index++){
            //左右不重合时求取面积
            if(leftIndex<rightIndex){
              // 两个元素的最小值
             int faction =    Math.min(height[leftIndex],height[rightIndex]);
             //当次的面积
             int tempArea = faction * (rightIndex - leftIndex);
             //留取面积大的
             area = Math.max(tempArea,area);

             //哪边值大，哪边暂时不动
             if(height[leftIndex]<height[rightIndex]){
                leftIndex ++ ;
             }else {
                 rightIndex--;
             }

            }

        }
        return area;
    }

    /**
     * 一个一个元素往后和所有的元素算面积 O（n^2）
     * @param height
     * @return
     */
    public int maxArea01Se(int[] height) {
        int area = 0;
        // 右指针
        int moveIndex = 0 ;

        for(int index = 0; index<height.length;index++){
            for (moveIndex = index +1;moveIndex<height.length;moveIndex++ ){
                // 两个元素的最小值
                int faction =    Math.min(height[moveIndex],height[index]);
                //当次的面积
                int tempArea = faction * (moveIndex - index );
                //留取面积大的
                area = Math.max(tempArea,area);
            }
        }
        return area;
    }



    //----------------- 第一/二 遍 2020/11/4-------------------------

    /**
     * 单指针，双重循环，时间复杂度 O（n ^ 2）
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int area = 0;
        int moveJ = 0;
        for (int index = 0; index < height.length; index++) {
            //一层层遍历从某个起始点 到之后每个点的面积，做判断
            for (moveJ = index + 1; moveJ < height.length; moveJ++) {
                //获取两个元素中较小的值
                int getSmallValue = height[index] < height[moveJ] ? height[index] : height[moveJ];
                //当次的面积值
                int tempArea = getSmallValue * (moveJ - index);
                //与上一次的面积比较，大的面积保留，用来进行下一次比较
                area = tempArea > area ? tempArea : area;
            }

        }

        return area;
    }

    /**
     * 使用双指针进行判断 时间复杂度 O（ n ）
     *
     * @param height
     * @return
     */
    public int maxArea02(int[] height) {
        int area = 0;
        //左边指针（数组左部）
        int moveLeft = 0;
        //右边指针（数组右部）
        int moveRight = height.length - 1;

        for (int index = 0; index < height.length; index++) {

            //获取两个元素中较小的值
            int getSmallValue = height[moveLeft] < height[moveRight] ? height[moveLeft] : height[moveRight];
            int tempArea = getSmallValue * (moveRight - moveLeft);
            area = tempArea > area ? tempArea : area;


            //左右不重合
            if (moveLeft < moveRight) {
                //哪边小就移动哪边
                if (height[moveLeft] < height[moveRight]) {
                    moveLeft++;
                } else {
                    moveRight--;
                }
            } else { //左右重合，返回面积
                return area;
            }


        }

        return area;
    }

    /**
     * 双指针代码简洁版 时间复杂度 O（ n ）
     *
     * @param height
     * @return
     */
    public int maxArea020(int[] height) {
        int area = 0;
        //左边指针（数组左部）
        int moveLeft = 0;
        //右边指针（数组右部）
        int moveRight = height.length - 1;

        //左右不重合
        while (moveLeft < moveRight) {
            //获取两个元素中较小的值
            int getSmallValue = Math.min(height[moveLeft], height[moveRight]);
            //本次面积与上次面积进行比较，大的保留
            area = Math.max(getSmallValue * (moveRight - moveLeft), area);
            //哪边小就移动哪边
            if (height[moveLeft] < height[moveRight]) {
                moveLeft++;
            } else {
                moveRight--;
            }
        }
        return area;

    }


}
