package interview.algorithm.greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther dapeng
 * @date 19-9-18 上午10:11
 */
public class Greed {
    public int minDeletionSize(String[] A) {
        int count = 0;
        for(int i = 0 ;i < A[0].length(); i++){
            for(int j = 1 ; j < A.length ; j ++){
                if(A[i].charAt(j) < A[i].charAt(j - 1)){
                    count ++;
                }
            }
        }
        return count;
    }
    //字符串大小写
    public List<String> letterCasePermutation(String s){
        List<String> list = new ArrayList<>();
        dfs(s.toCharArray(),0,list);
        return list;
    }
    public void dfs(char[] chars,int count ,List<String> list){
        if(chars.length == count){
            list.add(String.valueOf(chars));
            return;
        }
        dfs(chars,count+1,list);
        if(chars[count] < '0' || chars[count] > '9'){
            chars[count] ^= (1<<5);
            dfs(chars,count+1,list);
        }
    }
    public void mydfs(int num,int pos,int[] array){
        if(num == 0){
            for (int a :
                    array) {
                System.out.print(a);
            }
            System.out.println();
        }
        if(num != 0){
            for (int i = pos; i < 5 ; i ++){
                array[i] ++;
                mydfs(num-1,i+1,array);
                array[i] --;
            }

        }
    }

    //手表
    public void  dfs(int num,int pos,List<String> list,int[] times){
        if(num == 0){
            int hours = 0;
            for(int i = 0 ; i <4 ; i ++){
                hours += times[i] * (1 << i);
            }
            int minute = 0;
            for(int i = 4 ; i < 10; i ++){
                minute += times[i] * (1 << (i - 4));
            }
            if(hours <= 11 && minute <= 59){

                list.add(String.valueOf(hours) + ":" + (minute > 9 ? String.valueOf(minute) : "0" + String.valueOf(minute)));
            }
            return;
        }
        for(int i = pos ; i < 10 ; i ++ ){
            times[i] ++;
            dfs(num - 1,i + 1,list,times);
            times[i]--;
        }
    }
    public List<String> readBinaryWatch(int num) {
        int[] times = new int[10];
        List<String> list = new ArrayList<String>();
        dfs(num,0,list,times);
        return list;
    }

    //n个数字取出k个数字的组合
    public void dfs(List<List<Integer>> list,List<Integer> l,int n, int k,int curr){
        if(l.size() == k){
            list.add(new ArrayList(l));
            return;
        }
        for(int i = curr ; i < n + 1  ; i ++){
            l.add(i);
            dfs(list,l,n,k,i + 1);
            l.remove(l.size() - 1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        List<Integer> l = new ArrayList<Integer>();
        dfs(lists,l,n,k,1);
        return lists;
    }

    //上一个题目，迭代
    public List<List<Integer>> combine1(int n, int k) {
        // init first combination
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for(int i = 1; i < k + 1; ++i)
            nums.add(i);
        nums.add(n + 1);

        List<List<Integer>> output = new ArrayList<List<Integer>>();
        int j = 0;
        while (j < k) {
            // add current combination
            output.add(new LinkedList(nums.subList(0, k)));
            // increase first nums[j] by one
            // if nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
                nums.set(j, j++ + 1);
            nums.set(j, nums.get(j) + 1);
        }
        return output;


    }
    //上一个题目迭代
    public List<List<Integer>> combine2(int n,int k){
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for (int i = 0 ; i < k ; i ++){
            nums.add(0);
        }
        int i = 0;
        while(i >=0){
            nums.set(i,nums.get(i) + 1);
            if(nums.get(i) > n){
                i --;
            }else if(i == k - 1){
                lists.add(new ArrayList<>(nums));
            }else{
                i++;
                nums.set(i,nums.get(i - 1));
            }
        }
        return lists;
    }


    public static void main(String[] args) {
        String a = "cba";
        String b = "daf";
        String c = "ghi";
        String[] A = {a,b,c};
        int i = new Greed().minDeletionSize(A);
        System.out.println(i);

        //System.out.println(new Greed().letterCasePermutation("ab2d"));
//        String s = "1";
//        char[] chars = s.toCharArray();
//        System.out.println(chars);

        List<String> strings = new Greed().letterCasePermutation("ab1d");
        strings.stream().forEach(System.out::println);
    }

}
