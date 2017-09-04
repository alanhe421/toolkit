package com.tencent.mm.performance.loopertime;

import android.os.Looper;
import android.util.Printer;
import com.tencent.mm.performance.util.APerformanceController;
import com.tencent.mm.performance.wxperformancetool.WxPerformanceClient;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

public class LooperMonitorController extends APerformanceController {
    public static String TYPE = "LooperMonitorController";
    private HashSet<LooperMonitorCallback> mCallbacks;
    public WxPerformanceClient mClient;
    private HashMap<WeakReference<Looper>, LooperPrinter> mLoopers;

    public interface LooperMonitorCallback {
        void onLooperTimeOut(Looper looper, long j, int i, boolean z, String str);
    }

    public LooperMonitorController(WxPerformanceClient wxPerformanceClient) {
        this.mClient = wxPerformanceClient;
    }

    public void registerLooperMonitorCallback(LooperMonitorCallback looperMonitorCallback) {
        if (looperMonitorCallback == null) {
            throw new IllegalArgumentException("registerLooperMonitorCallback, callback is null");
        }
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(looperMonitorCallback);
        }
    }

    public void unregisterLooperMonitorCallback(LooperMonitorCallback looperMonitorCallback) {
        if (looperMonitorCallback == null) {
            throw new IllegalArgumentException("unregisterLooperMonitorCallback, callback is null");
        }
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(looperMonitorCallback);
        }
    }

    public void notifyLooperTimeout(Looper looper, long j, int i, boolean z, String str) {
        synchronized (this.mCallbacks) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((LooperMonitorCallback) it.next()).onLooperTimeOut(looper, j, i, z, str);
            }
        }
    }

    public void addMoniterLooper(Looper looper) {
        if (looper == null) {
            throw new IllegalArgumentException("addMoniterLooper, looper is null");
        }
        WeakReference weakReference = new WeakReference(looper);
        if (!this.mLoopers.containsKey(weakReference)) {
            Printer looperPrinter = new LooperPrinter(this, weakReference);
            synchronized (this) {
                this.mLoopers.put(weakReference, looperPrinter);
            }
            looper.setMessageLogging(looperPrinter);
        }
    }

    public void removeMonitorLooper(Looper looper) {
        if (looper == null) {
            throw new IllegalArgumentException("removeMonitorLooper, looper is null");
        }
        Object obj;
        Iterator it = new ArrayList(this.mLoopers.keySet()).iterator();
        while (it.hasNext()) {
            obj = (WeakReference) it.next();
            Looper looper2 = (Looper) obj.get();
            if (looper2 != null && looper2.equals(looper)) {
                break;
            }
        }
        obj = null;
        synchronized (this) {
            this.mLoopers.remove(obj);
        }
    }

    public boolean setLooperTimeoutMaxtime(int i, Looper looper) {
        if (looper == null) {
            throw new IllegalArgumentException("setLooperTimeoutMaxtime, looper is null");
        }
        Object obj;
        Iterator it = new ArrayList(this.mLoopers.keySet()).iterator();
        while (it.hasNext()) {
            obj = (WeakReference) it.next();
            Looper looper2 = (Looper) obj.get();
            if (looper2 != null && looper2.equals(looper)) {
                break;
            }
        }
        obj = null;
        if (obj == null) {
            return false;
        }
        ((LooperPrinter) this.mLoopers.get(obj)).mMaxTime = i;
        return true;
    }

    public void onPerformanceOpened() {
        this.mLoopers = new HashMap();
        this.mCallbacks = new HashSet();
    }

    public void onPerformanceClosed() {
        if (this.mLoopers != null) {
            this.mLoopers.clear();
            this.mLoopers = null;
        }
        if (this.mCallbacks != null) {
            this.mCallbacks.clear();
            this.mCallbacks = null;
        }
    }

    public String getMonitorType() {
        return TYPE;
    }

    public boolean canClosedMonitor() {
        return this.mLoopers == null || this.mLoopers.isEmpty();
    }

    public void onPerformanceProcess() {
        if (this.mLoopers != null && !this.mLoopers.isEmpty() && LooperPrinter.sHasBeginTag && LooperPrinter.sHasEndTag) {
            synchronized (this) {
                ArrayList arrayList = null;
                for (Entry entry : this.mLoopers.entrySet()) {
                    ArrayList arrayList2;
                    WeakReference weakReference = (WeakReference) entry.getKey();
                    LooperPrinter looperPrinter = (LooperPrinter) entry.getValue();
                    Looper looper = (Looper) weakReference.get();
                    if (looper != null) {
                        long currentDiffTime = looperPrinter.getCurrentDiffTime();
                        if (currentDiffTime > ((long) looperPrinter.mMaxTime)) {
                            looperPrinter.mLastTimeoutBeginTime = looperPrinter.mLastBeginTime;
                            notifyLooperTimeout(looper, currentDiffTime, looperPrinter.mMaxTime, false, looperPrinter.mBeginSting);
                        }
                        arrayList2 = arrayList;
                    } else {
                        if (arrayList == null) {
                            arrayList2 = new ArrayList();
                        } else {
                            arrayList2 = arrayList;
                        }
                        arrayList2.add(weakReference);
                    }
                    arrayList = arrayList2;
                }
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        this.mLoopers.remove((WeakReference) it.next());
                    }
                }
            }
        }
    }
}
