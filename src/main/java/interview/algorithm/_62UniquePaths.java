package interview.algorithm;

public class _62UniquePaths {


    public int uniquePaths(int n, int m) {
        if (m==1 || n==1){
            return 1;
        }
        int[][] array = new int[m][n];
        for (int i = 0 ;i < m  ; i ++){
            array[i][n-1] = 1;
        }
        for (int j = 0 ; j < n  ;  j ++){
            array[m-1][j] = 1;
        }
        for (int i = m - 2 ; i >= 0 ; i--){
            for (int j = n - 2; j >= 0 ; j--){
                array[i][j] = array[i + 1][j] + array[i][j+1];
            }
        }
        return array[0][0];
    }


    public static void main(String[] args) {
        int i = new _62UniquePaths().uniquePaths(7, 3);
        System.out.println(i);
    }
}
