package com.example.mock_stock.executor;

import java.util.concurrent.*;
import java.util.concurrent.ConcurrentHashMap;

/** 심볼(키)별로 "직렬 실행기"를 동적으로 제공 */
public final class PerKeySerialExecutors {
    private final Executor backend; // 가상 스레드 실행기
    private final ConcurrentHashMap<String, SerialExecutor> map = new ConcurrentHashMap<>();

    public PerKeySerialExecutors(Executor backend) { this.backend = backend; }

    public Executor forKey(String key) {
        return map.computeIfAbsent(key, k -> new SerialExecutor(backend));
    }
}
