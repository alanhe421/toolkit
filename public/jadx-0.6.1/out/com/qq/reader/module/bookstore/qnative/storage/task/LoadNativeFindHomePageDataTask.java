package com.qq.reader.module.bookstore.qnative.storage.task;

import android.content.Context;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.bookstore.qnative.storage.disk.LoadDiskPageDataTask;
import java.io.File;

public class LoadNativeFindHomePageDataTask extends LoadNativePageDataTask {
    public LoadNativeFindHomePageDataTask(Context context, b bVar) {
        super(context, bVar);
    }

    public void run() {
        setCurrentThread(Thread.currentThread());
        File a = d.b().a(this.mPage.g());
        if (a != null && a.exists() && isUseCache()) {
            LoadDiskPageDataTask loadDiskPageDataTask = new LoadDiskPageDataTask(this.mPage, a);
            loadDiskPageDataTask.setLoadListener(this);
            loadDiskPageDataTask.run();
            return;
        }
        tryDownloadPage();
    }

    public void onLoadSucess(Object obj) {
        synchronized (LoadNativeFindHomePageDataTask.class) {
            super.onLoadSucess(obj);
            try {
                if (((af) obj).b()) {
                    tryDownloadPage();
                }
            } catch (Exception e) {
                c.e("LoadNativeFindHomePageDataTask", e.getMessage());
            }
        }
    }
}
