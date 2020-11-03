package leetcode;

/**
 * 加一
 * leetcode 66 https://leetcode-cn.com/problems/plus-one/
 */
public class AddOne {

    public static void main(String[] args) {
        AddOne addOne_66 = new AddOne();
        int[] digits = new int[]{9, 9, 9};
       // digits = addOne_66.plusOne2(digits);
        digits = addOne_66.plusOne(digits);
        for (int num : digits) {
            System.out.print(num);
        }


    }

    public int[] plusOne(int[] digits) {
         int length = digits.length;
         //常规操作加1
         for (int index = length - 1; index >= 0; index--) {
             //最后一位无需进位时，加一操作
             if ( digits[index] ==  9   ) {
                 digits[index] = 0;
             } else {
                 digits[index] +=  1;
                 return digits;
             }

         }

         // 99,999等边界情况
         digits = new int[digits.length +1];
         digits[0] =  1;
         return digits;
     }


    public int[] plusOne1(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }

        }
        //如果所有位都是进位，则长度+1
        digits= new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


}
