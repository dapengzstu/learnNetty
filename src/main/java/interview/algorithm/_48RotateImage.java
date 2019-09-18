package interview.algorithm;

public class _48RotateImage {
    public void rotate(int[][] matrix) {
        int i = 0;
        int j = 0;

        int limit = matrix.length-1;
        int size = matrix.length;

        while (true) {
            int cycle = 0;
            while (j < size - 1) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][limit];
                matrix[j][limit] = tmp;

                tmp = matrix[i][j];
                matrix[i][j] = matrix[limit][limit-cycle];
                matrix[limit][limit-cycle] = tmp;

                tmp = matrix[i][j];
                matrix[i][j] = matrix[limit-cycle][i];
                matrix[limit-cycle][i] = tmp;

                j++;
                cycle++;
            }

            size -= 1;

            if (size <= 0) {
                break;
            } else {
                i++;
                j = i;
                limit = limit - 1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = { {1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        new _48RotateImage().rotate(m);
    }
}
