package sparseArray;

import java.sql.SQLOutput;

/**
 * @Author hjb
 * @Date 30/6/2021 下午 12:01
 * @Version 1.0
 * @Contact 1754729389@qq.com
 */
public class SparseArray {

    public static void main(String[] args) {

        //创建一个原始的数组
        int[][] fiveChess = new int[11][11];

        //0表示没有棋子，1表示黑棋，2表示蓝棋
        fiveChess[1][2] = 1;
        fiveChess[2][3] = 2;

        //遍历输出原始数组
        for (int[] row : fiveChess){
            for(int data : row){
                System.out.print(data);
                System.out.print("\t");
            }
            System.out.println("\n");
        }

        SparseArray sparseArray = new SparseArray();
        //转为稀疏数组
        int sparseChess[][] = sparseArray.toSparseArray(fiveChess);

        //遍历输出
        for (int[] row : sparseChess){
            for(int data : row){
                System.out.print(data);
                System.out.print("\t");
            }
            System.out.println("");
        }

        //转为二维数组
        int normalArray[][] = sparseArray.toNormalArray(sparseChess);

        //遍历输出
        for (int[] row : normalArray){
            for(int data : row){
                System.out.print(data);
                System.out.print("\t");
            }
            System.out.println("");
        }
        
    }

    /**
     * 将一个二位数组转为稀疏数组
     * @param arr 需转换的二维数组
     * @return 返回一个稀疏数组
     */
    public int[][] toSparseArray(int[][] arr){

        //1.遍历数组，统计非零个数
        int sum = 0;
        for (int[] row : arr){
            for(int data : row){
                if(data != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int sparseArray[][] = new int[sum + 1][3];

        //3.给稀疏数组赋值
        sparseArray[0][0] = arr.length;
        sparseArray[0][1] = arr[0].length;
        sparseArray[0][2] = sum;

        //4.遍历数组，将非零数据存入稀疏数组
        int parseRow = 1;
        for(int row = 0;row < arr.length;row++){
            for(int col = 0;col < arr[0].length;col++){
                if(arr[row][col] != 0){
                    sparseArray[parseRow][0] = row;
                    sparseArray[parseRow][1] = col;
                    sparseArray[parseRow][2] = arr[row][col];
                    parseRow++;
                }
            }
        }

        return sparseArray;

    }

    /**
     * 将一个稀疏数组转为二维数组
     * @param sparseArray 需转换的稀疏数组
     * @return 返回一个二维数组
     */
    public int[][] toNormalArray(int[][] sparseArray){

        //1.创建二维数组
        int normalArray[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        //2.还原数据
        for(int row = 1;row < sparseArray.length;row++){
            normalArray[sparseArray[row][0]][sparseArray[row][1]] = sparseArray[row][2];
        }

        return normalArray;

    }

}
