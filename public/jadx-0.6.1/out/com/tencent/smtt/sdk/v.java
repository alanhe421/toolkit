package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.smtt.utils.TbsLog;

final class v extends Handler {
    v(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 100:
                if (!TbsDownloader.c(true) && message.obj != null && (message.obj instanceof QbSdk$a)) {
                    ((QbSdk$a) message.obj).a();
                    return;
                }
                return;
            case 103:
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_CONTINUEINSTALL_TBSCORE");
                z.a().a((Context) message.obj);
                return;
            default:
                return;
        }
    }
}
