package com.tencent.smtt.sdk;

import com.dynamicload.Lib.DLConstants;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.k.a;

final class w implements a {
    final /* synthetic */ boolean a;
    final /* synthetic */ TbsDownloadConfig b;

    w(boolean z, TbsDownloadConfig tbsDownloadConfig) {
        this.a = z;
        this.b = tbsDownloadConfig;
    }

    public void a(int i) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.sendRequest] httpResponseCode=" + i);
        if (i < 300) {
            return;
        }
        if (this.a) {
            this.b.setDownloadInterruptCode(DLConstants.LOAD_ERR_ILLEGAL_DEPENDENCY);
        } else {
            this.b.setDownloadInterruptCode(-207);
        }
    }
}
