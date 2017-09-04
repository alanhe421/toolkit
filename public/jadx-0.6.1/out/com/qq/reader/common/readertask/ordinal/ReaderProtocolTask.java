package com.qq.reader.common.readertask.ordinal;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.f;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class ReaderProtocolTask extends ReaderNetTask {
    public static final String TASKNAME = "NetTask";
    public static final int[] lockObj = new int[0];
    private static final long serialVersionUID = 1;
    public String mApn;
    protected String mContentType;
    protected transient Context mContext;
    protected HashMap<String, String> mHeaders;
    private HttpResponse mHttpResponse;
    protected transient d mListener;
    protected transient a mLoginUser;
    protected String mRequest = null;
    private long mRunTime;
    private long mTid;

    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x001c in list [B:42:0x00c6]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r7 = this;
        super.run();
        r0 = r7.interuptNoConn();	 Catch:{ Exception -> 0x004e }
        if (r0 == 0) goto L_0x001d;	 Catch:{ Exception -> 0x004e }
    L_0x0009:
        r0 = r7.mContext;	 Catch:{ Exception -> 0x004e }
        r0 = com.qq.reader.common.utils.ao.d(r0);	 Catch:{ Exception -> 0x004e }
        if (r0 != 0) goto L_0x001d;	 Catch:{ Exception -> 0x004e }
    L_0x0011:
        r0 = new java.lang.Exception;	 Catch:{ Exception -> 0x004e }
        r1 = "net is unAvaiable!";	 Catch:{ Exception -> 0x004e }
        r0.<init>(r1);	 Catch:{ Exception -> 0x004e }
        r7.onError(r0);	 Catch:{ Exception -> 0x004e }
    L_0x001c:
        return;	 Catch:{ Exception -> 0x004e }
    L_0x001d:
        r0 = r7.getApn();	 Catch:{ Exception -> 0x004e }
        r7.mApn = r0;	 Catch:{ Exception -> 0x004e }
        r3 = r7.getRequestMethod();	 Catch:{ Exception -> 0x004e }
        r0 = r7.getContentType();	 Catch:{ Exception -> 0x004e }
        r7.mContentType = r0;	 Catch:{ Exception -> 0x004e }
        r0 = r7.getBasicHeader();	 Catch:{ Exception -> 0x004e }
        r7.mHeaders = r0;	 Catch:{ Exception -> 0x004e }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x004e }
        r7.mRunTime = r0;	 Catch:{ Exception -> 0x004e }
        r0 = r7.mHeaders;	 Catch:{ Exception -> 0x004e }
        r7.refreshHeader(r0);	 Catch:{ Exception -> 0x004e }
        r0 = java.lang.Thread.interrupted();	 Catch:{ Exception -> 0x004e }
        if (r0 == 0) goto L_0x0053;	 Catch:{ Exception -> 0x004e }
    L_0x0044:
        r0 = "thread interrupted";	 Catch:{ Exception -> 0x004e }
        r1 = "before request";	 Catch:{ Exception -> 0x004e }
        com.qq.reader.common.monitor.f.a(r0, r1);	 Catch:{ Exception -> 0x004e }
        goto L_0x001c;
    L_0x004e:
        r0 = move-exception;
        r7.onError(r0);
        goto L_0x001c;
    L_0x0053:
        r0 = "server";	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r1 = new java.lang.StringBuilder;	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r1.<init>();	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r2 = "url  ";	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r1 = r1.append(r2);	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r2 = r7.mUrl;	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r1 = r1.append(r2);	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r1 = r1.toString();	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        com.qq.reader.common.monitor.debug.c.d(r0, r1);	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r0 = r7.mUrl;	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r1 = r7.getRequest();	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r2 = r7.isRequestGzip();	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r4 = r7.mHeaders;	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r5 = r7.mContentType;	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r6 = r7.mContext;	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r0 = com.qq.reader.common.conn.http.b.a(r0, r1, r2, r3, r4, r5, r6);	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r7.mHttpResponse = r0;	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r0 = r7.mHttpResponse;	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r7.onFinish(r0);	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r0 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        if (r0 == 0) goto L_0x001c;	 Catch:{ Exception -> 0x004e }
    L_0x008e:
        r0 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        r0 = r0.getEntity();	 Catch:{ Exception -> 0x004e }
        r0.consumeContent();	 Catch:{ Exception -> 0x004e }
        goto L_0x001c;
    L_0x0098:
        r0 = move-exception;
        r7.onError(r0);	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r0 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        if (r0 == 0) goto L_0x001c;	 Catch:{ Exception -> 0x004e }
    L_0x00a0:
        r0 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        r0 = r0.getEntity();	 Catch:{ Exception -> 0x004e }
        r0.consumeContent();	 Catch:{ Exception -> 0x004e }
        goto L_0x001c;
    L_0x00ab:
        r0 = move-exception;
        r7.onError(r0);	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r0 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        if (r0 == 0) goto L_0x001c;	 Catch:{ Exception -> 0x004e }
    L_0x00b3:
        r0 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        r0 = r0.getEntity();	 Catch:{ Exception -> 0x004e }
        r0.consumeContent();	 Catch:{ Exception -> 0x004e }
        goto L_0x001c;
    L_0x00be:
        r0 = move-exception;
        r7.onError(r0);	 Catch:{ HttpResponseException -> 0x0098, IOException -> 0x00ab, Exception -> 0x00be, all -> 0x00d1 }
        r0 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        if (r0 == 0) goto L_0x001c;	 Catch:{ Exception -> 0x004e }
    L_0x00c6:
        r0 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        r0 = r0.getEntity();	 Catch:{ Exception -> 0x004e }
        r0.consumeContent();	 Catch:{ Exception -> 0x004e }
        goto L_0x001c;	 Catch:{ Exception -> 0x004e }
    L_0x00d1:
        r0 = move-exception;	 Catch:{ Exception -> 0x004e }
        r1 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        if (r1 == 0) goto L_0x00df;	 Catch:{ Exception -> 0x004e }
    L_0x00d6:
        r1 = r7.mHttpResponse;	 Catch:{ Exception -> 0x004e }
        r1 = r1.getEntity();	 Catch:{ Exception -> 0x004e }
        r1.consumeContent();	 Catch:{ Exception -> 0x004e }
    L_0x00df:
        throw r0;	 Catch:{ Exception -> 0x004e }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.readertask.ordinal.ReaderProtocolTask.run():void");
    }

    public ReaderProtocolTask() {
        init(null);
    }

    public ReaderProtocolTask(d dVar) {
        init(dVar);
    }

    private void init(d dVar) {
        this.mListener = dVar;
        this.mContext = ReaderApplication.getApplicationImp();
        initBasicHeader();
    }

    public Context getContext() {
        if (this.mContext == null) {
            this.mContext = ReaderApplication.getApplicationImp();
        }
        return this.mContext;
    }

    public String getTaskName() {
        return "NetTask";
    }

    public void setTid(long j) {
        this.mTid = j;
    }

    public String getApn() {
        return this.mApn;
    }

    public long getTid() {
        return this.mTid;
    }

    public long getRunTime() {
        return this.mRunTime;
    }

    public void registerNetTaskListener(d dVar) {
        this.mListener = dVar;
    }

    public d getRegisterNetTaskListener() {
        return this.mListener;
    }

    public void unregisterNetTaskListener() {
        this.mListener = null;
    }

    public HttpResponse getHttpResponse() {
        return this.mHttpResponse;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.mHttpResponse = httpResponse;
    }

    protected void refreshHeader(HashMap<String, String> hashMap) {
    }

    private void initBasicHeader() {
        Object obj = d.c() + "";
        if (obj.equals("0")) {
            obj = "";
        }
        String h = d.h(this.mContext);
        this.mHeaders = new HashMap();
        this.mLoginUser = c.c();
        if (c.b()) {
            this.mHeaders.put("loginType", String.valueOf(this.mLoginUser.d()));
            switch (this.mLoginUser.d()) {
                case 1:
                case 2:
                case 10:
                case 50:
                    if (!(this.mLoginUser instanceof com.qq.reader.common.login.b.d)) {
                        this.mHeaders.put("usid", this.mLoginUser.a(this.mContext));
                        this.mHeaders.put("uid", this.mLoginUser.c());
                        this.mHeaders.put("qqnum", this.mLoginUser.c());
                        break;
                    }
                    String a = this.mLoginUser.a(this.mContext);
                    this.mHeaders.put("skey", a);
                    this.mHeaders.put("cookie", "skey=" + a);
                    this.mHeaders.put("ckey", d.a(a));
                    this.mHeaders.put("qqnum", this.mLoginUser.c());
                    break;
            }
        }
        this.mHeaders.put("sid", obj);
        this.mHeaders.put("qimei", h);
        this.mHeaders.put("timi", d.z(this.mContext));
        this.mHeaders.put("nosid", "1");
        this.mHeaders.put("c_platform", "android");
        this.mHeaders.put("os", com.qq.reader.common.e.c.a());
        this.mHeaders.put("c_version", "qqreader_6.5.3.0888_android");
        this.mHeaders.put("mversion", f.a(this.mContext));
        this.mHeaders.put("ua", d.a());
        this.mHeaders.put("channel", ao.h(this.mContext));
        this.mHeaders.put("safekey", d.y(this.mContext));
        this.mHeaders.put("supportTS", "2");
        this.mHeaders.put("gselect", String.valueOf(d.aU(ReaderApplication.getApplicationImp())));
        this.mHeaders.put("themeid", String.valueOf(d.bS(ReaderApplication.getApplicationImp())));
        this.mHeaders.put("tinkerid", com.qq.reader.appconfig.a.a.a == null ? "" : com.qq.reader.appconfig.a.a.a);
        this.mHeaders.put("supportmh", "1");
    }

    protected void addHeader(String str, String str2) {
        this.mHeaders.put(str, str2);
    }

    public HashMap<String, String> getBasicHeader() {
        return this.mHeaders;
    }

    public String getHeaderPrintString() {
        try {
            if (this.mHeaders != null) {
                Set<String> keySet = this.mHeaders.keySet();
                StringBuffer stringBuffer = new StringBuffer();
                for (String str : keySet) {
                    stringBuffer.append(str + ":" + ((String) this.mHeaders.get(str)) + " | ");
                }
                return stringBuffer.toString();
            }
        } catch (Exception e) {
        }
        return "";
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }

    protected String getRequestContent() {
        return null;
    }

    public byte[] getRequest() {
        if (this.mRequest == null) {
            this.mRequest = getRequestContent();
        }
        if (this.mRequest != null) {
            try {
                return this.mRequest.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean isRequestGzip() {
        return false;
    }

    public boolean isResponseGzip() {
        return false;
    }

    public boolean isNeedLogin() {
        return false;
    }

    public String getContentType() {
        return null;
    }

    protected boolean interuptNoConn() {
        return false;
    }

    protected void onError(Exception exception) {
        exception.printStackTrace();
        com.qq.reader.common.monitor.debug.c.a(com.tencent.android.tpush.common.Constants.LogTag, getClass().getSimpleName() + " onError... : " + getTaskKey());
        if (Thread.interrupted()) {
            com.qq.reader.common.monitor.f.a("thread interrupted", "on error");
        } else if (!doReConnectFailedTask()) {
            if (this.mListener != null) {
                this.mListener.a(this, exception);
            }
            doConnectionErrorToRDM(this.isFailedTask, exception);
        }
    }

    protected void onFinish(HttpResponse httpResponse) throws Exception {
        com.qq.reader.common.monitor.debug.c.a(com.tencent.android.tpush.common.Constants.LogTag, getClass().getSimpleName() + " onFinish... : " + getTaskKey());
        if (Thread.interrupted()) {
            com.qq.reader.common.monitor.f.a("thread interrupted", "on finish");
            return;
        }
        InputStream inputStream = null;
        HttpEntity entity = httpResponse.getEntity();
        try {
            inputStream = entity.getContent();
            long contentLength = entity.getContentLength();
            if (this.mListener != null) {
                this.mListener.a(this, inputStream, contentLength);
            }
            onFailedTaskSuccess();
            doConnectionSuccessToRDM(this.isFailedTask);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReaderProtocolTask)) {
            return false;
        }
        ReaderProtocolTask readerProtocolTask = (ReaderProtocolTask) obj;
        if (getClass().toString().equals(readerProtocolTask.getClass().toString()) && this.mUrl.equalsIgnoreCase(readerProtocolTask.getUrl())) {
            return equalsRequestContent(this, readerProtocolTask);
        }
        return false;
    }

    public int hashCode() {
        return (getUrl() + getRequest()).hashCode();
    }

    private boolean equalsRequestContent(ReaderProtocolTask readerProtocolTask, ReaderProtocolTask readerProtocolTask2) {
        if (readerProtocolTask.getRequest() != null && readerProtocolTask2.getRequest() != null) {
            return readerProtocolTask.getRequest().equals(readerProtocolTask2.getRequest());
        }
        if (readerProtocolTask.getRequest() == null && readerProtocolTask2.getRequest() == null) {
            return true;
        }
        return false;
    }

    protected void doConnectionSuccessToRDM(boolean z) {
    }

    protected void doConnectionErrorToRDM(boolean z, Exception exception) {
    }

    public boolean isFailed() {
        return this.isFailedTask;
    }

    public boolean doReConnectFailedTask() {
        this.isFailedTask = true;
        return com.qq.reader.common.readertask.f.b().a((ReaderTask) this);
    }

    public void onFailedTaskSuccess() {
        if (this.isFailedTask) {
            com.qq.reader.common.readertask.f.b().b((ReaderTask) this);
            if (getFailedType() == 2) {
                com.qq.reader.common.readertask.f.b().a(getTaskKey());
            }
        }
    }
}
