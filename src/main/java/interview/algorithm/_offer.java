package interview.algorithm;

/**
 * @auther dapeng
 * @date 19-9-13 下午4:52
 */
public class _offer {
    public int minNumberInRotateArray(int [] array) {
        int left = 0,right = array.length-1;
        int mid = left;
        while(array[left] >= array[right]){

            if(left + 1 == right){
                return array[right];
            }
            mid = (left + right)/2;
            if(array[left] < array[mid]){
                left = mid;
            }else if(array[mid] < array[right]){
                right = mid;
            }
        }
        return array[right];
    }

    public static void main(String[] args) {
        int[] array = {1,1,0,1,1,1};
        int i = new _offer().minNumberInRotateArray(array);
        System.out.println(i);
    }
}
