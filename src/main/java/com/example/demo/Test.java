package com.example.demo;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import static io.lettuce.core.protocol.CommandKeyword.BY;

public class Test {

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        int i = random.nextInt(100);
        System.out.println(i);
    }



}
