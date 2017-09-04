package com.qq.reader.module.bookstore.qnative.storage.task;

import android.content.Context;
import android.os.Message;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.net.NativeDataProtocolTask;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.storage.disk.LoadDiskPageDataTask;
import com.qq.reader.module.bookstore.qnative.storage.disk.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class LoadNativePageDataTask extends BaseNativeDataTask implements c, a {
    private static final long serialVersionUID = 1;
    protected com.qq.reader.module.bookstore.qnative.page.c mPage;
    protected Map<String, Long> mPageRequestStartTimeList = Collections.synchronizedMap(new HashMap());

    public LoadNativePageDataTask(Context context, b bVar) {
        this.mPage = (com.qq.reader.module.bookstore.qnative.page.c) bVar;
    }

    public void run() {
        super.run();
        File a = d.b().a(this.mPage.g());
        if (a != null && a.exists() && isUseCache()) {
            ReaderTask loadDiskPageDataTask = new LoadDiskPageDataTask(this.mPage, a);
            loadDiskPageDataTask.setLoadListener(this);
            g.a().a(loadDiskPageDataTask);
            return;
        }
        tryDownloadPage();
    }

    protected void tryDownloadPage() {
        ReaderTask nativeDataProtocolTask = new NativeDataProtocolTask();
        nativeDataProtocolTask.registerNetTaskListener(this);
        nativeDataProtocolTask.setUrl(this.mPage.g());
        nativeDataProtocolTask.setPriority(getPriority());
        this.mPageRequestStartTimeList.put(nativeDataProtocolTask.getUrl(), Long.valueOf(System.currentTimeMillis()));
        g.a().a(nativeDataProtocolTask);
    }

    public void onLoadSucess(Object obj) {
        synchronized (this) {
            notifyLoadPageDataSuccess(true);
        }
    }

    public void onLoadFailed(Object obj) {
        synchronized (this) {
            tryDownloadPage();
        }
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        OutputStream outputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            if (this.mPage.e() != str.hashCode()) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt("code") != 0) {
                    onError(new Exception("reader server code error ,error code : " + jSONObject.optInt("code")));
                    return;
                }
                this.mPage.b(jSONObject);
                this.mPage.c(str);
                try {
                    OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        this.mPage.serialize(byteArrayOutputStream2);
                        byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayInputStream = null;
                        outputStream = byteArrayOutputStream2;
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                    try {
                        d.b().a(this.mPage.g(), byteArrayInputStream, null);
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                        if (byteArrayOutputStream2 != null) {
                            byteArrayOutputStream2.close();
                        }
                        long currentTimeMillis = System.currentTimeMillis() - ((Long) this.mPageRequestStartTimeList.get(readerProtocolTask.getUrl())).longValue();
                        i.a("event_localstore_serverpage_load_from_net", true, System.currentTimeMillis() - readerProtocolTask.getRunTime(), 0, null, ReaderApplication.getApplicationImp().getApplicationContext());
                        notifyLoadPageDataSuccess(false);
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream = byteArrayOutputStream2;
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    byteArrayInputStream = null;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e) {
            onError(e);
        }
    }

    private void onError(Exception exception) {
        try {
            exception.printStackTrace();
            notifyLoadPageDataFailed();
        } catch (Exception e) {
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        i.a("event_localstore_serverpage_load_from_net", false, 0, 0, null, true, false, ReaderApplication.getApplicationImp().getApplicationContext());
        onError(exception);
    }

    private void notifyLoadPageDataSuccess(boolean z) {
        this.mPage.a(1002);
        if (this.mActivityHandler != null) {
            Message obtain = Message.obtain();
            if (z) {
                obtain.what = 500000;
            } else {
                obtain.what = 500001;
            }
            obtain.obj = this.mPage;
            this.mActivityHandler.sendMessage(obtain);
        }
    }

    private void notifyLoadPageDataFailed() {
        if (this.mActivityHandler != null) {
            Message obtain = Message.obtain();
            obtain.what = 500004;
            obtain.obj = this.mPage;
            this.mActivityHandler.sendMessage(obtain);
        }
    }
}
