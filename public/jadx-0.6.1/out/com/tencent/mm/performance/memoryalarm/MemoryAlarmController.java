package com.tencent.mm.performance.memoryalarm;

import com.tencent.mm.performance.util.APerformanceController;
import java.util.HashSet;
import java.util.Iterator;

public class MemoryAlarmController extends APerformanceController {
    private static final String TAG = "MicroMsg.WxPerformace";
    public static String TYPE = "MemoryAlarmController";
    private HashSet<MemoryAlarmCallback> mCallbacks;
    private boolean mCanMonitor;
    public long mHigh;
    public long mLow;
    private long mMaxMemory;
    public long mMiddle;

    public interface MemoryAlarmCallback {
        void onMemoryDangerHigh(long j, long j2, long j3);

        void onMemoryDangerLow(long j, long j2, long j3);

        void onMemoryDangerMiddle(long j, long j2, long j3);
    }

    public MemoryAlarmController() {
        this.mMaxMemory = -1;
        this.mLow = -1;
        this.mMiddle = -1;
        this.mHigh = -1;
        this.mCanMonitor = false;
        this.mMaxMemory = Runtime.getRuntime().maxMemory();
    }

    public boolean startMonitorMemoryAlarm(long j, long j2, long j3, long j4) {
        this.mIntervalTimes = j;
        this.mLow = j2;
        this.mMiddle = j3;
        this.mHigh = j4;
        this.mCanMonitor = this.mMaxMemory > 0;
        return this.mCanMonitor;
    }

    public void registerMonitorMemoryAlarmCallback(MemoryAlarmCallback memoryAlarmCallback) {
        if (memoryAlarmCallback == null) {
            throw new IllegalArgumentException("registerMonitorMemoryAlarmCallback, callback is null");
        }
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(memoryAlarmCallback);
        }
    }

    public void unregisterMonitorMemoryAlarmCallback(MemoryAlarmCallback memoryAlarmCallback) {
        if (memoryAlarmCallback == null) {
            throw new IllegalArgumentException("unregisterMonitorMemoryAlarmCallback, callback is null");
        }
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(memoryAlarmCallback);
        }
    }

    private void notifyMonitorMemoryAlarm(int i, long j, long j2, long j3) {
        synchronized (this.mCallbacks) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                MemoryAlarmCallback memoryAlarmCallback = (MemoryAlarmCallback) it.next();
                switch (i) {
                    case 0:
                        memoryAlarmCallback.onMemoryDangerLow(j, j2, j3);
                        break;
                    case 1:
                        memoryAlarmCallback.onMemoryDangerMiddle(j, j2, j3);
                        break;
                    case 2:
                        memoryAlarmCallback.onMemoryDangerHigh(j, j2, j3);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void onPerformanceOpened() {
        this.mCallbacks = new HashSet();
    }

    public void onPerformanceClosed() {
        if (this.mCallbacks != null) {
            this.mCallbacks.clear();
            this.mCallbacks = null;
        }
    }

    public String getMonitorType() {
        return TYPE;
    }

    public boolean canClosedMonitor() {
        return true;
    }

    public void onPerformanceProcess() {
        if (this.mCanMonitor) {
            long totalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long j = totalMemory / this.mMaxMemory;
            if (j >= this.mHigh) {
                notifyMonitorMemoryAlarm(2, j, this.mMaxMemory, totalMemory);
            } else if (j >= this.mMiddle) {
                notifyMonitorMemoryAlarm(1, j, this.mMaxMemory, totalMemory);
            } else if (j >= this.mLow) {
                notifyMonitorMemoryAlarm(0, j, this.mMaxMemory, totalMemory);
            }
        }
    }
}
