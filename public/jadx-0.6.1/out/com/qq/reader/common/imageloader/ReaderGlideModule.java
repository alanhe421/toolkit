package com.qq.reader.common.imageloader;

import android.content.Context;
import com.bumptech.glide.d.a;
import com.bumptech.glide.h;
import com.bumptech.glide.load.engine.a.g;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a.a.c;
import com.tencent.av.logger.AVSDKLogger;

public class ReaderGlideModule implements a {
    public void a(Context context, h hVar) {
        int i;
        if (((double) ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().density) <= 1.5d) {
            i = 512000;
        } else {
            i = 5242880;
        }
        hVar.a(new c(ReaderApplication.getApplicationImp(), AVSDKLogger.DEFAULT_MAX_LOG_FILE_SIZE)).a(new g(i));
    }

    public void a(Context context, com.bumptech.glide.g gVar) {
    }
}
