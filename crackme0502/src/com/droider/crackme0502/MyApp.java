package com.droider.crackme0502;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyApp extends Application{

    @Override
    public void onCreate() {
        try {
            Class<?> c = Class.forName("com.droider.crackme0502.MainActivity");//
            Object o = c.newInstance();     //初始化外部类
            Class[] cs = c.getClasses();    //获取内部类
            Class[] args = new Class[2];
            args[0] = com.droider.crackme0502.MainActivity.class;   //第一个参数为父类引用
            args[1] = String.class;     //第二个参数为String类型
            Constructor co = cs[0].getConstructor(args);        //获取构造函数
            Object classSNChecker = co.newInstance(o, "11111");      //初始化SNChecker
            Method method = cs[0].getDeclaredMethod("isRegistered");    //获取isRegistered()方法
            boolean bMod = (Boolean) method.invoke(classSNChecker);     //调用isRegistered()方法
            if (bMod) {         //这个SN是错误的，如果返回为真说明程序被修改过
                System.out.println("程序被修改！");
                android.os.Process.killProcess(android.os.Process.myPid()); //结束程序
            }
        } catch (Exception e) {
            e.printStackTrace();
            android.os.Process.killProcess(android.os.Process.myPid()); //结束程序
        }
        
        super.onCreate();
    }
    
}
