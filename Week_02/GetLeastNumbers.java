package leetcode.week02;

import java.util.Arrays;

/**
 * 剑指 Offer 40. 最小的k个数
 */
public class GetLeastNumbers {

    public static void main(String[] args) {
        GetLeastNumbers getLeastNumbers = new GetLeastNumbers();
        int[] ints = {3, 2, 1};
        int[] ar = getLeastNumbers.getLeastNumbers(ints,2);
        for (int num : ar) {
            System.out.print(num + " ");
        }
    }

    public int[] getLeastNumbers(int[] arr, int k) {
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
