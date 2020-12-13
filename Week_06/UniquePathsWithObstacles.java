package leetcode.week06;

/**
 * 63 不同路径II
 */
public class UniquePathsWithObstacles {
    public static void main(String[] args) {
       // int[][] arr = new int[][] {{0,1},{0,0}};
        //int[][] arr = new int[][] { {1,0,0},{0,0,0}};
        int[][] arr = new int[][] { {0,0,0},{0,0,0}};
        //int[][] arr = new int[][]{{1, 0}};
        int i = new UniquePathsWithObstacles().uniquePathsWithObstacles(arr);
        System.out.println(i);
    }


    //第三遍
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        int row = obstacleGrid.length;
        int col =  obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            if(obstacleGrid[i][0] == 1){
                break;
            }else{
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < col; i++) {
            if(obstacleGrid[0][i] == 1){
                break;
            }else{
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if(obstacleGrid[i][j] == 1){
                    continue;
                }
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[row-1][col-1];
    }

    public int uniquePathsWithObstacles01(int[][] obstacleGrid) {
        // 网格行数
        int row = obstacleGrid.length;
        //网格列数
        int col = obstacleGrid[0].length;
        //状态数组
        int[][] dp = new int[row][col];
        //第一列都是只有一步或者0步(排除障碍物) 第一列某个位置遇到障碍物，这列后面的都不可达
        for (int i = 0; i < row  ; i++) {
            if(obstacleGrid[i][0] == 1){
                break;
            }else{
                dp[i][0]= 1;
            }
        }
        //第一行都只有一步或者0步(排除障碍物)，第一行某个位置遇到障碍物，这行后面的都不可达
        for (int i = 0; i < col  ; i++) {
            if(obstacleGrid[0][i] == 1){
                break;
            }else{
                dp[0][i]= 1;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //障碍物网格，走法都是0步
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                // 当前网格步数是当列上一格 和 当行前一格 步数 之和
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //获取到右底角网格的总路径数
        return dp[row - 1][col - 1];
    }

    public int uniquePathsWithObstacles00(int[][] obstacleGrid) {
        // 网格行数
        int row = obstacleGrid.length;
        //网格列数
        int col = obstacleGrid[0].length;
        //状态数组
        int[][] dp = new int[row][col];
        //  这种循环方式无法排除  第一行和第一列的某个位置设置障碍物后，这行这列之后都不可达的  情况
       /* for (int i = 0; i < row  ; i++) {
            if(obstacleGrid[i][0] == 0){
                dp[i][0]= 1;
            }
        }
        //第一行都只有一步或者0步(排除障碍物)
        for (int i = 0; i < col  ; i++) {
            if(obstacleGrid[0][i] == 0){
                dp[0][i]= 1;
            }
        }*/
        //第一列都是只有一步或者0步(排除障碍物) 第一列某个位置遇到障碍物，这列后面的都不可达
        for (int i = 0; i < row && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        //第一行都只有一步或者0步(排除障碍物)，第一行某个位置遇到障碍物，这行后面的都不可达
        for (int i = 0; i < col && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //障碍物网格，走法都是0步
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                // 当前网格步数是当列上一格 和 当行前一格 步数 之和
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //获取到右底角网格的总路径数
        return dp[row - 1][col - 1];
    }
}
