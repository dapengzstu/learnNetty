package interview.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @auther dapeng
 * @date 19-9-14 上午9:25
 */
public class _226InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            TreeNode treeNode = poll.left;
            poll.left = poll.right;
            poll.right = treeNode;
            if (poll.left != null){
                queue.add(poll.left);
            }
            if(poll.right != null){
                queue.add(poll.right);
            }


        }
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {

        return tran(nums,0,nums.length);
    }
    public TreeNode tran(int[] nums,int left,int right){
        if (left == right){
            return null;
        }
        int mid = (left + right)>>>1;
        TreeNode t = new TreeNode(nums[mid]);
        t.left = tran(nums,left,mid);
        t.right = tran(nums,mid + 1,right);
        return t;
    }

}
