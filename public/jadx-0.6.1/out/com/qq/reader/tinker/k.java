package com.qq.reader.tinker;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.tencent.tinker.lib.tinker.TinkerApplicationHelper;

/* compiled from: TinkerReporter */
public class k implements a {
    public void a(int i) {
        c.e("Tinker.TinkerReporter", i + "");
        switch (i) {
            case 7:
            case 8:
            case 9:
                i.a("event_patch_crash", null, ReaderApplication.getApplicationImp());
                a.c(ReaderApplication.getApplicationImp(), TinkerApplicationHelper.getCurrentVersion(ReaderApplication.getInstance()));
                return;
            default:
                return;
        }
    }

    public void a(String str) {
        c.e("Tinker.TinkerReporter", str);
    }
}
