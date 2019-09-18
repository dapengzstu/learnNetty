package interview.algorithm.tree;

/**
 * @auther dapeng
 * @date 19-9-14 上午9:05
 */
public class _617MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null){
            return t2;
        }
        inOrder(t1,t2,null,0);
        return t1;
    }
    public void inOrder(TreeNode t1,TreeNode t2,TreeNode parent,int flag){
        TreeNode t1Left = null ,  t2Left= null, t1Right= null,t2Right= null;
        if (t1 != null && t2 != null){
            t1.val = t1.val + t2.val;
            inOrder(t1.left,t2.left,t1,1);
            inOrder(t1.right,t2.right,t1,2);
        } else if(t1 == null && t2 != null ){
            if (parent != null){
                if (flag == 1){
                    parent.left = t2;
                }else if(flag == 2) {
                    parent.right = t2;
                }
            }
            return;
        }else {
            return ;
        }

    }
    //别人答案
    public TreeNode merget(TreeNode t1,TreeNode t2){
        if(t1 == null){
            return t1;
        }
        if(t2 == null){
            return t2;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left,t2.right);
        t1.right = mergeTrees(t1.right,t2.right);
        return t1;
    }
    public static void main(String[] args) {

    }
}
