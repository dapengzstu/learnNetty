package interview.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflect {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {



        Class classType = Class.forName("interview.reflect.Customer");
//        Constructor constructor1 = classType.getConstructor();
//        Object o = constructor1.newInstance();

        //获取构造方法
        Constructor constructor = classType.getConstructor(String.class,int.class);
        Object zhuopeng = constructor.newInstance("zhuopeng", 22);
        //获取属性
        Field[] declaredFields = classType.getDeclaredFields();

        for(Field f :declaredFields){
            String name = f.getName();
            //获取每个属性的get方法名字
            String getMethodName = "get" + name.substring(0,1).toUpperCase() + name.substring(1);
            //set
            String setMethodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
            //根据两个名字，获得两个方法
            Method getMethod = classType.getMethod(getMethodName);
            Method setMethod = classType.getMethod(setMethodName,f.getType());

            if("setId".equals(setMethodName)){
                setMethod.invoke(zhuopeng,(long)123123);

            }
            //通过invoke方法，调用某个对象的getMethod方法，返回结果
            Object invoke = getMethod.invoke(zhuopeng);
            System.out.println(getMethodName + "  "  + invoke);

        }

        //直接获取方法名字
        Method[] methods = classType.getDeclaredMethods();
        for(Method m:methods){
            System.out.println(m.getReturnType() + "  " + m.getName() + "( " + Arrays.toString(m.getParameterTypes()) + " )");
        }

    }
}
