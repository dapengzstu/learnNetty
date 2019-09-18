package interview.algorithm;

public class _27RemoveElement {
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0){
            return 0;
        }
        int index = nums.length;
        for (int i = 0 ;i < index ; i++){
            if(nums[i] == val){
                for (int j = i; j < index - 1; j++){
                    nums[j] = nums[j + 1];
                }
                index --;
                i --;
            }
        }
        return index;
    }
    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int[] nums1 = {2,2};
        int[] nums2 = {2};
        int[] nums3 = {};
        int[] nums4 = {3,3};
        int[] nums5 = {3};


        for (int c :
                nums) {
            System.out.print(c + ",");
        }
    }
}
