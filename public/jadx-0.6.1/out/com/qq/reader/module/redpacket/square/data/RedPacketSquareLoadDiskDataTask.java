package com.qq.reader.module.redpacket.square.data;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.bookstore.qnative.storage.disk.a;
import java.util.ArrayList;

public class RedPacketSquareLoadDiskDataTask extends ReaderDBTask {
    private a mLoadListener = null;
    private int mOptType;
    private long mPacketId;

    public RedPacketSquareLoadDiskDataTask(int i, long j) {
        this.mOptType = i;
        this.mPacketId = j;
    }

    public void setLoadListener(a aVar) {
        this.mLoadListener = aVar;
    }

    public void run() {
        Object obj;
        ArrayList arrayList = new ArrayList();
        try {
            switch (this.mOptType) {
                case 0:
                    arrayList = c.b().a(this.mPacketId, 20);
                    break;
                case 2:
                    arrayList = c.b().a(20);
                    break;
            }
            Object obj2 = arrayList;
            obj = 1;
        } catch (Exception e) {
            ArrayList arrayList2 = arrayList;
            obj = null;
        }
        if (this.mLoadListener == null) {
            return;
        }
        if (obj != null) {
            this.mLoadListener.onLoadSucess(obj2);
        } else {
            this.mLoadListener.onLoadFailed(obj2);
        }
    }
}
