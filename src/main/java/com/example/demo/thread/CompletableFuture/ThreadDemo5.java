package com.example.demo.thread.CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * then、handle方法使用示例
 *
 * API太多，有点眼花缭乱，很容易分类。
 * 带run的方法，无入参，无返回值。
 * accept的方法，有入参，无返回值。
 * 带supply的方法，无入参，有返回值。
 * 带apply的方法，有入参，有返回值。
 * 带handle的方法，有入参，有返回值，并且带异常处理。
 * 以Async结尾的方法，都是异步的，否则是同步的。
 * 以Either结尾的方法，只需完成任意一个。
 * 以Both/Combine结尾的方法，必须所有都完成。
 *
 *  获取结果
 *  join 阻塞等待，不会抛异常
 *  get 阻塞等待，会抛异常
 *  complete(T value) 不阻塞，如果任务已完成，返回处理结果。如果没完成，则返回传参value。
 *  completeExceptionally(Throwable ex) 不阻塞，如果任务已完成，返回处理结果。如果没完成，抛异常。
 */
public class ThreadDemo5 {

    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println("随机值：" + i);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("1. 开始淘米");
            return "2. 淘米完成";
        }).thenApplyAsync(result -> {
            System.out.println(result);
            System.out.println("3. 开始煮饭");
            // 生成一个1~10的随机数
            if (i > 5) {
                throw new RuntimeException("4. 电饭煲坏了，煮不了");
            }
            return "4. 煮饭完成";
        }).handleAsync((result, exception) -> {
            if (exception != null) {
                System.out.println(exception.getMessage());
                return "5. 今天没饭吃";
            } else {
                System.out.println(result);
                return "5. 开始吃饭";
            }
        });

        try {
            String result = completableFuture.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
