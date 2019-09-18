package interview.algorithm;

import java.util.ArrayList;
import java.util.List;

public class _78Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> l = new ArrayList<Integer>();
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        lists.add(l);
        for(int i = 0 ;i < nums.length ; i ++){
            int size = lists.size();
            for (int j = 0 ; j < size; j ++) {
                List<Integer> newList = new ArrayList<Integer>(lists.get(j));
                newList.add(nums[i]);
                lists.add(newList);
            }
            
        }
        return lists;
    }

    //第二种
    public List<List<Integer>> s(int[] nums){
        List<Integer> l = new ArrayList<Integer>();
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        lists.add(l);
        helper(lists,new ArrayList<Integer>(),nums,0);
        return lists;
    }
    public void helper(List<List<Integer>> lists,List<Integer> l,int[] nums,int k){
        for (int i = k ; i < nums.length ; i ++){
            List<Integer> newList = new ArrayList<Integer>(l);
            newList.add(nums[i]);
            lists.add(newList);
            helper(lists,newList,nums,i + 1);
        }
    }
    public static void main(String[] args) {

        int[] nums = {1,2,3};
        List<List<Integer>> subsets = new _78Subsets().s(nums);
        subsets.stream().forEach(l ->{
            l.stream().forEach(System.out::print);
            System.out.println();
        });
    }
}
