package com.lms.common.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple CNNNN id generator. Not production-ready — for demo/testing.
 */
public class IdGenerator {
    private static final AtomicInteger COUNTER = new AtomicInteger(0000);
    public static synchronized String next(String prefix) {
        int v = COUNTER.incrementAndGet();
        return String.format("%s%04d", prefix.charAt(0), v % 10000);
    }
}
