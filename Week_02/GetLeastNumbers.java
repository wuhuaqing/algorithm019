package leetcode.week02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer 40. 最小的k个数 https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 *
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/tu-jie-top-k-wen-ti-de-liang-chong-jie-fa-you-lie-/
 */
public class GetLeastNumbers {

    public static void main(String[] args) {
        GetLeastNumbers getLeastNumbers = new GetLeastNumbers();
       int[] ints = {3, 2, 1};
       // int[] ints = {0,1, 2, 1};
        //  int[] ints = {1,3,-1,-3,5,3,6,7};
        int[] ar = getLeastNumbers.getLeastNumbers(ints,2);
        for (int num : ar) {
            System.out.print(num + " ");
        }
    }


    //------- 第三遍 2020/11/18 -------------------

    public int[] getLeastNumbers(int[] arr, int k) {
       if(arr.length == 0 || k == 0){
            return new int[0];
        }
       //设置大顶堆
       Queue<Integer> heap =   new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int index = 0; index < arr.length; index++) {
            if(heap.size() < k){
                heap.offer(arr[index]);
            }
            // 如果数组元素小于堆顶元素，将较小元素放入堆中 此步操作确保入堆元素只有较小元素，排除掉不必入堆的大元素
            else if(heap.peek()>arr[index]){
                heap.poll();
                heap.offer(arr[index]);
            }
        }
        int[] result = new int[k];
        for (int j = 0; j < k; j++) {
            result[j] = heap.poll();
        }
        return result;
    }


    //---------- 第一二遍 2020/11/17 ---------------

    /**
     * 使用堆来处理 java中的最有数据结构是 PriorityQueue 优先队列
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers01(int[] arr, int k) {
        if(k== 0 || arr.length == 0 ){
            return new int[0];
        }

         Queue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        //
        for (int index = 0; index < arr.length; index++) {
            int item =  arr[index];
            if(heap.size()<k){
                heap.offer(item);
            }else if(heap.peek()>item){
                heap.poll();
                heap.offer(item);
            }
        }
        int[] newArr =  new int[heap.size()];
        for (int index = 0; index < newArr.length; index++) {
            newArr[index  ] = heap.poll();
        }

        return newArr;
    }

    public int[] getLeastNumbers00(int[] arr, int k) {
        int[] ints = new int[k];
        if(arr.length == 0 ){
          return ints;
        }
        Arrays.sort(arr);

        for (int index = 0; index < k; index++) {
            ints[index] = arr[index];
        }
        return ints;
    }
}
