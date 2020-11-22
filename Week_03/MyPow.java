package leetcode.week03;

/**
 * 50. Pow(x, n)  https://leetcode-cn.com/problems/powx-n/
 */
public class MyPow {

    public double myPow(double x, int n) {
        //指数 n  为负数时，我们可以计算 x^{-n}  再取倒数得到结果
       // return   (n>=0)? pow00(x,n) : 1.0 / pow00(x,-n);
        return   (n>=0)? pow(x,n) : 1.0 / pow(x,-n);
    }

    //第三遍  2020/11/22
    public double pow(double x,int n){
        if(n==0){
            return 1.0d;
        }
        double  y = pow(x,n/2);
        return n%2==0 ?y*y :y*y*x;
    }


    //第一二遍 2020/11/21

    public double pow01(double x, int n){
        //x的0次幂 等于1 作为终止条件
        if(n == 0){
            return 1.00d;
        }
        double y =  pow01(x,n/2);
        return n%2 == 0 ? y*y :y*y*x;
    }

    /**
     * 数学推导
     * https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
     * @param x
     * @param n
     * @return
     */
    public double pow00(double x, int n) {
        if (n == 0) {
            return 1.00d;
        }
        double y = 0.00d;
        //偶数 x ^n = y ^2
        //奇数 x ^n = y ^2 * x
        y = pow00(x, n / 2);
        return n % 2 ==0  ? y * y : y * y * x;
    }


}

