package com.example.mock_stock.executor;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/** 단일 백잉 Executor 위에 "연속 실행"을 제공하는 직렬 실행기 */
final class SerialExecutor implements Executor {
    private final Executor backend;
    private final Deque<Runnable> queue = new ArrayDeque<>();
    private final AtomicBoolean running = new AtomicBoolean(false);

    SerialExecutor(Executor backend) { this.backend = backend; }

    @Override public void execute(Runnable task) {
        synchronized (queue) { queue.addLast(task); }
        scheduleNext();
    }

    private void scheduleNext() {
        if (!running.compareAndSet(false, true)) return;
        backend.execute(() -> {
            try {
                for (;;) {
                    final Runnable next;
                    synchronized (queue) { next = queue.pollFirst(); }
                    if (next == null) break;
                    try { next.run(); } catch (Throwable t) { t.printStackTrace(); }
                }
            } finally {
                running.set(false);
                synchronized (queue) { if (!queue.isEmpty()) scheduleNext(); }
            }
        });
    }
}
