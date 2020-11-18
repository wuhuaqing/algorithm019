package leetcode.week02;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 347. 前 K 个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * 前多少位的热搜词等应用场景
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 */
public class TopKFrequent {

    public static void main(String[] args) {
       int[] nums = new int[]{1,1,1,2,2,3,3,4,4,4,5,5};
       int k = 2;
        int[] ints = new TopKFrequent().topKFrequent(nums, k);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
        }
    }

    //----------第一二遍 2020/11/18 -----------

    /**
     * 使用小顶堆 来得到top k  时间复杂度是 O(n * logk)
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 0 || k == 0 ){
            return new int[0];
        }
        // 元素值，元素出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[k];

        for (int index = 0; index < nums.length; index++) {
            map.put(nums[index],map.getOrDefault(nums[index],0) +1);
        }

        //使用小顶堆，默认堆顶元素为最小值，每次比较堆顶元素，如果比堆顶元素小，不入堆，如果比堆顶元素大，入堆后会加堆里的最小元素放回堆顶
        //小顶堆中判断数组第二位的大小，数组第二位代表元素出现的次数
        Queue<int[]> heap =  new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int [] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (HashMap.Entry entry : map.entrySet()) {
            int key = (int) entry.getKey();
            int count = (int) entry.getValue();
            if(heap.size() == k ){
                // 如果小于堆顶元素，不入堆;大于堆顶元素，删除堆顶元素，将较大值入堆
                if(heap.peek()[1] < count ){
                    heap.poll();
                    heap.offer(new int[]{key,count});
                }
            }else{
                heap.offer(new int[]{key,count});
            }
        }

        for (int index = 0; index < k; index++) {
            result[index] = heap.poll()[0];
        }

        return result;
    }
}
