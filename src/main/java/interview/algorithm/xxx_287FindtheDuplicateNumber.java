package interview.algorithm;

import java.util.HashSet;
import java.util.Set;

public class xxx_287FindtheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0 ; i < nums.length ; i++){
            if(!set.add(nums[i])){
                return nums[i];
            }
        }
        return 0;
    }
    //循环成环
    public int find(int[] nums){
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast)
        {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while (fast != slow)
        {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
    public static void main(String[] args) {
        int[] nums = {3,1,3,4,2};
        int duplicate = new xxx_287FindtheDuplicateNumber().find(nums);
        System.out.println(duplicate);
    }
}
