package com.qq.reader.module.qmessage.data;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.bookstore.qnative.storage.disk.a;
import java.util.ArrayList;

public class MessageLoadDiskDataTask extends ReaderDBTask {
    private c mDataPackage;
    private a mLoadListener = null;

    public MessageLoadDiskDataTask(c cVar) {
        this.mDataPackage = cVar;
    }

    public void setLoadListener(a aVar) {
        this.mLoadListener = aVar;
    }

    public void run() {
        Object obj = null;
        int c = this.mDataPackage.c();
        long b = this.mDataPackage.b();
        int d = this.mDataPackage.d();
        d f = this.mDataPackage.f();
        if (f != null) {
            try {
                ArrayList arrayList = new ArrayList();
                switch (c) {
                    case 0:
                        arrayList = b.b().a(d, b, 20);
                        break;
                    case 2:
                        arrayList = b.b().a(d, 20);
                        break;
                }
                f.a(arrayList);
                obj = 1;
            } catch (Exception e) {
            }
        }
        if (this.mLoadListener == null) {
            return;
        }
        if (obj != null) {
            this.mLoadListener.onLoadSucess(this.mDataPackage);
        } else {
            this.mLoadListener.onLoadFailed(this.mDataPackage);
        }
    }
}
