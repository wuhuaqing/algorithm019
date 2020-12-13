#### 动态规划

英文全称Dynamic Programming ，简称DP。其实叫动态递推更能显示这种算法思想的思路。

DP处理问题的关键点在于

1.寻找最优子结构

2.储存中间状态值

3.寻找归纳出DP方程式: 寻找问题的重复性，归纳方程式

#### 算法题解

##### [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/)

1.寻找最优子结构:Math.min()中有体现

2.存储中间状态值:dp数组中有体现 

3.归纳DP方程式：dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);

```java
/**
 * DP动态规划（动态递推）题解
 * https://leetcode-cn.com/problems/triangle/solution/san-chong-jie-fa-duo-tu-xiang-jie-120-san-jiao-xin/
 * @param triangle
 * @return
 */
public int minimumTotal(List<List<Integer>> triangle) {
    int row = triangle.size();
    int col = triangle.get(row - 1).size();
    int[][] dp = new int[row + 1][col + 1];
    for (int i = row-1; i >= 0; i--) {
         //当前这行的列数
        for (int j = triangle.get(i).size()-1; j >= 0; j--) {
            dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
        }
    }
    return dp[0][0];
}
```

##### [62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)

 耗时1ms,9%的时间复杂度优先

```java
public int uniquePaths00(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
        //第一列 都只有一种走法
        dp[i][0] = 1;
        // System.out.println("[" + i + ",0] = " + " " + dp[i][0]);
        for (int j = 0; j < n; j++) {
            //第一行都只有一种走法
            if (i == 0 || j == 0) {
                dp[0][j] = 1;
                // System.out.println("[0," + j + "] = " + " " + dp[i][0]);
                continue;
            }
            // i j 这个点的走法是前一个 和 上一个 点 走法之和
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            // System.out.println("[" + i + "," + j + "] = " + " " + dp[i][j]);
        }
    }
    return dp[m - 1][n - 1];
}
```

将第一行第一列的数据单独赋值后，能到0ms，同时100%的时间复杂度优先

```java
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    //将第一行第一列的值单独赋值
    for (int i = 0; i < m; i++) {
        //第一列 都只有一种走法
        dp[i][0] = 1;
    }
    for (int j = 0; j < n; j++) {
        //第一行都只有一种走法
        dp[0][j] = 1;
    }
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            // i j 这个点的走法是前一个 和 上一个 点 走法之和
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }
    return dp[m - 1][n - 1];
}
```

##### [63. 不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/)

注意第一行第一列for循环的写法

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
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
```

写法直观一些

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
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
```

##### [1143. 最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)

''

```java
/**
 * 动态规划题解 https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-tu-wen-jie-xi-by-yijiaoqian/
 * 将两字符串构建成二位表格进行 状态的递推
 *
 * @param text1
 * @param text2
 * @return
 */
public int longestCommonSubsequence(String text1, String text2) {
    if (text1 == null || text2 == null) {
        return 0;
    }
    int m = text1.length();
    int n = text2.length();
    int[][] dp = new int[m + 1][n + 1];

    char[] charsT1 = text1.toCharArray();
    char[] charsT2 = text2.toCharArray();
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            char t1 = charsT1[i];
            char t2 = charsT2[j];
            //当前两字符，则取左上角的值加1 ：代表目前位置的字串个数加1（又找到一个相同的字符）
            if (t1 == t2) {
                dp[i + 1][j + 1] = dp[i][j] + 1;
            } else {
                //两字符不相等，则要保持当前字串之前遍历得到的相同字符数的记录 （中间状态得保持）
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
    }

    return dp[m][n];
}
```