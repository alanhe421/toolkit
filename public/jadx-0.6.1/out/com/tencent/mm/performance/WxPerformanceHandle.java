package com.tencent.mm.performance;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.monitor.f;
import com.qq.reader.view.af;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.mm.performance.loopertime.LooperMonitorController.LooperMonitorCallback;
import com.tencent.mm.performance.memoryalarm.MemoryAlarmController.MemoryAlarmCallback;
import com.tencent.mm.performance.memoryleak.MemoryLeakController;
import com.tencent.mm.performance.memoryleak.MemoryLeakController.MemoryLeakCallback;
import com.tencent.mm.performance.util.Util;
import com.tencent.mm.performance.wxperformancetool.WxPerformanceClient;
import com.tencent.upload.log.trace.TracerConfig;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import tencent.tls.platform.SigType;

public class WxPerformanceHandle {
    public static final String MESSAGE_CLASS = "class";
    public static final String MESSAGE_KEY = "key";
    public static final String MESSAGE_TAG = "tag";
    private static final String TAG = "MicroMsg.WxPerformace";
    private static WxPerformanceHandle sInstance;
    public static HashMap<String, WeakReference<Object>> sWeakObjects = new HashMap();
    private WxPerformanceClient client;

    public void startMonitorPerformance() {
        if (this.client != null) {
            this.client.startMonitorPerformance();
        }
    }

    public void stopMonitorPerformance() {
        if (this.client != null) {
            this.client.stopMointorPerforamce();
        }
    }

    public void setMonitorPuase(boolean z) {
        if (this.client != null) {
            this.client.setMonitorPuase(z);
        }
    }

    public static WxPerformanceHandle getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new WxPerformanceHandle();
            sInstance.init(context);
        }
        return sInstance;
    }

    private void init(final Context context) {
        this.client = new WxPerformanceClient();
        this.client.setMonitorLooper(true);
        this.client.registerLooperMonitorCallback(new LooperMonitorCallback() {
            public void onLooperTimeOut(Looper looper, long j, int i, boolean z, String str) {
                f.a(WxPerformanceHandle.TAG, "current thread name===" + Thread.currentThread().getName());
                f.a(WxPerformanceHandle.TAG, "looper thread name===" + looper.getThread().getName());
                f.a(WxPerformanceHandle.TAG, "looper: " + looper.hashCode() + " usetimefrombegin:" + j + " maxtime:" + i + " end: " + z + " " + str);
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "发生卡顿，请查看logcat", 0).a();
                if (!z) {
                    f.a(WxPerformanceHandle.TAG, "looper stack: " + Util.getThreadStack(looper.getThread(), true));
                }
            }
        });
        if (this.client.setMonitorActivityLifeCycle(true)) {
            this.client.registerActivityLifeCycleCallback(new ActivityLifeCycleTimeUse());
            if (b.k && this.client.setMonitorMemoryLeak(true)) {
                this.client.setTypeMointorInterval(MemoryLeakController.TYPE, TracerConfig.LOG_FLUSH_DURATION);
                this.client.registerMemoryLeakCallback(new MemoryLeakCallback() {
                    public void onObjectMustBeLeak(WeakReference<Object> weakReference, boolean z) {
                        Object obj = weakReference.get();
                        if (obj != null) {
                            f.a(WxPerformanceHandle.TAG, "memoryleak===" + obj.getClass().toString() + "   " + z);
                            if (!obj.getClass().equals(MemoryLeakActivity.class)) {
                                Intent intent = new Intent();
                                if (z) {
                                    intent.putExtra(WxPerformanceHandle.MESSAGE_TAG, "activity:");
                                } else {
                                    intent.putExtra(WxPerformanceHandle.MESSAGE_TAG, "object:");
                                }
                                String valueOf = String.valueOf(System.currentTimeMillis());
                                intent.putExtra("key", valueOf);
                                WxPerformanceHandle.sWeakObjects.put(valueOf, weakReference);
                                intent.putExtra(WxPerformanceHandle.MESSAGE_CLASS, String.format("%s", new Object[]{obj}));
                                intent.setClass(context, MemoryLeakActivity.class);
                                intent.addFlags(SigType.TLS);
                                context.startActivity(intent);
                            }
                        }
                    }
                });
            }
        }
        if (this.client.setMonitorMemoryAlarm(true, BuglyBroadcastRecevier.UPLOADLIMITED, 50, 70, 90)) {
            this.client.registerMemoryAlarmCallback(new MemoryAlarmCallback() {
                public void onMemoryDangerLow(long j, long j2, long j3) {
                    f.a(WxPerformanceHandle.TAG, "onMemoryDangerLow percentage:" + j + " max:" + j2 + " used:" + j3);
                    af.a(ReaderApplication.getApplicationImp(), (CharSequence) "内存告警：低（50%）", 0).a();
                }

                public void onMemoryDangerMiddle(long j, long j2, long j3) {
                    f.a(WxPerformanceHandle.TAG, "onMemoryDangerMiddle percentage:" + j + " max:" + j2 + " used:" + j3);
                    af.a(ReaderApplication.getApplicationImp(), (CharSequence) "内存告警：中（70%）", 0).a();
                }

                public void onMemoryDangerHigh(long j, long j2, long j3) {
                    f.a(WxPerformanceHandle.TAG, "onMemoryDangerHigh percentage:" + j + " max:" + j2 + " used:" + j3);
                    af.a(ReaderApplication.getApplicationImp(), (CharSequence) "内存告警：高（90%）", 0).a();
                }
            });
        }
        this.client.setMonitorInterval(APPluginErrorCode.ERROR_APP_TENPAY);
        startMonitorPerformance();
    }
}
