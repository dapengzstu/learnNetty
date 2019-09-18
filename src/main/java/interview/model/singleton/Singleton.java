package interview.model.singleton;

/**
 *  最佳实现1
 * */
public class Singleton {
    //1.    构造函数私有化
    private Singleton(){}
    //2.    创造静态内部类，静态内部类和内部类都是在使用的时候才加载
    private static class Instance{
        private static Singleton singleton = new Singleton();
    }
    //3.    get（）
    public Singleton getInstance(){
        return Instance.singleton;
    }

}

