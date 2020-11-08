package leetcode.week01;

/**
 * 加一
 * leetcode 66 https://leetcode-cn.com/problems/plus-one/
 */
public class AddOne {

    public static void main(String[] args) {
        AddOne addOne_66 = new AddOne();
        int[] digits = new int[]{9, 9};
        //  int[] digits = new int[]{1,2,9};
        // digits = addOne_66.plusOne2(digits);
        // digits = addOne_66.plusOne(digits);
       // digits = addOne_66.plusOneSecond(digits);
        //digits = addOne_66.plusOne02Second(digits);
        digits = addOne_66.plusOne03Second(digits);
        for (int num : digits) {
            System.out.print(num);
        }

    }


    //---------------- 第二遍 2020/11/4 -----------------------

    public int[] plusOneSecond(int[] numb) {
        for (int index = numb.length - 1; index >= 0; index--) {
            //不等于9 ，无需进制，加一返回即可
            if (numb[index] != 9) {
                numb[index] += 1;
                return numb;
            } else {
                numb[index] = 0;
                //numb[index--] +=1;
            }
        }
        if (numb[0] == 0) {
            numb = new int[numb.length + 1];
            numb[0] = 1;
        }

        return numb;
    }

    public int[] plusOne02Second(int[] digits) {
        for (int index = digits.length - 1; index >= 0; index--) {
            if(digits[index]!=9){
                digits[index]++;
                return digits;
            }
            digits[index]++;
            digits[index] =  digits[index]%10;
        }

        if (digits[0] == 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }

        return digits;
    }

    public int[] plusOne03Second(int[] digits) {
        for (int index = digits.length - 1; index >= 0; index--) {
            //加一后，取余
            digits[index]++;
            digits[index] =  digits[index]%10;
            //不为9,加一返回,无需进制
            if(digits[index]!=0){
                return digits;
            }
        }
        //（9，9等情况）数组加一个长度，第一位置1
        if (digits[0] == 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }

        return digits;
    }
    //--------------------------------------------


    //----------------第一遍 2020/11/3-----------------------

    public int[] plusOne(int[] digits) {
        int length = digits.length;
        //常规操作加1
        for (int index = length - 1; index >= 0; index--) {
            //最后一位无需进位时，加一操作
            if (digits[index] == 9) {
                digits[index] = 0;
            } else {
                digits[index] += 1;
                return digits;
            }

        }

        // 99,999等边界情况
        digits = new int[digits.length + 1];
        digits[0] = 1;
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
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    //-----------------------------------------------
}
