package org.greenrobot.greendao.async;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.database.Database;

class AsyncOperationExecutor implements Callback, Runnable {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private int countOperationsCompleted;
    private int countOperationsEnqueued;
    private volatile boolean executorRunning;
    private Handler handlerMainThread;
    private int lastSequenceNumber;
    private volatile AsyncOperationListener listener;
    private volatile AsyncOperationListener listenerMainThread;
    private volatile int maxOperationCountToMerge = 50;
    private final BlockingQueue<AsyncOperation> queue = new LinkedBlockingQueue();
    private volatile int waitForMergeMillis = 50;

    AsyncOperationExecutor() {
    }

    public void enqueue(AsyncOperation asyncOperation) {
        synchronized (this) {
            int i = this.lastSequenceNumber + 1;
            this.lastSequenceNumber = i;
            asyncOperation.sequenceNumber = i;
            this.queue.add(asyncOperation);
            this.countOperationsEnqueued++;
            if (!this.executorRunning) {
                this.executorRunning = true;
                executorService.execute(this);
            }
        }
    }

    public int getMaxOperationCountToMerge() {
        return this.maxOperationCountToMerge;
    }

    public void setMaxOperationCountToMerge(int i) {
        this.maxOperationCountToMerge = i;
    }

    public int getWaitForMergeMillis() {
        return this.waitForMergeMillis;
    }

    public void setWaitForMergeMillis(int i) {
        this.waitForMergeMillis = i;
    }

    public AsyncOperationListener getListener() {
        return this.listener;
    }

    public void setListener(AsyncOperationListener asyncOperationListener) {
        this.listener = asyncOperationListener;
    }

    public AsyncOperationListener getListenerMainThread() {
        return this.listenerMainThread;
    }

    public void setListenerMainThread(AsyncOperationListener asyncOperationListener) {
        this.listenerMainThread = asyncOperationListener;
    }

    public synchronized boolean isCompleted() {
        return this.countOperationsEnqueued == this.countOperationsCompleted;
    }

    public synchronized void waitForCompletion() {
        while (!isCompleted()) {
            try {
                wait();
            } catch (Throwable e) {
                throw new DaoException("Interrupted while waiting for all operations to complete", e);
            }
        }
    }

    public synchronized boolean waitForCompletion(int i) {
        if (!isCompleted()) {
            try {
                wait((long) i);
            } catch (Throwable e) {
                throw new DaoException("Interrupted while waiting for all operations to complete", e);
            }
        }
        return isCompleted();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r6 = this;
        r5 = 0;
    L_0x0001:
        r0 = r6.queue;	 Catch:{ InterruptedException -> 0x0042 }
        r2 = 1;
        r1 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x0042 }
        r0 = r0.poll(r2, r1);	 Catch:{ InterruptedException -> 0x0042 }
        r0 = (org.greenrobot.greendao.async.AsyncOperation) r0;	 Catch:{ InterruptedException -> 0x0042 }
        if (r0 != 0) goto L_0x0077;
    L_0x000f:
        monitor-enter(r6);	 Catch:{ InterruptedException -> 0x0042 }
        r0 = r6.queue;	 Catch:{ all -> 0x0065 }
        r0 = r0.poll();	 Catch:{ all -> 0x0065 }
        r0 = (org.greenrobot.greendao.async.AsyncOperation) r0;	 Catch:{ all -> 0x0065 }
        if (r0 != 0) goto L_0x0021;
    L_0x001a:
        r0 = 0;
        r6.executorRunning = r0;	 Catch:{ all -> 0x0065 }
        monitor-exit(r6);	 Catch:{ all -> 0x0065 }
        r6.executorRunning = r5;
    L_0x0020:
        return;
    L_0x0021:
        monitor-exit(r6);	 Catch:{ all -> 0x0065 }
        r1 = r0;
    L_0x0023:
        r0 = r1.isMergeTx();	 Catch:{ InterruptedException -> 0x0042 }
        if (r0 == 0) goto L_0x0073;
    L_0x0029:
        r0 = r6.queue;	 Catch:{ InterruptedException -> 0x0042 }
        r2 = r6.waitForMergeMillis;	 Catch:{ InterruptedException -> 0x0042 }
        r2 = (long) r2;	 Catch:{ InterruptedException -> 0x0042 }
        r4 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x0042 }
        r0 = r0.poll(r2, r4);	 Catch:{ InterruptedException -> 0x0042 }
        r0 = (org.greenrobot.greendao.async.AsyncOperation) r0;	 Catch:{ InterruptedException -> 0x0042 }
        if (r0 == 0) goto L_0x0073;
    L_0x0038:
        r2 = r1.isMergeableWith(r0);	 Catch:{ InterruptedException -> 0x0042 }
        if (r2 == 0) goto L_0x006c;
    L_0x003e:
        r6.mergeTxAndExecute(r1, r0);	 Catch:{ InterruptedException -> 0x0042 }
        goto L_0x0001;
    L_0x0042:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0068 }
        r1.<init>();	 Catch:{ all -> 0x0068 }
        r2 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0068 }
        r2 = r2.getName();	 Catch:{ all -> 0x0068 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0068 }
        r2 = " was interruppted";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0068 }
        r1 = r1.toString();	 Catch:{ all -> 0x0068 }
        org.greenrobot.greendao.DaoLog.w(r1, r0);	 Catch:{ all -> 0x0068 }
        r6.executorRunning = r5;
        goto L_0x0020;
    L_0x0065:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0065 }
        throw r0;	 Catch:{ InterruptedException -> 0x0042 }
    L_0x0068:
        r0 = move-exception;
        r6.executorRunning = r5;
        throw r0;
    L_0x006c:
        r6.executeOperationAndPostCompleted(r1);	 Catch:{ InterruptedException -> 0x0042 }
        r6.executeOperationAndPostCompleted(r0);	 Catch:{ InterruptedException -> 0x0042 }
        goto L_0x0001;
    L_0x0073:
        r6.executeOperationAndPostCompleted(r1);	 Catch:{ InterruptedException -> 0x0042 }
        goto L_0x0001;
    L_0x0077:
        r1 = r0;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.greendao.async.AsyncOperationExecutor.run():void");
    }

    private void mergeTxAndExecute(AsyncOperation asyncOperation, AsyncOperation asyncOperation2) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        arrayList.add(asyncOperation);
        arrayList.add(asyncOperation2);
        Database database = asyncOperation.getDatabase();
        database.beginTransaction();
        int i = 0;
        while (i < arrayList.size()) {
            AsyncOperation asyncOperation3;
            try {
                asyncOperation3 = (AsyncOperation) arrayList.get(i);
                executeOperation(asyncOperation3);
                if (asyncOperation3.isFailed()) {
                    z = false;
                    break;
                }
                if (i == arrayList.size() - 1) {
                    AsyncOperation asyncOperation4 = (AsyncOperation) this.queue.peek();
                    if (i >= this.maxOperationCountToMerge || !asyncOperation3.isMergeableWith(asyncOperation4)) {
                        database.setTransactionSuccessful();
                        z = true;
                        break;
                    }
                    asyncOperation3 = (AsyncOperation) this.queue.remove();
                    if (asyncOperation3 != asyncOperation4) {
                        throw new DaoException("Internal error: peeked op did not match removed op");
                    }
                    arrayList.add(asyncOperation3);
                }
                i++;
            } finally {
                try {
                    database.endTransaction();
                } catch (Throwable e) {
                    arrayList = "Async transaction could not be ended, success so far was: ";
                    DaoLog.i(arrayList + false, e);
                }
            }
        }
        z = false;
        if (z) {
            int size = arrayList.size();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                asyncOperation3 = (AsyncOperation) it.next();
                asyncOperation3.mergedOperationsCount = size;
                handleOperationCompleted(asyncOperation3);
            }
            return;
        }
        DaoLog.i("Reverted merged transaction because one of the operations failed. Executing operations one by one instead...");
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            asyncOperation3 = (AsyncOperation) it2.next();
            asyncOperation3.reset();
            executeOperationAndPostCompleted(asyncOperation3);
        }
    }

    private void handleOperationCompleted(AsyncOperation asyncOperation) {
        asyncOperation.setCompleted();
        AsyncOperationListener asyncOperationListener = this.listener;
        if (asyncOperationListener != null) {
            asyncOperationListener.onAsyncOperationCompleted(asyncOperation);
        }
        if (this.listenerMainThread != null) {
            if (this.handlerMainThread == null) {
                this.handlerMainThread = new Handler(Looper.getMainLooper(), this);
            }
            this.handlerMainThread.sendMessage(this.handlerMainThread.obtainMessage(1, asyncOperation));
        }
        synchronized (this) {
            this.countOperationsCompleted++;
            if (this.countOperationsCompleted == this.countOperationsEnqueued) {
                notifyAll();
            }
        }
    }

    private void executeOperationAndPostCompleted(AsyncOperation asyncOperation) {
        executeOperation(asyncOperation);
        handleOperationCompleted(asyncOperation);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void executeOperation(org.greenrobot.greendao.async.AsyncOperation r4) {
        /*
        r3 = this;
        r0 = java.lang.System.currentTimeMillis();
        r4.timeStarted = r0;
        r0 = org.greenrobot.greendao.async.AsyncOperationExecutor.AnonymousClass1.$SwitchMap$org$greenrobot$greendao$async$AsyncOperation$OperationType;	 Catch:{ Throwable -> 0x002f }
        r1 = r4.type;	 Catch:{ Throwable -> 0x002f }
        r1 = r1.ordinal();	 Catch:{ Throwable -> 0x002f }
        r0 = r0[r1];	 Catch:{ Throwable -> 0x002f }
        switch(r0) {
            case 1: goto L_0x0039;
            case 2: goto L_0x0041;
            case 3: goto L_0x004b;
            case 4: goto L_0x0057;
            case 5: goto L_0x005f;
            case 6: goto L_0x0069;
            case 7: goto L_0x0075;
            case 8: goto L_0x007d;
            case 9: goto L_0x0087;
            case 10: goto L_0x0093;
            case 11: goto L_0x009b;
            case 12: goto L_0x00a5;
            case 13: goto L_0x00b1;
            case 14: goto L_0x00b6;
            case 15: goto L_0x00bb;
            case 16: goto L_0x00cb;
            case 17: goto L_0x00db;
            case 18: goto L_0x00e4;
            case 19: goto L_0x00eb;
            case 20: goto L_0x00f7;
            case 21: goto L_0x0101;
            case 22: goto L_0x010f;
            default: goto L_0x0013;
        };	 Catch:{ Throwable -> 0x002f }
    L_0x0013:
        r0 = new org.greenrobot.greendao.DaoException;	 Catch:{ Throwable -> 0x002f }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x002f }
        r1.<init>();	 Catch:{ Throwable -> 0x002f }
        r2 = "Unsupported operation: ";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x002f }
        r2 = r4.type;	 Catch:{ Throwable -> 0x002f }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x002f }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x002f }
        r0.<init>(r1);	 Catch:{ Throwable -> 0x002f }
        throw r0;	 Catch:{ Throwable -> 0x002f }
    L_0x002f:
        r0 = move-exception;
        r4.throwable = r0;
    L_0x0032:
        r0 = java.lang.System.currentTimeMillis();
        r4.timeCompleted = r0;
        return;
    L_0x0039:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r1 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0.delete(r1);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x0041:
        r1 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Iterable) r0;	 Catch:{ Throwable -> 0x002f }
        r1.deleteInTx(r0);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x004b:
        r1 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Object[]) r0;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Object[]) r0;	 Catch:{ Throwable -> 0x002f }
        r1.deleteInTx(r0);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x0057:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r1 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0.insert(r1);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x005f:
        r1 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Iterable) r0;	 Catch:{ Throwable -> 0x002f }
        r1.insertInTx(r0);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x0069:
        r1 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Object[]) r0;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Object[]) r0;	 Catch:{ Throwable -> 0x002f }
        r1.insertInTx(r0);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x0075:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r1 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0.insertOrReplace(r1);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x007d:
        r1 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Iterable) r0;	 Catch:{ Throwable -> 0x002f }
        r1.insertOrReplaceInTx(r0);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x0087:
        r1 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Object[]) r0;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Object[]) r0;	 Catch:{ Throwable -> 0x002f }
        r1.insertOrReplaceInTx(r0);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x0093:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r1 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0.update(r1);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x009b:
        r1 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Iterable) r0;	 Catch:{ Throwable -> 0x002f }
        r1.updateInTx(r0);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x00a5:
        r1 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Object[]) r0;	 Catch:{ Throwable -> 0x002f }
        r0 = (java.lang.Object[]) r0;	 Catch:{ Throwable -> 0x002f }
        r1.updateInTx(r0);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x00b1:
        r3.executeTransactionRunnable(r4);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x00b6:
        r3.executeTransactionCallable(r4);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x00bb:
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (org.greenrobot.greendao.query.Query) r0;	 Catch:{ Throwable -> 0x002f }
        r0 = r0.forCurrentThread();	 Catch:{ Throwable -> 0x002f }
        r0 = r0.list();	 Catch:{ Throwable -> 0x002f }
        r4.result = r0;	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x00cb:
        r0 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = (org.greenrobot.greendao.query.Query) r0;	 Catch:{ Throwable -> 0x002f }
        r0 = r0.forCurrentThread();	 Catch:{ Throwable -> 0x002f }
        r0 = r0.unique();	 Catch:{ Throwable -> 0x002f }
        r4.result = r0;	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x00db:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r1 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0.deleteByKey(r1);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x00e4:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0.deleteAll();	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x00eb:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r1 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0 = r0.load(r1);	 Catch:{ Throwable -> 0x002f }
        r4.result = r0;	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x00f7:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r0.loadAll();	 Catch:{ Throwable -> 0x002f }
        r4.result = r0;	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x0101:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r0 = r0.count();	 Catch:{ Throwable -> 0x002f }
        r0 = java.lang.Long.valueOf(r0);	 Catch:{ Throwable -> 0x002f }
        r4.result = r0;	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
    L_0x010f:
        r0 = r4.dao;	 Catch:{ Throwable -> 0x002f }
        r1 = r4.parameter;	 Catch:{ Throwable -> 0x002f }
        r0.refresh(r1);	 Catch:{ Throwable -> 0x002f }
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.greendao.async.AsyncOperationExecutor.executeOperation(org.greenrobot.greendao.async.AsyncOperation):void");
    }

    private void executeTransactionRunnable(AsyncOperation asyncOperation) {
        Database database = asyncOperation.getDatabase();
        database.beginTransaction();
        try {
            ((Runnable) asyncOperation.parameter).run();
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    private void executeTransactionCallable(AsyncOperation asyncOperation) throws Exception {
        Database database = asyncOperation.getDatabase();
        database.beginTransaction();
        try {
            asyncOperation.result = ((Callable) asyncOperation.parameter).call();
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    public boolean handleMessage(Message message) {
        AsyncOperationListener asyncOperationListener = this.listenerMainThread;
        if (asyncOperationListener != null) {
            asyncOperationListener.onAsyncOperationCompleted((AsyncOperation) message.obj);
        }
        return false;
    }
}
