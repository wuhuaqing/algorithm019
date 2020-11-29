package leetcode.week04;

/**
 * 860. 柠檬水找零 https://leetcode-cn.com/problems/lemonade-change/
 */
public class LemonadeChange {
    public static void main(String[] args) {
       // int [] nums = new int[]{5,5,5,10,20};
        int [] nums = new int[]{5,5,10,10,20};
        boolean b = new LemonadeChange().lemonadeChange(nums);
        System.out.println(b);
    }

    /**
     * 10 5 块作为最优解，贪心算法处理
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int fiveUnitCount = 0,tenUnitCount =0;
        for (int i = 0; i < bills.length; i++) {
            if(bills[i] == 5){
                fiveUnitCount ++;
            }else if(bills[i] == 10){
                if(fiveUnitCount==0){
                    return false;
                }
                fiveUnitCount--;
                tenUnitCount++;
            } else{
                if(fiveUnitCount>0 && tenUnitCount>0){
                    fiveUnitCount--;
                    tenUnitCount--;
                }else if (fiveUnitCount>=3){
                    fiveUnitCount -= 3;
                }else{
                    return false;
                }

            }
        }
        return true;


    }
}
