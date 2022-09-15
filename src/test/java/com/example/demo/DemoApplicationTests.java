package com.example.demo;

import com.example.demo.rabbitMq.Receiver;
import com.example.demo.rabbitMq.Sender;
import com.example.demo.thread.Async.AsyncTask;
import com.example.demo.thread.AsyncManager.AsyncManager;
import com.example.demo.thread.AsyncManager.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private final static Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Autowired
    private Task task;

    @Test
    public void contextLoads() {
        log.info("start");
        asyncTask.task1();
        log.info("finish");
    }

    @Test
    public void testTask2()  {
        log.info("start");
        Future<String> future = asyncTask.task2();
        while (true) {
            if (future.isDone()) {
                try {
                    log.info("result is " + future.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        log.info("finish");
    }

    @Test
    public void test3() {
        // 异步线程池
        AsyncManager.me().execute(task.sleepingTest());
    }

    @Test
    public void send() {
        sender.sendMessage(1L,100,null);
    }

    @Test
    public void receive() {
        receiver.handle(1L);
    }
}
