package leetcode.week04;

/**
 * 122. 买卖股票的最佳时机 II https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class MaxProfit {

    public static void main(String[] args) {
        //int []  nums =  new int[]{7,1,5,3,6,4};
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int i = new MaxProfit().maxProfit(nums);
        System.out.println(i);
    }

    /**
     * 贪心算法 只要后一天的价格高于前一天，就卖出，然后再买入
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int maxCount = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i > 0 && prices[i] < prices[i - 1]) {
                continue;
            }

            if (i > 0) {
                maxCount = maxCount + (prices[i] - prices[i - 1]);
            }

        }
        return maxCount;
    }
}
