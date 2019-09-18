package interview.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJDKProxy {
    static class SubjectImpl implements Subject{

        @Override
        public void sayHello() {
            System.out.println("Hello");
        }

        @Override
        public void sayBye() {
            System.out.println("Bye");
        }
    }
    static class ProxyInvocationHandler implements InvocationHandler{
        //要代理的类
        private Subject target;
        public ProxyInvocationHandler(Subject target){
            this.target = target;
        }
        //在调用target的方法之前，都要输出一句proxy before这样子就实现了代理
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("proxy before");
            Object invoke = method.invoke(target, args);
            return invoke;
        }
    }
    public static void main(String[] args) {
        //要代理的目标
        Subject s = new SubjectImpl();
        //传入类加载器，接口，代理的具体方式，返回一个同接口的代理类
        Subject o = (Subject) Proxy.newProxyInstance(s.getClass().getClassLoader(), s.getClass().getInterfaces(), new ProxyInvocationHandler(s));
        o.sayBye();
        o.sayHello();

    }
}
