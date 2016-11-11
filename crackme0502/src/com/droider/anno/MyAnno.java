package com.droider.anno;


@MyAnnoClass()
public class MyAnno {
    @MyAnnoField(info = "Hello my friend")
    public String sayWhat;
    
    @MyAnnoMethod(name="droider", age=26)
    public void outputInfo() {
        System.out.println("My AnnoMethod");
    }    
}
