package leetcode.week04;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 169. 多数元素 https://leetcode-cn.com/problems/majority-element/
 */
public class MajorityElement {

    public static void main(String[] args) {
         int [] nums = new int[]{ 2,2,1,1,1,2,2};
        //int[] nums = new int[]{3, 2, 3};
        int i = new MajorityElement().majorityElement(nums);
        System.out.println(i);
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        //System.out.println(length/2);
        int item = length % 2 == 0 ? length / 2 : length / 2 + 1;
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry en : entries) {
            Integer key = (Integer) en.getKey();
            Integer value = (Integer) en.getValue();
            if (value >= item) {
                result = key;
            }
        }
        return result;
    }
}
