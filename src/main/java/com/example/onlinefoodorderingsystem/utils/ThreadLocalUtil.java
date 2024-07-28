package com.example.onlinefoodorderingsystem.utils;

import java.text.SimpleDateFormat;

public class ThreadLocalUtil {
    private static final  ThreadLocal   THREAD_LOCAL = new ThreadLocal();
    public static<T> T getThreadLocal() {
        return (T) THREAD_LOCAL.get();
    }

    public static void setThreadLocal(Object value){
        THREAD_LOCAL.set(value);

    }

    public static void removeThreadLocal(){
        THREAD_LOCAL.remove();
    }
}
