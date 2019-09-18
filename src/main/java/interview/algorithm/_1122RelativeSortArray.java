package interview.algorithm;

public class _1122RelativeSortArray {


    //因为指定了arr2中的数字最大值为1000
    public  int[] re(int[] arr1,int[] arr2){
        int[] count = new int[1001];
        for (int i = 0 ;i < arr1.length ; i++){
            count[arr1[i]] ++;
        }
        int j = 0;
        for (int i = 0 ; i < arr2.length ; i++){
            for (int k = 0 ; k < count[arr2[i]] ;  k ++){
                arr1[j++] = arr2[i];
            }
            count[arr2[i]] = 0;
        }
        for (int i = 0 ; i < count.length ; i ++){
            while (count[i] != 0){
                arr1[j++] = i;
                count[i] --;
            }
        }

        return arr1;
    }
    //自己写的，先交换得到前面的，复杂度O（n^2)
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int j = 0;

        for(int i = 0 ;i < arr2.length; i++){
            int flag = arr2[i];
            for (int k = j ; k < arr1.length ; k++){
                if (arr1[k] == flag){
                    int swapTemp = arr1[j];
                    arr1[j] = arr1[k];
                    arr1[k] = swapTemp;
                    j ++;
                }
            }
        }
        quickSort(arr1,j,arr1.length -1);
        return  arr1;
    }
    public void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }
    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }


    public static void main(String[] args) {
        int[] arr1 = {2,21,43,38,0,42,33,7,24,13,12,27,12,24,5,23,29,48,30,31};
        int[] arr2 = {2,42,38,0,43,21};
        int[] ints = new _1122RelativeSortArray().re(arr1, arr2);
        for (int i :
                ints) {
            System.out.println(i);
        }
    }
}
