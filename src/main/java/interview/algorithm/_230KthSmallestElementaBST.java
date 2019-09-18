package interview.algorithm;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class _230KthSmallestElementaBST {
    public void inOrder(TreeNode root,List<Integer> list){
        if (root != null){
            inOrder(root.left,list);
            list.add(root.val);
            inOrder(root.right,list);
        }
    }
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<Integer>();
        inOrder(root,list);
        return list.get( k + 1);
    }
    public static void main(String[] args) {

    }
}
