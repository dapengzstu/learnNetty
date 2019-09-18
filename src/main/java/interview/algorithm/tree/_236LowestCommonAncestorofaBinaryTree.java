package interview.algorithm.tree;

/**
 * @auther dapeng
 * @date 19-9-13 上午11:30
 */
public class _236LowestCommonAncestorofaBinaryTree {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode target = null;
        inOrder(root,p,q,0,target);
        return target;
    }

    private int inOrder(TreeNode root,TreeNode p, TreeNode q,int count,TreeNode target) {

        if (root != null){
            if (root == p){
                count++;
            }else if (root == q){
                count ++;
            }
            int sub = count;
            int i1 = inOrder(root.left, p, q, sub,target);

            if (i1 == 2 || i1 == -1){
                target = root;
                return -1;
            }
            int i2 = inOrder(root.right,p,q,sub,target);
            if (i1 == 2 || i1 == -1){
                target = root;
                return -1;
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
