package com.itheima.reggie.utils;

public class BaseContextUtil {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void set(Long id) {
        threadLocal.set(id);
    }

    public static Long get() {
        return threadLocal.get();
    }
}
