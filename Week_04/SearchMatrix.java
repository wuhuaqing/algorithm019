package leetcode.week04;

/**
 * 74. 搜索二维矩阵
 */
public class SearchMatrix {

    public static void main(String[] args) {
        // int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int[][] matrix = new int[][]{{1, 3}};
        boolean b = new SearchMatrix().searchMatrix(matrix, 3);
        System.out.println(b);
    }

    /**
     * 搜索矩阵 矩阵行中从左到右，列中从上到下 （递增）可以看作是一个从左到右的递增 一维数组
     * 满足二分查找的三个条件：上下有界/单调性（单调递增）/有索引值
     * 需要特殊处理mid值，数学规律： 虚拟一维数组中中间值 行数= 中间值除以列数  列数 = 中间值取余列数
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0) {
            return false;
        }
        //数组行数
        int rows = matrix.length;
        //数组列数
        int columns = matrix[0].length;
        int left = 0, right = rows * columns - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            //虚拟一维数组中中间值 行数= 中间值除以列数  列数 = 中间值取余列数
            int midValue = matrix[mid / columns][mid % columns];
            if (midValue == target) {
                return true;
            } else if (target < midValue) {
                right = mid - 1;
            } else {

                left = mid + 1;
            }
        }

        return false;

    }

    /**
     * 从矩阵右上方开始遍历，一层层一列列判断
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix00(int[][] matrix, int target) {
        // System.out.println(matrix.length);
        // System.out.println(matrix[0].length);

        if (matrix.length == 0) {
            return false;
        }
        int row = 0, column = matrix[0].length - 1;
        //边界条件： 行数 && 列数 不越界
        while ((row >= 0 && row < matrix.length) && (column >= 0 && column < matrix[0].length)) {
            // System.out.println(matrix[row][column]);
            if (matrix[row][column] == target) {
                return true;
            }
            if (matrix[row][column] > target) {
                column--;
            } else if (matrix[row][column] < target) {
                row++;
            }
        }
        return false;
    }
}
