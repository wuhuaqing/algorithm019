package leetcode;

import leetcode.timeuseproxy.TimeUseProxy;

/** 盛放最多水的容器
 * leetcode 11 https://leetcode-cn.com/problems/container-with-most-water/
 */
public class MoreWater {

    public static void main(String[] args) {
        TimeUseProxy timeUseProxy =  new TimeUseProxy();
        MoreWater moreWater = new MoreWater();
        timeUseProxy.bind(moreWater);


        int[] height =  new int[]{1,8,6,2,5,4,8,3,7};
       // int[] height =  new int[]{2,2,3};
       // System.out.println(moreWater.maxArea(height));
        System.out.println(moreWater.maxArea020(height));

    }

    //-----------------第一遍 2020/11/4-------------------------
    /**
     *  单指针，双重循环，时间复杂度 O（n ^ 2）
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int area = 0 ;
        int moveJ = 0 ;
        for(int index =0; index<height.length; index++){
            //一层层遍历从某个起始点 到之后每个点的面积，做判断
            for(moveJ = index +1; moveJ<height.length;moveJ++){
               //获取两个元素中较小的值
               int getSmallValue =   height[index] < height[moveJ] ? height[index] : height[moveJ ];
               //当次的面积值
               int  tempArea = getSmallValue * (moveJ - index);
               //与上一次的面积比较，大的面积保留，用来进行下一次比较
               area = tempArea > area ? tempArea : area;
            }

        }

        return area;
    }

    /**
     * 使用双指针进行判断 时间复杂度 O（ n ）
     * @param height
     * @return
     */
    public int maxArea02(int[] height) {
        int area = 0 ;
        //左边指针（数组左部）
        int moveLeft = 0 ;
        //右边指针（数组右部）
        int moveRight = height.length-1;

        for(int index =0; index<height.length; index++){

            //获取两个元素中较小的值
             int getSmallValue =   height[moveLeft] < height[moveRight] ? height[moveLeft] : height[moveRight ];
             int tempArea =  getSmallValue * (moveRight- moveLeft);
             area = tempArea > area ? tempArea : area;


            //左右不重合
            if(  moveLeft <  moveRight ){
                //哪边小就移动哪边
                if(height[moveLeft] <height[moveRight]){
                    moveLeft++;
                }else{
                    moveRight--;
                }
            }else { //左右重合，返回面积
               return area;
            }


        }

        return area;
    }

    /**
     * 双指针代码简洁版 时间复杂度 O（ n ）
     * @param height
     * @return
     */
    public int maxArea020(int[] height) {
        int area = 0 ;
        //左边指针（数组左部）
        int moveLeft = 0 ;
        //右边指针（数组右部）
        int moveRight = height.length-1;

        //左右不重合
        while (moveLeft<moveRight){
            //获取两个元素中较小的值
            int getSmallValue =   Math.min(height[moveLeft] , height[moveRight]);
            //本次面积与上次面积进行比较，大的保留
            area = Math.max(getSmallValue * (moveRight- moveLeft),area) ;
            //哪边小就移动哪边
            if(height[moveLeft] <height[moveRight]){
                moveLeft++;
            }else{
                moveRight--;
            }
        }
        return area;

    }


}
