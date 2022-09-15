package com.example.demo.thread.Async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class AsyncTest {

    private final static Logger log = LoggerFactory.getLogger(AsyncTest.class);

    @Autowired
    private static AsyncTask asyncTask;

    public static void main(String[] args) {
        log.info("start");
        asyncTask.task1();
        log.info("finish");

    }

}
