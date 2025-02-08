package me.hannsi.lfjg.utils.toolkit;

import me.hannsi.lfjg.debug.debug.DebugLog;
import me.hannsi.lfjg.debug.debug.LogGenerator;

import java.util.HashMap;

/**
 * Utility class for managing a cache of threads.
 */
public class ThreadCache {
    private HashMap<Long, Thread> threadCache;

    /**
     * Constructs a new ThreadCache instance.
     */
    public ThreadCache() {
        threadCache = new HashMap<>();
    }

    /**
     * Creates a cache entry for the specified thread.
     *
     * @param thread the thread to cache
     */
    public void createCache(Thread thread) {
        threadCache.put(thread.threadId(), thread);

        LogGenerator logGenerator = new LogGenerator("ThreadCache Debug Message", "Source: ThreadCache", "Type: Cache Creation", "ID: " + thread.threadId(), "Severity: Info", "Message: Create thread cache: " + thread.getName());

        DebugLog.debug(getClass(), logGenerator.createLog());
    }

    /**
     * Starts all threads in the cache.
     */
    public void run() {
        threadCache.forEach((key, value) -> {
            value.start();
        });
    }

    /**
     * Interrupts all threads in the cache.
     */
    public void stop() {
        threadCache.forEach((key, value) -> {
            value.interrupt();
        });
    }

    /**
     * Starts the threads with the specified IDs.
     *
     * @param threadId the IDs of the threads to start
     */
    public void run(long... threadId) {
        threadCache.forEach((key, value) -> {
            for (long l : threadId) {
                if (key.equals(l)) {
                    value.start();
                }
            }
        });
    }

    /**
     * Interrupts the threads with the specified IDs.
     *
     * @param threadId the IDs of the threads to interrupt
     */
    public void stop(long... threadId) {
        threadCache.forEach((key, value) -> {
            for (long l : threadId) {
                if (key.equals(l)) {
                    value.interrupt();
                }
            }
        });
    }

    /**
     * Interrupts all threads in the cache and clears the cache.
     */
    public void cleanup() {
        threadCache.forEach((key, value) -> {
            if (!value.isInterrupted()) {
                value.interrupt();
            }
        });

        threadCache.clear();
    }

    /**
     * Gets the current thread cache.
     *
     * @return the current thread cache
     */
    public HashMap<Long, Thread> getThreadCache() {
        return threadCache;
    }

    /**
     * Sets the thread cache.
     *
     * @param threadCache the new thread cache
     */
    public void setThreadCache(HashMap<Long, Thread> threadCache) {
        this.threadCache = threadCache;
    }
}