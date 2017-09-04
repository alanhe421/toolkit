package com.qq.reader.module.comic;

import android.content.Context;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qrcomic.c.e;
import java.util.Map;

/* compiled from: ComicHelper */
class a$1 implements e {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void a(String str, Map<String, String> map, Context context) {
        i.a(str, map, context);
    }

    public void a(String str, Map<String, String> map, Context context, boolean z, long j, long j2) {
        i.a(str, z, j, j2, map, context);
    }

    public void a(String str, long j) {
        StatisticsManager.a().a("readTime", Long.valueOf(j)).a("bid", str).a(102).c();
        c.d("阅读时长", "上报阅读时长啦。。。。。。 cid = " + str + " rt = " + j);
    }
}
