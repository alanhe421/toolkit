package com.tencent.feedback.eup;

import com.tencent.feedback.common.e;
import java.util.Locale;

/* compiled from: RQDSRC */
public class CrashStrategyBean implements Cloneable {
    public static final String format = "[MSNum:%d ,Wifi:%d,GPRS:%d,ODay:%d,isMerged:%b,AfQ:%b,Silent:%b,mLog:%d,tag:%s,assert:%s, interval:%s, limit:%s]";
    private int a = 10;
    private int b = 10;
    private int c = 3;
    private int d = 10;
    private boolean e = true;
    private boolean f = false;
    private boolean g = true;
    private int h = 100;
    private String i = null;
    private boolean j = false;
    private String k = null;
    private int l = 5000;
    private int m = 3;
    private int n = 100;
    private boolean o = false;
    private int p = 60;
    private int q = 50;
    private boolean r = true;
    private boolean s = false;
    private boolean t = false;

    public synchronized int getMaxStoredNum() {
        return this.a;
    }

    public synchronized void setMaxStoredNum(int i) {
        if (i > 0 && i <= 20) {
            this.a = i;
        }
    }

    public synchronized int getMaxUploadNum_Wifi() {
        return this.b;
    }

    public synchronized void setMaxUploadNum_Wifi(int i) {
        if (i > 0) {
            this.b = i;
        }
    }

    public synchronized int getMaxUploadNum_GPRS() {
        return this.c;
    }

    public synchronized void setMaxUploadNum_GPRS(int i) {
        if (i > 0) {
            this.c = i;
        }
    }

    public synchronized int getRecordOverDays() {
        return this.d;
    }

    public synchronized void setRecordOverDays(int i) {
        if (i > 0) {
            this.d = i;
        }
    }

    public synchronized boolean isMerged() {
        return this.e;
    }

    public synchronized void setMerged(boolean z) {
        this.e = z;
    }

    public synchronized boolean isEnableAfterQuery() {
        return this.f;
    }

    public synchronized void setEnableAfterQuery(boolean z) {
        this.f = z;
    }

    public synchronized boolean isSilentUpload() {
        return this.g;
    }

    public synchronized void setSilentUpload(boolean z) {
        this.g = z;
    }

    public synchronized int getMaxLogRow() {
        return this.h;
    }

    public synchronized void setMaxLogRow(int i) {
        if (i > 0) {
            this.h = i;
        }
    }

    public synchronized String getOnlyLogTag() {
        return this.i;
    }

    public synchronized void setOnlyLogTag(String str) {
        this.i = str;
    }

    public synchronized boolean isStoreCrashSdcard() {
        return this.j;
    }

    public synchronized void setStoreCrashSdcard(boolean z) {
        this.j = z;
    }

    public synchronized int getCrashSdcardMaxSize() {
        return this.l;
    }

    public synchronized void setCrashSdcardMaxSize(int i) {
        if (i > 0) {
            this.l = i;
        }
    }

    public synchronized String toString() {
        String format;
        try {
            format = String.format(Locale.US, format, new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Boolean.valueOf(this.e), Boolean.valueOf(this.f), Boolean.valueOf(this.g), Integer.valueOf(this.h), this.i, Boolean.valueOf(this.o), Integer.valueOf(this.q), Integer.valueOf(this.p)});
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            format = "error";
        }
        return format;
    }

    public synchronized CrashStrategyBean clone() throws CloneNotSupportedException {
        CrashStrategyBean crashStrategyBean;
        crashStrategyBean = new CrashStrategyBean();
        crashStrategyBean.setEnableAfterQuery(this.f);
        crashStrategyBean.setMaxStoredNum(this.a);
        crashStrategyBean.setMaxUploadNum_GPRS(this.c);
        crashStrategyBean.setMaxUploadNum_Wifi(this.b);
        crashStrategyBean.setMerged(this.e);
        crashStrategyBean.setRecordOverDays(this.d);
        crashStrategyBean.setSilentUpload(this.g);
        crashStrategyBean.setMaxLogRow(this.h);
        crashStrategyBean.setOnlyLogTag(this.i);
        crashStrategyBean.setAssertEnable(this.o);
        crashStrategyBean.setAssertTaskInterval(this.p);
        crashStrategyBean.setAssertLimitCount(this.q);
        return crashStrategyBean;
    }

    public synchronized void setAssertEnable(boolean z) {
        this.o = z;
    }

    public synchronized boolean isAssertOn() {
        return this.o;
    }

    public synchronized void setAssertTaskInterval(int i) {
        if (i < 60) {
            e.a("rqdp{The interval of assert check task is smaller than the default time.} [%s s]", Integer.valueOf(i));
        }
        if (i <= 0) {
            i = 60;
        }
        this.p = i;
    }

    public synchronized int getAssertTaskInterval() {
        return this.p;
    }

    public synchronized void setAssertLimitCount(int i) {
        if (i < 50) {
            e.a("rqdp{The trigger count of the assert store is smaller than the default count.} [%s]", Integer.valueOf(i));
        }
        if (i <= 0) {
            i = 50;
        }
        this.q = i;
    }

    public synchronized int getAssertLimitCount() {
        return this.q;
    }

    public synchronized String getStoreDirectoryPath() {
        return this.k;
    }

    public synchronized void setStoreDirectoryPath(String str) {
        this.k = str;
    }

    public synchronized int getMaxStackFrame() {
        return this.m;
    }

    public synchronized void setMaxStackFrame(int i) {
        this.m = i;
    }

    public synchronized int getMaxStackLine() {
        return this.n;
    }

    public synchronized void setMaxStackLine(int i) {
        this.n = i;
    }

    public synchronized boolean isOpenANR() {
        return this.r;
    }

    public synchronized void setOpenANR(boolean z) {
        this.r = z;
    }

    public synchronized boolean isBroadcast() {
        return this.s;
    }

    public synchronized void setBroadcast(boolean z) {
        this.s = z;
    }

    public synchronized boolean isReceiveBroadcast() {
        return this.t;
    }

    public synchronized void setReceiveBroadcast(boolean z) {
        this.t = z;
    }
}
