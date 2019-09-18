package interview.algorithm;

public class _35_SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {
        if(nums.length == 0 || nums == null){
            return 0;
        }
        int left  = 0;
        int right = nums.length -1;

        if(nums[left] >= target){
            return left;
        }else if (nums[right] == target){
            return right;
        }else if(nums[right] < target){
            return right + 1;
        }
        while (true){
            int mid = (left + right)/2;

            if (nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                right = mid;
            }else if (nums[mid] < target){
                left = mid;
            }
            if (right == left + 1){
                return right;
            }
        }

    }

    //别人的通过递归的方法
    public static int search(int[] nums, int target){

        if(nums.length == 0 || nums == null){
            return 0;
        }
        if(nums[0] >= target){
            return 0;
        }else if (nums[nums.length - 1] == target){
            return nums.length - 1;
        }else if(nums[nums.length - 1] < target){
            return nums.length ;
        }
        return bSearch(nums,target,0,nums.length-1);
    }
    public static int bSearch(int[] nums,int target,int left ,int right){

        if (left + 1 == right){
            return left + 1;
        }

        int mid = (left + right)/2;
        if(nums[mid] == target){
            return mid;
        }else if(nums[mid] < target){
            return bSearch(nums,target,mid,right);
        }else{
            return bSearch(nums,target,left,mid);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int[] target = {5,2,7,0};
        int i = -1;
        for (int j = 0 ; j < target.length ;j ++){
            i = searchInsert(nums, target[j]);
            System.out.println(i);
        }


    }
}
