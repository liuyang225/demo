package com.example.demo;

import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;

public class Test {

    private String a;

    public static void main(String[] args) throws Exception {
        String name = Test.class.getName();
        Test t = new Test();
        String name1 = t.getClass().getName();
        Field a = t.getClass().getDeclaredField("a");
        System.out.println(a.getName());
        String s = ObjectUtils.identityToString(Test.class);
        System.out.println(name);
        System.out.println(name1);
        System.out.println(s);
        System.out.println(a);
    }
}
