package leetcode.week02;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        //  int[] ints = {1, 3,4};
        int[] ints = {1, 3, -1, -3, 5, 3, 6, 7};

//         int ar = new MaxSlidingWindow().getMaxValue(ints, 1);
//         System.out.println(ar);

        int[] ar = new MaxSlidingWindow().maxSlidingWindow(ints, 3);
        for (int num : ar) {
            System.out.print(num + " ");
        }
    }


    //------- 第三遍 2020/11/18 -------------------

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];

        Queue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int index = 0; index < nums.length; index++) {
            int item = nums[index];
            int oldStartIndex = index - k;
            if (oldStartIndex >= 0) {
                heap.remove(nums[oldStartIndex]);
            }
            heap.offer(item);
            //取出当前滑动窗口中的堆顶元素
            if (index - k + 1 >= 0) {
                result[index - k + 1] = heap.peek();
            }

        }
        return result;

    }


    //---------- 第一二遍 2020/11/17 ——----------------

    /**
     * 虽然超出时间限制，但是最好理解
     * 使用堆来处理
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow02(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        //k大于数组长度，无法构建窗口
        if (k > nums.length) {
            return new int[0];
        }
        //PriorityQueue 默认是小顶堆，这里使用大顶堆，需要给定一个比较器
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int subCount = nums.length - k + 1;
        int[] result = new int[subCount];
        for (int index = 0; index < nums.length; index++) {

            int start = index - k;
            //将已不再窗口中的元素移出堆
            if (start >= 0) {
                heap.remove(nums[start]);
            }
            //将最新的值加入堆中
            heap.offer(nums[index]);

            if (heap.size() == k) {
                result[index - k + 1] = heap.peek();
            }

        }
        return result;

    }


    /**
     * 双端队列 解法 暂时理解不了
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow01(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return nums;
        }
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // 结果数组
        int[] result = new int[nums.length - k + 1];
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if (i + 1 >= k) {
                result[i + 1 - k] = nums[queue.peek()];
            }
        }
        return result;
    }


    /***
     * leetcode超出时间限制
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow00(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        //k大于数组长度，无法构建窗口
        if (k > nums.length) {
            return new int[0];
        }
        int subCount = nums.length - k + 1;
        int[] result = new int[subCount];
        for (int index = 0; index < subCount; index++) {
            //宽口的起点
            int start = index;
            int[] subArr = new int[k];
            //构造窗口数组
            for (int j = 0; j < k; j++) {
                subArr[j] = nums[start++];
            }
            //取出窗口中的最大值
            int max = getMaxValue(subArr, k);
            //将本次窗口中的最大值放入数组中
            result[index] = max;

        }
        return result;

    }

    /**
     * 使用优先队列实现堆排序
     *
     * @param nums
     * @param k
     * @return
     */
    public int getMaxValue(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int index = 0; index < nums.length; index++) {
            int item = nums[index];
            if (heap.size() < k) {
                heap.offer(item);
            } else if (heap.peek() > item) {
                heap.poll();
                heap.offer(item);
            }
        }
        return heap.poll();
    }
}
