package interview.algorithm.sort;

public class QuckSort {
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
                swap(arr,i,j);
            }
        }
        i ++;
        swap(arr,i,end);
        return i;
    }
    public void swap(int [] nums,int i ,int j){
        int swapTemp = nums[i];
        nums[i] = nums[j];
        nums[j] = swapTemp;
    }

    public static void main(String[] args) {
        int[] nums = {6,1,2,7,9,3,4,5,10,8};
        new QuckSort().quickSort(nums,0,nums.length - 1);
        for (int n :
                nums) {
            System.out.println(n);
        }
    }
}
