package leetcode.week04;

/**
 * 69. x 的平方根
 */
public class MySqrt {

    public static void main(String[] args) {
        int i = new MySqrt().mySqrt(9);
        System.out.println(i);
    }

    public int mySqrt(int x) {
        if(x == 0){
            return x;
        }
        long left = 1;
        long right = x / 2;
        long mid = 0;
        while (left < right) {
            mid = (left + right + 1) / 2;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int) left;
    }
}
