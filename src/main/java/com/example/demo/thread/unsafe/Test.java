package com.example.demo.thread.unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe)theUnsafe.get(null);
        System.out.println(unsafe);

        // 1.获取域的偏移地址
        long idOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("id"));
        long nameOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("name"));
        Student s = new Student();
        // 2.执行cas操作
        unsafe.compareAndSwapInt(s, idOffset, 0, 1);
        unsafe.compareAndSwapObject(s, nameOffset, null, "张三");
        System.out.println(s);
    }
}

@Data
class Student{
     private volatile int id;
     private volatile String name;
}
