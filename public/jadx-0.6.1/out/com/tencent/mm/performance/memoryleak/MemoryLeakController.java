package com.tencent.mm.performance.memoryleak;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.qq.reader.common.monitor.f;
import com.tencent.mm.performance.activitylifecycle.ActivityMonitorController.ActivityLifeCycleCallback;
import com.tencent.mm.performance.util.APerformanceController;
import com.tencent.mm.performance.wxperformancetool.WxPerformanceClient;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class MemoryLeakController extends APerformanceController {
    private static final int BUFFER_TIME = 5000;
    private static final int MAIN_THREAD_GC = 1;
    private static final int MIN_DECT_TIME = 120000;
    private static final int MIN_GC_TIME = 30000;
    private static final int MUST_CONFIRM_TIMES = 3;
    private static final int MUST_CREATE_ACTIVITY_COUNT = 6;
    private static final String TAG = "MicroMsg.WxPerformace";
    public static String TYPE = "MemoryLeakController";
    private HashSet<MemoryLeakCallback> mCallbacks;
    public boolean mCanUseGc = true;
    private long mHasCreateActivityCount = 0;
    private long mLastGcTime = -1;
    private Handler mMainHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    f.d(MemoryLeakController.TAG, "gc==" + Thread.currentThread().getName());
                    System.gc();
                    MemoryLeakController.this.mLastGcTime = System.currentTimeMillis();
                    return;
                default:
                    return;
            }
        }
    };
    private HashSet<ActivityLeakInfo> mRemoveObjects;
    private HashMap<WeakReference<Object>, Integer> mTryConfirmObjects;

    public interface MemoryLeakCallback {
        void onObjectMustBeLeak(WeakReference<Object> weakReference, boolean z);
    }

    private class ActivityLeakInfo {
        public WeakReference<Object> mObject;
        public long mRemovedCount;
        public long mRemovedTime;
        public WeakReference<Object> mTestObject;

        private ActivityLeakInfo() {
            this.mRemovedCount = 0;
        }
    }

    private class MemoryActivityLifeCycleCallback extends ActivityLifeCycleCallback {
        private MemoryActivityLifeCycleCallback() {
        }

        public void onAfterActivityDestroy(Activity activity) {
            super.onAfterActivityDestroy(activity);
            MemoryLeakController.this.monitorObjectRemoved(activity);
        }

        public void onAfterActivityCreate(Activity activity, Bundle bundle) {
            super.onAfterActivityCreate(activity, bundle);
            MemoryLeakController.this.mHasCreateActivityCount = 1 + MemoryLeakController.this.mHasCreateActivityCount;
            if (MemoryLeakController.this.mHasCreateActivityCount < 0) {
                MemoryLeakController.this.mHasCreateActivityCount = 0;
            }
        }
    }

    public void startMemoryLeakMonitor(WxPerformanceClient wxPerformanceClient) {
        wxPerformanceClient.registerActivityLifeCycleCallback(new MemoryActivityLifeCycleCallback());
    }

    public void monitorObjectRemoved(Object obj) {
        WeakReference weakReference = new WeakReference(new Object());
        WeakReference weakReference2 = new WeakReference(obj);
        synchronized (this) {
            ActivityLeakInfo activityLeakInfo = new ActivityLeakInfo();
            activityLeakInfo.mObject = weakReference2;
            activityLeakInfo.mTestObject = weakReference;
            activityLeakInfo.mRemovedTime = System.currentTimeMillis();
            activityLeakInfo.mRemovedCount = this.mHasCreateActivityCount;
            this.mRemoveObjects.add(activityLeakInfo);
        }
    }

    public void registerMemoryLeakCallback(MemoryLeakCallback memoryLeakCallback) {
        if (memoryLeakCallback == null) {
            throw new IllegalArgumentException("registerMemoryLeakCallback, callback is null");
        }
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(memoryLeakCallback);
        }
    }

    public void unregisterMemoryLeakCallback(MemoryLeakCallback memoryLeakCallback) {
        if (memoryLeakCallback == null) {
            throw new IllegalArgumentException("unregisterMemoryLeakCallback, callback is null");
        }
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(memoryLeakCallback);
        }
    }

    private void notifyObjectMemoryLeak(WeakReference<Object> weakReference) {
        Object obj = weakReference.get();
        if (obj != null) {
            boolean z = obj instanceof Activity;
            synchronized (this.mCallbacks) {
                Iterator it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((MemoryLeakCallback) it.next()).onObjectMustBeLeak(weakReference, z);
                }
            }
        }
    }

    public void onPerformanceOpened() {
        this.mCallbacks = new HashSet();
        this.mRemoveObjects = new HashSet();
        this.mTryConfirmObjects = new HashMap();
    }

    public void onPerformanceClosed() {
        if (this.mCallbacks != null) {
            this.mCallbacks.clear();
            this.mCallbacks = null;
        }
        if (this.mRemoveObjects != null) {
            this.mRemoveObjects.clear();
            this.mRemoveObjects = null;
        }
        if (this.mTryConfirmObjects != null) {
            this.mTryConfirmObjects.clear();
            this.mTryConfirmObjects = null;
        }
    }

    public String getMonitorType() {
        return TYPE;
    }

    public boolean canClosedMonitor() {
        return true;
    }

    public void onPerformanceProcess() {
        if (this.mRemoveObjects != null && !this.mRemoveObjects.isEmpty()) {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this) {
                ArrayList arrayList;
                Iterator it;
                if (!this.mTryConfirmObjects.isEmpty()) {
                    arrayList = null;
                    for (WeakReference weakReference : this.mTryConfirmObjects.keySet()) {
                        if (weakReference.get() == null) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(weakReference);
                        }
                        arrayList = arrayList;
                    }
                    if (arrayList != null) {
                        it = arrayList.iterator();
                        while (it.hasNext()) {
                            this.mTryConfirmObjects.remove((WeakReference) it.next());
                        }
                    }
                }
                Iterator it2 = this.mRemoveObjects.iterator();
                ArrayList arrayList2 = null;
                Object obj = null;
                Object obj2 = null;
                while (it2.hasNext()) {
                    Object obj3;
                    ActivityLeakInfo activityLeakInfo = (ActivityLeakInfo) it2.next();
                    long j = activityLeakInfo.mRemovedCount;
                    if (activityLeakInfo.mTestObject.get() == null) {
                        if (activityLeakInfo.mObject.get() == null) {
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            arrayList2.add(activityLeakInfo);
                        } else if (this.mHasCreateActivityCount - j > 6 && currentTimeMillis - activityLeakInfo.mRemovedTime >= 120000) {
                            if (this.mTryConfirmObjects.containsKey(activityLeakInfo.mObject)) {
                                int intValue = ((Integer) this.mTryConfirmObjects.get(activityLeakInfo.mObject)).intValue();
                                if (intValue >= 3) {
                                    notifyObjectMemoryLeak(activityLeakInfo.mObject);
                                    f.d(TAG, "memoryleak activity ===" + activityLeakInfo.mObject.get());
                                    if (arrayList2 == null) {
                                        arrayList = new ArrayList();
                                    } else {
                                        arrayList = arrayList2;
                                    }
                                    arrayList.add(activityLeakInfo);
                                    obj3 = obj2;
                                } else {
                                    this.mTryConfirmObjects.put(activityLeakInfo.mObject, Integer.valueOf(intValue + 1));
                                    int i = 1;
                                    arrayList = arrayList2;
                                }
                                obj2 = obj3;
                                arrayList2 = arrayList;
                            } else if (activityLeakInfo.mRemovedTime < this.mLastGcTime - 5000) {
                                this.mTryConfirmObjects.put(activityLeakInfo.mObject, Integer.valueOf(1));
                            } else {
                                obj2 = 1;
                            }
                        }
                        obj3 = obj;
                    } else {
                        obj3 = 1;
                    }
                    obj = obj3;
                }
                if (arrayList2 != null) {
                    it = arrayList2.iterator();
                    while (it.hasNext()) {
                        this.mRemoveObjects.remove((ActivityLeakInfo) it.next());
                    }
                }
                if (this.mCanUseGc) {
                    if (obj2 != null) {
                        this.mMainHandler.sendEmptyMessage(1);
                    } else if (obj != null && currentTimeMillis - this.mLastGcTime > 30000) {
                        this.mMainHandler.sendEmptyMessage(1);
                    }
                }
            }
        }
    }
}
