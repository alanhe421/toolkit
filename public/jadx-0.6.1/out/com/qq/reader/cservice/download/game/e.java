package com.qq.reader.cservice.download.game;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.download.task.c;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.k;
import com.qq.reader.common.download.task.state.TaskActionEnum;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: DownloadGameWorker */
public class e extends c {
    public e(k kVar, g gVar, Object obj, Context context) {
        super(kVar, gVar, obj, context);
    }

    protected void a() {
    }

    protected boolean b() {
        return true;
    }

    protected void a(f fVar) {
        if (fVar instanceof d) {
            d dVar = (d) fVar;
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, dVar.d());
            i.a("event_A259", hashMap, ReaderApplication.getApplicationImp());
        }
        File file = new File(fVar.getTempFilePath());
        File file2 = new File(fVar.getFilePath() + ShareConstants.PATCH_SUFFIX);
        if (file.renameTo(file2)) {
            a.a(this.e, file2);
        }
        this.f.a((g) fVar, TaskActionEnum.Finish);
    }
}
