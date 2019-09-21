package interview.algorithm.tree;


import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @auther dapeng
 * @date 19-9-14 下午3:07
 */

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};

public class TreeQuestion {
    //559. Maximum Depth of N-ary Tree
    public int maxDepth(Node root){
        return maxDepth(root,1) ;
    }
    public int maxDepth(Node root,int deep){
        int max = 0;
        if (root.children.size() == 0){
            return deep;
        }else{
            for (Node node : root.children){
                int i = maxDepth(node, deep + 1);
                max = Math.max(i,max);
            }
        }
        return max;
    }
    public List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if(root == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        ArrayList<Integer> l = new ArrayList<>();
        l.add(root.val);
        lists.add(l);
        int count = 1;
        while (!queue.isEmpty()){
            int c = 0;
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0 ; i < count ; i ++){
                Node poll = queue.poll();
                for (int j = 0 ; j < poll.children.size() ; j ++){
                    queue.add(poll.children.get(j));
                    list.add(poll.children.get(j).val);
                    c ++;
                }
            }
            lists.add(list);
            count = c;
        }
        return lists;
    }


    public TreeNode increasingBST(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode head = new TreeNode(0);
        inOrder(root,head);
        return head.right;
    }
    public TreeNode inOrder(TreeNode root,TreeNode head){
        if(root == null){
            return head;
        }
        head = inOrder(root.left,head);

        head.right = root;
        head = root;
        head.left = null;

        head = inOrder(root.right,head);

        return head;

    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        List<String> list = new ArrayList<>();
        binaryTreePaths(root,l,list);
        return list;
    }
    public void binaryTreePaths(TreeNode root,List<Integer> l,List<String> list){
        if(root == null){
            StringBuilder s = new StringBuilder();
            for (Integer in : l) {
                if (s.length() == 0) {
                    s.append(in);
                } else{
                    s.append("->" + in);
                }
            }
            l.remove(l.size() - 1);
        }
        l.add(root.val);
        binaryTreePaths(root.left);
        binaryTreePaths(root.right);


    }
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        convertBST(root,sum);
        return root;
    }
    public int convertBST(TreeNode root,int sum){
        if(root == null){
            return sum;
        }
        sum = convertBST(root.right, sum);
        sum += root.val;
        root.val = sum;
        sum = convertBST(root.left,sum);

        return sum;
    }
    TreeNode pre = null;
    int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root){
        if(root == null){
            return 0;
        }
        getMinimumDifference(root.left);
        if(pre != null){
            min = Math.min(min,root.val - pre.val);
        }
        pre = root;
        getMinimumDifference(root.right);
        return min;
    }
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        return helper(root, map, sum, 0);
    }

    int helper(TreeNode root, HashMap<Integer, Integer> map, int sum, int pathSum){
        int res = 0;
        if(root == null) return 0;

        pathSum += root.val;
        res += map.getOrDefault(pathSum - sum, 0);
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        res = helper(root.left, map, sum, pathSum) + helper(root.right, map, sum, pathSum) + res;
        map.put(pathSum, map.get(pathSum) - 1);
        return res;
    }

    Integer p ;
    int max = Integer.MIN_VALUE;
    int count = 0;
    public void inorder(TreeNode root,Set set){
        if(root == null){
            return ;
        }
        inorder(root.left,set);
        if(p == null){
            p = root.val;
            count = 1;
            max = 1;
            set.add(p);
        }else{
            if(p == root.val){
                count ++;
            }else{
                p = root.val;
                count = 1;
            }
            if(count > max ) {
                max = count;
                set.clear();
                set.add(p);
            }else if(count == max){
                set.add(p);
            }

        }
        inorder(root.right,set);
    }
    public int[] findMode(TreeNode root) {
        Set<Integer> set = new HashSet<Integer>();
        inorder(root,set);
        int[] nums = new int[set.size()];
        int i = 0;
        for(Integer n:set){
            nums[i] = n;
            i++;
        }
        return nums;
    }
    public String preOrder(TreeNode t){
        if(t == null){
            return ")";
        }
        StringBuilder str = new StringBuilder();
        str.append(t.val);

        if(t.left == null && t.right == null){
            return str.toString() ;
        }
        if(t.left != null){
            str.append("(" + preOrder(t.left) + ")");
        }
        if(t.right != null){
            str.append("(" + preOrder(t.right) + ")");
        }

        return str.toString();
    }
    public String tree2str(TreeNode t) {
        if(t == null){
            return "";
        }
        return preOrder(t);
    }
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> list = new LinkedList<Integer>();

    public List<List<Integer>> pSum(TreeNode root, int sum) {
        if(root == null){
            return null;
        }
        sum -= root.val;
        list.add(list.size(),root.val);
        if(root.left == null && root.right == null){
            if(sum == 0){
                result.add(new ArrayList<Integer>(list));
            }
            list.remove(list.size() - 1);
            return null;
        }
        pSum(root.left,sum);
        pSum(root.right,sum);
        return result;
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> lists = new LinkedList<List<Integer>>();
        Queue<TreeNode> parent = new LinkedList<TreeNode>();
        List<Integer> l = new LinkedList<Integer>();
        if(root == null){
            return lists;
        }
        TreeNode node = root;
        int levelCount = 0;
        int count = 0;
        while(node != null || !l.isEmpty()){
            l.clear();
            count = 0;
            if(parent.isEmpty() && lists.isEmpty()){
                parent.add(root);
                levelCount = 1;
            }else{

                for(int i = 0 ;i < levelCount ; i ++){
                    node = parent.poll();
                    if(node.left != null){
                        parent.add(node.left);
                        count++;
                    }
                    if(node.right != null){
                        parent.add(node.right);
                        count ++;
                    }
                    l.add(node.val);
                }
                node = null;
                levelCount = count;
                if(!l.isEmpty()){
                    lists.addFirst(new ArrayList<>(l));
                }

            }
        }
        return lists;
    }
    public int deepthOfTree(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(deepthOfTree(root.left),deepthOfTree(root.right)) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        return  Math.abs(deepthOfTree(root.left) - deepthOfTree(root.right)) <= 1 ? true : false;
    }

    List<Integer> minArray ;
    public int updateMinArray(int val,int k){
        int left = 0;
        int right = k;
        while(left < right){
            int mid = (left + right)/2;
            if(minArray.get(mid) > val){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;

    }
    public void  inOrder(TreeNode root,int k){
        if(root == null){
            return ;
        }
        if(minArray.size() < k ){
            minArray.add(root.val);
        }else if(root.val < minArray.get(k)){
            updateMinArray(root.val,k);
        }else{
            inOrder(root.left,k);
            inOrder(root.right,k);
        }

    }
    public int kthSmallest(TreeNode root, int k) {
        minArray = new ArrayList<Integer>(k);
        inOrder(root,k);
        return minArray.get(k-1);
    }
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3  = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t3.right = t4;
        boolean i = new TreeQuestion().isBalanced(t1);
        System.out.println(i);
//        List<List<Integer>> s = new TreeQuestion().levelOrderBottom(t1);
//        System.out.println(s);
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.set(0,list.get(0) + 1);
//        System.out.println(list.get(0));


    }
    public void test(List<List<Integer>> lists){
        List<Integer> list = new LinkedList<>();
        list.add(2);
        lists.add(list);

    }

}
