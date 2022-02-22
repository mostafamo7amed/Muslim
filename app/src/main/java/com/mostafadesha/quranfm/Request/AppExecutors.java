package com.mostafadesha.quranfm.Request;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {
    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if(instance == null)
            instance = new AppExecutors();
        return instance;
    }

    private final ScheduledExecutorService mNetwork = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService NetworkIO() {

        return mNetwork;
    }
}
