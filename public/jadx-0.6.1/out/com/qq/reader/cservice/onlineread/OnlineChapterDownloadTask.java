package com.qq.reader.cservice.onlineread;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.conn.a.b;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.l;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineFile;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.r;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.s;

public class OnlineChapterDownloadTask extends ReaderNetTask {
    private static final String LOG_TAG = "OnlineChapterDownloadTask";
    private s applicationInterceptor;
    private List<Integer> downloadChapters = new ArrayList();
    private boolean hasRetryed = false;
    private boolean mBatDownload = false;
    private Context mContext;
    private c mListener = null;
    private boolean mRunInBackground = false;
    private String mUsingIp;
    private OnlineTag tag = null;
    String usid;

    private HashMap<String, String> obtainHeaders() {
        HashMap<String, String> hashMap = new HashMap();
        a c = c.c();
        this.usid = c.c().a(this.mContext);
        Object obj = d.c() + "";
        if (obj.equals("0")) {
            obj = "";
        }
        int d = c.d();
        hashMap.put("text_type", "1");
        hashMap.put("sid", obj);
        hashMap.put("qimei", d.h(this.mContext));
        hashMap.put("safekey", d.y(this.mContext));
        hashMap.put("channel", ao.h(this.mContext));
        hashMap.put("timi", d.z(this.mContext));
        hashMap.put("usid", this.usid);
        hashMap.put("nosid", "1");
        hashMap.put("uid", c.c());
        hashMap.put("c_platform", "android");
        hashMap.put("c_version", "qqreader_6.5.3.0888_android");
        hashMap.put(com.qq.reader.module.bookstore.qnative.item.s.STATPARAM_KEY, l.a(this.tag.k()));
        hashMap.put("loginType", String.valueOf(d));
        hashMap.put("ua", d.a());
        switch (d) {
            case 2:
            case 10:
            case 50:
                break;
            default:
                hashMap.put("skey", this.usid);
                hashMap.put("qqnum", c.c());
                break;
        }
        if (com.qq.reader.common.c.a.y) {
            hashMap.put("Connection", "Close");
        }
        return hashMap;
    }

    public OnlineChapterDownloadTask(OnlineTag onlineTag, c cVar) throws NullPointerException {
        if (onlineTag == null || cVar == null) {
            throw new NullPointerException("online tag & listener must not null");
        }
        this.tag = onlineTag;
        this.mListener = cVar;
        this.mContext = ReaderApplication.getApplicationImp().getApplicationContext();
    }

    public void setBackgroundRun(boolean z) {
        this.mRunInBackground = z;
    }

    public void setBatDownload(boolean z) {
        this.mBatDownload = z;
    }

    public boolean isBackgroundRun() {
        return this.mRunInBackground;
    }

    public void setRetryTag() {
        this.hasRetryed = true;
    }

    public boolean hasRetryTag() {
        return this.hasRetryed;
    }

    public void setToDownloadChapters(List<Integer> list) {
        this.downloadChapters.clear();
        this.downloadChapters.addAll(list);
    }

    public ArrayList<Integer> getDownloadChap() {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.addAll(this.downloadChapters);
        return arrayList;
    }

    private boolean isStatForegroundConn() {
        return (this.mRunInBackground || this.mBatDownload) ? false : true;
    }

    private boolean isBackgroundRetry() {
        return this.mRunInBackground && this.hasRetryed;
    }

    private String buildUrl(b bVar) {
        StringBuilder stringBuilder = new StringBuilder();
        this.mUsingIp = bVar.a();
        stringBuilder.append(this.tag.a(this.mUsingIp, this.usid));
        if (this.mBatDownload) {
            stringBuilder.append(ao.a(this.downloadChapters));
        } else {
            stringBuilder.append(this.tag.s());
            if (bVar.b()) {
                if (!this.hasRetryed) {
                    stringBuilder.append("-");
                    stringBuilder.append(this.tag.s() + 5);
                }
            } else if (this.tag.s() > 1 && !this.hasRetryed) {
                stringBuilder.append("-");
                stringBuilder.append(this.tag.s() + 3);
            }
        }
        if ((com.qq.reader.appconfig.a.c && this.tag.x()) || this.tag.E()) {
            stringBuilder.append("&autopay=1");
        }
        f.a("IP", stringBuilder.toString());
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r18 = this;
        r9 = 0;
        r2 = 0;
        r8 = 0;
        r15 = 0;
        r3 = com.qq.reader.common.conn.a.c.a();	 Catch:{ Throwable -> 0x0184, all -> 0x0181 }
        r14 = r3.c();	 Catch:{ Throwable -> 0x0184, all -> 0x0181 }
        r0 = r18;
        r2 = r0.buildUrl(r14);	 Catch:{ Throwable -> 0x0188, all -> 0x0181 }
        r3 = "OKHTTP";
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r4.<init>();	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r5 = "IpAddress = ";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r5 = r14.a();	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        com.qq.reader.common.monitor.debug.c.a(r3, r4);	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r16 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r3 = new com.qq.reader.common.conn.http.b.b;	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r4 = r14.d();	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r5 = r18.isStatForegroundConn();	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r3.<init>(r4, r5);	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r0 = r18;
        r0.applicationInterceptor = r3;	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r3 = 0;
        r4 = "GET";
        r5 = r18.obtainHeaders();	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r6 = 0;
        r0 = r18;
        r7 = r0.applicationInterceptor;	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r8 = 0;
        r3 = com.qq.reader.common.conn.http.c.a(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Throwable -> 0x018e, all -> 0x0181 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r6 = r4 - r16;
        r0 = r18;
        r4 = r0.mBatDownload;	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        if (r4 != 0) goto L_0x007d;
    L_0x0063:
        r4 = r18.isBackgroundRetry();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        if (r4 != 0) goto L_0x007d;
    L_0x0069:
        r4 = "online_conn_first";
        r5 = 1;
        r8 = 0;
        r10 = 0;
        r11 = 1;
        r12 = 0;
        r13 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r13 = r13.getApplicationContext();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        com.qq.reader.common.monitor.i.a(r4, r5, r6, r8, r10, r11, r12, r13);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
    L_0x007d:
        r4 = "";
        r4 = new java.io.File;	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r0 = r18;
        r5 = r0.tag;	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r5 = r5.c();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r4.<init>(r5);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        com.qq.reader.common.utils.ab.a(r4);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r0 = r18;
        r4 = r0.tag;	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r5 = r4.n();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r0 = r18;
        r4 = r0.tag;	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r6 = com.qq.reader.common.protocol.ReadOnline.a(r3, r4);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r0 = r18;
        r4 = r0.downloadChapters;	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r6.a(r4);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r8 = r8 - r16;
        r7 = r6.t();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r10 = r6.k();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r11 = r6.s();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r4 = 0;
        if (r11 != 0) goto L_0x013b;
    L_0x00bc:
        r11 = r6.x();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        if (r11 == 0) goto L_0x00c8;
    L_0x00c2:
        r12 = r11.size();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        if (r12 != 0) goto L_0x00ec;
    L_0x00c8:
        r0 = r18;
        r0.onError(r6);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
    L_0x00cd:
        r0 = r18;
        r0.notifySetVip(r10);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
    L_0x00d2:
        if (r4 != 0) goto L_0x00e1;
    L_0x00d4:
        r0 = r18;
        r4 = r0.tag;	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r4 = r4.n();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        if (r5 >= r4) goto L_0x00e1;
    L_0x00de:
        r18.updateChapterCount();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
    L_0x00e1:
        r0 = r18;
        r0.uploadSuccessRDM(r8);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        if (r3 == 0) goto L_0x00eb;
    L_0x00e8:
        r3.close();	 Catch:{ IOException -> 0x017c }
    L_0x00eb:
        return;
    L_0x00ec:
        r0 = r18;
        r12 = r0.tag;	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r12.b(r7);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r0 = r18;
        r7 = r0.tag;	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r6 = onNewFinish(r6, r7);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        if (r6 == 0) goto L_0x00cd;
    L_0x00fd:
        r18.notifySucces();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r0 = r18;
        r0.updateChapterFileList(r11);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        goto L_0x00cd;
    L_0x0106:
        r5 = move-exception;
        r8 = r14;
        r4 = r2;
        r10 = r3;
    L_0x010a:
        r6 = com.qq.reader.cservice.onlineread.a.a(r5);	 Catch:{ all -> 0x0179 }
        r0 = r18;
        r2 = r0.mContext;	 Catch:{ all -> 0x0179 }
        r2 = com.qq.reader.common.utils.ao.d(r2);	 Catch:{ all -> 0x0179 }
        if (r15 == 0) goto L_0x016f;
    L_0x0118:
        r7 = 1;
    L_0x0119:
        if (r2 == 0) goto L_0x0173;
    L_0x011b:
        if (r10 == 0) goto L_0x0171;
    L_0x011d:
        r9 = 1;
    L_0x011e:
        r11 = new com.qq.reader.common.utils.networkUtil.f;	 Catch:{ all -> 0x0179 }
        r12 = "www.qq.com";
        r2 = new com.qq.reader.cservice.onlineread.OnlineChapterDownloadTask$1;	 Catch:{ all -> 0x0179 }
        r3 = r18;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x0179 }
        r11.<init>(r12, r2);	 Catch:{ all -> 0x0179 }
        r11.a();	 Catch:{ all -> 0x0179 }
    L_0x0130:
        r5.printStackTrace();	 Catch:{ all -> 0x0179 }
        if (r10 == 0) goto L_0x00eb;
    L_0x0135:
        r10.close();	 Catch:{ IOException -> 0x0139 }
        goto L_0x00eb;
    L_0x0139:
        r2 = move-exception;
        goto L_0x00eb;
    L_0x013b:
        r7 = r6.f();	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        if (r7 == 0) goto L_0x014e;
    L_0x0141:
        r0 = r18;
        r0.notifyNeedVipOrPay(r6);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        goto L_0x00d2;
    L_0x0147:
        r2 = move-exception;
    L_0x0148:
        if (r3 == 0) goto L_0x014d;
    L_0x014a:
        r3.close();	 Catch:{ IOException -> 0x017f }
    L_0x014d:
        throw r2;
    L_0x014e:
        r7 = -11;
        if (r11 == r7) goto L_0x0155;
    L_0x0152:
        r7 = -3;
        if (r11 != r7) goto L_0x015c;
    L_0x0155:
        r0 = r18;
        r0.notifyIsSerialized(r6);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        goto L_0x00d2;
    L_0x015c:
        r4 = -1;
        if (r11 != r4) goto L_0x0167;
    L_0x015f:
        r0 = r18;
        r0.onError(r6);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r4 = 1;
        goto L_0x00d2;
    L_0x0167:
        r0 = r18;
        r0.onError(r6);	 Catch:{ Throwable -> 0x0106, all -> 0x0147 }
        r4 = 1;
        goto L_0x00d2;
    L_0x016f:
        r7 = 0;
        goto L_0x0119;
    L_0x0171:
        r9 = 0;
        goto L_0x011e;
    L_0x0173:
        r0 = r18;
        r0.netNotAvailableToCallBack(r6, r5);	 Catch:{ all -> 0x0179 }
        goto L_0x0130;
    L_0x0179:
        r2 = move-exception;
        r3 = r10;
        goto L_0x0148;
    L_0x017c:
        r2 = move-exception;
        goto L_0x00eb;
    L_0x017f:
        r3 = move-exception;
        goto L_0x014d;
    L_0x0181:
        r2 = move-exception;
        r3 = r9;
        goto L_0x0148;
    L_0x0184:
        r5 = move-exception;
        r4 = r2;
        r10 = r9;
        goto L_0x010a;
    L_0x0188:
        r5 = move-exception;
        r8 = r14;
        r4 = r2;
        r10 = r9;
        goto L_0x010a;
    L_0x018e:
        r5 = move-exception;
        r8 = r14;
        r4 = r2;
        r10 = r9;
        goto L_0x010a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.cservice.onlineread.OnlineChapterDownloadTask.run():void");
    }

    private void netNotAvailableToCallBack(int i, Throwable th) {
        f.d("OKHTTP", "============ERROR netNotAvailableToCallBack============");
        ReadOnlineResult readOnlineResult = new ReadOnlineResult();
        readOnlineResult.a(this.downloadChapters);
        readOnlineResult.e(a.a(this.mContext, th, i));
        readOnlineResult.m(i);
        onError(readOnlineResult);
    }

    boolean tryDelPerOnlineChapter() {
        if (this.tag != null && this.tag.s() > 1) {
            int max = Math.max(1, this.tag.s() - 2);
            long c = r.c();
            for (int max2 = Math.max(1, this.tag.s() - 8); max2 <= max; max2++) {
                ao.a(new File(v.a(this.tag.k(), max2)));
            }
            if (r.c() > c) {
                return true;
            }
        }
        return false;
    }

    public void notifySucces() {
        if (this.mListener != null) {
            this.mListener.a(this.tag, this);
        }
    }

    public void updateChapterCount() {
        try {
            if (this.mListener != null) {
                this.mListener.a(this.tag);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void updateChapterFileList(List<ReadOnlineFile> list) {
        try {
            if (this.mListener != null) {
                this.mListener.a((List) list);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void notifyNeedVipOrPay(ReadOnlineResult readOnlineResult) {
        if (this.mListener != null) {
            this.mListener.b(this.tag, readOnlineResult);
        }
    }

    public void notifyIsSerialized(ReadOnlineResult readOnlineResult) {
        if (this.mListener != null) {
            this.mListener.a(this.tag, readOnlineResult);
        }
    }

    public void notifySetVip(int i) {
        if (i == 1) {
            this.mListener.a();
        }
    }

    public void notifyError(ReadOnlineResult readOnlineResult, OnlineChapterDownloadTask onlineChapterDownloadTask) {
        if (this.mListener != null) {
            this.mListener.a(this.tag, readOnlineResult, this);
        }
    }

    private void prepareFileDir(String str) throws IOException {
        File file = new File(str);
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException("cannot create directory:" + file.getParent());
        }
    }

    private static synchronized boolean onNewFinish(ReadOnlineResult readOnlineResult, OnlineTag onlineTag) throws IOException {
        synchronized (OnlineChapterDownloadTask.class) {
            for (ReadOnlineFile readOnlineFile : readOnlineResult.x()) {
                File file = new File(onlineTag.b(readOnlineFile.getChapterId()));
                File destFile = readOnlineFile.getDestFile();
                if (destFile != null) {
                    destFile.renameTo(file);
                    b.a(onlineTag, readOnlineFile.getChapterId());
                }
            }
        }
        return true;
    }

    private void onError(ReadOnlineResult readOnlineResult) {
        notifyError(readOnlineResult, this);
    }

    public c getListener() {
        return this.mListener;
    }

    private void uploadSuccessRDM(long j) {
        try {
            if (this.mBatDownload) {
                i.a("online_batdownload", true, j, 0, null, true, false, this.mContext);
            } else {
                if (!isBackgroundRetry()) {
                    i.a("online_conn", true, j, 0, null, true, false, this.mContext);
                }
                i.a("online_conn_all", true, j, 0, null, true, false, this.mContext);
            }
            if (isStatForegroundConn()) {
                i.a("online_conn_foreground", true, j, 0, null, true, false, this.mContext);
            }
            if (isBackgroundRetry()) {
                i.a("online_conn_background_retry", true, j, 0, null, true, false, this.mContext);
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("uploadSuccessRDM", "Exception : " + e.toString());
        }
    }

    private void uploadErrorRDM(String str, Throwable th, int i, boolean z) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("Exception", th.toString() + " || " + th.getMessage());
            hashMap.put(SocialConstants.PARAM_URL, str);
            hashMap.put("conn", String.valueOf(z));
            hashMap.put("param_FailCode", "" + i);
            if (i != 1002) {
                if (this.mBatDownload) {
                    i.a("online_batdownload", false, 0, 0, hashMap, true, false, this.mContext);
                } else {
                    if (!isBackgroundRetry()) {
                        i.a("online_conn", false, 0, 0, hashMap, true, false, this.mContext);
                    }
                    if (!isBackgroundRun() || hasRetryTag()) {
                        i.a("online_conn_all", false, 0, 0, hashMap, true, false, this.mContext);
                    }
                }
                if (!(z || this.mBatDownload || isBackgroundRetry())) {
                    i.a("online_conn_first", false, 0, 0, hashMap, true, false, this.mContext);
                }
                if (isStatForegroundConn()) {
                    i.a("online_conn_foreground", false, 0, 0, hashMap, true, false, this.mContext);
                }
                if (isBackgroundRetry()) {
                    i.a("online_conn_background_retry", false, 0, 0, hashMap, true, false, this.mContext);
                }
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("uploadErrorRDM", "Exception : " + e.toString());
        }
    }
}
