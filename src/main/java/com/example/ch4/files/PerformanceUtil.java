package com.example.ch4.files;

public class PerformanceUtil {
    public static void measurePerformance(Runnable task, String taskName) {
        long startTime = System.currentTimeMillis();
        task.run();
        long endTime = System.currentTimeMillis();
        System.out.printf("[%s] 작업 완료: 소요 시간 = %d ms%n", taskName, (endTime - startTime));
    }
}
