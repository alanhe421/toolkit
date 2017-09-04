package com.tencent.mm.performance.util;

public abstract class APerformanceController {
    public long mIntervalTimes = -1;
    protected long mLastMonitorTime = -1;

    public abstract boolean canClosedMonitor();

    public abstract String getMonitorType();

    public abstract void onPerformanceClosed();

    public abstract void onPerformanceOpened();

    public abstract void onPerformanceProcess();

    public boolean checkTime() {
        if (this.mIntervalTimes == -1) {
            return true;
        }
        if (this.mLastMonitorTime == -1) {
            this.mLastMonitorTime = System.currentTimeMillis();
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastMonitorTime < this.mIntervalTimes) {
            return false;
        }
        this.mLastMonitorTime = currentTimeMillis;
        return true;
    }
}
