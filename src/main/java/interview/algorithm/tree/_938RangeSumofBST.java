package interview.algorithm.tree;

/**
 * @auther dapeng
 * @date 19-9-14 上午8:59
 */
public class _938RangeSumofBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        int sum = 0;
        sum = inOrder(root, L, R, sum);
        return  sum;
    }
    public int inOrder(TreeNode root,int L,int R,int sum){
        if(root.val <= R && root.val >= L){
            sum += root.val;
        }
        if(root.left != null){
            sum = inOrder(root.left,L,R,sum);
        }
        if(root.right != null){
            sum = inOrder(root.right, L, R, sum);
        }
        return sum;
    }
    public static void main(String[] args) {

    }
}
