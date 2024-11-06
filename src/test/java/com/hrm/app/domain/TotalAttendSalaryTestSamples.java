package com.hrm.app.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TotalAttendSalaryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static TotalAttendSalary getTotalAttendSalarySample1() {
        return new TotalAttendSalary().id(1L).totalAttend(1).totalSalary(1);
    }

    public static TotalAttendSalary getTotalAttendSalarySample2() {
        return new TotalAttendSalary().id(2L).totalAttend(2).totalSalary(2);
    }

    public static TotalAttendSalary getTotalAttendSalaryRandomSampleGenerator() {
        return new TotalAttendSalary()
            .id(longCount.incrementAndGet())
            .totalAttend(intCount.incrementAndGet())
            .totalSalary(intCount.incrementAndGet());
    }
}
