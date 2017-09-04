package com.qq.reader.plugin;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.HashMap;
import java.util.Map;

class SkinManager$4 extends ReaderIOTask {
    SkinManager$4() {
    }

    public void run() {
        try {
            if (!d.cd(ReaderApplication.getApplicationImp())) {
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, d.bS(ReaderApplication.getApplicationImp()));
                i.a("event_B248", hashMap, ReaderApplication.getApplicationImp());
                d.cc(ReaderApplication.getApplicationImp());
            }
        } catch (Exception e) {
            c.e("Error", e.getMessage());
        }
    }
}
