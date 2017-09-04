package com.tencent.mm.performance.wxperformancetool;

import android.os.Looper;
import com.tencent.mm.performance.activitylifecycle.ActivityMonitorController;
import com.tencent.mm.performance.activitylifecycle.ActivityMonitorController.ActivityLifeCycleCallback;
import com.tencent.mm.performance.loopertime.LooperMonitorController;
import com.tencent.mm.performance.loopertime.LooperMonitorController.LooperMonitorCallback;
import com.tencent.mm.performance.memoryalarm.MemoryAlarmController;
import com.tencent.mm.performance.memoryalarm.MemoryAlarmController.MemoryAlarmCallback;
import com.tencent.mm.performance.memoryleak.MemoryLeakController;
import com.tencent.mm.performance.memoryleak.MemoryLeakController.MemoryLeakCallback;
import com.tencent.mm.performance.util.APerformanceController;
import com.tencent.mm.performance.util.MonitorHandlerThread;
import java.util.HashSet;
import java.util.Iterator;

public class WxPerformanceClient {
    private HashSet<APerformanceController> mControllers = new HashSet();
    private MonitorHandlerThread mThread;

    public void startMonitorPerformance() {
        if (this.mThread == null) {
            this.mThread = new MonitorHandlerThread("MonitorHandlerThread", this);
            this.mThread.start();
            this.mThread.setHandler(this.mThread.getLooper());
        }
    }

    public void stopMointorPerforamce() {
        if (this.mThread != null) {
            this.mThread.quit();
            this.mThread = null;
        }
    }

    public void postToMoniterThread(Runnable runnable) {
        if (this.mThread != null && this.mThread.isAlive() && runnable != null) {
            this.mThread.mHandler.post(runnable);
        }
    }

    public boolean isMointerRunning() {
        return this.mThread != null && this.mThread.isAlive();
    }

    public void setMonitorInterval(int i) {
        MonitorHandlerThread.sInterval = i;
    }

    public void setTypeMointorInterval(String str, long j) {
        APerformanceController moniterByType = getMoniterByType(str);
        if (moniterByType != null) {
            moniterByType.mIntervalTimes = j;
            return;
        }
        throw new IllegalArgumentException("setTypeMointorInterval, " + str + " is not opened");
    }

    public void setMonitorPuase(boolean z) {
        if (this.mThread != null && this.mThread.isAlive()) {
            this.mThread.setPause(z);
        }
    }

    public void processMonitor() {
        Iterator it = this.mControllers.iterator();
        while (it.hasNext()) {
            APerformanceController aPerformanceController = (APerformanceController) it.next();
            if (aPerformanceController.checkTime()) {
                aPerformanceController.onPerformanceProcess();
            }
        }
    }

    private boolean checkMoniterType(String str) {
        if (this.mControllers.isEmpty()) {
            return false;
        }
        Iterator it = this.mControllers.iterator();
        while (it.hasNext()) {
            if (((APerformanceController) it.next()).getMonitorType().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private APerformanceController getMoniterByType(String str) {
        if (this.mControllers.isEmpty()) {
            return null;
        }
        Iterator it = this.mControllers.iterator();
        while (it.hasNext()) {
            APerformanceController aPerformanceController = (APerformanceController) it.next();
            if (aPerformanceController.getMonitorType().equals(str)) {
                return aPerformanceController;
            }
        }
        return null;
    }

    public void registerLooperMonitorCallback(LooperMonitorCallback looperMonitorCallback) {
        if (checkMoniterType(LooperMonitorController.TYPE)) {
            ((LooperMonitorController) getMoniterByType(LooperMonitorController.TYPE)).registerLooperMonitorCallback(looperMonitorCallback);
            return;
        }
        throw new IllegalArgumentException("registerLooperMonitorCallback, you must set a monitored looper first");
    }

    public void unregisterLooperMonitorCallback(LooperMonitorCallback looperMonitorCallback) {
        if (checkMoniterType(LooperMonitorController.TYPE)) {
            ((LooperMonitorController) getMoniterByType(LooperMonitorController.TYPE)).unregisterLooperMonitorCallback(looperMonitorCallback);
            return;
        }
        throw new IllegalArgumentException("unregisterLooperMonitorCallback, you must set a monitored looper first");
    }

    public void setMonitorLooper(boolean z, Looper looper) {
        boolean checkMoniterType = checkMoniterType(LooperMonitorController.TYPE);
        LooperMonitorController looperMonitorController;
        if (z) {
            if (checkMoniterType) {
                ((LooperMonitorController) getMoniterByType(LooperMonitorController.TYPE)).addMoniterLooper(looper);
                return;
            }
            looperMonitorController = new LooperMonitorController(this);
            looperMonitorController.onPerformanceOpened();
            looperMonitorController.addMoniterLooper(looper);
            this.mControllers.add(looperMonitorController);
        } else if (checkMoniterType) {
            looperMonitorController = (LooperMonitorController) getMoniterByType(LooperMonitorController.TYPE);
            looperMonitorController.removeMonitorLooper(looper);
            if (looperMonitorController.canClosedMonitor()) {
                looperMonitorController.onPerformanceClosed();
                this.mControllers.remove(looperMonitorController);
            }
        }
    }

    public void setMonitorLooper(boolean z) {
        setMonitorLooper(z, Looper.getMainLooper());
    }

    public boolean setLooperTimeoutMaxtime(int i, Looper looper) {
        if (checkMoniterType(LooperMonitorController.TYPE)) {
            return ((LooperMonitorController) getMoniterByType(LooperMonitorController.TYPE)).setLooperTimeoutMaxtime(i, looper);
        }
        throw new IllegalArgumentException("setLooperTimeoutMaxtime, you must set a monitored looper first");
    }

    public boolean setMonitorActivityLifeCycle(boolean z) {
        boolean checkMoniterType = checkMoniterType(ActivityMonitorController.TYPE);
        boolean startActivityLifeCycleMonitor;
        if (z) {
            if (!checkMoniterType) {
                ActivityMonitorController activityMonitorController = new ActivityMonitorController();
                startActivityLifeCycleMonitor = activityMonitorController.startActivityLifeCycleMonitor();
                if (startActivityLifeCycleMonitor) {
                    activityMonitorController.onPerformanceOpened();
                    this.mControllers.add(activityMonitorController);
                }
            }
            startActivityLifeCycleMonitor = false;
        } else {
            if (checkMoniterType) {
                if (checkMoniterType(MemoryLeakController.TYPE)) {
                    throw new IllegalArgumentException("setMonitorActivityLifeCycle, you must close monitor memoryleak first");
                }
                ActivityMonitorController activityMonitorController2 = (ActivityMonitorController) getMoniterByType(ActivityMonitorController.TYPE);
                if (activityMonitorController2.canClosedMonitor()) {
                    activityMonitorController2.onPerformanceClosed();
                    this.mControllers.remove(activityMonitorController2);
                }
            }
            startActivityLifeCycleMonitor = false;
        }
        if (checkMoniterType || r0) {
            return true;
        }
        return false;
    }

    public void registerActivityLifeCycleCallback(ActivityLifeCycleCallback activityLifeCycleCallback) {
        if (checkMoniterType(ActivityMonitorController.TYPE)) {
            ((ActivityMonitorController) getMoniterByType(ActivityMonitorController.TYPE)).registerActivityLifeCycleCallback(activityLifeCycleCallback);
            return;
        }
        throw new IllegalArgumentException("registerActivityLifeCycleCallback, you must set monitor activity lifecycle first");
    }

    public void unregisterActivityLifeCycleCallback(ActivityLifeCycleCallback activityLifeCycleCallback) {
        if (checkMoniterType(ActivityMonitorController.TYPE)) {
            ((ActivityMonitorController) getMoniterByType(ActivityMonitorController.TYPE)).unregisterActivityLifeCycleCallback(activityLifeCycleCallback);
            return;
        }
        throw new IllegalArgumentException("unregisterActivityLifeCycleCallback, you must set monitor activity lifecycle first");
    }

    public boolean setMonitorMemoryLeak(boolean z) {
        boolean checkMoniterType = checkMoniterType(MemoryLeakController.TYPE);
        MemoryLeakController memoryLeakController;
        if (z) {
            if (checkMoniterType) {
                return false;
            }
            if (checkMoniterType(ActivityMonitorController.TYPE)) {
                memoryLeakController = new MemoryLeakController();
                memoryLeakController.onPerformanceOpened();
                memoryLeakController.startMemoryLeakMonitor(this);
                this.mControllers.add(memoryLeakController);
                return true;
            }
            throw new IllegalArgumentException("setMonitorMemoryLeak, you should setMonitorActivityLifeCycle first(and return true)");
        } else if (!checkMoniterType) {
            return false;
        } else {
            memoryLeakController = (MemoryLeakController) getMoniterByType(MemoryLeakController.TYPE);
            if (memoryLeakController.canClosedMonitor()) {
                memoryLeakController.onPerformanceClosed();
                this.mControllers.remove(memoryLeakController);
            }
            return true;
        }
    }

    public void registerMemoryLeakCallback(MemoryLeakCallback memoryLeakCallback) {
        if (checkMoniterType(MemoryLeakController.TYPE)) {
            ((MemoryLeakController) getMoniterByType(MemoryLeakController.TYPE)).registerMemoryLeakCallback(memoryLeakCallback);
            return;
        }
        throw new IllegalArgumentException("registerMemoryLeakCallback, you must set monitor memoryleak first");
    }

    public void unregisterMemoryLeakCallback(MemoryLeakCallback memoryLeakCallback) {
        if (checkMoniterType(MemoryLeakController.TYPE)) {
            ((MemoryLeakController) getMoniterByType(MemoryLeakController.TYPE)).unregisterMemoryLeakCallback(memoryLeakCallback);
            return;
        }
        throw new IllegalArgumentException("unregisterMemoryLeakCallback, you must set monitor memoryleak first");
    }

    public void monitorObjectMemoryLeak(Object obj) {
        MemoryLeakController memoryLeakController = (MemoryLeakController) getMoniterByType(MemoryLeakController.TYPE);
        if (memoryLeakController != null) {
            memoryLeakController.monitorObjectRemoved(obj);
            return;
        }
        throw new IllegalArgumentException("monitorObjectMemoryLeak, you must set monitor memoryleak first");
    }

    public void setMonitorMemoryLeakCanUseGc(boolean z) {
        MemoryLeakController memoryLeakController = (MemoryLeakController) getMoniterByType(MemoryLeakController.TYPE);
        if (memoryLeakController != null) {
            memoryLeakController.mCanUseGc = z;
            return;
        }
        throw new IllegalArgumentException("setMonitorMemoryLeakCanUseGc, you must set monitor memoryleak first");
    }

    public boolean setMonitorMemoryAlarm(boolean z, long j, long j2, long j3, long j4) {
        int startMonitorMemoryAlarm;
        boolean checkMoniterType = checkMoniterType(MemoryAlarmController.TYPE);
        if (z) {
            if (!checkMoniterType) {
                MemoryAlarmController memoryAlarmController = new MemoryAlarmController();
                startMonitorMemoryAlarm = memoryAlarmController.startMonitorMemoryAlarm(j, j2, j3, j4);
                if (startMonitorMemoryAlarm != 0) {
                    memoryAlarmController.onPerformanceOpened();
                    this.mControllers.add(memoryAlarmController);
                }
            }
            startMonitorMemoryAlarm = 0;
        } else {
            if (checkMoniterType) {
                MemoryAlarmController memoryAlarmController2 = (MemoryAlarmController) getMoniterByType(MemoryAlarmController.TYPE);
                if (memoryAlarmController2.canClosedMonitor()) {
                    memoryAlarmController2.onPerformanceClosed();
                    this.mControllers.remove(memoryAlarmController2);
                }
            }
            startMonitorMemoryAlarm = 0;
        }
        return startMonitorMemoryAlarm | checkMoniterType;
    }

    public void registerMemoryAlarmCallback(MemoryAlarmCallback memoryAlarmCallback) {
        if (checkMoniterType(MemoryAlarmController.TYPE)) {
            ((MemoryAlarmController) getMoniterByType(MemoryAlarmController.TYPE)).registerMonitorMemoryAlarmCallback(memoryAlarmCallback);
            return;
        }
        throw new IllegalArgumentException("registerMemoryAlarmCallback, you must set monitor memoryleak first");
    }

    public void unregisterMemoryAlarmCallback(MemoryAlarmCallback memoryAlarmCallback) {
        if (checkMoniterType(MemoryAlarmController.TYPE)) {
            ((MemoryAlarmController) getMoniterByType(MemoryAlarmController.TYPE)).unregisterMonitorMemoryAlarmCallback(memoryAlarmCallback);
            return;
        }
        throw new IllegalArgumentException("unregisterMemoryAlarmCallback, you must set monitor memoryleak first");
    }

    public void setMonitorMemoryAlarmParams(long j, long j2, long j3, long j4) {
        MemoryAlarmController memoryAlarmController = (MemoryAlarmController) getMoniterByType(MemoryAlarmController.TYPE);
        if (memoryAlarmController != null) {
            memoryAlarmController.mIntervalTimes = j;
            memoryAlarmController.mLow = j2;
            memoryAlarmController.mMiddle = j3;
            memoryAlarmController.mHigh = j4;
            return;
        }
        throw new IllegalArgumentException("setMonitorMemoryAlarmParams, you must set monitor memoryleak first");
    }
}
