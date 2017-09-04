package com.qq.reader.common.download.task;

import android.content.Context;
import com.qq.reader.cservice.download.book.c;
import com.qq.reader.cservice.download.game.e;
import com.qq.reader.plugin.audiobook.h;

/* compiled from: TaskWorkerFactory */
public final class p {
    public static o a(k kVar, g gVar, Thread thread, Context context) {
        switch (gVar.getTaskType()) {
            case 100:
                return new c(kVar, gVar, thread, context);
            case 102:
                return new h(kVar, gVar, thread, context);
            case 106:
                return new e(kVar, gVar, thread, context);
            default:
                return null;
        }
    }
}
