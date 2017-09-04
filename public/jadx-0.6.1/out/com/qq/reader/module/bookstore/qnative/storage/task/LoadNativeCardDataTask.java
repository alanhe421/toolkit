package com.qq.reader.module.bookstore.qnative.storage.task;

import android.content.Context;
import android.os.Message;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.m;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.net.NativeDataProtocolTask;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.storage.disk.LoadDiskCardDataTask;
import com.qq.reader.module.bookstore.qnative.storage.disk.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class LoadNativeCardDataTask extends BaseNativeDataTask implements c, a {
    private static final long serialVersionUID = 1;
    private Map<String, Long> mCardsRequestStartTimeList = Collections.synchronizedMap(new HashMap());
    private List<com.qq.reader.module.bookstore.qnative.card.a> mDataReadyCardList = new ArrayList();
    private List<com.qq.reader.module.bookstore.qnative.card.a> mFetchFromNetCardIdList = new ArrayList();
    private b mPage;
    private List<com.qq.reader.module.bookstore.qnative.card.a> mStartDiskLoadTasks = new ArrayList();

    public LoadNativeCardDataTask(Context context, b bVar) {
        this.mPage = bVar;
    }

    public void run() {
        super.run();
        List<com.qq.reader.module.bookstore.qnative.card.a> m = this.mPage.m();
        List<ReaderTask> arrayList = new ArrayList();
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : m) {
            if (aVar != null) {
                if (aVar.isDataReady() && isUseCache()) {
                    if (aVar.isExpired()) {
                        this.mFetchFromNetCardIdList.add(aVar);
                    }
                    this.mDataReadyCardList.add(aVar);
                } else if (!aVar.selfPrepareData()) {
                    File a = d.b().a(aVar.getUri());
                    if (a != null && a.exists() && isUseCache()) {
                        LoadDiskCardDataTask loadDiskCardDataTask = new LoadDiskCardDataTask(aVar, a);
                        loadDiskCardDataTask.setLoadListener(this);
                        this.mStartDiskLoadTasks.add(aVar);
                        arrayList.add(loadDiskCardDataTask);
                    } else {
                        this.mFetchFromNetCardIdList.add(aVar);
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            for (ReaderTask a2 : arrayList) {
                g.a().a(a2);
            }
            return;
        }
        if (this.mDataReadyCardList.size() > 0) {
            notifyLoadPageDataSuccess(true);
        }
        tryDownloadCardData(this.mFetchFromNetCardIdList);
    }

    private void tryDownloadCardData(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        if (list != null && list.size() > 0) {
            String o = this.mPage.o();
            if (o == null || o.length() == 0) {
                o = this.mPage.a((List) list);
            }
            ReaderTask nativeDataProtocolTask = new NativeDataProtocolTask();
            nativeDataProtocolTask.registerNetTaskListener(this);
            nativeDataProtocolTask.setUrl(o);
            this.mCardsRequestStartTimeList.put(nativeDataProtocolTask.getUrl(), Long.valueOf(System.currentTimeMillis()));
            g.a().a(nativeDataProtocolTask);
        }
    }

    public void onLoadSucess(Object obj) {
        try {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) obj;
            synchronized (this) {
                if (aVar.isExpired()) {
                    this.mFetchFromNetCardIdList.add(aVar);
                }
                this.mDataReadyCardList.add(aVar);
                checkIfNeedDownload(aVar);
            }
        } catch (Exception e) {
        }
    }

    public void onLoadFailed(Object obj) {
        try {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) obj;
            synchronized (this) {
                this.mFetchFromNetCardIdList.add(aVar);
                checkIfNeedDownload(aVar);
            }
        } catch (Exception e) {
        }
    }

    private void checkIfNeedDownload(com.qq.reader.module.bookstore.qnative.card.a aVar) {
        this.mStartDiskLoadTasks.remove(aVar);
        if (this.mStartDiskLoadTasks.size() == 0) {
            if (this.mDataReadyCardList.size() > 0) {
                notifyLoadPageDataSuccess(true);
                if (m.a(this.mPage)) {
                    return;
                }
            }
            tryDownloadCardData(this.mFetchFromNetCardIdList);
        }
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        OutputStream outputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        JSONObject jSONObject = new JSONObject(str);
        List<com.qq.reader.module.bookstore.qnative.card.a> m = this.mPage.m();
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.mFetchFromNetCardIdList) {
            for (com.qq.reader.module.bookstore.qnative.card.a aVar2 : m) {
                if (aVar2.equals(aVar)) {
                    aVar2.setInvalidData();
                    d.b().b(aVar.getUri());
                    break;
                }
            }
        }
        this.mPage.b(jSONObject);
        long currentTimeMillis = System.currentTimeMillis() - ((Long) this.mCardsRequestStartTimeList.get(readerProtocolTask.getUrl())).longValue();
        i.a("event_localstore_localpage_load_from_net", true, System.currentTimeMillis() - readerProtocolTask.getRunTime(), 0, null, ReaderApplication.getApplicationImp().getApplicationContext());
        notifyLoadPageDataSuccess(false);
        for (com.qq.reader.module.bookstore.qnative.card.a aVar3 : this.mFetchFromNetCardIdList) {
            if (aVar3.isNeedCacheOnDisk() && aVar3.isDataReady()) {
                try {
                    OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        aVar3.serialize(byteArrayOutputStream2);
                        byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
                        try {
                            d.b().a(aVar3.getUri(), byteArrayInputStream, null);
                            if (byteArrayInputStream != null) {
                                try {
                                    byteArrayInputStream.close();
                                } catch (Exception e) {
                                    onError(e);
                                    return;
                                }
                            }
                            if (byteArrayOutputStream2 != null) {
                                byteArrayOutputStream2.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            outputStream = byteArrayOutputStream2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        byteArrayInputStream = null;
                        outputStream = byteArrayOutputStream2;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    byteArrayInputStream = null;
                }
            }
        }
        return;
        if (byteArrayInputStream != null) {
            byteArrayInputStream.close();
        }
        if (byteArrayOutputStream != null) {
            byteArrayOutputStream.close();
        }
        throw th;
    }

    private void onError(Exception exception) {
        exception.printStackTrace();
        notifyLoadPageDataFailed();
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        long currentTimeMillis = System.currentTimeMillis() - readerProtocolTask.getRunTime();
        i.a("event_localstore_localpage_load_from_net", false, 0, 0, null, true, false, ReaderApplication.getApplicationImp().getApplicationContext());
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
        if (this.mActivityHandler != null && this.mPage.p() != 1002) {
            Message obtain = Message.obtain();
            obtain.what = 500004;
            obtain.obj = this.mPage;
            this.mActivityHandler.sendMessage(obtain);
        }
    }
}
