package com.blue.cat.handler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    private static ThreadFactory baseThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("promptRecord-pool-%d").build();
    public static ThreadPoolExecutor promptRecordPool = new ThreadPoolExecutor(1, 2, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100),baseThreadFactory);

}
