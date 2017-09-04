package com.tencent.tinker.loader;

import android.util.Log;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public final class TinkerParallelDexOptimizer {
    private static final String TAG = "Tinker.ParallelDex";

    public interface ResultCallback {
        void onFailed(File file, File file2, Throwable th);

        void onStart(File file, File file2);

        void onSuccess(File file, File file2, File file3);
    }

    private static class OptimizeWorker implements Runnable {
        private final ResultCallback callback;
        private final File dexFile;
        private final File optimizedDir;
        private final AtomicInteger successCount;
        private final CountDownLatch waitingLauch;

        OptimizeWorker(File file, File file2, AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResultCallback resultCallback) {
            this.dexFile = file;
            this.optimizedDir = file2;
            this.successCount = atomicInteger;
            this.waitingLauch = countDownLatch;
            this.callback = resultCallback;
        }

        public void run() {
            try {
                if (!(SharePatchFileUtil.isLegalFile(this.dexFile) || this.callback == null)) {
                    this.callback.onFailed(this.dexFile, this.optimizedDir, new IOException("dex file " + this.dexFile.getAbsolutePath() + " is not exist!"));
                }
                if (this.callback != null) {
                    this.callback.onStart(this.dexFile, this.optimizedDir);
                }
                String optimizedPathFor = SharePatchFileUtil.optimizedPathFor(this.dexFile, this.optimizedDir);
                DexFile.loadDex(this.dexFile.getAbsolutePath(), optimizedPathFor, 0);
                this.successCount.incrementAndGet();
                if (this.callback != null) {
                    this.callback.onSuccess(this.dexFile, this.optimizedDir, new File(optimizedPathFor));
                }
                this.waitingLauch.countDown();
            } catch (Throwable th) {
                this.waitingLauch.countDown();
            }
        }
    }

    public static synchronized boolean optimizeAll(File[] fileArr, File file, ResultCallback resultCallback) {
        boolean optimizeAllLocked;
        synchronized (TinkerParallelDexOptimizer.class) {
            optimizeAllLocked = optimizeAllLocked(Arrays.asList(fileArr), file, new AtomicInteger(0), resultCallback);
        }
        return optimizeAllLocked;
    }

    public static synchronized boolean optimizeAll(Collection<File> collection, File file, ResultCallback resultCallback) {
        boolean optimizeAllLocked;
        synchronized (TinkerParallelDexOptimizer.class) {
            optimizeAllLocked = optimizeAllLocked(collection, file, new AtomicInteger(0), resultCallback);
        }
        return optimizeAllLocked;
    }

    private static boolean optimizeAllLocked(Collection<File> collection, File file, AtomicInteger atomicInteger, ResultCallback resultCallback) {
        CountDownLatch countDownLatch = new CountDownLatch(collection.size());
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        long nanoTime = System.nanoTime();
        for (File optimizeWorker : collection) {
            newCachedThreadPool.submit(new OptimizeWorker(optimizeWorker, file, atomicInteger, countDownLatch, resultCallback));
        }
        boolean z;
        try {
            countDownLatch.await();
            long nanoTime2 = (System.nanoTime() - nanoTime) / 1000000;
            if (atomicInteger.get() == collection.size()) {
                Log.i(TAG, "All dexes are optimized successfully, cost: " + nanoTime2 + " ms.");
                z = true;
                return z;
            }
            Log.e(TAG, "Dexes optimizing failed, some dexes are not optimized.");
            newCachedThreadPool.shutdown();
            return false;
        } catch (InterruptedException e) {
            z = e;
            Log.w(TAG, "Dex optimizing was interrupted.", z);
            return false;
        } finally {
            newCachedThreadPool.shutdown();
        }
    }
}
