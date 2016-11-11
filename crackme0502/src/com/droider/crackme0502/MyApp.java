package com.droider.crackme0502;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyApp extends Application{

    @Override
    public void onCreate() {
        try {
            Class<?> c = Class.forName("com.droider.crackme0502.MainActivity");//
            Object o = c.newInstance();     //��ʼ���ⲿ��
            Class[] cs = c.getClasses();    //��ȡ�ڲ���
            Class[] args = new Class[2];
            args[0] = com.droider.crackme0502.MainActivity.class;   //��һ������Ϊ��������
            args[1] = String.class;     //�ڶ�������ΪString����
            Constructor co = cs[0].getConstructor(args);        //��ȡ���캯��
            Object classSNChecker = co.newInstance(o, "11111");      //��ʼ��SNChecker
            Method method = cs[0].getDeclaredMethod("isRegistered");    //��ȡisRegistered()����
            boolean bMod = (Boolean) method.invoke(classSNChecker);     //����isRegistered()����
            if (bMod) {         //���SN�Ǵ���ģ��������Ϊ��˵�������޸Ĺ�
                System.out.println("�����޸ģ�");
                android.os.Process.killProcess(android.os.Process.myPid()); //��������
            }
        } catch (Exception e) {
            e.printStackTrace();
            android.os.Process.killProcess(android.os.Process.myPid()); //��������
        }
        
        super.onCreate();
    }
    
}
