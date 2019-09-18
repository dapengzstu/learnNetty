package interview.model.singleton;

public class Singleton2 {
    //1.    设置私有
    private Singleton2(){}
    //2.    返回一个对象
    public static Singleton2 getInstance(){
        return Sin.INSTANCE.getInstance();
    }

    private enum Sin{
        INSTANCE;

        private Singleton2 singleton2;

        Sin(){
            singleton2 = new Singleton2();
        }

        public Singleton2 getInstance(){
            return singleton2;
        }
    }
}
