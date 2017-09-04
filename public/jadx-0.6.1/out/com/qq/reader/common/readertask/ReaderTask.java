package com.qq.reader.common.readertask;

import java.io.Serializable;

public abstract class ReaderTask implements Serializable, Comparable<ReaderTask>, Runnable {
    public static final int AUTO_RETRY = 1;
    protected static final int MAX_AUTO_FAILED_TIME = 3;
    public static final int MENUAL_RETRY = 2;
    public static final int NO_RETRY = 0;
    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_IMMEDIATELY = 4;
    public static final int PRIORITY_LOW = 1;
    public static final int PRIORITY_MID = 2;
    public static final int PRIORITY_SINGLE_TASK_QUEUE_OF_ONLINE = 5;
    private static final long serialVersionUID = 1;
    protected boolean isFailedTask = false;
    private transient Thread mCurrentThread;
    private long mDelayTime = 0;
    protected int mFaiedAutoTryedTime = 0;
    private int mPriority = 2;
    private int mTaskFailedType = 0;
    protected String mTaskKey = null;

    public abstract String getTaskName();

    public void setPriority(int i) {
        this.mPriority = i;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public void setDelayTime(long j) {
        this.mDelayTime = j;
    }

    public long getDelayTime() {
        return this.mDelayTime;
    }

    public int compareTo(ReaderTask readerTask) {
        if (getPriority() == readerTask.getPriority()) {
            return 0;
        }
        return getPriority() < readerTask.getPriority() ? 1 : -1;
    }

    public boolean isReachMaxAutoFailedTime() {
        return this.mFaiedAutoTryedTime >= 3;
    }

    public int getFailedType() {
        return this.mTaskFailedType;
    }

    public void setFailedType(int i) {
        if (i == 0 || i == 1 || i == 2) {
            this.mTaskFailedType = i;
        }
    }

    public void setCurrentThread(Thread thread) {
        this.mCurrentThread = thread;
    }

    public Thread getCurrentThread() {
        return this.mCurrentThread;
    }

    public void run() {
        setCurrentThread(Thread.currentThread());
    }

    public String getTaskKey() {
        if (this.mTaskKey == null || this.mTaskKey.length() == 0) {
            this.mTaskKey = getClass().getSimpleName() + generateTaskKey();
        }
        return this.mTaskKey;
    }

    protected String generateTaskKey() {
        return "";
    }

    public boolean isSameofTask(ReaderTask readerTask) {
        return getTaskKey().equals(readerTask.getTaskKey());
    }

    public boolean isSameKindofTask(ReaderTask readerTask) {
        return this.mTaskKey != null && getTaskKey().equals(readerTask.getTaskKey());
    }
}
