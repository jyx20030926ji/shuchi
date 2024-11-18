package com.testvue.testvue.basecont;







public class BaseCont {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();


    public static void set(Integer userId) {
        threadLocal.set(userId);
    }

    public static Integer get() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }

    // 提供一个更安全的设置方法，带有类型检查和异常处理
    public static void setUserIdSafely(Object userIdObj) {
        try {

             Integer userId = (Integer) userIdObj;
             set(userId);
        } catch (ClassCastException e) {
            // 处理类型转换错误，例如记录日志或抛出更具体的异常
            System.err.println("Failed to set userId: " + userIdObj + " (not a Long)");
            // 可以选择抛出运行时异常或进行其他错误处理
            // throw new IllegalArgumentException("userId must be of type Long", e);
        }
    }
}




